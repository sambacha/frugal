package golang

import (
	"fmt"
	"os"
	"os/exec"

	"github.com/Workiva/frugal/compiler/generator"
	"github.com/Workiva/frugal/compiler/globals"
	"github.com/Workiva/frugal/compiler/parser"
)

const (
	suffix           = "go"
	defaultOutputDir = "gen-go"
)

type Generator struct {
	*generator.BaseGenerator
}

func NewGenerator() generator.OOGenerator {
	return &Generator{&generator.BaseGenerator{}}
}

func (g *Generator) DefaultOutputDir() string {
	return defaultOutputDir
}

func (g *Generator) CheckCompile(path string) error {
	if out, err := exec.Command("go", "build", path).CombinedOutput(); err != nil {
		fmt.Println(string(out))
		return err
	}
	return nil
}

func (g *Generator) GenerateFile(name, outputDir string, namespaces []*parser.Namespace) (*os.File, error) {
	return g.CreateFile(name, outputDir, suffix, namespaces)
}

func (g *Generator) GenerateDocStringComment(file *os.File) error {
	comment := fmt.Sprintf(
		"// Autogenerated by Frugal Compiler (%s)\n"+
			"// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING",
		generator.Version)

	_, err := file.WriteString(comment)
	return err
}

func (g *Generator) GeneratePackage(file *os.File, name, outputDir string) error {
	_, err := file.WriteString(fmt.Sprintf("package %s", name))
	return err
}

func (g *Generator) GenerateImports(file *os.File) error {
	imports := "import (\n"
	imports += "\t\"fmt\"\n"
	imports += "\t\"log\"\n\n"
	imports += "\t\"git.apache.org/thrift.git/lib/go/thrift\"\n"
	imports += "\t\"github.com/Workiva/frugal/lib/go\"\n"
	imports += ")"
	_, err := file.WriteString(imports)
	return err
}

func (g *Generator) GenerateConstants(file *os.File, name string) error {
	constants := fmt.Sprintf("const delimiter = \"%s\"", globals.TopicDelimiter)
	_, err := file.WriteString(constants)
	return err
}

func (g *Generator) GeneratePublishers(file *os.File, namespaces []*parser.Namespace) error {
	publishers := ""
	for _, namespace := range namespaces {
		publishers = generatePublisher(publishers, namespace)
	}
	_, err := file.WriteString(publishers)
	return err
}

func generatePublisher(publishers string, namespace *parser.Namespace) string {
	publishers += fmt.Sprintf("type %sPublisher struct {\n", namespace.Name)
	publishers += "\tTransport frugal.Transport\n"
	publishers += "\tProtocol  thrift.TProtocol\n"
	publishers += "\tSeqId     int32\n"
	publishers += "}\n\n"

	publishers += fmt.Sprintf("func New%sPublisher(p *frugal.Provider) *%sPublisher {\n", namespace.Name, namespace.Name)
	publishers += "\ttransport, protocol := p.New()\n"
	publishers += fmt.Sprintf("\treturn &%sPublisher{\n", namespace.Name)
	publishers += "\t\tTransport: transport,\n"
	publishers += "\t\tProtocol:  protocol,\n"
	publishers += "\t\tSeqId:     0,\n"
	publishers += "\t}\n"
	publishers += "}\n\n"

	args := ""
	if len(namespace.Prefix.Variables) > 0 {
		for _, variable := range namespace.Prefix.Variables {
			args += ", " + variable
		}
		args += " string"
	}

	prefix := ""
	for _, op := range namespace.Operations {
		publishers += prefix
		prefix = "\n\n"
		publishers += fmt.Sprintf("func (l *%sPublisher) Publish%s(req *%s%s) error {\n",
			namespace.Name, op.Name, op.Param, args)
		publishers += fmt.Sprintf("\top := \"%s\"\n", op.Name)
		publishers += fmt.Sprintf("\tprefix := %s\n", generatePrefixStringTemplate(namespace))
		publishers += "\ttopic := fmt.Sprintf(\"%s" + namespace.Name + "%s%s\", prefix, delimiter, op)\n"
		publishers += "\tl.Transport.PreparePublish(topic)\n"
		publishers += "\toprot := l.Protocol\n"
		publishers += "\tl.SeqId++\n"
		publishers += "\tif err := oprot.WriteMessageBegin(op, thrift.CALL, l.SeqId); err != nil {\n"
		publishers += "\t\treturn err\n"
		publishers += "\t}\n"
		publishers += "\tif err := req.Write(oprot); err != nil {\n"
		publishers += "\t\treturn err\n"
		publishers += "\t}\n"
		publishers += "\tif err := oprot.WriteMessageEnd(); err != nil {\n"
		publishers += "\t\treturn err\n"
		publishers += "\t}\n"
		publishers += "\treturn oprot.Flush()\n"
		publishers += "}"
	}

	return publishers
}

func generatePrefixStringTemplate(namespace *parser.Namespace) string {
	if len(namespace.Prefix.Variables) == 0 {
		return `""`
	}
	template := "fmt.Sprintf(\""
	template += namespace.Prefix.Template()
	template += globals.TopicDelimiter + "\", "
	prefix := ""
	for _, variable := range namespace.Prefix.Variables {
		template += prefix + variable
		prefix = ", "
	}
	template += ")"
	return template
}

func (g *Generator) GenerateSubscribers(file *os.File, namespaces []*parser.Namespace) error {
	subscribers := ""
	for _, namespace := range namespaces {
		subscribers = generateSubscriber(subscribers, namespace)
	}
	_, err := file.WriteString(subscribers)
	return err
}

func generateSubscriber(subscribers string, namespace *parser.Namespace) string {
	subscribers += fmt.Sprintf("type %sSubscriber struct {\n", namespace.Name)
	subscribers += "\tProvider *frugal.Provider\n"
	subscribers += "}\n\n"

	subscribers += fmt.Sprintf("func New%sSubscriber(p *frugal.Provider) *%sSubscriber {\n", namespace.Name, namespace.Name)
	subscribers += fmt.Sprintf("\treturn &%sSubscriber{Provider: p}\n", namespace.Name)
	subscribers += "}\n\n"

	args := ""
	prefix := ""
	if len(namespace.Prefix.Variables) > 0 {
		for _, variable := range namespace.Prefix.Variables {
			args += prefix + variable
			prefix = ", "
		}
		args += " string, "
	}

	prefix = ""
	for _, op := range namespace.Operations {
		subscribers += prefix
		prefix = "\n\n"
		subscribers += fmt.Sprintf("func (l *%sSubscriber) Subscribe%s(%shandler func(*%s)) (*frugal.Subscription, error) {\n",
			namespace.Name, op.Name, args, op.Param)
		subscribers += fmt.Sprintf("\top := \"%s\"\n", op.Name)
		subscribers += fmt.Sprintf("\tprefix := %s\n", generatePrefixStringTemplate(namespace))
		subscribers += "\ttopic := fmt.Sprintf(\"%s" + namespace.Name + "%s%s\", prefix, delimiter, op)\n"
		subscribers += "\ttransport, protocol := l.Provider.New()\n"
		subscribers += "\tif err := transport.Subscribe(topic); err != nil {\n"
		subscribers += "\t\treturn nil, err\n"
		subscribers += "\t}\n\n"
		subscribers += "\tgo func() {\n"
		subscribers += "\t\tfor {\n"
		subscribers += fmt.Sprintf("\t\t\treceived, err := l.recv%s(op, protocol)\n", op.Name)
		subscribers += "\t\t\tif err != nil {\n"
		subscribers += "\t\t\t\tif e, ok := err.(thrift.TTransportException); ok && e.TypeId() == thrift.END_OF_FILE {\n"
		subscribers += "\t\t\t\t\tbreak\n"
		subscribers += "\t\t\t\t}\n"
		subscribers += "\t\t\t\tlog.Println(\"frugal: error receiving:\", err)\n"
		subscribers += "\t\t\t\tcontinue\n"
		subscribers += "\t\t\t}\n"
		subscribers += "\t\t\thandler(received)\n"
		subscribers += "\t\t}\n"
		subscribers += "\t}()\n\n"
		subscribers += "\treturn frugal.NewSubscription(topic, transport), nil\n"
		subscribers += "}\n\n"

		subscribers += fmt.Sprintf("func (l *%sSubscriber) recv%s(op string, iprot thrift.TProtocol) (*%s, error) {\n",
			namespace.Name, op.Name, op.Param)
		subscribers += "\tname, _, _, err := iprot.ReadMessageBegin()\n"
		subscribers += "\tif err != nil {\n"
		subscribers += "\t\treturn nil, err\n"
		subscribers += "\t}\n"
		subscribers += "\tif name != op {\n"
		subscribers += "\t\tiprot.Skip(thrift.STRUCT)\n"
		subscribers += "\t\tiprot.ReadMessageEnd()\n"
		subscribers += "\t\tx9 := thrift.NewTApplicationException(thrift.UNKNOWN_METHOD, \"Unknown function \"+name)\n"
		subscribers += "\t\treturn nil, x9\n"
		subscribers += "\t}\n"
		subscribers += fmt.Sprintf("\treq := &%s{}\n", op.Param)
		subscribers += "\tif err := req.Read(iprot); err != nil {\n"
		subscribers += "\t\treturn nil, err\n"
		subscribers += "\t}\n\n"
		subscribers += "\tiprot.ReadMessageEnd()\n"
		subscribers += "\treturn req, nil\n"
		subscribers += "}"
	}
	subscribers += "\n"

	return subscribers
}
