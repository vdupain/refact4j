package org.refact4j.eom;

import org.refact4j.eom.model.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * EntitySerializerUtils is an utility class for serializing and deserializing
 * EntityObjects.
 */
public final class EntitySerializerHelper {

    private EntitySerializerHelper() {
    }

    private static byte[] serialize(Key key) {
        return serialize(key, key.getEntityDescriptor().getKeyFields(), key.getEntityDescriptor());
    }

    private static byte[] serialize(Key key, Collection<Field> fields, EntityDescriptor entityDescriptor) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            serialize(objectOutputStream, entityDescriptor, fields, key);
            objectOutputStream.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] serialize(EntityObject entityObject) {
        return serialize(entityObject, entityObject.getEntityDescriptor().getFields(), entityObject
                .getEntityDescriptor());
    }

    private static byte[] serialize(EntityObject entityObject, Collection<Field> fields,
                                    EntityDescriptor entityDescriptor) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            serialize(objectOutputStream, entityDescriptor, fields, entityObject);
            objectOutputStream.flush();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void serialize(final ObjectOutputStream objectOutputStream, EntityDescriptor entityDescriptor,
                                  Collection<Field> fields, final EntityObject entityObject) throws IOException {
        objectOutputStream.writeObject(entityDescriptor.getName());
        objectOutputStream.writeInt(fields.size());
        for (Field field : fields) {
            objectOutputStream.writeObject(field.getName());
        }
        for (Field field : fields) {
            FieldVisitor serializerVisitor = new AbstractSerializerDeSerializerVisitor() {
                public void visitField(Field field) {
                    try {
                        objectOutputStream.writeObject(entityObject.get(field));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
                    try {
                        Key key = entityObject.get(manyToOneRelationField);
                        objectOutputStream.writeObject(key != null ? serialize(key) : null);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            field.accept(serializerVisitor);
        }
    }

    private static void serialize(ObjectOutputStream objectOutputStream, EntityDescriptor entityDescriptor,
                                  Collection<Field> fields, Key key) throws IOException {
        objectOutputStream.writeObject(entityDescriptor.getName());
        objectOutputStream.writeInt(fields.size());
        for (Field field : fields) {
            objectOutputStream.writeObject(field.getName());
        }
        for (Field field : fields) {
            objectOutputStream.writeObject(key.getFieldValue(field));
        }
    }

    public static EntityObject deserialize(byte[] bytes, final EntityDescriptorRepository repository) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try {
            final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String entityDescName = (String) objectInputStream.readObject();
            EntityDescriptor entityDescriptor = repository.getEntityDescriptor(entityDescName);
            int fieldCount = objectInputStream.readInt();
            List<Field> fields = new ArrayList<Field>(fieldCount);
            for (int i = 0; i < fieldCount; i++) {
                String fieldName = (String) objectInputStream.readObject();
                fields.add(entityDescriptor.getField(fieldName));
            }
            final EntityObjectBuilder entityObjectBuilder = EntityObjectBuilder.init(entityDescriptor);
            for (Field field : fields) {
                FieldVisitor deserializerVisitor = new AbstractSerializerDeSerializerVisitor() {
                    public void visitField(Field field) {
                        try {
                            entityObjectBuilder.set(field, objectInputStream.readObject());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                    public void visitManyToOneRelationField(ManyToOneRelationField manyToOneRelationField) {
                        try {
                            byte[] bytes = (byte[]) objectInputStream.readObject();
                            Key key = bytes != null ? deserializeKey(bytes, repository) : null;
                            entityObjectBuilder.set(manyToOneRelationField, key);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
                field.accept(deserializerVisitor);
            }
            return entityObjectBuilder.getEntity();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Key deserializeKey(byte[] bytes, EntityDescriptorRepository repository) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            String entityDescName = (String) objectInputStream.readObject();
            EntityDescriptor entityDescriptor = repository.getEntityDescriptor(entityDescName);
            int fieldCount = objectInputStream.readInt();
            List<Field> fields = new ArrayList<Field>(fieldCount);
            for (int i = 0; i < fieldCount; i++) {
                String fieldName = (String) objectInputStream.readObject();
                fields.add(entityDescriptor.getField(fieldName));
            }
            KeyBuilder keyBuilder = KeyBuilder.init(entityDescriptor);
            for (Field field : fields) {
                keyBuilder.set(field, objectInputStream.readObject());
            }
            return keyBuilder.getKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    abstract static class AbstractSerializerDeSerializerVisitor implements FieldVisitor {

        public void visitIntegerField(IntegerField integerField) {
            this.visitField(integerField);
        }

        public void visitDoubleField(DoubleField doubleField) {
            this.visitField(doubleField);
        }

        public void visitStringField(StringField stringField) {
            this.visitField(stringField);
        }

        public void visitDateField(DateField dateField) {
            this.visitField(dateField);
        }

        public void visitBooleanField(BooleanField booleanField) {
            this.visitField(booleanField);
        }

        public void visitOneToManyRelationField(OneToManyRelationField oneToManyRelationField) {
        }

        public void visitOneToOneRelationField(OneToOneRelationField oneToOneRelationField) {
        }
    }
}
