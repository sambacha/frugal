/**
 * Autogenerated by Frugal Compiler (2.26.0)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */
package v1.music;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exceptions are converted to the native format for each compiled
 * language.
 */
@Generated(value = "Autogenerated by Frugal Compiler (2.26.0)")
public class PurchasingError extends TException implements org.apache.thrift.TBase<PurchasingError, PurchasingError._Fields>, java.io.Serializable, Cloneable, Comparable<PurchasingError> {
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PurchasingError");

	private static final org.apache.thrift.protocol.TField MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("message", org.apache.thrift.protocol.TType.STRING, (short)1);
	private static final org.apache.thrift.protocol.TField ERROR_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("error_code", org.apache.thrift.protocol.TType.I16, (short)2);

	private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
	static {
		schemes.put(StandardScheme.class, new PurchasingErrorStandardSchemeFactory());
		schemes.put(TupleScheme.class, new PurchasingErrorTupleSchemeFactory());
	}

	public String message;
	public short error_code;
	/** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum {
		MESSAGE((short)1, "message"),
		ERROR_CODE((short)2, "error_code")
		;

		private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

		static {
			for (_Fields field : EnumSet.allOf(_Fields.class)) {
				byName.put(field.getFieldName(), field);
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, or null if its not found.
		 */
		public static _Fields findByThriftId(int fieldId) {
			switch(fieldId) {
				case 1: // MESSAGE
					return MESSAGE;
				case 2: // ERROR_CODE
					return ERROR_CODE;
				default:
					return null;
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, throwing an exception
		 * if it is not found.
		 */
		public static _Fields findByThriftIdOrThrow(int fieldId) {
			_Fields fields = findByThriftId(fieldId);
			if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
			return fields;
		}

		/**
		 * Find the _Fields constant that matches name, or null if its not found.
		 */
		public static _Fields findByName(String name) {
			return byName.get(name);
		}

		private final short _thriftId;
		private final String _fieldName;

		_Fields(short thriftId, String fieldName) {
			_thriftId = thriftId;
			_fieldName = fieldName;
		}

		public short getThriftFieldId() {
			return _thriftId;
		}

		public String getFieldName() {
			return _fieldName;
		}
	}

	// isset id assignments
	private static final int __ERROR_CODE_ISSET_ID = 0;
	private byte __isset_bitfield = 0;
	public PurchasingError() {
	}

	public PurchasingError(
		String message,
		short error_code) {
		this();
		this.message = message;
		this.error_code = error_code;
		setError_codeIsSet(true);
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public PurchasingError(PurchasingError other) {
		__isset_bitfield = other.__isset_bitfield;
		if (other.isSetMessage()) {
			this.message = other.message;
		}
		this.error_code = other.error_code;
	}

	public PurchasingError deepCopy() {
		return new PurchasingError(this);
	}

	@Override
	public void clear() {
		this.message = null;

		setError_codeIsSet(false);
		this.error_code = (short)0;

	}

	public String getMessage() {
		return this.message;
	}

	public PurchasingError setMessage(String message) {
		this.message = message;
		return this;
	}

	public void unsetMessage() {
		this.message = null;
	}

	/** Returns true if field message is set (has been assigned a value) and false otherwise */
	public boolean isSetMessage() {
		return this.message != null;
	}

	public void setMessageIsSet(boolean value) {
		if (!value) {
			this.message = null;
		}
	}

	public short getError_code() {
		return this.error_code;
	}

	public PurchasingError setError_code(short error_code) {
		this.error_code = error_code;
		setError_codeIsSet(true);
		return this;
	}

	public void unsetError_code() {
		__isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ERROR_CODE_ISSET_ID);
	}

	/** Returns true if field error_code is set (has been assigned a value) and false otherwise */
	public boolean isSetError_code() {
		return EncodingUtils.testBit(__isset_bitfield, __ERROR_CODE_ISSET_ID);
	}

	public void setError_codeIsSet(boolean value) {
		__isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ERROR_CODE_ISSET_ID, value);
	}

	public void setFieldValue(_Fields field, Object value) {
		switch (field) {
		case MESSAGE:
			if (value == null) {
				unsetMessage();
			} else {
				setMessage((String)value);
			}
			break;

		case ERROR_CODE:
			if (value == null) {
				unsetError_code();
			} else {
				setError_code((Short)value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field) {
		switch (field) {
		case MESSAGE:
			return getMessage();

		case ERROR_CODE:
			return getError_code();

		}
		throw new IllegalStateException();
	}

	/** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
	public boolean isSet(_Fields field) {
		if (field == null) {
			throw new IllegalArgumentException();
		}

		switch (field) {
		case MESSAGE:
			return isSetMessage();
		case ERROR_CODE:
			return isSetError_code();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that instanceof PurchasingError)
			return this.equals((PurchasingError)that);
		return false;
	}

	public boolean equals(PurchasingError that) {
		if (that == null)
			return false;

		boolean this_present_message = true && this.isSetMessage();
		boolean that_present_message = true && that.isSetMessage();
		if (this_present_message || that_present_message) {
			if (!(this_present_message && that_present_message))
				return false;
			if (!this.message.equals(that.message))
				return false;
		}

		boolean this_present_error_code = true;
		boolean that_present_error_code = true;
		if (this_present_error_code || that_present_error_code) {
			if (!(this_present_error_code && that_present_error_code))
				return false;
			if (this.error_code != that.error_code)
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		List<Object> list = new ArrayList<Object>();

		boolean present_message = true && (isSetMessage());
		list.add(present_message);
		if (present_message)
			list.add(message);

		boolean present_error_code = true;
		list.add(present_error_code);
		if (present_error_code)
			list.add(error_code);

		return list.hashCode();
	}

	@Override
	public int compareTo(PurchasingError other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;

		lastComparison = Boolean.valueOf(isSetMessage()).compareTo(other.isSetMessage());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetMessage()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.message, other.message);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetError_code()).compareTo(other.isSetError_code());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetError_code()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.error_code, other.error_code);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		return 0;
	}

	public _Fields fieldForId(int fieldId) {
		return _Fields.findByThriftId(fieldId);
	}

	public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
		schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
	}

	public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
		schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("PurchasingError(");
		boolean first = true;

		sb.append("message:");
		if (this.message == null) {
			sb.append("null");
		} else {
			sb.append(this.message);
		}
		first = false;
		if (!first) sb.append(", ");
		sb.append("error_code:");
		sb.append(this.error_code);
		first = false;
		sb.append(")");
		return sb.toString();
	}

	public void validate() throws org.apache.thrift.TException {
		// check for required fields
		// check for sub-struct validity
	}

	private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
		try {
			write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
		try {
			// it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
			__isset_bitfield = 0;
			read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private static class PurchasingErrorStandardSchemeFactory implements SchemeFactory {
		public PurchasingErrorStandardScheme getScheme() {
			return new PurchasingErrorStandardScheme();
		}
	}

	private static class PurchasingErrorStandardScheme extends StandardScheme<PurchasingError> {

		public void read(org.apache.thrift.protocol.TProtocol iprot, PurchasingError struct) throws org.apache.thrift.TException {
			org.apache.thrift.protocol.TField schemeField;
			iprot.readStructBegin();
			while (true) {
				schemeField = iprot.readFieldBegin();
				if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
					break;
				}
				switch (schemeField.id) {
					case 1: // MESSAGE
						if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
							struct.message = iprot.readString();
							struct.setMessageIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					case 2: // ERROR_CODE
						if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
							struct.error_code = iprot.readI16();
							struct.setError_codeIsSet(true);
						} else {
							org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
						}
						break;
					default:
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
				}
				iprot.readFieldEnd();
			}
			iprot.readStructEnd();

			// check for required fields of primitive type, which can't be checked in the validate method
			struct.validate();
		}

		public void write(org.apache.thrift.protocol.TProtocol oprot, PurchasingError struct) throws org.apache.thrift.TException {
			struct.validate();

			oprot.writeStructBegin(STRUCT_DESC);
			if (struct.message != null) {
				oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
				String elem26 = struct.message;
				oprot.writeString(elem26);
				oprot.writeFieldEnd();
			}
			oprot.writeFieldBegin(ERROR_CODE_FIELD_DESC);
			short elem27 = struct.error_code;
			oprot.writeI16(elem27);
			oprot.writeFieldEnd();
			oprot.writeFieldStop();
			oprot.writeStructEnd();
		}

	}

	private static class PurchasingErrorTupleSchemeFactory implements SchemeFactory {
		public PurchasingErrorTupleScheme getScheme() {
			return new PurchasingErrorTupleScheme();
		}
	}

	private static class PurchasingErrorTupleScheme extends TupleScheme<PurchasingError> {

		@Override
		public void write(org.apache.thrift.protocol.TProtocol prot, PurchasingError struct) throws org.apache.thrift.TException {
			TTupleProtocol oprot = (TTupleProtocol) prot;
			BitSet optionals = new BitSet();
			if (struct.isSetMessage()) {
				optionals.set(0);
			}
			if (struct.isSetError_code()) {
				optionals.set(1);
			}
			oprot.writeBitSet(optionals, 2);
			if (struct.isSetMessage()) {
				String elem28 = struct.message;
				oprot.writeString(elem28);
			}
			if (struct.isSetError_code()) {
				short elem29 = struct.error_code;
				oprot.writeI16(elem29);
			}
		}

		@Override
		public void read(org.apache.thrift.protocol.TProtocol prot, PurchasingError struct) throws org.apache.thrift.TException {
			TTupleProtocol iprot = (TTupleProtocol) prot;
			BitSet incoming = iprot.readBitSet(2);
			if (incoming.get(0)) {
				struct.message = iprot.readString();
				struct.setMessageIsSet(true);
			}
			if (incoming.get(1)) {
				struct.error_code = iprot.readI16();
				struct.setError_codeIsSet(true);
			}
		}

	}

}
