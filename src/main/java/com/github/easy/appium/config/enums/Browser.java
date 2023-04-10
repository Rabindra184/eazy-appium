
package com.github.easy.appium.config.enums;

import io.appium.java_client.remote.MobileBrowserType;

/**
 * @author Rabindra Biswal
 * @since Jul 15, 2017 4:50:08 PM
 */
public enum Browser {
    /**
     * System browser.
     */
    BROWSER(MobileBrowserType.BROWSER),
    /**
     * Chrome browser.
     */
    CHROME(MobileBrowserType.CHROME),
    /**
     * Chromiuim browser.
     */
    CHROMIUM(MobileBrowserType.CHROMIUM),
    /**
     * Safari browser.
     */
    SAFARI(MobileBrowserType.SAFARI);

    private final String name;

    Browser(final String name) {
        this.name = name;
    }

    /**
     * @return the browser
     * @author Rabindra Biswal
     * @since Jul 15, 2017 5:04:12 PM
     */
    @Override
    public String toString() {
        return this.name;
    }
}