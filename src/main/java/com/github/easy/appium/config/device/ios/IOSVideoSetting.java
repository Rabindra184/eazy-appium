
package com.github.easy.appium.config.device.ios;

import static io.appium.java_client.ios.IOSStartScreenRecordingOptions.VideoQuality.MEDIUM;

import io.appium.java_client.ios.IOSStartScreenRecordingOptions.VideoQuality;
import lombok.Data;


@Data
public class IOSVideoSetting {
    private String       codec   = "mpeg4";
    private int          fps     = 10;
    private VideoQuality quality = MEDIUM;
}