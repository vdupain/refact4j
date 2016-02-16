package org.refact4j.xml;

import org.refact4j.collection.Set;

@SuppressWarnings("unchecked")
public interface DataSet2XmlConverter {

    void unmarshal(String xml, java.util.Set dataSet);

    String marshal(Set dataSet);

}
