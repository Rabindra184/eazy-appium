
package com.github.parse;

import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import com.github.parse.error.OperationNotSupportedError;
import com.github.parse.parser.IDataSource;
import com.github.parse.parser.JsonDataSource;
import com.github.parse.parser.PropertiesDataSource;
import com.github.parse.parser.XmlDataSource;
import com.github.parse.parser.YamlDataSource;
import com.github.parse.utils.DataFileUtil;

/**
 * Helper class to parse data file.
 */
public class DataSource {
    /**
     * Parses the data file according to their file format.
     *
     * @param dataClass Data file class.
     * @param <T> Class type.
     *
     * @return Data class object.
     */
    public static <T> T parse (final Class<T> dataClass) {
        final DataFileUtil<T> dataFile = DataFileUtil.getInstance (dataClass);
        final String fileName = dataFile.getFileName ();
        final String extension = fileName.substring (fileName.lastIndexOf ('.') + 1);
        final IDataSource dataSource = getDataSource (extension);
        return requireNonNull (dataSource).parse (dataFile.getPath (), dataClass);
    }

    private static IDataSource getDataSource (final String extension) {
        switch (extension.toLowerCase ()) {
            case "yaml":
            case "yml":
                return new YamlDataSource ();
            case "json":
                return new JsonDataSource ();
            case "properties":
                return new PropertiesDataSource ();
            case "xml":
                return new XmlDataSource ();
            default:
                throw new OperationNotSupportedError (
                    format ("This data file format [{0}] is not supported.", extension));
        }
    }

    private DataSource () {
        // Utility class.
    }
}