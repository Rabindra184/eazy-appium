
package com.github.easy.appium.config.enums;

import io.appium.java_client.remote.MobilePlatform;


public enum PlatformType {
    /**
     * Android Device.
     */
    ANDROID(MobilePlatform.ANDROID),
    /**
     * iOS Device.
     */
    IOS(MobilePlatform.IOS),
    /**
     * Windows Device.
     */
    WINDOWS(MobilePlatform.WINDOWS);

    private final String name;

    PlatformType(final String name) {
        this.name = name;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }
}