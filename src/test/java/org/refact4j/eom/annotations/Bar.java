package org.refact4j.eom.annotations;


@EntityBindable(entityDescriptor = "Bar")
public interface Bar {

    @EntityField(name = "idBar", prettyName = "the id", isKey = true)
    Integer getId();

    @EntityField(name = "name", prettyName = "the name")
    String getName();

    @EntityField(name = "foo", prettyName = "the foo")
    Foo getFoo();

}
