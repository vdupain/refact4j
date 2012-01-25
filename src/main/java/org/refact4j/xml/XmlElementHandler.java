package org.refact4j.xml;

public interface XmlElementHandler {

    String getTagName();

    boolean hasNext();

    XmlElementHandler[] handleNext(XmlWriter xmlWriter) throws Exception;

}
