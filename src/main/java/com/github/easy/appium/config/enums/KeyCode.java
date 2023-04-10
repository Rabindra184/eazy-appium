
package com.github.easy.appium.config.enums;

import io.appium.java_client.android.nativekey.AndroidKey;



public enum KeyCode {
    /**
     * Home key
     */
    HOME (AndroidKey.HOME),
    /**
     * Power key
     */
    POWER (AndroidKey.POWER),
    /**
     * Volume up key
     */
    VOLUME_UP (AndroidKey.VOLUME_UP),
    /**
     * Volume down key
     */
    VOLUME_DOWN (AndroidKey.VOLUME_DOWN),
    /**
     * Back key
     */
    BACK (AndroidKey.BACK),
    /**
     * Enter key
     */
    ENTER (AndroidKey.ENTER);

    private final AndroidKey key;

    KeyCode (final AndroidKey key) {
        this.key = key;
    }

    public AndroidKey getKey () {
        return this.key;
    }
}