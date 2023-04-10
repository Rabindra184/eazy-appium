
package com.github.easy.appium.config.device;

import lombok.Data;


@Data
public class OtherSetting {
    private boolean clearFiles;
    private boolean clearLogs;
    private boolean fullReset;
    private boolean noReset;
}