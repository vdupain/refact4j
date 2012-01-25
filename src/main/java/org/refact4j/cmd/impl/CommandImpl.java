package org.refact4j.cmd.impl;

import org.refact4j.cmd.Command;
import org.refact4j.cmd.Receiver;

public class CommandImpl<P, R> implements Command<P, R> {

	private P parameter;
	private Receiver<P, R> receiver;

	public R getResult() {
		return this.receiver.getResult();
	}

	public void setParameter(P parameter) {
		this.parameter = parameter;
	}

	public void setReceiver(Receiver<P, R> receiver) {
		this.receiver = receiver;
	}

	public void execute() {
		this.receiver.setParameter(parameter);
		this.receiver.action();
	}

}
