package org.refact4j.eom;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.refact4j.eom.model.Field;
import org.refact4j.model.BarDesc;
import org.refact4j.model.DummyRepository;
import org.refact4j.model.FooDesc;

import java.util.Collection;
import java.util.Date;

public class EntitySerializationTest {

    @Test
    public void testEntitySerialization() throws Exception {
        EntityObject bar = EntityObjectBuilder.init(BarDesc.INSTANCE).set(BarDesc.ID, 1).getEntity();
        EntityObject expectedEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).set(
                FooDesc.NAME, "foo").set(FooDesc.FLAG, true).set(FooDesc.BEGIN_DATE, new Date()).set(
                FooDesc.BAR, bar).getEntity();

        byte[] bytes = EntitySerializerHelper.serialize(expectedEntity);
        EntityObject actualEntity = EntitySerializerHelper.deserialize(bytes, DummyRepository.get());

        Collection<Field> fields = expectedEntity.getEntityDescriptor().getFields();
        for (Field field : fields) {
            assertEquals(expectedEntity.get(field), actualEntity.get(field));
        }

        expectedEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).set(FooDesc.ID, 1).set(FooDesc.NAME, "dummy")
                .set(FooDesc.FLAG, true).set(FooDesc.BEGIN_DATE, new Date()).getEntity();

        bytes = EntitySerializerHelper.serialize(expectedEntity);
        actualEntity = EntitySerializerHelper.deserialize(bytes, DummyRepository.get());

        for (Field field : fields) {
            assertEquals(expectedEntity.get(field), actualEntity.get(field));
        }

    }
}
