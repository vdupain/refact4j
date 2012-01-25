package org.refact4j.eom.xml.reader;

import org.refact4j.eom.EntityFinder;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.OneToOneRelationField;
import org.refact4j.eom.model.RelationField;
import org.refact4j.xml.DatasetConverterHolder;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.reader.DefaultXmlElementReader;

import java.util.Collection;

public class EntityXmlElementReader extends DefaultXmlElementReader {

    private final EntityObject entityObject;

    private final EntityDescriptorRepository entityDescriptorRepository;

    private final EntityFinder finder;

    public EntityXmlElementReader(EntityDescriptor entityDescriptor,
                                  EntityDescriptorRepository entityDescriptorRepository, EntityFinder finder, XmlAttributes xmlAttrs,
                                  DatasetConverterHolder holder, final EntityObject parentEntity) {
        super(holder);
        this.entityDescriptorRepository = entityDescriptorRepository;
        this.finder = finder;
        final EntityObjectBuilder builder = EntityObjectBuilder.init(entityDescriptor);
        EntityXmlReaderHelper.parse(xmlAttrs, builder, new EntityListImpl(holder.getDataSet()), finder);
        if (parentEntity != null) {
            Collection<RelationField> fields = entityDescriptor.getRelationFields();
            for (RelationField relationField : fields) {
                if (relationField.getTargetEntityDescriptor().equals(parentEntity.getEntityDescriptor())) {
                    if (builder.getEntity().get(relationField) == null) {
                        builder.set(relationField, parentEntity.getKey());
                        if (relationField instanceof OneToOneRelationField) {
                            parentEntity.set(relationField.getInverseRelationField(), builder.getEntity().getKey());
                        }
                        break;
                    }
                }
            }
        }
        entityObject = builder.getEntity();
        this.add(entityObject);
    }

    public XmlElement createChildXmlElement(String localName, String qName, XmlAttributes attributes) {
        for (EntityDescriptor entityDescriptor : entityDescriptorRepository) {
            if (localName.equals(entityDescriptor.getName())) {
                return new EntityXmlElementReader(entityDescriptor, entityDescriptorRepository, finder, attributes,
                        this, entityObject);
            }
        }
        return super.createChildXmlElement(localName, qName, attributes);
    }
}
