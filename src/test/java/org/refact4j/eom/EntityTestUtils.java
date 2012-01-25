package org.refact4j.eom;

import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.eom.xml.writer.EntityXmlWriterHelper;
import org.refact4j.util.XmlAssert;

public class EntityTestUtils {

    public static void assertEquals(EntityList expectedEntities, String actualXmlDescription) {
        try {
            XmlAssert.assertXmlEquivalent(EntityXmlWriterHelper.dump(expectedEntities, "dataset"), "<dataset>"
                    + actualXmlDescription + "</dataset>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void assertEquals(EntityList entityObjects, String xmlDescription, String rooTag) {
        assertEquals(entityObjects, xmlDescription, rooTag, EntityXmlReaderHelper.EMPTY_EXCLUDED_FIELDS);
    }

    public static void assertEquals(EntityList expectedEntities, String actualXmlDescription, String rootTag,
                                    String[] excludedFields) {
        String expected = EntityXmlWriterHelper.dump(expectedEntities, rootTag, excludedFields);
        XmlAssert.assertXmlEquivalent(expected, actualXmlDescription);
    }

    public static void assertEquals(EntityList expectedEntities, EntityList actualEntities, String rooTag,
                                    String[] excludedFields) {
        XmlAssert.assertXmlEquivalent(EntityXmlWriterHelper.dump(expectedEntities, rooTag, excludedFields),
                EntityXmlWriterHelper.dump(actualEntities, rooTag, excludedFields));
    }

    public static void assertEquals(EntityList expectedEntities, EntityList actualEntities) {
        XmlAssert.assertXmlEquivalent(EntityXmlWriterHelper.dump(expectedEntities, "dataset"), EntityXmlWriterHelper
                .dump(actualEntities, "dataset"));
    }

}
