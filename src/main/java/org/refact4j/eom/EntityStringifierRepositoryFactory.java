package org.refact4j.eom;

/**
 * EntityStringifierRepositoryFactory is an interface used to define methods
 * used to create EntityStringifier repository.
 */
public interface EntityStringifierRepositoryFactory {

    EntityStringifierRepo createEntityStringifierRepository();

    EntityStringifierRepo createEntityStringifierRepository(EntityStringifierRepo initalEntityStringifierRepository);

}
