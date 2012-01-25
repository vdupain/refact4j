package org.refact4j.eom.annotations;


@EntityBindable(entityDescriptor = "Foo")
public interface Foo {

    @EntityField(name = "idFoo", prettyName = "the foo id", isKey = true)
    Integer getId();

    @EntityField(name = "name", prettyName = "the name", isKey = true)
    String getName();

    @EntityField(name = "value", prettyName = "the value")
    Double getValue();

    @EntityField(name = "booleanFlag", prettyName = "the boolean flag")
    boolean isFlag();
}
