package org.refact4j.eom;

import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;
import org.refact4j.eom.xml.writer.EntityXmlWriterHelper;
import org.refact4j.util.XmlAssert;

import java.util.List;

public class EntityTestUtils {

    public static void assertEquals(List<EntityObject> expectedEntities, String actualXmlDescription) {
        try {
            XmlAssert.assertXmlEquivalent(EntityXmlWriterHelper.dump(expectedEntities, "dataset"), "<dataset>"
                    + actualXmlDescription + "</dataset>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void assertEquals(List<EntityObject> entityObjects, String xmlDescription, String rooTag) {
        assertEquals(entityObjects, xmlDescription, rooTag, EntityXmlReaderHelper.EMPTY_EXCLUDED_FIELDS);
    }

    public static void assertEquals(List<EntityObject> expectedEntities, String actualXmlDescription, String rootTag,
                                    String[] excludedFields) {
        String expected = EntityXmlWriterHelper.dump(expectedEntities, rootTag, excludedFields);
        XmlAssert.assertXmlEquivalent(expected, actualXmlDescription);
    }

    public static void assertEquals(List<EntityObject> expectedEntities, List<EntityObject> actualEntities, String rooTag,
                                    String[] excludedFields) {
        XmlAssert.assertXmlEquivalent(EntityXmlWriterHelper.dump(expectedEntities, rooTag, excludedFields),
                EntityXmlWriterHelper.dump(actualEntities, rooTag, excludedFields));
    }

    public static void assertEquals(List<EntityObject> expectedEntities, List<EntityObject> actualEntities) {
        XmlAssert.assertXmlEquivalent(EntityXmlWriterHelper.dump(expectedEntities, "dataset"), EntityXmlWriterHelper
                .dump(actualEntities, "dataset"));
    }

}
