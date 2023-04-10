
package com.github.easy.appium.android;


import com.github.easy.appium.config.enums.AutomationType;
import com.github.easy.appium.device.DeviceElementActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * @author Rabindra Biswal
 * @since 02-May-2017 6:31:57 PM
 */
public class AndroidDeviceElementActions
    extends DeviceElementActions<AndroidDriver, AndroidDevice, AndroidTouchAction> {
    private static final Logger log = LogManager.getLogger ();

    /**
     * @param device Device instance
     * @param name Name of element
     * @param element Element under test
     *
     * @author Rabindra Biswal
     * @since 02-May-2017 6:32:14 PM
     */
    public AndroidDeviceElementActions (final AndroidDevice device, final String name, final WebElement element) {
        super (device, name, element, new AndroidTouchAction (device.getDriver ()));
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceElementActions#pinch(int)
     */
    @Override
    public void pinch (final int distance) {
        if (this.device.getSetting ()
            .getAutomation () == AutomationType.ESPRESSO) {
            super.pinch (distance);
        } else {
            log.warn ("Pinch is only available when Automation type is Espresso...");
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceElementActions#verifyThat()
     */
    @Override
    public AndroidElementVerify verifyThat () {
        return new AndroidElementVerify (this);
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceElementActions#zoom(int)
     */
    @Override
    public void zoom (final int distance) {
        if (this.device.getSetting ()
            .getAutomation () == AutomationType.ESPRESSO) {
            super.zoom (distance);
        } else {
            log.warn ("Zoom is only available when Automation type is Espresso...");
        }
    }
}