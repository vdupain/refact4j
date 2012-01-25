package org.refact4j.eom;

import org.refact4j.eom.model.EntityDescriptor;
import org.refact4j.eom.model.Field;
import org.refact4j.eom.model.Key;
import org.refact4j.eom.model.KeyBuilder;
import org.refact4j.util.EqualsHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This class provides static methods to manipulate {@link EntityObject}
 */
public final class EntityUtils {

    private EntityUtils() {
    }

    /**
     * Apply default values to all fields of the {@link EntityObject} including
     * already valued fields.
     *
     * @param entityObject The EntityObject to generate default values from
     */
    public static void applyDefaultValues(EntityObject entityObject) {
        for (Field field : entityObject.getEntityDescriptor().getFields()) {
            entityObject.set(field, field.getDefaultValue());
        }
    }

    /**
     * Apply default values to all fields of the {@link EntityObject} excluding
     * already valued fields.
     *
     * @param entityObject entityObject to generate default values from
     */
    public static void applyEmptyDefaultValues(EntityObject entityObject) {
        for (Field field : entityObject.getEntityDescriptor().getFields()) {
            if (entityObject.get(field) == null) {
                entityObject.set(field, field.getDefaultValue());
            }
        }
    }

    /**
     * Get key value from key
     *
     * @param key the key to get value from
     * @return key value
     */
    public static Object getKeyValue(Key key) {
        Field keyField = getKeyField(key.getEntityDescriptor());
        return key.getFieldValue(keyField);
    }

    /**
     * Get field used in an object type
     *
     * @param entityDescriptor the object type to get key from
     * @return the field used in the key of the object type
     */
    private static Field getKeyField(EntityDescriptor entityDescriptor) {
        List<Field> keys = entityDescriptor.getKeyFields();
        if (keys.size() != 1) {
            throw new RuntimeException("Entity  '" + entityDescriptor.getName() + "' should have only one key");
        }
        return keys.get(0);
    }

    /**
     * Test equality between two {@link EntityObject}
     *
     * @param entityObject1 first {@link EntityObject} to compare
     * @param entityObject2 second {@link EntityObject} to compare
     * @return true if equals, false otherwise
     */
    public static boolean equals(EntityObject entityObject1, EntityObject entityObject2) {
        return EqualsHelper.equals(entityObject1, entityObject2);
    }

    /**
     * Formats a date using format MM/dd/yy
     *
     * @param date the date to be converted
     * @return a string containing formatted data
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat("MM/dd/yy").format(date);
    }

    /**
     * Formats a timestamp using format MM/dd/yy-HH:mm:ss
     *
     * @param date the date to be converted
     * @return a string containing formatted timestamp
     */
    public static String formatTimestamp(Date date) {
        if (date == null)
            return "";
        return new SimpleDateFormat("MM/dd/yy-HH:mm:ss").format(date);
    }

    /**
     * Test a date against MM/dd/yy format
     *
     * @param date the date to be tested
     * @return parsed date
     */
    public static Date parseDate(String date) {
        if (date == null)
            return null;
        try {
            return new SimpleDateFormat("MM/dd/yy").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test a date against MM/dd/yy-HH:mm:ss format
     *
     * @param date the string to be converted
     * @return date constructed out of the string
     */
    public static Date parseTimestamp(String date) {
        if (date == null)
            return null;
        try {
            return new SimpleDateFormat("MM/dd/yy-HH:mm:ss").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Key createKey(EntityDescriptor entityDescriptor, Object keyValue) {
        return KeyBuilder.init(entityDescriptor).set(EntityUtils.getKeyField(entityDescriptor), keyValue).getKey();
    }

}
