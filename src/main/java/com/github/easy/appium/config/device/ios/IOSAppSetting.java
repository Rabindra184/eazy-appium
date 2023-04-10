
package com.github.easy.appium.config.device.ios;

import static com.github.easy.appium.config.enums.ApplicationType.NATIVE;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import com.github.easy.appium.config.enums.ApplicationType;
import lombok.Data;


@Data
public class IOSAppSetting {
    private String          bundleId      = EMPTY;
    private boolean         external;
    private long            launchTimeout = 20000;
    private String          path = EMPTY;
    private ApplicationType type = NATIVE;
}