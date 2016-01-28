package org.refact4j.visitor;

import java.util.function.Consumer;

/**
 * Visitable is an interface for implementing Visitor Design Pattern.
 */
public interface Visitable extends Consumer<Visitor> {

}
