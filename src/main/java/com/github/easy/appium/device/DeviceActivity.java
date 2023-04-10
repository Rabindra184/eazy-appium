
package com.github.easy.appium.device;

import static com.github.easy.appium.utils.ErrorHandler.handleAndThrow;
import static com.github.easy.appium.utils.ErrorHandler.throwError;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.github.easy.appium.checker.ServerChecker;
import com.github.easy.appium.config.device.DeviceSetting;
import com.github.easy.appium.config.device.PlaybackSetting;
import com.github.easy.appium.config.enums.AutomationType;
import com.github.easy.appium.config.enums.Message;
import com.github.easy.appium.config.enums.PlatformType;
import com.github.easy.appium.config.enums.WaitStrategy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @param <D>
 * @param <E>
 * @param <T>
 *
 * @author Rabindra Biswal
 * @since 26-Apr-2017 4:31:24 PM
 */
public abstract class DeviceActivity<D extends AppiumDriver, E extends Device<D, T>, T extends TouchAction<T>> {
    private static final Logger log = LogManager.getLogger ();

    protected final AutomationType automation;
    protected final E              device;
    protected final Map<String, DeviceElement> deviceElements;
    protected final PlatformType    platform;
    private final   PlaybackSetting playSetting;
    private final   T               touch;
    private final   WebDriverWait              wait;

    /**
     * @param device Device instance
     * @param touch Action instance
     *
     * @author Rabindra Biswal
     * @since 26-Apr-2017 4:32:45 PM
     */
    protected DeviceActivity (final E device, final T touch) {
        this.device = device;
        this.touch = touch;
        this.deviceElements = new HashMap<> ();
        final DeviceSetting deviceSetting = device.getSetting ();
        this.automation = deviceSetting.getAutomation ();
        this.platform = deviceSetting.getOs ();
        this.playSetting = deviceSetting.getPlayback ();
        this.wait = new WebDriverWait (device.getDriver (), Duration.ofSeconds (ofSeconds (this.playSetting.getDelay ()
            .getExplicit ()).getSeconds ()));
    }

    /**
     * @param name Element name
     *
     * @return element
     *
     * @author Rabindra Biswal
     * @since Feb 2, 2018 1:44:52 PM
     */
    public WebElement getElement (final String name) {
        load ();
        log.trace ("Getting element with name [{}]...", name);
        return findElements (getDeviceElement (name));
    }

    /**
     * @return device actions
     *
     * @author Rabindra Biswal
     * @since 26-Apr-2017 8:41:07 PM
     */
    public DeviceActions<D, E, T> onDevice () {
        this.device.checkServerRunning ();
        log.info ("Preparing to perform actions on device...");
        return new DeviceActions<> (this.device, this.touch);
    }

    /**
     * @param name Element name
     *
     * @return element actions
     *
     * @author Rabindra Biswal
     * @since 26-Apr-2017 6:45:09 PM
     */
    public DeviceElementActions<D, E, T> onElement (final String name) {
        ServerChecker.checkServerRunning (this.device.server);
        log.trace ("Preparing to perform actions on device element [{}]...", name);
        return new DeviceElementActions<> (this.device, name, getElement (name), this.touch);
    }

    /**
     * @param name Element name
     * @param index Element index
     *
     * @return actions
     *
     * @author Rabindra Biswal
     * @since Jul 5, 2017 6:55:54 AM
     */
    public DeviceElementActions<D, E, T> onElement (final String name, final int index) {

        ServerChecker.checkServerRunning (this.device.server);
        log.trace ("Preparing to perform actions on dynamic device element [{}] on index [{}]...", name, index);
        final DeviceElement e = getDeviceElement (name);
        final DeviceElement element = Objects.requireNonNull (e, "Element not found.")
            .index (index);
        return new DeviceElementActions<> (this.device, name, findElements (element), this.touch);
    }

    /**
     * @return element
     *
     * @author Rabindra Biswal
     * @since 02-May-2017 4:38:00 PM
     */
    protected abstract DeviceElement prepare ();

    private void captureScreenshotOnError () {
        if (this.playSetting.getScreenshot ()
            .isOnError ()) {
            onDevice ().captureScreenshot ();
        }
    }

    private WebElement find (final D deviceDriver, final DeviceElement parent, final By locator, final int index,
        final WaitStrategy strategy) {
        try {
            wait (locator, strategy);
            List<WebElement> result = null;
            if (parent != null) {
                log.trace ("Finding child element of [{}] parent using [{}] at index [{}]...", parent.name (), locator,
                    index);
                final WebElement mobileElement = getElement (parent.name ());
                result = mobileElement.findElements (locator);
            } else {
                log.trace ("Finding root element using [{}] at index [{}]...", locator, index);
                result = deviceDriver.findElements (locator);
            }
            return result.get (index);
        } catch (final TimeoutException e) {
            final String message = format ("[{0}] locator timed out.", locator);
            log.warn (message);
        } catch (final NoSuchSessionException e) {
            handleAndThrow(Message.APPIUM_SERVER_STOPPED_ERROR,e);
        } catch (final InvalidSelectorException e) {

            handleAndThrow(Message.APPIUM_SELECTOR_NOT_IMPLEMENTED_ERROR,e);
        } catch (final Exception e) {
            captureScreenshotOnError ();
            String message = "";
            if (parent == null) {
                message = format ("Error occurred while finding root device element with locator [{0}] at index [{1}].",
                    locator, index);
                handleAndThrow(Message.DEVICE_ELEMENT_NOT_FOUND_ERROR,e,message);
            } else {
                message = format (
                    "Error occurred while finding device element with locator [{0}] at index [{1}] under parent {2}.",
                    locator, index, parent.name ());
                handleAndThrow(Message.DEVICE_ELEMENT_NOT_FOUND_ERROR,e,message);

            }
        }
        return null;
    }

    private WebElement findElements (final DeviceElement element) {
        Objects.requireNonNull (element, "Element is required.");
        final DeviceElement parent = element.parent ();
        final By locator = element.locator (this.platform, this.automation);
        final int index = element.index ();
        final WaitStrategy strategy = element.waitStrategy ();
        return find (this.device.getDriver (), parent, locator, index, strategy);
    }

    private DeviceElement getDeviceElement (final String name) {
        if (this.deviceElements.containsKey (name)) {
            return this.deviceElements.get (name);
        }
        final String msg = format ("DeviceElement with name [{0}] not found.", name);
        throwError (Message.DEVICE_ELEMENT_NOT_FOUND_ERROR, msg);
        return null;
    }

    private void load () {
        if (this.deviceElements.size () == 0) {
            log.trace ("Loading elements on [{}] activity...", this.platform);
            loadElements (prepare ());
        }
    }

    private void loadElements (final DeviceElement rootElement) {
        //ServerChecker.checkServerRunning (this.device.server);
        if (!this.deviceElements.containsKey (rootElement.name ())) {
            this.deviceElements.put (rootElement.name (), rootElement);
        }
        final List<DeviceElement> childs = rootElement.childs ();
        for (final DeviceElement child : childs) {
            loadElements (child);
        }
    }

    /**
     * @param locator Element locator
     * @param waitStrategy Wait strategy
     *
     * @author Rabindra Biswal
     * @since Jan 30, 2018 7:33:47 PM
     */
    private void wait (final By locator, final WaitStrategy waitStrategy) {
        switch (waitStrategy) {
            case ENABLED:
                this.wait.until (ExpectedConditions.elementToBeClickable (locator));
                break;
            case PRESENT:
                this.wait.until (ExpectedConditions.presenceOfElementLocated (locator));
                break;
            case VISIBLE:
                this.wait.until (visibilityOfElementLocated (locator));
                break;
            case NONE:
            default:
                break;
        }
    }
}