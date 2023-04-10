package com.github.easy.appium.ios;

import com.github.easy.appium.device.DeviceElementVerify;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;

/**
 * @author Rabindra Biswal
 * @since 20-May-2017 12:29:27 PM
 */
public class IOSElementVerify extends DeviceElementVerify<IOSDriver, IOSDevice, IOSTouchAction> {
    /**
     * @param actions Action instance
     *
     * @author Rabindra Biswal
     * @since 20-May-2017 12:31:51 PM
     */
    public IOSElementVerify (final IOSDeviceElementActions actions) {
        super (actions);
    }
}