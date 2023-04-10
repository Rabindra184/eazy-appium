package com.github.easy.appium.screen.login.ios;

import static com.github.easy.appium.config.enums.WaitStrategy.VISIBLE;
import static com.github.easy.appium.device.DeviceElement.create;
import static io.appium.java_client.AppiumBy.accessibilityId;

import com.github.easy.appium.device.DeviceElement;
import com.github.easy.appium.ios.IOSActivity;
import com.github.easy.appium.ios.IOSDevice;
import com.github.easy.appium.screen.login.LoginScreen;
import org.openqa.selenium.By;

public class LoginScreenIos extends IOSActivity implements LoginScreen {
    public LoginScreenIos (final IOSDevice device) {
        super (device);
    }

    @Override
    protected DeviceElement prepare () {
        final DeviceElement page = create ("Login Form").forIos (accessibilityId ("test-Login"));

        create (USER_NAME).parent (page)
            .waitStrategy (VISIBLE)
            .forIos (By.xpath ("//XCUIElementTypeTextField[@name='test-Username']"));
        create (PASSWORD).parent (page)
            .waitStrategy (VISIBLE)
            .forIos (By.xpath ("//XCUIElementTypeSecureTextField[@name='test-Password']"));
        create (LOGIN_BTN).parent (page)
            .waitStrategy (VISIBLE)
            .forIos (By.xpath ("//XCUIElementTypeOther[@name='test-LOGIN']"));

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

