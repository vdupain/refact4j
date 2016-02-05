package org.refact4j.xml.reader;

import org.refact4j.collection.Set;
import org.refact4j.util.StringHelper;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

class DataSetRefXmlElementReader extends DefaultXmlElementReader {

    public DataSetRefXmlElementReader(Set dataset, Dataset2XmlConverterImpl dataset2XmlConverter) {
        super(dataset, dataset2XmlConverter);
    }

    public DataSetRefXmlElementReader(XmlAttributes xmlAttrs, DatasetConverterHolder dataSetConverterHolder) {
        super(dataSetConverterHolder);
        String fileName = xmlAttrs.getValue("file");
        try {
            File datasetFile = findDatasetFile(fileName);
            InputStream is = new FileInputStream(datasetFile);
            String datasetXml = StringHelper.getStringFromUTF8File(is);
            getDataset2XmlConverter().unmarshal(datasetXml, this.getDataSet());
        } catch (Exception e) {
            throw new RuntimeException("dataset file '" + fileName + "' not found");
        }
    }

    private File findDatasetFile(String fileName) {
        File datasetFile;

        datasetFile = new File(fileName);
        if (datasetFile.exists()) {
            return datasetFile;
        }
        try {
            URL url = this.getClass().getResource(fileName);
            datasetFile = new File(new URI(url.toString()));
            if (datasetFile.exists()) {
                return datasetFile;
            }
        } catch (Exception e) {
            throw new RuntimeException("dataset file '" + fileName + "' not found");
        }
        throw new RuntimeException("dataset file '" + fileName + "' not found");
    }
}
