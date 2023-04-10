
package com.github.easy.appium.config.enums;

import io.appium.java_client.remote.AutomationName;

/**
 * @author Rabindra Biswal
 * @since 24-Apr-2017 9:11:30 PM
 */
public enum AutomationType {
    /**
     * Appium.
     */
    APPIUM(AutomationName.APPIUM),
    /**
     * Espresso.
     */
    ESPRESSO(AutomationName.ESPRESSO),
    /**
     * Android UIAutomator2.
     */
    UIAUTOMATOR2(AutomationName.ANDROID_UIAUTOMATOR2),
    /**
     * XCUITest.
     */
    XCUI(AutomationName.IOS_XCUI_TEST);

    private final String name;

    AutomationType(final String name) {
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