package org.refact4j.util;

import java.io.*;
import java.util.regex.Pattern;

public final class StringHelper {
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final char WINDOWS_FILE_SEPARATOR_CHAR = '\\';
    public static final char UNIX_FILE_SEPARATOR_CHAR = '/';

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private static final Pattern MAJ_PATTERN = Pattern.compile("([A-Z]+)");

    private static final Pattern NUMBER_PATTERN = Pattern.compile("([0-9]+)");

    private StringHelper() {
    }

    public static String toUpperCaseWithUnderscores(String name) {
        if (name.length() == 0) {
            return "";
        }
        String result = MAJ_PATTERN.matcher(name).replaceAll("_$1").toUpperCase();
        result = NUMBER_PATTERN.matcher(result).replaceAll("_$1").toUpperCase();
        result = result.replaceAll("_+", "_");
        if (!name.startsWith("_") && result.startsWith("_")) {
            result = result.substring(1);
        }
        return result;
    }

    private static String read(InputStreamReader isr) throws IOException {
        StringBuilder buffer = new StringBuilder();
        Reader in = new BufferedReader(isr);
        int ch;
        while ((ch = in.read()) > -1) {
            buffer.append((char) ch);
        }
        in.close();
        return buffer.toString();
    }

    public static String capitalize(String name) {
        return name.length() > 0 ? name.substring(0, 1).toUpperCase() + name.substring(1) : name;
    }

    public static String uncapitalize(String name) {
        return name.length() > 0 ? name.substring(0, 1).toLowerCase() + name.substring(1) : name;
    }

    public static String getStringFromResourceFile(String fileName) {
        try {
            InputStream is = StringHelper.class.getResourceAsStream(fileName);
            return read(new InputStreamReader(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromUTF8File(InputStream is) {
        try {
            return read(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e;
        StringBuilder result = new StringBuilder();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

}
