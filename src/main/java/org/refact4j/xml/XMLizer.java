package org.refact4j.xml;

import org.refact4j.xml.impl.XMLImpl;

@FunctionalInterface
public interface XMLizer<T> {
    /**
     * Default implementation of a XMLizer calls the toXml method of the
     * ToXml object or </> if the object is null.
     */

    XMLizer<ToXmlString> DEFAULT = (arg) -> (arg != null ? new XMLImpl(arg) : XML.EMPTY_XML);

    /**
     * Produce a XML representation of an object.
     *
     * @param arg the Object for which a XML should be produced
     * @return xml
     */
    XML xml(T arg);

}
