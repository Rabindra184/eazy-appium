
package com.github.easy.appium.config.device;

import lombok.Data;

/**
 * @author Rabindra Biswal
 * @since Jan 18, 2018 9:32:14 PM
 */
@Data
public class PlaybackSetting {
    private DelaySetting       delay         = new DelaySetting ();
    private int                maxSwipeCount = 5;
    private RecordSetting      recording     = new RecordSetting ();
    private ScreenshotSetting  screenshot    = new ScreenshotSetting ();
    private VideoStreamSetting stream        = new VideoStreamSetting ();
}