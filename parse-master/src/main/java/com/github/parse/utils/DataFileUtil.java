package com.github.parse.utils;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_HYPHEN;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.File;

import com.github.parse.annotation.DataFile;
import com.github.parse.error.OperationNotSupportedError;

/**
 * Helper class to parse data file and convert the same to data class object.
 *
 * @param <T> Data class type
 */
public class DataFileUtil<T> {
    public static <T> DataFileUtil<T> getInstance (final Class<T> dataClass) {
        return new DataFileUtil<> (dataClass);
    }

    private final Class<T> dataClass;
    private final DataFile dataFile;

    private DataFileUtil (final Class<T> dataClass) {
        this.dataClass = dataClass;
        validateDataClass ();
        this.dataFile = dataClass.getAnnotation (DataFile.class);
    }

    /**
     * Gets the data file name as per the data class name or @DataFile annotation. It will by default convert data class
     * name to lower case hyphen separated words. If the file name is specified in @DataFile annotation then it will
     * have higher precedence.
     *
     * @return Data file name.
     *
     * @throws OperationNotSupportedError When file not found in folder.
     */
    public String getFileName () {
        if (isNotEmpty (this.dataFile.fileName ())) {
            return this.dataFile.fileName ();
        }
        final String fileName = LOWER_CAMEL.to (LOWER_HYPHEN, this.dataClass.getSimpleName ());
        final File folder = new File (getFolder ());
        final File[] files = folder.listFiles ((d, f) -> f.startsWith (fileName));
        if (requireNonNull (files).length == 0) {
            throw new OperationNotSupportedError (format ("File [{0}] not found.", fileName));
        }
        return files[0].getName ();
    }

    /**
     * Gets the folder path containing data file.
     *
     * @return Data file folder path.
     *
     * @throws OperationNotSupportedError When folder is not a directory.
     */
    public String getFolder () {
        String folder = "src/test/resources";
        if (isNotEmpty (this.dataFile.folderPath ())) {
            folder = this.dataFile.folderPath ();
        }
        validateDirectory (folder);
        return folder;
    }

    /**
     * Gets the complete path to the data file.
     *
     * @return Complete path of data file.
     */
    public String getPath () {
        return format ("{0}/{1}/{2}", getRootFolder (), getFolder (), getFileName ());
    }

    /**
     * Gets the root directory of data file.
     *
     * @return Data file root directory.
     */
    public String getRootFolder () {
        String root = getProperty ("user.dir");
        if (isNotEmpty (this.dataFile.rootFolder ())) {
            root = this.dataFile.rootFolder ();
        }
        validateDirectory (root);
        return root;
    }

    private void validateDataClass () {
        if (!this.dataClass.isAnnotationPresent (DataFile.class)) {
            throw new OperationNotSupportedError ("Data Class must have @DataFile annotation.");
        }
    }

    private void validateDirectory (final String folder) {
        final File dir = new File (folder);
        if (!dir.isDirectory ()) {
            throw new OperationNotSupportedError (format ("[{0}] is not a directory.", folder));
        }
    }
}