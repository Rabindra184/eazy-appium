
package com.github.easy.appium.config.device.android;

import static io.appium.java_client.android.NetworkSpeed.FULL;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import io.appium.java_client.android.NetworkSpeed;
import lombok.Data;

@Data
public class AvdSetting {
    private String       args          = EMPTY;
    private long         launchTimeout = 120000;
    private String       name          = EMPTY;
    private NetworkSpeed networkSpeed  = FULL;
    private long         readyTimeout  = 120000;
}