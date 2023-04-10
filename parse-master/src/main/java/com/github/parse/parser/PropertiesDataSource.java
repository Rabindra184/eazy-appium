
package com.github.parse.parser;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;

import java.io.File;

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import lombok.SneakyThrows;


public class PropertiesDataSource implements IDataSource {
    private final JavaPropsMapper mapper;

    /**
     * Data source constructor.
     */
    public PropertiesDataSource () {
        this.mapper = new JavaPropsMapper ();
        this.mapper.setPropertyNamingStrategy (SNAKE_CASE);
    }

    @SneakyThrows
    @Override
    public <T> T parse (final String path, final Class<T> dataClass) {
        return this.mapper.readValue (new File (path), dataClass);
    }
}