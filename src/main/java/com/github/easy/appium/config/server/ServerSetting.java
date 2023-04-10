
package com.github.easy.appium.config.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.easy.appium.config.enums.CloudProviders;
import com.github.easy.appium.config.enums.Protocol;
import lombok.Data;

/**
 * @author Rabindra Biswal
 * @since 12-Apr-2017 8:43:22 PM
 */
@Data
public class ServerSetting {
    private boolean             allowCors;
    private List<String>        allowInsecure;
    private AndroidSetting      android        = new AndroidSetting ();
    private String              appiumPath;
    private String              callbackIp;
    private int                 callbackPort;
    private CloudProviders      cloud;
    private Map<String, String> environments   = new HashMap<> ();
    private boolean             external;
    private String              host;
    private IOSSetting          ios            = new IOSSetting ();
    private LogSetting          logs           = new LogSetting ();
    private String              nodeConfig;
    private String              nodePath;
    private String              password;
    private int                 port;
    private boolean  preLaunch;
    private Protocol protocol = Protocol.HTTP;
    private boolean  relaxedSecurity;
    private boolean             sessionOverride;
    private int                 startUpTimeout = 60000;
    private boolean             strictCapabilities;
    private String              userName;
}