
package com.github.easy.appium.ios;


import static com.github.easy.appium.utils.BatteryHealth.check;

import com.github.easy.appium.config.enums.DeviceType;
import com.github.easy.appium.device.DeviceActivity;
import io.appium.java_client.ios.IOSBatteryInfo;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Rabindra Biswal
 * @since 26-Apr-2017 7:41:49 PM
 */
public abstract class IOSActivity extends DeviceActivity<IOSDriver, IOSDevice, IOSTouchAction> {
    private static final Logger log = LogManager.getLogger ();

    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since 26-Apr-2017 7:42:13 PM
     */
    protected IOSActivity (final IOSDevice device) {
        super (device, new IOSTouchAction (device.getDriver ()));
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceActivity#onDevice()
     */
    @Override
    public IOSDeviceActions onDevice () {
        checkBattery ();
        log.trace ("Preparing to perform actions on iOS device...");
        return new IOSDeviceActions (this.device);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.wasiqb.coteafs.appium.device.DeviceActivity#onElement(java.lang.
     * String)
     */
    @Override
    public IOSDeviceElementActions onElement (final String name) {
        checkBattery ();
        log.trace ("Preparing to perform actions on iOS device element [{}]...", name);
        return new IOSDeviceElementActions (this.device, name, getElement (name));
    }

    private void checkBattery () {
        final IOSBatteryInfo battery = this.device.getDriver ()
            .getBatteryInfo ();
        if (!this.device.getSetting ()
            .isCloud () && this.device.getSetting ()
            .getType () == DeviceType.REAL) {
            check (battery.getState ()
                .name (), battery.getLevel ());
        }
    }
}