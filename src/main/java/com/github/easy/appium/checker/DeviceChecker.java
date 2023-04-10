
package com.github.easy.appium.checker;
import static com.github.easy.appium.utils.ErrorHandler.throwError;
import static java.text.MessageFormat.format;

import com.github.easy.appium.config.enums.Message;
import org.openqa.selenium.WebElement;

/**
 * @author Rabindra Biswal
 * @since 04-May-2017 4:30:02 PM
 */
public final class DeviceChecker {
    /**
     * @param key Capability key
     * @param value Capability value
     *
     * @author Rabindra Biswal
     * @since 12-May-2017 7:31:31 PM
     */
    public static void checkCapabilitiesParams (final String key, final Object value) {
        if (value == null) {
            final String msg = "Device Desired Capabilities value for [{0}] key not set.";
            throwError (Message.DEVICE_DESIRED_CAPABILITIES_NOT_SET_ERROR ,format (msg, key));
        }
    }

    /**
     * @param element Element under test
     * @param name Name of element
     *
     * @author Rabindra Biswal
     * @since 04-May-2017 10:10:28 PM
     */
    public static void checkDeviceElementDisplayed (final WebElement element, final String name) {
        if (!element.isDisplayed ()) {
            final String msg = "Device element [{0}] is not displayed.";
            throwError (Message.DEVICE_ELEMENT_NOT_DISPLAYED_ERROR, format (msg, name));
        }
    }

    /**
     * @param element Element under test
     * @param name Name of element
     *
     * @author Rabindra Biswal
     * @since 04-May-2017 11:08:59 PM
     */
    public static void checkDeviceElementEnabled (final WebElement element, final String name) {
        if (!element.isEnabled ()) {
            final String msg = "Device element [{0}] is disabled.";
            throwError (Message.DEVICE_ELEMENT_DISABLED_ERROR, format (msg, name));
        }
    }

    private DeviceChecker () {
        // Utility class.
    }
}