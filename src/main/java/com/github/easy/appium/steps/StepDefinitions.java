package com.github.easy.appium.steps;
import java.util.List;

import com.github.easy.appium.android.AndroidDevice;
import com.github.easy.appium.device.Device;
import com.github.easy.appium.device.DeviceManager;
import com.github.easy.appium.ios.IOSDevice;
import com.github.easy.appium.screen.login.LoginScreen;
import com.github.easy.appium.screen.login.andoid.LoginScreenAndroid;
import com.github.easy.appium.screen.login.ios.LoginScreenIos;
import io.cucumber.java.Scenario;

public abstract class StepDefinitions {

    private Scenario scenario;

    public LoginScreen getLoginScreen (int index) {

        if (getDevice (index) instanceof AndroidDevice) {
            return new LoginScreenAndroid (getAndroidDevice (index));
        } else {
            return new LoginScreenIos (getIosDevice (index));
        }
    }

    public LoginScreen getLoginScreen () {

        if (getDevice (1) instanceof AndroidDevice) {
            System.out.println (getAndroidDevice (1).getServer ().isRunning ());
            return new LoginScreenAndroid (getAndroidDevice (1));
        } else {
            return new LoginScreenIos (getIosDevice (1));
        }
    }

    protected abstract void before (Scenario scenario);

    protected void setScenario (Scenario scenario) {
        this.scenario = scenario;
    }

    private AndroidDevice getAndroidDevice (int device) {
        return DeviceManager.getAndroidDevices ()
            .get (device - 1);
    }

    private Device getDevice (int device) {
        return DeviceManager.getDevice (device - 1);
    }

    private List<? extends Device> getDevices () {
        return DeviceManager.getDevices ();

    }

    private IOSDevice getIosDevice (int device) {

        return DeviceManager.getIosDevices ()
            .get (device - 1);
    }
}
