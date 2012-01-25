package org.refact4j.xml;

import org.refact4j.xml.impl.stax.StaxXmlWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.Writer;

public class XmlWriterHelper {

    public static void build(Writer writer, XmlElementHandler rootBuilder) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = outputFactory.createXMLStreamWriter(writer);
        xmlStreamWriter.writeStartElement(rootBuilder.getTagName());
        StaxXmlWriter xmlWriter = new StaxXmlWriter(xmlStreamWriter);
        XmlElementHandler[] xmlHandlers = rootBuilder.handleNext(xmlWriter);
        write(xmlWriter, xmlHandlers);
        xmlStreamWriter.writeEndElement();
    }

    private static void write(XmlWriter xmlWriter, XmlElementHandler[] xmlElementHandlers) throws Exception {
        for (XmlElementHandler xmlHandler : xmlElementHandlers) {
            write(xmlWriter, xmlHandler);
        }
    }

    private static void write(XmlWriter xmlWriter, XmlElementHandler xmlElementHandler) throws Exception {
        while (xmlElementHandler.hasNext()) {
            xmlWriter.writeStartElement(xmlElementHandler.getTagName());
            write(xmlWriter, xmlElementHandler.handleNext(xmlWriter));
            xmlWriter.writeEndElement();
        }
    }

}
