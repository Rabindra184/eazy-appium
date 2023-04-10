
package com.github.easy.appium.config.device.android;
import static com.github.easy.appium.config.enums.ApplicationType.NATIVE;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.List;

import com.github.easy.appium.config.enums.ApplicationType;
import lombok.Data;

@Data
public class AndroidAppSetting {
    private String          activityName   = EMPTY;
    private boolean         external;
    private boolean         grantPermission;
    private boolean         ignoreUnimportantViews;
    private long            installTimeout = 90000;
    private boolean         noStopOnReset;
    private List<String>    otherApps;
    private String          packageName    = EMPTY;
    private String          path         = EMPTY;
    private ApplicationType type         = NATIVE;
    private String          waitActivity = EMPTY;
    private String          waitPackage    = EMPTY;
    private long            waitTimeout    = 20000;
}