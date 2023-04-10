
package com.github.parse.utils;

import org.apache.commons.text.StringSubstitutor;

public final class StringUtils {
    /**
     * Does string interpolation if the value contains a place holder.
     *
     * @param value Value to parse
     *
     * @return Parsed value
     */
    public static String interpolate (final String value) {
        if (value.startsWith ("${")) {
            final StringSubstitutor substitute = StringSubstitutor.createInterpolator ();
            substitute.setEnableSubstitutionInVariables (true);
            return substitute.replace (value);
        }
        return value;
    }

    private StringUtils () {
        // Util class.
    }
}