
package com.github.easy.appium.config.device.android;

import com.github.easy.appium.config.enums.UnlockType;
import lombok.Data;

/**
 * @author Rabindra Biswal
 * @since Jan 18, 2018 9:05:52 PM
 */
@Data
public class AndroidDeviceSetting {
    private AdbSetting        adb = new AdbSetting ();
    private AndroidAppSetting app = new AndroidAppSetting ();
    private AvdSetting        avd = new AvdSetting ();
    private boolean           disableAnimation;
    private boolean           skipDeviceInit;
    private boolean           skipServerInstall;
    private boolean           skipUnlock;
    private int               systemPort;
    private String            unlockKey;
    private UnlockType        unlockType;
    private AndroidWebSetting web = new AndroidWebSetting ();
}