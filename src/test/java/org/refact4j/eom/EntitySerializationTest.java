package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.model.Field;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;

import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class EntitySerializationTest {

    @Test
    public void testEntitySerialization() throws Exception {
        EntityObject bar = EntityObjectBuilder.init(BarDesc.INSTANCE).set(BarDesc.ID, 1).get();
        EntityObject expectedEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).set(
                FooDesc.NAME, "foo").set(FooDesc.FLAG, true).set(FooDesc.BEGIN_DATE, new Date()).set(
                FooDesc.BAR, bar).get();

        byte[] bytes = EntitySerializerHelper.serialize(expectedEntity);
        EntityObject actualEntity = EntitySerializerHelper.deserialize(bytes, DummyRepository.get());

        Collection<Field> fields = expectedEntity.getEntityDescriptor().getFields();
        for (Field field : fields) {
            assertEquals(expectedEntity.get(field), actualEntity.get(field));
        }

        expectedEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).set(FooDesc.NAME, "dummy")
                .set(FooDesc.FLAG, true).set(FooDesc.BEGIN_DATE, new Date()).get();

        bytes = EntitySerializerHelper.serialize(expectedEntity);
        actualEntity = EntitySerializerHelper.deserialize(bytes, DummyRepository.get());

        for (Field field : fields) {
            assertEquals(expectedEntity.get(field), actualEntity.get(field));
        }

    }
}
