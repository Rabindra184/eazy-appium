
package com.github.easy.appium.config.device;

import com.github.easy.appium.config.enums.VideoQuality;
import lombok.Data;


@Data
public class VideoStreamSetting {
    private int          bitRate = 20;
    private boolean      enabled;
    private int          height  = 1024;
    private String       host    = "127.0.0.1";
    private int          port    = 8093;
    private VideoQuality quality = VideoQuality.MEDIUM;
    private int          width   = 700;
}