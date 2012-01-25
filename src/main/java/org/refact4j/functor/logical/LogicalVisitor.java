package org.refact4j.functor.logical;

import org.refact4j.functor.logical.And.AndVisitor;
import org.refact4j.functor.logical.Not.NotVisitor;
import org.refact4j.functor.logical.Or.OrVisitor;

/**
 * LogicalVisitor interface.
 */
public interface LogicalVisitor extends AndVisitor, OrVisitor, NotVisitor {

}
