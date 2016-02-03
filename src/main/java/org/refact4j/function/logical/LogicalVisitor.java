package org.refact4j.function.logical;

import org.refact4j.function.logical.And.AndVisitor;
import org.refact4j.function.logical.Not.NotVisitor;
import org.refact4j.function.logical.Or.OrVisitor;

/**
 * LogicalVisitor interface.
 */
public interface LogicalVisitor extends AndVisitor, OrVisitor, NotVisitor {

}
