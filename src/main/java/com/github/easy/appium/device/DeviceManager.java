package com.github.easy.appium.device;

import static com.github.easy.appium.config.enums.PlatformType.ANDROID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.easy.appium.android.AndroidDevice;
import com.github.easy.appium.config.AppiumSetting;
import com.github.easy.appium.config.device.DeviceSetting;
import com.github.easy.appium.ios.IOSDevice;
import com.github.easy.appium.service.AppiumServer;
import com.github.parse.DataSource;

public class DeviceManager {

    private static final List<AndroidDevice> androidDevices = new ArrayList<> ();
    private static final List<Device>    devices    = new ArrayList<> ();
    private static final List<IOSDevice> iosDevices = new ArrayList<> ();

    public static List<AndroidDevice> getAndroidDevices () {
        return androidDevices;
    }

    public static Device getDevice (int device) {
        return getDevices ().get (device);
    }

    public static List<Device> getDevices () {
        AppiumServer server;
        AndroidDevice androidDevice;
        IOSDevice iosDevice;
        if (devices.isEmpty ()) {
            for (Map.Entry<String, DeviceSetting> entry : DataSource.parse (AppiumSetting.class)
                .getDevices ()
                .entrySet ()) {
                System.out.println (entry.getValue ()
                    .getOs ());
                if (entry.getValue ()
                    .getOs ()
                    .equals (ANDROID)) {
                    server = new AppiumServer ("android");
                    server.start ();
                    System.out.println ("..............");
                    System.out.println (server.isRunning ());
                    System.out.println (server.getServiceUrl ());
                    System.out.println ("..............");
                    androidDevice = new AndroidDevice (server, entry.getKey ());
                    androidDevice.start ();
                    androidDevices.add (androidDevice);
                    devices.add (androidDevice);
                } else {
                    server = new AppiumServer ("ios");
                    server.start ();
                    iosDevice = new IOSDevice (server, entry.getKey ());
                    iosDevice.start ();
                    iosDevices.add (iosDevice);
                    devices.add (iosDevice);

                }
            }
        }
        return devices;
    }

    public static List<IOSDevice> getIosDevices () {
        return iosDevices;
    }

    public static void main (String[] args) {
        System.out.println (getDevice (1));
    }

    private DeviceManager () {
    }
}






