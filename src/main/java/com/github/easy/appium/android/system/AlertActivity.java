package com.github.easy.appium.android.system;

import com.github.easy.appium.android.AndroidActivity;
import com.github.easy.appium.android.AndroidDevice;
import com.github.easy.appium.device.DeviceElement;
import org.openqa.selenium.By;

/**
 * @author Rabindra Biswal
 * @since Feb 8, 2018 3:44:36 PM
 */

public class AlertActivity extends AndroidActivity {
    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since Feb 8, 2018 3:44:36 PM
     */
    public AlertActivity (final AndroidDevice device) {
        super (device);
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceActivity#prepare()
     */
    @Override
    protected DeviceElement prepare () {
        final DeviceElement alert = DeviceElement.create ("Alert")
            .forAndroid (By.id ("android:id/parentPanel"));

        DeviceElement.create ("Title")
            .parent (alert)
            .forAndroid (By.id ("android:id/alertTitle"));
        DeviceElement.create ("Message")
            .parent (alert)
            .forAndroid (By.id ("android:id/message"));
        DeviceElement.create ("OK")
            .parent (alert)
            .forAndroid (By.id ("android:id/button1"));

        return alert;
    }
}