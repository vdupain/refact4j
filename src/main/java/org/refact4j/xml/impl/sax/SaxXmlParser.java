package org.refact4j.xml.impl.sax;

import org.refact4j.xml.XmlElement;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;

public class SaxXmlParser {
    private XMLReader xmlReader;
    private final SaxHandler saxHandler = new SaxHandler();

    public SaxXmlParser() {
    }

    public void setXmlReader(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
        this.xmlReader.setContentHandler(saxHandler);
        this.xmlReader.setErrorHandler(saxHandler);
    }

    public void setXmlRootElement(XmlElement xmlRootElement) {
        this.saxHandler.setXmlRootElement(xmlRootElement);
    }

    public void parse(InputSource is, EntityResolver entityResolver) throws IOException, SAXException {
        if (entityResolver != null) {
            this.xmlReader.setEntityResolver(entityResolver);
        }
        this.xmlReader.parse(is);
    }

}
