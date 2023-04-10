
package com.github.easy.appium.device;

import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;

/**
 * @param <D> Driver
 * @param <E> Device
 * @param <T>
 *
 * @author Rabindra Biswal
 * @since Oct 23, 2017 10:51:31 PM
 */
public abstract class DeviceActivityActions<D extends AppiumDriver, E extends Device<D, T>, T extends TouchAction<T>> {
    private final E                   device;
    private final Map<String, Object> values;

    /**
     * @param device Device instance
     *
     * @author Rabindra Biswal
     * @since Oct 23, 2017 10:51:31 PM
     */
    protected DeviceActivityActions (final E device) {
        this.device = device;
        this.values = new HashMap<> ();
    }

    /**
     * @param element Element name
     * @param value Input value
     *
     * @return instance
     *
     * @author Rabindra Biswal
     * @since Oct 23, 2017 11:01:15 PM
     */
    public DeviceActivityActions<D, E, T> addInputValue (final String element, final Object value) {
        this.values.put (element, value);
        return this;
    }

    /**
     * @return the device
     *
     * @author Rabindra Biswal
     * @since Oct 23, 2017 10:55:13 PM
     */
    public E getDevice () {
        return this.device;
    }

    /**
     * @author Rabindra Biswal
     * @since Oct 23, 2017 10:53:59 PM
     */
    public abstract void perform ();

    @SuppressWarnings ("unchecked")
    protected <X> X value (final String element) {
        return (X) this.values.get (element);
    }
}