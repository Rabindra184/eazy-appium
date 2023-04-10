
package com.github.easy.appium.config.device.ios;

import lombok.Data;

/**
 * @author Rabindra Biswal
 * @since Jan 18, 2018 9:11:11 PM
 */
@Data
public class IOSDeviceSetting {
    private IOSAppSetting app = new IOSAppSetting ();
    private boolean       autoAcceptAlerts;
    private WDASetting    wda = new WDASetting ();
    private IOSWebSetting web = new IOSWebSetting ();
}