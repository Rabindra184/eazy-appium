package com.github.parse.internal.factory;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.github.parse.internal.parser.JsonDataParser;

public class JsonDataFactory extends MappingJsonFactory {

    private static final long serialVersionUID = 7623910291405568916L;

    public JsonDataFactory () {
        super ();
    }

    /*
     * (non-Javadoc)
     * @see @see com.fasterxml.jackson.core.JsonFactory#_createParser(byte[], int,
     * int, com.fasterxml.jackson.core.io.IOContext)
     */
    @Override
    protected JsonParser _createParser (final byte[] data, final int offset, final int len, final IOContext ctxt)
        throws IOException {
        return new JsonDataParser (super._createParser (data, offset, len, ctxt));
    }

    /*
     * (non-Javadoc)
     * @see @see com.fasterxml.jackson.core.JsonFactory#_createParser(char[], int,
     * int, com.fasterxml.jackson.core.io.IOContext, boolean)
     */
    @Override
    protected JsonParser _createParser (final char[] data, final int offset, final int len, final IOContext ctxt,
        final boolean recyclable) throws IOException {
        return new JsonDataParser (super._createParser (data, offset, len, ctxt, recyclable));
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.core.JsonFactory#_createParser(java.io.DataInput,
     * com.fasterxml.jackson.core.io.IOContext)
     */
    @Override
    protected JsonParser _createParser (final DataInput input, final IOContext ctxt) throws IOException {
        return new JsonDataParser (super._createParser (input, ctxt));
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.core.JsonFactory#_createParser(java.io.InputStream,
     * com.fasterxml.jackson.core.io.IOContext)
     */
    @Override
    protected JsonParser _createParser (final InputStream in, final IOContext ctxt) throws IOException {
        return new JsonDataParser (super._createParser (in, ctxt));
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.core.JsonFactory#_createParser(java.io.Reader,
     * com.fasterxml.jackson.core.io.IOContext)
     */
    @Override
    protected JsonParser _createParser (final Reader r, final IOContext ctxt) throws IOException {
        return new JsonDataParser (super._createParser (r, ctxt));
    }
}