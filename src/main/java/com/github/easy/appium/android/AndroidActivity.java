
package com.github.easy.appium.android;

import static com.github.easy.appium.config.enums.AutomationType.UIAUTOMATOR2;
import static com.github.easy.appium.utils.BatteryHealth.check;

import com.github.easy.appium.device.DeviceActivity;
import io.appium.java_client.android.AndroidBatteryInfo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Rabindra Biswal
 * @since 26-Apr-2017 6:19:46 PM
 */
public abstract class AndroidActivity
    extends DeviceActivity<AndroidDriver, AndroidDevice, AndroidTouchAction> {
    private static final Logger LOG = LogManager.getLogger ();

    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since 26-Apr-2017 6:20:08 PM
     */
    protected AndroidActivity (final AndroidDevice device) {
        super (device, new AndroidTouchAction (device.getDriver ()));
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceActivity#onDevice()
     */
    @Override
    public AndroidDeviceActions onDevice () {
        checkBattery ();
        LOG.trace ("Preparing to perform actions on Android device...");
        return new AndroidDeviceActions (this.device);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.github.wasiqb.coteafs.appium.device.DeviceActivity#onElement(java.lang.
     * String)
     */
    @Override
    public AndroidDeviceElementActions onElement (final String name) {
        //checkBattery ();
        LOG.trace ("Preparing to perform actions on Android device element [{}]...", name);
        return new AndroidDeviceElementActions (this.device, name, getElement (name));
    }

    private void checkBattery () {
        if (this.device.getSetting ()
            .getAutomation () == UIAUTOMATOR2 && !this.device.getSetting ()
            .isCloud ()) {
            LOG.trace ("Checking Battery status...");
            final AndroidBatteryInfo battery = this.device.getDriver ()
                .getBatteryInfo ();
            check (battery.getState ()
                .name (), battery.getLevel ());
        }
    }
}