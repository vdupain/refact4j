package org.refact4j.visitor;

/**
 * PrePostVisitor is an interface for implementing Visitor Design Pattern with
 * Pre and Post visit methods.
 */
public interface PrePostVisitor extends Visitor {

    void preVisit(Visitable visitable);

    void postVisit(Visitable visitable);
}
