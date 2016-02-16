package org.refact4j.model;

import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityStringifier;
import org.refact4j.eom.EntityStringifierRepo;
import org.refact4j.eom.EntityStringifierRepoBuilder;

public class DummyStringifierRepository {

    public static final EntityStringifier FOO_STRINGIFIER = new EntityStringifier() {

        @Override
        public String stringify(EntityObject arg) {
            return arg.get(FooDesc.NAME);
        }
    };
    public static final EntityStringifier BAR_STRINGIFIER = new EntityStringifier() {

        @Override
        public String stringify(EntityObject arg) {
            return arg.get(BarDesc.NAME);
        }
    };
    private static final EntityStringifierRepo repository;

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
