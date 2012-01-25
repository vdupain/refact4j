package org.refact4j.cmd;

public interface Command<P, R> {
	R getResult();

	void setParameter(P parameter);

	void setReceiver(Receiver<P, R> receiver);

	void execute();
}
