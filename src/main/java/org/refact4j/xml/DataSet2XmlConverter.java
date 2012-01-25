package org.refact4j.xml;

import org.refact4j.collection.DataSet;

@SuppressWarnings("unchecked")
public interface DataSet2XmlConverter {

    void unmarshal(String xml, DataSet dataSet);

    String marshal(DataSet dataSet);

}
