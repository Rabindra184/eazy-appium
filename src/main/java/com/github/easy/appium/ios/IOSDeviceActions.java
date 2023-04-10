
package com.github.easy.appium.ios;

import static com.github.easy.appium.constants.ErrorMessage.SERVER_STOPPED;
import static com.github.easy.appium.utils.ErrorHandler.handleAndThrow;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.github.easy.appium.config.enums.ClipboardType;
import com.github.easy.appium.config.enums.Message;
import com.github.easy.appium.config.enums.SwipeDirection;
import com.github.easy.appium.device.DeviceActions;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.TimeoutException;

/**
 * @author Rabindra Biswal
 * @since 26-Apr-2017 11:34:39 PM
 */
public class IOSDeviceActions extends DeviceActions<IOSDriver, IOSDevice, IOSTouchAction> {
    private static final Logger LOG = LogManager.getLogger ();

    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since 26-Apr-2017 11:34:58 PM
     */
    public IOSDeviceActions (final IOSDevice device) {
        super (device, new IOSTouchAction (device.getDriver ()));
    }

    /**
     * @return clipboard text
     *
     * @author wasiqb
     * @since Nov 2, 2018
     */
    public String clipboard () {
        LOG.info ("Getting clipboard text...");
        return this.driver.getClipboardText ();
    }

    /**
     * @param type Getting clipboard for type
     *
     * @return clipboard
     *
     * @author wasiqb
     * @since Nov 2, 2018
     */
    public String clipboard (final ClipboardType type) {
        LOG.info ("Getting clipboard [{}]...", type);
        return this.driver.getClipboard (type.getType ());
    }

    /**
     * @param img Image to save on clipboard
     *
     * @author Faisal Khatri
     * @since Mar 13, 2021
     */
    public void clipboard (final BufferedImage img) {
        LOG.info ("Setting clipboard image...");
        try {
            this.driver.setClipboardImage (img);
        } catch (final IOException e) {
            LOG.warn ("Error while setting clipboard image....");
            LOG.warn (e.getMessage ());
        }
    }

    /**
     * @param text Text to save in Clipboard
     *
     * @author Faisal Khatri
     * @since Mar 13, 2021
     */
    public void clipboard (final String text) {
        LOG.trace ("Setting clipboard text to [{}]...", text);
        this.driver.setClipboardText (text);
    }

    /**
     * @param url Navigation URL
     *
     * @author Faisal Khatri
     * @since Mar 13, 2021
     */
    public void clipboard (final URL url) {
        LOG.trace ("Setting clipboard URL to [{}]...", url);
        this.driver.setClipboardUrl (url);
    }

    /**
     * @return message
     *
     * @author Rabindra Biswal
     * @since 09-May-2017 8:46:51 PM
     */
    public String handleAlert () {
        LOG.trace ("Handling iOS Alert pop-up...");
        try {
            final Alert alert = this.wait.until (d -> d.switchTo ()
                .alert ());
            final String description = alert.getText ();
            LOG.info ("Alert Text: [{}]", description);
            alert.accept ();
            return description;
        } catch (final TimeoutException e) {
            LOG.warn ("Expecting Alert not displayed...");
            LOG.warn (e.getMessage ());
        } catch (final NoSuchSessionException e) {
            handleAndThrow (Message.APPIUM_SERVER_STOPPED_ERROR,e, SERVER_STOPPED);
        }
        return null;
    }

    /**
     * @param strategy Hide keyboard strategy
     * @param keyName Key name to press for hiding keyboard
     *
     * @author Rabindra Biswal
     * @since 08-May-2017 3:21:20 PM
     */
    public void hideKeyboard (final String strategy, final String keyName) {
        LOG.info ("Hiding keyboard on device using {} strategy for key {}...", strategy, keyName);
        try {
            if (this.driver.isKeyboardShown ()) {
                this.driver.hideKeyboard (strategy, keyName);
            }
        } catch (final NoSuchSessionException e) {
            handleAndThrow (Message.APPIUM_SERVER_STOPPED_ERROR,e, SERVER_STOPPED);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceActions#pinch(int)
     */
    @Override
    public void pinch (final int distance) {
        LOG.info ("Pinching on device screen by [{}] distance...", distance);
        final Map<String, Object> param = new HashMap<> ();
        param.put ("scale", 0.5);
        param.put ("velocity", distance);
        this.device.executeCommand ("mobile: pinch", param);
    }

    /**
     * @param devicePath Path on device
     * @param filePath File path to put on device
     *
     * @author Wasiq Bhamla
     * @since 19-Mar-2021
     */
    public void pushFile (final String devicePath, final String filePath) {
        LOG.info ("Pushing file to Android device...");
        try {
            this.driver.pushFile (devicePath, new File (filePath));
        } catch (final IOException e) {
            LOG.error ("Error while pushing file to device...");
            LOG.catching (e);
        }
    }

    /**
     * @author Rabindra Biswal
     * @since 26-Apr-2017 11:37:04 PM
     */
    public void shake () {
        LOG.info ("Shaking the device...");
        try {
            this.driver.shake ();
        } catch (final NoSuchSessionException e) {
            handleAndThrow (Message.APPIUM_SERVER_STOPPED_ERROR,e, SERVER_STOPPED);
        }
    }

    /**
     * @param direction Swipe direction
     *
     * @author wasiqb
     * @since Oct 28, 2018
     */
    public void swipe (final SwipeDirection direction) {
        LOG.info ("Swiping [{}] on device screen...", direction);
        final Map<String, Object> param = new HashMap<> ();
        param.put ("direction", direction.name ()
            .toLowerCase ());
        this.device.executeCommand ("mobile: swipe", param);
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.DeviceActions#zoom(int)
     */
    @Override
    public void zoom (final int distance) {
        LOG.info ("Zooming in device screen by [{}] distance...", distance);
        final Map<String, Object> param = new HashMap<> ();
        param.put ("scale", 1.5);
        param.put ("velocity", distance);
        this.device.executeCommand ("mobile: pinch", param);
    }

    /**
     * @param devicePath File device path to pull
     *
     * @return Base64 decoded content of the file.
     *
     * @author Wasiq Bhamla
     * @since 19-Mar-2021
     */
    public byte[] pullFile (final String devicePath) {
        LOG.info ("Pulling file [{}] from Android device...", devicePath);
        return this.driver.pullFile (devicePath);
    }

    /**
     * @param devicePath Folder device path to pull
     *
     * @return Base64 decoded content of folder path.
     *
     * @author Wasiq Bhamla
     * @since 19-Mar-2021
     */
    public byte[] pullFolder (final String devicePath) {
        LOG.info ("Pulling folder [{}] from Android device...", devicePath);
        return this.driver.pullFolder (devicePath);
    }

    /**
     * @param type Screen orientation type

     * @since Oct 20, 2018
     */
    public void rotate (final ScreenOrientation type) {
        LOG.info ("Rotating device screen as [{}]...", type);
        this.driver.rotate (type);
    }

    /**
     * @return rotation
     *
     * @since Oct 20, 2018
     */
    public ScreenOrientation rotation () {
        LOG.info ("Getting rotation type for device...");
        return this.driver.getOrientation ();
    }
}