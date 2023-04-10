package com.github.easy.appium.config.device;

import java.util.HashMap;
import java.util.Map;

import com.github.easy.appium.config.device.android.AndroidDeviceSetting;
import com.github.easy.appium.config.device.ios.IOSDeviceSetting;
import com.github.easy.appium.config.enums.AutomationType;
import com.github.easy.appium.config.enums.Browser;
import com.github.easy.appium.config.enums.DeviceType;
import com.github.easy.appium.config.enums.Language;
import com.github.easy.appium.config.enums.PlatformType;
import lombok.Data;

@Data
public class DeviceSetting {
    private AndroidDeviceSetting android;
    private boolean              autoWebView;
    private AutomationType       automation        = AutomationType.APPIUM;
    private Browser              browser;
    private Map<String, String>  cloudCapabilities = new HashMap<> ();
    private boolean              headless;
    private IOSDeviceSetting     ios;
    private Language             language          = Language.US;
    private String               name;
    private PlatformType         os                = PlatformType.ANDROID;
    private OtherSetting         others            = new OtherSetting ();
    private PlaybackSetting      playback          = new PlaybackSetting ();
    private int                  sessionTimeout    = 60;
    private DeviceType           type              = DeviceType.SIMULATOR;
    private String               version;

    /**
     * @return Cloud Capabilities
     *
     */
    public boolean isCloud () {
        return getCloudCapabilities ().size () > 0;
    }
}