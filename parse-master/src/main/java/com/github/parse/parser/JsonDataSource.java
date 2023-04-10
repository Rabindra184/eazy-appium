
package com.github.parse.parser;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SNAKE_CASE;

import java.io.File;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.parse.internal.factory.JsonDataFactory;
import lombok.SneakyThrows;

public class JsonDataSource implements IDataSource {
    private final ObjectMapper mapper;

    /**
     * Data source constructor.
     */
    public JsonDataSource () {
        final JsonFactory factory = new JsonDataFactory ();
        this.mapper = new ObjectMapper (factory);
        this.mapper.setPropertyNamingStrategy (SNAKE_CASE);
    }

    @SneakyThrows
    @Override
    public <T> T parse (final String path, final Class<T> dataClass) {
        return this.mapper.readValue (new File (path), dataClass);
    }
}