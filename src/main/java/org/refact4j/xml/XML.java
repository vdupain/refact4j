package org.refact4j.xml;

import org.refact4j.core.Converter;
import org.refact4j.xml.impl.XMLImpl;

import java.io.Serializable;

public interface XML extends ToXmlString, Serializable {

    XML EMPTY_XML = new XMLImpl(ToXmlString.EMPTY_TO_XML);
    Converter<ToXmlString, XML> DEFAULT = (arg) -> (arg != null ? new XMLImpl(arg) : XML.EMPTY_XML);
    String HEADER = "<?xml version='1.0' encoding='utf-8'?>";

    String toString();
}
