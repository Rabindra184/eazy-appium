
package com.github.easy.appium.config.device.ios;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.Data;


@Data
public class IOSWebSetting {
    private boolean allowPopups;
    private boolean consoleLogs;
    private String  initialUrl = EMPTY;
    private boolean nativeTaps;
    private boolean networkLogs;
}