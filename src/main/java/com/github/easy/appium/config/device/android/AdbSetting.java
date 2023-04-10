
package com.github.easy.appium.config.device.android;

import lombok.Data;

/**
 * @author Wasiq Bhamla
 * @since Mar 13, 2021
 */
@Data
public class AdbSetting {
    private String host;
    private int    port;
    private long   timeout;
}