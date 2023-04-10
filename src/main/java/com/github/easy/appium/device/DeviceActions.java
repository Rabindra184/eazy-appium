
package com.github.easy.appium.device;

import static com.github.easy.appium.constants.ErrorMessage.SERVER_STOPPED;
import static com.github.easy.appium.utils.ErrorHandler.handleAndThrow;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import com.github.easy.appium.config.device.PlaybackSetting;
import com.github.easy.appium.config.device.ScreenshotSetting;
import com.github.easy.appium.config.enums.Message;
import com.github.easy.appium.config.enums.SwipeDirection;
import com.github.easy.appium.config.enums.SwipeStartPosition;
import com.github.easy.appium.utils.SwipeUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @param <D>
 * @param <E>
 * @param <T>
 *
 * @author Rabindra Biswal
 * @since 26-Apr-2017 8:39:17 PM
 */
public class DeviceActions<D extends AppiumDriver, E extends Device<D, T>, T extends TouchAction<T>> {
    private static final Logger LOG = LogManager.getLogger ();

    protected final E               device;
    protected final D               driver;
    protected final WebDriverWait   wait;
    private final T               actions;
    private final PlaybackSetting setting;

    /**
     * @param device Device instance
     * @param actions Touch actions instance
     *
     * @author Rabindra Biswal
     * @since 26-Apr-2017 8:39:17 PM
     */
    public DeviceActions (final E device, final T actions) {
        this.device = device;
        this.actions = actions;
        this.driver = device.getDriver ();
        this.setting = device.getSetting ()
            .getPlayback ();
        this.wait = new WebDriverWait (this.driver, Duration.ofSeconds (ofSeconds (this.setting.getDelay ()
            .getExplicit ()).getSeconds ()));
    }

    /**
     * @author Rabindra Biswal
     * @since Oct 9, 2017 9:32:56 PM
     */
    public void captureScreenshot () {
        final ScreenshotSetting screenshotSetting = this.setting.getScreenshot ();
        final String path = screenshotSetting.getPath ();
        final String prefix = screenshotSetting.getPrefix ();
        final SimpleDateFormat date = new SimpleDateFormat ("yyyyMMdd-HHmmss");
        final String timeStamp = date.format (Calendar.getInstance ()
            .getTime ());
        final String fileName = "{0}/{1}-{2}.{3}";
        captureScreenshot (format (fileName, path, prefix, timeStamp, "jpeg"));
    }

    /**
     * @param url URL to navigate to.
     *
     * @author Rabindra Biswal
     * @since Jul 15, 2017 5:19:41 PM
     */
    public void navigateTo (final String url) {
        LOG.info ("Navigating to URL [{}]...", url);
        this.driver.get (url);
    }

    /**
     * @param distance Distance percent to pinch
     *
     * @author Rabindra Biswal
     * @since Oct 20, 2017 8:45:31 PM
     */
    public void pinch (final int distance) {
        LOG.info ("Pinching on device screen by [{}]% distance...", distance);
        doubleFingerGesture (SwipeDirection.DOWN, SwipeDirection.UP, SwipeStartPosition.TOP, SwipeStartPosition.BOTTOM,
            distance);
    }

    /**
     * @param source File copy source
     * @param destination File copy destination
     *
     * @author Rabindra Biswal
     * @since Jul 22, 2017 11:03:48 PM
     */
    private static void copyFile (final File source, final String destination) {
        try {
            FileUtils.copyFile (source, new File (destination));
        } catch (final IOException e) {
            LOG.error ("Error occurred while capturing screenshot...");
            LOG.catching (e);
        }
    }

    /**
     * @param direction Swipe direction
     * @param start Finger start position
     * @param distance Distance percent to swipe
     *
     * @author Rabindra Biswal
     * @since Oct 20, 2017 7:52:29 PM
     */
    public void swipe (final SwipeDirection direction, final SwipeStartPosition start, final int distance) {
        LOG.info ("Swiping [{}] on device screen by [{}] perc distance from [{}] of the screen...", direction, distance,
            start);
        swipeTo (null, direction, start, distance).perform ();
    }

    /**
     * @param targetElement Element until swipe needs to be done
     * @param direction Swipe direction
     * @param start Finger start position
     * @param distance Distance percent to swipe
     *
     * @author Wasiq Bhamla
     * @since 21-Mar-2021
     */
    public void swipe (final WebElement targetElement, final SwipeDirection direction, final SwipeStartPosition start,
        final int distance) {
        LOG.info ("Swiping [{}] on device screen by [{}] % distance from [{}] of the screen...", direction, distance,
            start);
        swipeTo (targetElement, direction, start, distance).perform ();
    }

    /**
     * @param distance Distance percent to zoom
     *
     * @author Rabindra Biswal
     * @since Oct 20, 2017 8:44:00 PM
     */
    public void zoom (final int distance) {
        LOG.info ("Zooming in device screen by [{}]% distance...", distance);
        doubleFingerGesture (SwipeDirection.UP, SwipeDirection.DOWN, SwipeStartPosition.CENTER,
            SwipeStartPosition.CENTER, distance);
    }

    private void captureScreenshot (final String path) {
        LOG.info ("Capturing screenshot and saving at [{}]...", path);
        try {
            final File srcFiler = this.driver.getScreenshotAs (OutputType.FILE);
            copyFile (srcFiler, path);
        } catch (final NoSuchSessionException e) {
            handleAndThrow (Message.APPIUM_SERVER_STOPPED_ERROR,e, SERVER_STOPPED);
        }
    }

    private void doubleFingerGesture (final SwipeDirection finger1, final SwipeDirection finger2,
        final SwipeStartPosition start1, final SwipeStartPosition start2, final int distancePercent) {
        final T firstFinger = swipeTo (null, finger1, start1, distancePercent);
        final T secondFinger = swipeTo (null, finger2, start2, distancePercent);
        final MultiTouchAction multiTouch = new MultiTouchAction ((PerformsTouchActions) this.driver);
        multiTouch.add (firstFinger)
            .add (secondFinger)
            .perform ();
    }

    private T swipeTo (final WebElement targetElement, final SwipeDirection direction, final SwipeStartPosition start,
        final int distancePercent) {
        return SwipeUtils.<T>init ()
            .actions (this.actions)
            .setting (this.setting.getDelay ())
            .direction (direction)
            .startPosition (start)
            .targetElement (targetElement)
            .maxSwipe (this.setting.getMaxSwipeCount ())
            .distancePercent (distancePercent)
            .screenSize (this.driver.manage ()
                .window ()
                .getSize ())
            .prepare ()
            .swipeTo ();
    }

}