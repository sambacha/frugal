/**
 * Autogenerated by Frugal Compiler (1.12.1)
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *
 * @generated
 */

package foo;

import com.workiva.frugal.middleware.InvocationHandler;
import com.workiva.frugal.middleware.ServiceMiddleware;
import com.workiva.frugal.protocol.*;
import com.workiva.frugal.provider.FScopeProvider;
import com.workiva.frugal.transport.FScopeTransport;
import com.workiva.frugal.transport.FSubscription;
import org.apache.thrift.TException;
import org.apache.thrift.TApplicationException;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.protocol.*;

import javax.annotation.Generated;
import java.util.logging.Logger;




/**
 * And this is a scope docstring.
 */
@Generated(value = "Autogenerated by Frugal Compiler (1.12.1)", date = "2015-11-24")
public class FooPublisher {

	private static final String DELIMITER = ".";

	private final InternalFooPublisher target;
	private final InternalFooPublisher proxy;

	public FooPublisher(FScopeProvider provider, ServiceMiddleware... middleware) {
		target = new InternalFooPublisher(provider);
		proxy = (InternalFooPublisher) InvocationHandler.composeMiddlewareClass(target, InternalFooPublisher.class, middleware);
	}

	public void open() throws TException {
		target.open();
	}

	public void close() throws TException {
		target.close();
	}

	/**
	 * This is an operation docstring.
	 */
	public void publishFoo(FContext ctx, String baz, Thing req) throws TException {
		proxy.publishFoo(ctx, baz, req);
	}

	public void publishBar(FContext ctx, String baz, Stuff req) throws TException {
		proxy.publishBar(ctx, baz, req);
	}

	protected static class InternalFooPublisher {

		private FScopeProvider provider;
		private FScopeTransport transport;
		private FProtocol protocol;

		protected InternalFooPublisher() {
		}

		public InternalFooPublisher(FScopeProvider provider) {
			this.provider = provider;
		}

		public void open() throws TException {
			FScopeProvider.Client client = provider.build();
			transport = client.getTransport();
			protocol = client.getProtocol();
			transport.open();
		}

		public void close() throws TException {
			transport.close();
		}

		/**
		 * This is an operation docstring.
		 */
		public void publishFoo(FContext ctx, String baz, Thing req) throws TException {
			String op = "Foo";
			String prefix = String.format("foo.bar.%s.qux.", baz);
			String topic = String.format("%sFoo%s%s", prefix, DELIMITER, op);
			transport.lockTopic(topic);
			try {
				protocol.writeRequestHeader(ctx);
				protocol.writeMessageBegin(new TMessage(op, TMessageType.CALL, 0));
				req.write(protocol);
				protocol.writeMessageEnd();
				transport.flush();
			} finally {
				transport.unlockTopic();
			}
		}


		public void publishBar(FContext ctx, String baz, Stuff req) throws TException {
			String op = "Bar";
			String prefix = String.format("foo.bar.%s.qux.", baz);
			String topic = String.format("%sFoo%s%s", prefix, DELIMITER, op);
			transport.lockTopic(topic);
			try {
				protocol.writeRequestHeader(ctx);
				protocol.writeMessageBegin(new TMessage(op, TMessageType.CALL, 0));
				req.write(protocol);
				protocol.writeMessageEnd();
				transport.flush();
			} finally {
				transport.unlockTopic();
			}
		}
	}
}
