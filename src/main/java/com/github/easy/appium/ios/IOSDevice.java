
package com.github.easy.appium.ios;
import com.github.easy.appium.config.device.ios.IOSVideoSetting;
import com.github.easy.appium.device.Device;
import com.github.easy.appium.service.AppiumServer;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions.VideoQuality;
import io.appium.java_client.ios.IOSStopScreenRecordingOptions;
import io.appium.java_client.ios.IOSTouchAction;

/**
 * @author Rabindra Biswal
 * @since 13-Apr-2017 5:33:35 PM
 */
public class IOSDevice extends Device<IOSDriver, IOSTouchAction> {
    /**
     * @param server Server instance
     * @param name Server name
     *
     * @author Rabindra Biswal
     * @since 13-Apr-2017 9:12:09 PM
     */
    public IOSDevice (final AppiumServer server, final String name) {
        super (server, name);
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.Device#startRecordSetting()
     */
    @SuppressWarnings ("unchecked")
    @Override
    protected IOSStartScreenRecordingOptions startRecordSetting () {
        final IOSStartScreenRecordingOptions options = IOSStartScreenRecordingOptions.startScreenRecordingOptions ();
        final IOSVideoSetting record = this.setting.getPlayback ()
            .getRecording ()
            .getIos ();
        if (record.getQuality () != VideoQuality.MEDIUM) {
            options.withVideoQuality (record.getQuality ());
        }
        if (record.getFps () > 0) {
            options.withFps (record.getFps ());
        }
        if (record.getCodec () != null) {
            options.withVideoType (record.getCodec ());
        }
        return options;
    }

    /*
     * (non-Javadoc)
     * @see com.github.wasiqb.coteafs.appium.device.Device#stopRecordSetting()
     */
    @SuppressWarnings ("unchecked")
    @Override
    protected IOSStopScreenRecordingOptions stopRecordSetting () {
        return IOSStopScreenRecordingOptions.stopScreenRecordingOptions ();
    }
}