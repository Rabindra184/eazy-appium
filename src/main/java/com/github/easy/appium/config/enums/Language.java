
package com.github.easy.appium.config.enums;

import java.util.Locale;


public enum Language {
    /**
     * French.
     */
    FRANCE (Locale.FRANCE),
    /**
     * Japanese.
     */
    JAPAN (Locale.JAPAN),
    /**
     * English UK.
     */
    UK (Locale.UK),
    /**
     * English US.
     */
    US (Locale.US);

    private final Locale locale;

    Language (final Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale () {
        return this.locale;
    }
}
