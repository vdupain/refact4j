package org.refact4j.cmd;

import java.lang.reflect.Proxy;

import org.junit.Assert;
import org.junit.Test;
import org.refact4j.cmd.impl.CommandImpl;
import org.refact4j.cmd.impl.InvokerImpl;
import org.refact4j.evt.EventLogger;
import org.refact4j.evt.EventLoggerInvocationHandler;

public class CommandTest {
	EventLogger eventLogger = new EventLogger();

	@Test
	public void testCommand() {
		MockResult expected = new MockResult();

		Invoker<MockParameter, MockResult> invoker = (Invoker<MockParameter, MockResult>) Proxy.newProxyInstance(
				Invoker.class.getClassLoader(), new Class[] { Invoker.class }, new EventLoggerInvocationHandler(
						new MockInvoker(), eventLogger));

		Command<MockParameter, MockResult> command = (Command<MockParameter, MockResult>) Proxy.newProxyInstance(
				Command.class.getClassLoader(), new Class[] { Command.class }, new EventLoggerInvocationHandler(
						new MockCommand(), eventLogger));

		Receiver<MockParameter, MockResult> receiver = (Receiver<MockParameter, MockResult>) Proxy.newProxyInstance(
				Receiver.class.getClassLoader(), new Class[] { Receiver.class }, new EventLoggerInvocationHandler(
						new MockReceiver(expected), eventLogger));

		// Invoker<MockParameter, MockResult> invoker = new MockInvoker();
		// Command<MockParameter, MockResult> command = new MockCommand();
		// Receiver<MockParameter, MockResult> receiver = new
		// MockReceiver(expected);

		invoker.setCommand(command);
		command.setReceiver(receiver);

		invoker.setParameter(new MockParameter());
		invoker.invoke();
		MockResult actual = invoker.getResult();

		Assert.assertEquals(expected, actual);
		// eventLogger.assertEquals("</>");
	}

	class MockParameter {

	}

	class MockResult {

	}

	class MockReceiver implements Receiver<MockParameter, MockResult> {
		public MockResult result;

		public MockReceiver(MockResult result) {
			this.result = result;
		}

		public MockResult getResult() {
			return this.result;
		}

		public void setParameter(MockParameter parameter) {
		}

		public void action() {
		}

	}

	class MockCommand extends CommandImpl<MockParameter, MockResult> {

	}

	class MockInvoker extends InvokerImpl<MockParameter, MockResult> {
	}
}
