
package com.github.easy.appium.config.device.android;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.Data;


@Data
public class AndroidWebSetting {
    private boolean    acceptSslCerts   = true;
    private String     chromeDriverPath = EMPTY;
    private boolean    nativeScreenshot;
    private WebOptions options          = new WebOptions ();
    private boolean    showBrowserLogs;
}