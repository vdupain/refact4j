package org.refact4j.cmd;

public interface Invoker<P, R> {
	void invoke();

	void setParameter(P parameter);

	void setCommand(Command<P, R> command);
	
	R getResult();
}
