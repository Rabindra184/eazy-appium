
package com.github.parse.parser;


public interface IDataSource {
    /**
     * Parses data file.
     *
     * @param path Path to file.
     * @param dataClass Class object for data file to parse to.
     * @param <T> Type of Class.
     *
     * @return List of class objects.
     */
    <T> T parse (String path, Class<T> dataClass);
}