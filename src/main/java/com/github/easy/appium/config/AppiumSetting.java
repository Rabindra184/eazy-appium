
package com.github.easy.appium.config;
import java.util.Map;

import com.github.easy.appium.config.device.DeviceSetting;
import com.github.easy.appium.config.server.ServerSetting;
import com.github.parse.annotation.DataFile;
import lombok.Data;

/**
 * @author Rabindra Biswal
 * @since 12-Apr-2017 8:58:57 PM
 */
@Data
@DataFile (fileName = "appium-config.yaml")
public class AppiumSetting {
    private DeviceSetting              device;
    private Map<String, DeviceSetting> devices;
    private ServerSetting              server;
    private Map<String, ServerSetting> servers;

    /**
     * @param key Device key
     *
     * @return the device
     *
     * @author Rabindra Biswal
     * @since 12-Apr-2017 9:00:16 PM
     */
    public DeviceSetting getDevice (final String key) {
        return this.devices.get (key);
    }

    /**
     * @param key Server key
     *
     * @return the server
     *
     * @author Rabindra Biswal
     * @since 12-Apr-2017 9:00:16 PM
     */
    public ServerSetting getServer (final String key) {
        return this.servers.get (key);
    }
}