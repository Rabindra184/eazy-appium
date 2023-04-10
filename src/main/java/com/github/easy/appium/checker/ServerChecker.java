
package com.github.easy.appium.checker;

import static com.github.easy.appium.config.enums.Message.APPIUM_SERVER_NOT_RUNNING_ERROR;
import static com.github.easy.appium.config.enums.Message.APPUIM_CONFIG_PARAMETER_NOT_FOUND_ERROR;
import static com.github.easy.appium.utils.ErrorHandler.throwError;
import static java.text.MessageFormat.format;

import com.github.easy.appium.service.AppiumServer;

/**
 * @author Rabindra Biswal
 * @since 09-May-2017 3:49:21 PM
 */
public final class ServerChecker {
    /**
     * @param key Config key
     * @param value Config value
     *
     * @author Rabindra Biswal
     * @since 09-May-2017 3:52:30 PM
     */
    public static void checkServerConfigParams (final String key, final Object value) {
        if (value == null) {
            final String msg = "Server Config value for {0} key not set.";
            throwError (APPUIM_CONFIG_PARAMETER_NOT_FOUND_ERROR, format (msg, key));
        }
    }

    /**
     * @param server Server instance
     *
     * @author Rabindra Biswal
     * @since 04-May-2017 4:36:57 PM
     */
    public static void checkServerRunning (final AppiumServer server) {
        System.out.println (server.isRunning ());
        if (!server.isRunning ()) {
            throwError (APPIUM_SERVER_NOT_RUNNING_ERROR, "Server not started yet.");
        }
    }

    private ServerChecker () {
        // Utility class.
    }
}