
package com.github.easy.appium.config.enums;


public enum Protocol {
    /**
     * HTTP protocol.
     */
    HTTP("http"),
    /**
     * HTTPS protocol.
     */
    HTTPS("https");

    private final String name;

    Protocol(final String name) {
        this.name = name;
    }

    /**
     * @return the protocol name
     * @author wasiqb
     * @since Sep 29, 2018
     */
    public String getName() {
        return this.name;
    }
}