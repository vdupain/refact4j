package org.refact4j.functor.commons;

import org.refact4j.functor.AbstractBinaryPredicate;
import org.refact4j.visitor.Visitor;

public class InstanceOf extends AbstractBinaryPredicate<Object, Class<?>> {

	@Override
	protected Boolean evaluate(Object firstArg, Class<?> secondArg) {
		return firstArg != null && firstArg.getClass().isAssignableFrom(secondArg);
	}

	public void accept(Visitor visitor) {
		if (visitor instanceof InstanceOfVisitor) {
			((InstanceOfVisitor) visitor).visitInstanceOf(this);
		}
	}

	public interface InstanceOfVisitor extends Visitor {
		void visitInstanceOf(InstanceOf instanceOf);
	}

}
