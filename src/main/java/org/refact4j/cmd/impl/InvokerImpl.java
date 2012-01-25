package org.refact4j.cmd.impl;

import org.refact4j.cmd.Command;
import org.refact4j.cmd.Invoker;

public class InvokerImpl<P, R> implements Invoker<P, R> {

	private Command<P, R> command;
	private P parameter;

	public void invoke() {
		this.command.setParameter(parameter);
		this.command.execute();
	}

	public void setCommand(Command<P, R> command) {
		this.command = command;
	}

	public void setParameter(P parameter) {
		this.parameter = parameter;
	}

	public R getResult() {
		return this.command.getResult();
	}

}
