package org.refact4j.xml.reader;

import junit.framework.Assert;
import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.model.BarDesc;
import org.refact4j.model.FooDesc;
import org.refact4j.xml.DataSetConverterHolder;
import org.refact4j.xml.XmlAttributeNotFoundException;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlHelper;

public class FooXmlElementReader extends DefaultXmlElementReader {

    public FooXmlElementReader(XmlAttributes xmlAttrs, DataSetConverterHolder dataSetConverterHolder) {
        super(dataSetConverterHolder);

        String name = XmlHelper.getAttrValue("name", xmlAttrs);

        EntityObject fooEntity = ((EntityDataSet) dataSetConverterHolder.getDataSet()).getEntityByName(FooDesc.INSTANCE,
                FooDesc.NAME, name);
        if (fooEntity == null) {
            fooEntity = EntityObjectBuilder.init(FooDesc.INSTANCE).getEntity();
        }

        try {
            final String parentName = XmlHelper.getAttrValue("parent", xmlAttrs);
            EntityObject parentEntity = ((EntityDataSet) dataSetConverterHolder.getDataSet()).getEntityByName(BarDesc.INSTANCE, BarDesc.NAME, parentName);

            EntityObject parentEntity2 = ((EntityDataSet) dataSetConverterHolder.getDataSet()).getEntityByPredicate(BarDesc.INSTANCE, arg -> arg.get(BarDesc.NAME).equals(parentName));
            Assert.assertEquals(parentEntity, parentEntity2);

            fooEntity.set(FooDesc.BAR, parentEntity);
        } catch (XmlAttributeNotFoundException e) {
            // ignored
        }

        fooEntity.set(FooDesc.ID, (Integer) ConverterHelper.convertString2Value(XmlHelper.getAttrValue("id", xmlAttrs),
                FooDesc.ID));
        fooEntity.set(FooDesc.NAME, name);
        this.add(fooEntity);
    }

}
