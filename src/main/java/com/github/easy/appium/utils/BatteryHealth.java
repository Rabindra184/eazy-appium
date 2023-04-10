
package com.github.easy.appium.utils;

import static com.github.easy.appium.utils.ErrorHandler.throwError;

import com.github.easy.appium.config.enums.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author wasiqb
 * @since Oct 2, 2018
 */
public final class BatteryHealth {
    private static final Logger log = LogManager.getLogger ();

    /**
     * @param state Battery state
     * @param level Battery level
     *
     * @author wasiqb
     * @since Oct 2, 2018
     */
    public static void check (final String state, final double level) {
        log.trace ("Current Battery status is [{}] with charge level as [{}%]...", state, level * 100);
        if (!state.equals ("CHARGING") && !state.equals ("FULL") && level < 0.2) {
            throwError (Message.NOT_ENOUGH_BATTERY_CHARGE_ERROR,
                "Battery does not have enough charging, to continue, put your device on USB...");
        }
    }

    private BatteryHealth () {
        // Utility class.
    }
}