package org.refact4j.xml.impl;

import org.refact4j.collection.Set;
import org.refact4j.core.Finder;
import org.refact4j.xml.*;
import org.refact4j.xml.impl.sax.DefaultSaxErrorHandler;
import org.refact4j.xml.reader.DatasetXmlElementReader;
import org.refact4j.xml.writer.DatasetXmlWriter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Dataset2XmlConverterImpl implements DataSet2XmlConverter {

    private static final String XMLSCHEMA_INSTANCE = "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";

    private static final String DATASET_TAGNAME = "dataset";

    private final XmlElementFactoryRepositoryImpl xmlElementFactoryRepository = new XmlElementFactoryRepositoryImpl();

    private final List<XmlDescriptor> xmlDescriptors = new ArrayList<>();

    public void unmarshal(String xml, Set dataset) {
        unmarshal(xml, dataset, null);
    }

    public void unmarshal(String xml, Set dataset, Finder finder) {
        if (xml.indexOf(XMLSCHEMA_INSTANCE) != -1) {
            validate(new StringReader(xml));
        }
        unmarshal(new StringReader(xml), dataset, finder);
    }

    void validate(Reader reader) {
        try {
            org.xml.sax.XMLReader xmlReader = createXMLReader();
            xmlReader.setErrorHandler(new DefaultSaxErrorHandler());
            xmlReader.parse(new org.xml.sax.InputSource(reader));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void unmarshal(Reader reader, Set dataset) {
        unmarshal(reader, dataset, null);
    }

    void unmarshal(Reader reader, Set dataset, Finder finder) {
        try {
            org.xml.sax.XMLReader xmlReader = createXMLReader();
            final DatasetXmlElementReader datasetXmlNodeReader = new DatasetXmlElementReader(dataset, finder, this);
            XmlParserHelper.parse(xmlReader, new InputSource(reader), new AbstractXmlElement() {

                public XmlElement createChildXmlElement(String localName, String name, XmlAttributes attributes) {
                    if (localName.equals(DATASET_TAGNAME)) {
                        return datasetXmlNodeReader;
                    }
                    return null;
                }

            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public XmlElementFactoryRepository getXmlElementFactoryRepository() {
        return this.xmlElementFactoryRepository;
    }

    public void register(XmlDescriptor xmlDescriptor) {
        xmlDescriptors.add(xmlDescriptor);
        xmlElementFactoryRepository.register(xmlDescriptor);
    }

    private org.xml.sax.XMLReader createXMLReader() throws ParserConfigurationException, SAXException {
        javax.xml.parsers.SAXParserFactory spf = javax.xml.parsers.SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser sp = spf.newSAXParser();
        org.xml.sax.XMLReader xmlReader = sp.getXMLReader();
        xmlReader.setFeature("http://xml.org/sax/features/validation", true);
        xmlReader.setFeature("http://xml.org/sax/features/namespaces", true);
        xmlReader.setFeature("http://apache.org/xml/features/validation/schema", true);
        return xmlReader;
    }

    public Collection<XmlElementHandler> getXmlElementHandlers(DatasetConverterHolder holder) {
        List<XmlElementHandler> xmlWriters = new ArrayList<>();
        for (XmlDescriptor descriptor : xmlDescriptors) {
            xmlWriters.addAll(Arrays.asList(descriptor.getXmlElementHandlers(holder)));
        }
        return xmlWriters;
    }


    public String marshal(Set dataset) {
        try {
            StringWriter result = new StringWriter();
            XmlWriterHelper.build(result, new DatasetXmlWriter(dataset, null, this));
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
