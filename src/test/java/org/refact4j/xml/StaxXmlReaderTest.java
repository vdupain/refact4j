package org.refact4j.xml;

import org.refact4j.xml.impl.DefaultXmlReaderHandler;
import org.refact4j.xml.impl.stax.StaxAttributes;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.StringReader;

public class StaxXmlReaderTest extends AbstractXmlReaderTest {

    private final DefaultXmlReaderHandler xmlReaderHandler = new DefaultXmlReaderHandler();

    private void processEvent(XMLStreamReader xmlr) {
        switch (xmlr.getEventType()) {
            case XMLStreamConstants.START_ELEMENT:
                String qName = xmlr.getName().getLocalPart();
                if (xmlr.getName().getPrefix().length() > 0) {
                    qName = xmlr.getName().getPrefix() + ":" + qName;
                }
                xmlReaderHandler.startElement(xmlr.getNamespaceURI(), xmlr.getLocalName(), qName, new StaxAttributes(xmlr));
                break;
            case XMLStreamConstants.END_ELEMENT:
                qName = xmlr.getName().getLocalPart();
                if (xmlr.getName().getPrefix().length() > 0) {
                    qName = xmlr.getName().getPrefix() + ":" + qName;
                }
                xmlReaderHandler.endElement(xmlr.getNamespaceURI(), xmlr.getLocalName(), qName);
                break;
            case XMLStreamConstants.SPACE:
            case XMLStreamConstants.CHARACTERS:
                int start = xmlr.getTextStart();
                int length = xmlr.getTextLength();
                xmlReaderHandler.characters(xmlr.getTextCharacters(), start, length);
                break;
            case XMLStreamConstants.COMMENT:
            case XMLStreamConstants.PROCESSING_INSTRUCTION:
                break;
        }
    }

    @Override
    protected void readXml() throws Exception {
        xmlReaderHandler.setXmlRootElement(xmlRootElement);

        // Create the FIELD_XML input factory
        XMLInputFactory factory = XMLInputFactory.newInstance();
        // Create the FIELD_XML event reader
        StringReader reader = new StringReader(xmlWithNameSpace);
        XMLStreamReader r = factory.createXMLStreamReader(reader);
        // Loop over FIELD_XML input stream and process events
        while (r.hasNext()) {
            processEvent(r);
            r.next();
        }
        r.close();
    }
}
