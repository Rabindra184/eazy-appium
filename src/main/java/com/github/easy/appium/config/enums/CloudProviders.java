
package com.github.easy.appium.config.enums;


public enum CloudProviders {
    /**
     * Cloud Provider URL
     *
     * @since Mar 13, 2021
     */
    BROWSERSTACK ("hub-cloud.browserstack.com");

    private final String url;

    CloudProviders (final String url) {
        this.url = url;
    }

    /**
     * @return Server URL
     */
    public String getUrl () {
        return this.url;
    }
}