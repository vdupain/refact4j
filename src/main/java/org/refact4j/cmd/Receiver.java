package org.refact4j.cmd;

public interface Receiver<P, R> {

	R getResult();

	void setParameter(P parameter);

	void action();
}
