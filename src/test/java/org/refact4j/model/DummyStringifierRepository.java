package org.refact4j.model;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.EntityStringifierRepo;
import org.refact4j.eom.EntityStringifierRepoBuilder;

public class DummyStringifierRepository {

    private static final EntityStringifierRepo repository;

    public static EntityStringifier FOO_STRINGIFIER = new EntityStringifier() {

        @Override
        public String stringify(EntityObject arg) {
            return arg.get(FooDesc.NAME);
        }
    };

    public static EntityStringifier BAR_STRINGIFIER = new EntityStringifier() {

        @Override
        public String stringify(EntityObject arg) {
            return arg.get(BarDesc.NAME);
        }
    };

    static {
        EntityStringifierRepo repository1 = EntityStringifierRepoBuilder.init().add(FooDesc.INSTANCE, FOO_STRINGIFIER)
                .getStringifierRepository();
        repository = EntityStringifierRepoBuilder.init(repository1)
                .add(BarDesc.INSTANCE, BAR_STRINGIFIER)
                .getStringifierRepository();
    }

    public static EntityStringifierRepo get() {
        return repository;
    }

}
