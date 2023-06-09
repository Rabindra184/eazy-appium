
package com.github.parse.annotation;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark Pojo as data file. It will search the folder for file with name class name (each word in name
 * separated with hyphen (-). Also the file extension must be any of the following:
 * <p>1. yml</p>
 * <p>2. yaml</p>
 * <p>3. json</p>
 * <p>4. xml</p>
 * <p>5. properties</p>
 */
@Target (ElementType.TYPE)
@Retention (RetentionPolicy.RUNTIME)
public @interface DataFile {
    /**
     * Data file name, if not provided, it will be data class name in lower case hyphen separated words. E.g. If class
     * name is {@code LoginData} then file name is considered as {@code login-data}.
     *
     * @return Data file name
     */
    String fileName () default EMPTY;

    /**
     * Relative folder path containing data files. If not provided, it will consider {@code src/test/resources}
     *
     * @return Folder path
     */
    String folderPath () default EMPTY;

    /**
     * Root project folder path. By default, it will be project root.
     *
     * @return Root folder path.
     */
    String rootFolder () default EMPTY;
}