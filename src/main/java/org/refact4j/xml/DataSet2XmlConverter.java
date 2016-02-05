package org.refact4j.xml;

import org.refact4j.collection.Set;

@SuppressWarnings("unchecked")
public interface Dataset2XmlConverter {

    void unmarshal(String xml, Set dataSet);

    String marshal(Set dataSet);

}
