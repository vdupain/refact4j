package org.refact4j.xml;

import org.refact4j.xml.impl.DefaultXmlElementHandler;
import org.refact4j.xml.reader.DefaultXmlElementReader;
import org.refact4j.xml.reader.FooBarsXmlElementReader;
import org.refact4j.xml.writer.BarXmlElementWriter;
import org.refact4j.xml.writer.FooXmlElementWriter;

import java.util.Collection;
import java.util.Collections;

public class DummyXmlDescriptor implements XmlDescriptor {

    XmlElementFactory factory = new XmlElementFactory() {

        public XmlElement createXmlElement(DefaultXmlElementReader xmlElement) {
            return new FooBarsXmlElementReader(xmlElement);
        }

        public String getXmlElementTagName() {
            return "fooBars";
        }

    };

    public Collection<XmlElementFactory> getXmlElementFactories() {
        return Collections.singletonList(factory);
    }


    public XmlElementHandler[] getXmlElementHandlers(DataSetConverterHolder holder) {


        return new XmlElementHandler[]{
                new DefaultXmlElementHandler("fooBars", new XmlElementHandler[]{
                        new DefaultXmlElementHandler("bars", new BarXmlElementWriter(holder)),
                        new DefaultXmlElementHandler("foos", new FooXmlElementWriter(holder))})};

    }

}
