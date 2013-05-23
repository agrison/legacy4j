package me.grison.legacy4j.engine;

import java.io.File;

/**
 * Some static utilities to open fixed length files.
 *
 * @author Alexandre Grison <a.grison@gmail.com>
 */
public class LegacyFiles {
    /**
     * Returns a new FixedLengthFile LegacyFile implementation for the given file and mapping class.
     * @param file the file to read
     * @param clazz the class object
     * @param <T>
     * @return a {@see FixedLengthFile} implementation of {@see LegacyFile}
     */
    public static<T> LegacyFile<T> fileReader(String file, Class<T> clazz) {
        return new FixedLengthFile<T>(file, clazz);
    }
    /**
     * Returns a new FixedLengthFile LegacyFile implementation for the given file and mapping class.
     * @param file the file to read
     * @param clazz the class object
     * @param <T>
     * @return a {@see FixedLengthFile} implementation of {@see LegacyFile}
     */
    public static<T> LegacyFile<T> fileReader(File file, Class<T> clazz) {
        return new FixedLengthFile<T>(file, clazz);
    }

    /**
     * Returns a new FixedLengthFile LegacyFile implementation for the given file and mapping class.
     * Note: the file reader is already open.
     * @param file the file to read
     * @param clazz the class object
     * @param <T>
     * @return a {@see FixedLengthFile} implementation of {@see LegacyFile}
     */
    public static<T> LegacyFile<T> openFileReader(String file, Class<T> clazz) {
        LegacyFile<T> lfile = new FixedLengthFile<T>(file, clazz);
        lfile.open();
        return lfile;
    }

    /**
     * Returns a new FixedLengthFile LegacyFile implementation for the given file and mapping class.
     * Note: the file reader is already open.
     * @param file the file to read
     * @param clazz the class object
     * @param <T>
     * @return a {@see FixedLengthFile} implementation of {@see LegacyFile}
     */
    public static<T> LegacyFile<T> openFileReader(File file, Class<T> clazz) {
        LegacyFile<T> lfile = new FixedLengthFile<T>(file, clazz);
        lfile.open();
        return lfile;
    }
}
