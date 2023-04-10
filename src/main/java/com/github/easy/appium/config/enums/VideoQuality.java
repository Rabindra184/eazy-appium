
package com.github.easy.appium.config.enums;


public enum VideoQuality {
    HD (100),
    HIGH (75),
    LOW (25),
    MEDIUM (50);

    private final int quality;

    VideoQuality (final int quality) {
        this.quality = quality;
    }

    /**
     * @return video quality
     */
    public int getQuality () {
        return this.quality;
    }
}