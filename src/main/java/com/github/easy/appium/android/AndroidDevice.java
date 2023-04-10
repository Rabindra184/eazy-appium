
package com.github.easy.appium.android;

import com.github.easy.appium.config.device.android.AndroidVideoSetting;
import com.github.easy.appium.device.Device;
import com.github.easy.appium.service.AppiumServer;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.AndroidStopScreenRecordingOptions;
import io.appium.java_client.android.AndroidTouchAction;

/**
 * @author Rabindra Biswal
 * @since 13-Apr-2017 5:32:01 PM
 */
public class AndroidDevice extends Device<AndroidDriver, AndroidTouchAction> {
    /**
     * @param server Server instance
     * @param name Device name
     *
     * @author Rabindra Biswal
     * @since 13-Apr-2017 9:12:47 PM
     */
    public AndroidDevice (final AppiumServer server, final String name) {
        super (server, name);
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.Device#startRecordSetting()
     */
    @SuppressWarnings ("unchecked")
    @Override
    protected AndroidStartScreenRecordingOptions startRecordSetting () {
        final AndroidStartScreenRecordingOptions options = AndroidStartScreenRecordingOptions.startScreenRecordingOptions ();
        final AndroidVideoSetting record = this.setting.getPlayback ()
            .getRecording ()
            .getAndroid ();
        if (record.getBitRate () != 4) {
            options.withBitRate (record.getBitRate ());
        }
        if (record.getSize () != null) {
            options.withVideoSize (record.getSize ());
        }
        return options;
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.Device#stopRecordSetting()
     */
    @SuppressWarnings ("unchecked")
    @Override
    protected AndroidStopScreenRecordingOptions stopRecordSetting () {
        return AndroidStopScreenRecordingOptions.stopScreenRecordingOptions ();
    }
}