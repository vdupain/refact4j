package org.refact4j.xml;

import java.util.Collection;

public interface XmlDescriptor {

    Collection<XmlElementFactory> getXmlElementFactories();

    XmlElementHandler[] getXmlElementHandlers(DataSetConverterHolder holder);


}
