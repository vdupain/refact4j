package org.refact4j.visitor;

/**
 * Visitor is an interface for implementing Visitor Design Pattern.
 */
@FunctionalInterface
public interface Visitor {

    void visit(Visitable visitable);
}
