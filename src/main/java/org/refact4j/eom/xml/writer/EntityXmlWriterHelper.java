package org.refact4j.eom.xml.writer;

import org.refact4j.eom.ConverterHelper;
import org.refact4j.eom.EntityList;
import org.refact4j.eom.EntityObject;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.FieldNameComparator;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class EntityXmlWriterHelper {

    private static final String[] EMPTY_EXCLUDED_FIELDS = EntityXmlReaderHelper.EMPTY_EXCLUDED_FIELDS;

    private static final String ROOT_TAG = EntityXmlReaderHelper.ENTITIES_TAGNAME;

    private EntityXmlWriterHelper() {
    }

    public static String dump(EntityList entityObjects) {
        return dump(entityObjects, ROOT_TAG, EMPTY_EXCLUDED_FIELDS);
    }

    public static String dump(EntityList entityObjects, String rootTag) {
        return dump(entityObjects, rootTag, EMPTY_EXCLUDED_FIELDS);
    }

    public static String dump(EntityList entityObjects, String rootTag, String[] excludedFields) {
        if (rootTag == null) {
            rootTag = ROOT_TAG;
        }
        StringWriter stringWriter = new StringWriter();
        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(stringWriter);
            writer.writeStartElement(rootTag);
            for (EntityObject entityObject : entityObjects) {
                writer.writeStartElement(entityObject.getEntityDescriptor().getName());
                dumpAttributes(entityObject, writer, excludedFields);
                writer.writeEndElement();
            }
            writer.writeEndElement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }

    public static String dump(EntityObject entityObject) {
        StringWriter stringWriter = new StringWriter();
        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = output.createXMLStreamWriter(stringWriter);
            writer.writeEmptyElement(entityObject.getEntityDescriptor().getName());
            dumpAttributes(entityObject, writer, EMPTY_EXCLUDED_FIELDS);
            writer.writeCharacters(""); // closes empty tag (bug stax)
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringWriter.toString();
    }

    private static void dumpAttributes(EntityObject entityObject, XMLStreamWriter xmlStreamWriter,
                                       String[] excludedFields) throws Exception {
        List<Field> fields = new ArrayList<>(entityObject.getEntityDescriptor().getFields());
        Collections.sort(fields, new FieldNameComparator());
        for (Field field : fields) {
            dumpEntityField(entityObject, field, xmlStreamWriter, excludedFields);
        }
    }

    private static void dumpEntityField(EntityObject entityObject, Field field, XMLStreamWriter xmlStreamWriter,
                                        String[] excludedFields) throws Exception {
        List<String> listExcludedFields = Arrays.asList(excludedFields);
        if (!listExcludedFields.contains(field.getName())) {
            String sValue = ConverterHelper.convertValue2String(entityObject.get(field), field);
            if (sValue != null)
                xmlStreamWriter.writeAttribute(field.getName(), sValue);
        }
    }

}
