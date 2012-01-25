package org.refact4j.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class SerializerHelper {

    private SerializerHelper() {
    }

    /**
     * Serialized the given object into an array of bytes.
     *
     * @param serializable
     * @return
     * @throws java.io.IOException
     */
    public static byte[] serialize(Serializable serializable) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        try {
            oos.writeObject(serializable);
            oos.flush();

            return baos.toByteArray();
        } finally {
            oos.close();
            baos.close();
        }
    }

    /**
     * Deserialized an array of bytes into a <tt>Serializable</tt> object.
     *
     * @param serial
     * @return
     * @throws ClassNotFoundException
     * @throws java.io.IOException
     */
    public static Object deserialize(byte[] serial) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(serial);
        ObjectInputStream ois = new ObjectInputStream(bais);

        try {
            return ois.readObject();
        } finally {
            ois.close();
            bais.close();
        }
    }

}
