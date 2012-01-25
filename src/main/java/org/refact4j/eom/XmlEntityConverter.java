package org.refact4j.eom;

import org.refact4j.eom.impl.AbstractEntityConverter;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

public class XmlEntityConverter extends AbstractEntityConverter<String> {

    @Override
    public EntityObject convert(String xml) {
        return EntityXmlReaderHelper.parse(entityDescriptorRepository, xml).get(0);
    }

}
