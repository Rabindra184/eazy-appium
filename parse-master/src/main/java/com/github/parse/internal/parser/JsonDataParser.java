
package com.github.parse.internal.parser;


import static com.github.parse.utils.StringUtils.interpolate;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.JsonParserDelegate;

public class JsonDataParser extends JsonParserDelegate {
    public JsonDataParser (final JsonParser parser) {
        super (parser);
    }


    @Override
    public String getText () throws IOException {
        return interpolate (super.getText ());
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.core.util.JsonParserDelegate#getValueAsString()
     */
    @Override
    public String getValueAsString () throws IOException {
        return interpolate (super.getValueAsString ());
    }

    /*
     * (non-Javadoc)
     * @see @see
     * com.fasterxml.jackson.core.util.JsonParserDelegate#getValueAsString(java.lang
     * .String)
     */
    @Override
    public String getValueAsString (final String defaultValue) throws IOException {
        return interpolate (super.getValueAsString (defaultValue));
    }
}