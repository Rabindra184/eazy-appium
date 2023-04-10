
package com.github.easy.appium.config.device;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;

import com.github.easy.appium.config.device.android.AndroidVideoSetting;
import com.github.easy.appium.config.device.ios.IOSVideoSetting;
import lombok.Data;


@Data
public class RecordSetting {
    private AndroidVideoSetting android = new AndroidVideoSetting ();
    private boolean         enabled;
    private IOSVideoSetting ios  = new IOSVideoSetting ();
    private String          path = format ("{0}/videos", getProperty ("user.dir"));
    private String              prefix    = "VID";
    private int                 timeLimit = 3;
}