
package com.github.easy.appium.android.system;
import com.github.easy.appium.android.AndroidActivity;
import com.github.easy.appium.android.AndroidDevice;
import com.github.easy.appium.device.DeviceElement;
import org.openqa.selenium.By;

/**
 * @author Rabindra Biswal
 * @since 10-May-2017 7:53:52 PM
 */
public class PermissionActivity extends AndroidActivity {
    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since 10-May-2017 7:53:57 PM
     */
    public PermissionActivity (final AndroidDevice device) {
        super (device);
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceActivity#build()
     */
    @Override
    protected DeviceElement prepare () {
        final DeviceElement container = DeviceElement.create ("Permission Window")
            .forAndroid (By.id ("com.android.packageinstaller:id/dialog_container"));
        DeviceElement.create ("Message")
            .parent (container)
            .forAndroid (By.id ("com.android.packageinstaller:id/permission_message"));
        DeviceElement.create ("Allow")
            .parent (container)
            .forAndroid (By.id ("com.android.packageinstaller:id/permission_allow_button"));
        DeviceElement.create ("Deny")
            .parent (container)
            .forAndroid (By.id ("com.android.packageinstaller:id/permission_deny_button"));
        return container;
    }
}