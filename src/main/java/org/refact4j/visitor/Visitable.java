package org.refact4j.visitor;

/**
 * Visitable is an interface for implementing Visitor Design Pattern.
 */
public interface Visitable {

    void accept(Visitor visitor);

}
