package org.refact4j.eom;

import org.junit.Test;
import org.refact4j.eom.model.EntityDescriptorBuilder;
import org.refact4j.eom.model.FieldFactory;
import org.refact4j.eom.xml.reader.EntityXmlReaderHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class EntityUtilsTest {

    @Test
    public void testApplyEmptyDefaultValues() {
        EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("foo");
        FieldFactory.init(builder, "field1").setNullable(true).setDefaultValue("default for field1")
                .createStringField();
        FieldFactory.init(builder, "field2").setNullable(true).setDefaultValue(false).createBooleanField();
        FieldFactory.init(builder, "id").setNullable(false).createIntegerField();

        String dataXml = "<entities><foo id='1' field1='foo1' field2='true'/>" + "<foo id='2' field1='foo2'/>"
                + "<foo id='3' field2='true'/></entities>";
        EntityList dataEntities = EntityXmlReaderHelper.parse(builder.getEntityDescriptor(), dataXml);
        dataEntities.stream().forEach(EntityUtils::applyEmptyDefaultValues);

        String expected = "<entities>" +
                "<foo field1='foo1' field2='true' id='1'/>" +
                "<foo field1='foo2' field2='false' id='2'/>" +
                "<foo field1='default for field1' field2='true' id='3'/>" +
                "</entities>";
        EntityList expectedEntities = EntityXmlReaderHelper.parse(builder.getEntityDescriptor(), expected);
        EntityTestUtils.assertEquals(expectedEntities, dataEntities);
    }

    @Test
    public void testApplyDefaultValues() {
        EntityDescriptorBuilder builder = EntityDescriptorBuilder.init("foo");
        FieldFactory.init(builder, "field1").setNullable(true).setDefaultValue("default for field1")
                .createStringField();
        FieldFactory.init(builder, "field2").setNullable(true).setDefaultValue(false).createBooleanField();
        FieldFactory.init(builder, "id").setNullable(false).createIntegerField();

        String dataXml = "<entities><foo id='1' field1='foo1' field2='true'/>" + "<foo id='2' field1='foo2'/>"
                + "<foo id='3' field2='true'/></entities>";
        EntityList dataEntities = EntityXmlReaderHelper.parse(builder.getEntityDescriptor(), dataXml);
        dataEntities.stream().forEach(EntityUtils::applyDefaultValues);
        String expected = "<entities>" +
                "<foo field1='default for field1' field2='false' id='null'/>" +
                "<foo field1='default for field1' field2='false' id='null'/>" +
                "<foo field1='default for field1' field2='false' id='null'/>" +
                "</entities>";
        EntityList expectedEntities = EntityXmlReaderHelper.parse(builder.getEntityDescriptor(), expected);
        EntityTestUtils.assertEquals(expectedEntities, dataEntities);
    }

    @Test
    public void testParseFormatDate() {
        assertNull(EntityUtils.parseDate(null));
        assertNull(EntityUtils.parseTimestamp(null));
        assertEquals("", EntityUtils.formatDate(null));
        assertEquals("", EntityUtils.formatTimestamp(null));
        Calendar xmas = new GregorianCalendar(1998, Calendar.DECEMBER, 25, 12, 34, 56);
        Calendar xmasIgnoreTime = new GregorianCalendar(1998, Calendar.DECEMBER, 25);
        Date date = xmas.getTime();
        Date dateIgnoreTime = xmasIgnoreTime.getTime();
        assertEquals("12/25/98", EntityUtils.formatDate(date));
        assertEquals("12/25/98-12:34:56", EntityUtils.formatTimestamp(date));
        assertEquals(dateIgnoreTime, EntityUtils.parseDate("12/25/98"));
        assertEquals(dateIgnoreTime, EntityUtils.parseDate("12/25/98-12:34:56"));
        assertEquals(date, EntityUtils.parseTimestamp("12/25/98-12:34:56"));
        try {
            EntityUtils.parseDate("azerty");
            fail("Expected Exception");
        } catch (Exception e) {
            assertEquals("java.text.ParseException: Unparseable date: \"azerty\"", e.getMessage());
        }
        try {
            EntityUtils.parseTimestamp("azerty");
            fail("Expected Exception");
        } catch (Exception e) {
            assertEquals("java.text.ParseException: Unparseable date: \"azerty\"", e.getMessage());
        }
    }
}
