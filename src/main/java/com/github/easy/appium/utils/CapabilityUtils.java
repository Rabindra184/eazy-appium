
package com.github.easy.appium.utils;


import com.github.easy.appium.checker.DeviceChecker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;


public final class CapabilityUtils {
    private static final Logger log = LogManager.getLogger ();

    public static <T> void setCapability (final String key, final T value, final DesiredCapabilities capabilities) {
        setCapability (key, value, null, capabilities);
    }

    /**
     * @param key Capability key
     * @param value Capability value
     * @param defaultValue Capability default value
     * @param capabilities Capabilities
     *
     * @author Rabindra Biswal
     * @since 12-May-2017 9:54:46 PM
     */
    public static <T> void setCapability (final String key, final T value, final T defaultValue,
        final DesiredCapabilities capabilities) {
        setCapability (key, value, defaultValue, capabilities, false);
    }

    /**
     * @param key Capability key
     * @param value Capability value
     * @param defaultValue Capability default value
     * @param capabilities Capabilities
     * @param mandatory Is mandatory
     *
     * @author Rabindra Biswal
     * @since 08-May-2017 7:53:28 PM
     */
    public static <T> void setCapability (final String key, final T value, final T defaultValue,
        final DesiredCapabilities capabilities, final boolean mandatory) {
        if (mandatory) {
            DeviceChecker.checkCapabilitiesParams (key, value);
        }
        if (value != null && value != defaultValue) {
            if (value instanceof Integer && (Integer) value == 0) {
                return;
            }
            log.trace ("Setting capability [key: {}, value: {}]...", key, value);
            capabilities.setCapability (key, value);
        }
    }


    private CapabilityUtils () {
        // Utility class.
    }
}