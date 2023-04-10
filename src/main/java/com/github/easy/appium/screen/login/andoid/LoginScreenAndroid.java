package com.github.easy.appium.screen.login.andoid;

import static com.github.easy.appium.config.enums.WaitStrategy.VISIBLE;
import static com.github.easy.appium.device.DeviceElement.create;
import static io.appium.java_client.AppiumBy.accessibilityId;

import com.github.easy.appium.android.AndroidActivity;
import com.github.easy.appium.android.AndroidDevice;
import com.github.easy.appium.device.DeviceElement;
import com.github.easy.appium.screen.login.LoginScreen;

public class LoginScreenAndroid extends AndroidActivity implements LoginScreen {

    public LoginScreenAndroid (AndroidDevice device) {
        super (device);
        System.out.println (device.getServer ()
            .isRunning ());
    }

    @Override
    protected DeviceElement prepare () {
        final DeviceElement page = create ("Login Form").forAndroid (accessibilityId ("test-Login"));

        create (USER_NAME).parent (page)
            .waitStrategy (VISIBLE)
            .forAndroid (accessibilityId ("test-Username"));
        create (PASSWORD).parent (page)
            .waitStrategy (VISIBLE)
            .forAndroid (accessibilityId ("test-Password"));
        create (LOGIN_BTN).parent (page)
            .waitStrategy (VISIBLE)
            .forAndroid (accessibilityId ("test-LOGIN"));
        return page;
    }

    @Override
    public void login () {
        this.onElement (USER_NAME)
            .enterText ("standard_user");
        this.onElement (PASSWORD)
            .enterText ("secret_sauce");
        this.onElement (LOGIN_BTN)
            .click ();

    }
}