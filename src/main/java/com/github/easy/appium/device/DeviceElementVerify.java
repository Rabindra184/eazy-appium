
package com.github.easy.appium.device;

import static com.google.common.truth.Truth.assertThat;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @param <D>
 * @param <E>
 * @param <T>
 *
 * @author Rabindra Biswal
 * @since 19-May-2017 9:57:58 PM
 */
public class DeviceElementVerify<D extends AppiumDriver, E extends Device<D, T>, T extends TouchAction<T>> {
    private static final Logger log = LogManager.getLogger ();

    private final DeviceElementActions<D, E, T> actions;

    /**
     * @param actions Actions instance
     *
     * @author Rabindra Biswal
     * @since 19-May-2017 9:59:32 PM
     */
    public DeviceElementVerify (final DeviceElementActions<D, E, T> actions) {
        this.actions = actions;
    }

    /**
     * @author Rabindra Biswal
     * @since 19-May-2017 10:07:53 PM
     */
    public void shouldBeDisabled () {
        log.info ("Verifying if element is disabled...");
        assertThat (this.actions.enabled ()).isFalse ();
    }

    /**
     * @author Rabindra Biswal
     * @since 19-May-2017 10:08:28 PM
     */
    public void shouldBeDisplayed () {
        log.info ("Verifying if element is displayed...");
        assertThat (this.actions.visible ()).isTrue ();
    }

    /**
     * @author Rabindra Biswal
     * @since 19-May-2017 10:07:22 PM
     */
    public void shouldBeEnabled () {
        log.info ("Verifying if element is enabled...");
        assertThat (this.actions.enabled ()).isTrue ();
    }

    /**
     * @author Rabindra Biswal
     * @since 20-May-2017 12:24:26 PM
     */
    public void shouldNotBeDisplayed () {
        log.info ("Verifying if element is not displayed...");
        assertThat (this.actions.visible ()).isFalse ();
    }

    /**
     * @param expected Expected value
     *
     * @author Rabindra Biswal
     * @since 20-May-2017 12:39:27 PM
     */
    public void textShouldBeEqualTo (final String expected) {
        log.info ("Verifying if element text is equal to [{}]...", expected);
        assertThat (this.actions.text ()).isEqualTo (expected);
    }
}