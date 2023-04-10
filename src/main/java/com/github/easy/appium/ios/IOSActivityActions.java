
package com.github.easy.appium.ios;

import com.github.easy.appium.device.DeviceActivityActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;

/**
 * @author Rabindra Biswal
 * @since Oct 23, 2017 11:06:13 PM
 */
public abstract class IOSActivityActions
    extends DeviceActivityActions<IOSDriver, IOSDevice, IOSTouchAction> {
    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since Oct 23, 2017 11:06:33 PM
     */
    protected IOSActivityActions (final IOSDevice device) {
        super (device);
    }
}