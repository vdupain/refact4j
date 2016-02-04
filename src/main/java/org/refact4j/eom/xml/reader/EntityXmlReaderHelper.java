package org.refact4j.eom.xml.reader;

import org.refact4j.collection.Set;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.EntityObjectBuilder;
import org.refact4j.eom.String2ValueFieldConverter;
import org.refact4j.eom.impl.EntityDataSet;
import org.refact4j.eom.impl.EntityListImpl;
import org.refact4j.eom.model.DefaultFieldVisitor;
import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.EntityDescriptorRepository;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.xml.EntityXmlDescriptor;
import org.refact4j.xml.XmlAttributes;
import org.refact4j.xml.XmlElement;
import org.refact4j.xml.XmlParserHelper;
import org.refact4j.xml.impl.Dataset2XmlConverterImpl;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class EntityXmlReaderHelper {

    public static final String[] EMPTY_EXCLUDED_FIELDS = new String[]{};

    public static final String ENTITIES_TAGNAME = "entities";

    private EntityXmlReaderHelper() {

    }

    public static org.refact4j.eom.EntityList unmarshal(final EntityDescriptorRepository repository, String xmlData) {
        Dataset2XmlConverterImpl dataset2XmlConverter = new Dataset2XmlConverterImpl();
        Set dataset = new EntityDataSet();
        dataset2XmlConverter.register(new EntityXmlDescriptor(repository));
        dataset2XmlConverter.unmarshal(xmlData, dataset);
        return new EntityListImpl(dataset);
    }

    public static org.refact4j.eom.EntityList parse(final EntityDescriptorRepository repository, String xmlData) {
        return unmarshal(repository::getEntityDescriptor, xmlData, ENTITIES_TAGNAME, EMPTY_EXCLUDED_FIELDS);
    }

    public static org.refact4j.eom.EntityList parse(EntityDescriptor entityDescriptor, String xmlData) {
        return parse(entityDescriptor, xmlData, ENTITIES_TAGNAME, EMPTY_EXCLUDED_FIELDS);
    }

    public static org.refact4j.eom.EntityList parse(EntityDescriptor entityDescriptor, String expectedXmlData, String rootTag) {
        return parse(entityDescriptor, expectedXmlData, rootTag, EMPTY_EXCLUDED_FIELDS);
    }

    private static org.refact4j.eom.EntityList parse(final EntityDescriptor entityDescriptor, String xmlData, String rootTag,
                                                     String[] excludedFields) {
        return unmarshal(name -> entityDescriptor, xmlData, rootTag, excludedFields);
    }

    private static org.refact4j.eom.EntityList unmarshal(final GetEntityDescriptor functor, String xmlData, String rootTag,
                                                         final String[] excludedFields) {
        if (rootTag == null)
            rootTag = ENTITIES_TAGNAME;
        final String rootTagName = rootTag;
        final org.refact4j.eom.EntityList entityObjects = new EntityListImpl();
        try {
            XmlParserHelper.parse(new StringReader("<" + rootTag + ">" + xmlData + "</" + rootTag + ">"),
                    new XmlElement() {

                        public XmlElement createChildXmlElement(String localName, String name, XmlAttributes attributes) {
                            if (rootTagName.equals(localName)) {
                                return this;
                            }
                            entityObjects.add(parse(attributes, functor.getEntityDescriptor(localName), excludedFields,
                                    entityObjects));
                            return this;
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return entityObjects;
    }

    public static EntityObject parse(XmlAttributes xmlAttrs, EntityDescriptor entityDescriptor) {
        return parse(xmlAttrs, entityDescriptor, EMPTY_EXCLUDED_FIELDS, null);
    }

    private static EntityObject parse(XmlAttributes xmlAttrs, EntityDescriptor entityDescriptor, String[] excludedFields,
                                      Collection<EntityObject> entityObjects) {
        EntityObjectBuilder builder = EntityObjectBuilder.init(entityDescriptor);
        parse(xmlAttrs, builder, excludedFields, entityObjects);
        return builder.get();
    }

    public static void parse(XmlAttributes xmlAttrs, EntityObjectBuilder builder, Collection<EntityObject> entityObjects) {
        parse(xmlAttrs, builder, EMPTY_EXCLUDED_FIELDS, entityObjects);
    }

    private static void parse(XmlAttributes xmlAttrs, final EntityObjectBuilder builder, String[] excludedFields,
                              final Collection<EntityObject> entityObjects) {
        List<String> listExcludedFields = Arrays.asList(excludedFields);
        EntityDescriptor entityDescriptor = builder.get().getEntityDescriptor();
        for (int i = 0; i < xmlAttrs.getLength(); i++) {
            String name = xmlAttrs.getLocalName(i);
            final String value = xmlAttrs.getValue(i);
            if (!listExcludedFields.contains(name)) {
                Field attribute = entityDescriptor.getField(name);
                if (attribute == null) {
                    throw new RuntimeException("field with name '" + name + "' doesn't exist for EntityDescriptor '"
                            + entityDescriptor + "'");
                }
                final String2ValueFieldConverter converter = new String2ValueFieldConverter(value);
                attribute.accept(new DefaultFieldVisitor());
                attribute.accept(converter);
                builder.set(attribute, converter.getValue());
            }
        }
    }

    interface GetEntityDescriptor {
        EntityDescriptor getEntityDescriptor(String name);
    }

}
