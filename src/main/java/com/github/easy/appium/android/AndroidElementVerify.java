
package com.github.easy.appium.android;

import com.github.easy.appium.device.DeviceElementVerify;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;


/**
 * @author Rabindra Biswal
 * @since 20-May-2017 12:26:00 PM
 */
public class AndroidElementVerify
    extends DeviceElementVerify<AndroidDriver, AndroidDevice, AndroidTouchAction> {
    /**
     * @param actions Actions instance
     *
     * @author Rabindra Biswal
     * @since 20-May-2017 12:26:38 PM
     */
    public AndroidElementVerify (final AndroidDeviceElementActions actions) {
        super (actions);
    }
}