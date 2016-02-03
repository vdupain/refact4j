package org.refact4j.xml.reader;

import org.refact4j.collection.Set;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.EntityDescriptorRepositoryHolder;
import org.refact4j.eom.xml.EntityXmlDescriptor;
import org.refact4j.eom.xml.reader.EntityXmlElementReader;
import org.refact4j.xml.*;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

public class DatasetXmlElementReader extends DefaultXmlElementReader {

    public DatasetXmlElementReader(Set dataset, DataSet2XmlConverter dataset2XmlConverter) {
        super(dataset, dataset2XmlConverter);
    }

    private DatasetXmlElementReader(DataSetConverterHolder dataSetConverterHolder) {
        super(dataSetConverterHolder);
    }

    @Override
    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        if (localName.equals("dataset")) {
            return new DatasetXmlElementReader(this);
        } else if (localName.equals("datasetRef")) {
            return new DatasetRefXmlElementReader(attributes, this);
        }

        XmlElementFactory xmlFactory = ((Dataset2XmlConverterImpl) getDataset2XmlConverter())
                .getXmlElementFactoryRepository().getXmlElementFactory(localName);
        if (xmlFactory == null) {
            xmlFactory = ((Dataset2XmlConverterImpl) getDataset2XmlConverter()).getXmlElementFactoryRepository()
                    .getXmlElementFactory(EntityXmlDescriptor.ROOT_TAGNAME);
            if (xmlFactory == null) {
                throw new RuntimeException("No parser registered for " + localName);
            }
            EntityDescriptorRepository entityDescriptorRepository = ((EntityDescriptorRepositoryHolder) xmlFactory)
                    .getEntityDescriptorRepository();
            try {
                EntityDescriptor entityDescriptor = entityDescriptorRepository.getEntityDescriptor(localName);
                return new EntityXmlElementReader(entityDescriptor, entityDescriptorRepository, attributes, this, null);
            } catch (Exception ignored) {
            }
        }
        return xmlFactory.createXmlElement(this);

    }

}
