
package com.github.easy.appium.android;

import com.github.easy.appium.device.DeviceActivityActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;


public abstract class AndroidActivityActions
    extends DeviceActivityActions<AndroidDriver, AndroidDevice, AndroidTouchAction> {
    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since Oct 23, 2017 11:05:14 PM
     */
    protected AndroidActivityActions (final AndroidDevice device) {
        super (device);
    }
}