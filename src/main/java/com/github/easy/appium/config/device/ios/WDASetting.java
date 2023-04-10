
package com.github.easy.appium.config.device.ios;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import lombok.Data;


@Data
public class WDASetting {
    private String  agentPath            = EMPTY;
    private String  bootstrapPath        = EMPTY;
    private long    connectionTimeout;
    private long    launchTimeout        = 60000;
    private int     localPort            = 8100;
    private String  signingId            = EMPTY;
    private int     startupRetries       = 2;
    private long    startupRetryInterval = 10000;
    private String  teamId               = EMPTY;
    private String  updateBundleId       = EMPTY;
    private boolean useNew;
    private boolean usePrebuilt;
}