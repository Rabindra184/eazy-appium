
package com.github.easy.appium.config.enums;

import io.appium.java_client.clipboard.ClipboardContentType;

public enum ClipboardType {
    /**
     * Image.
     */
    IMAGE(ClipboardContentType.IMAGE),
    /**
     * Text.
     */
    TEXT(ClipboardContentType.PLAINTEXT),
    /**
     * URL.
     */
    URL(ClipboardContentType.URL);

    private final ClipboardContentType type;

    ClipboardType(final ClipboardContentType type) {
        this.type = type;
    }

    /**
     * @return the type
     * @author wasiqb
     * @since Nov 2, 2018
     */
    public ClipboardContentType getType() {
        return this.type;
    }
}