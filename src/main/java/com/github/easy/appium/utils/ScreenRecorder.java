
package com.github.easy.appium.utils;

import static java.text.MessageFormat.format;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.github.easy.appium.config.device.RecordSetting;
import com.github.easy.appium.config.device.VideoStreamSetting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ScreenRecorder {
    private static final Logger LOG = LogManager.getLogger ();

    /**
     * @param streamSetting Video Stream settings
     *
     * @return streaming args
     *
     * @author Wasiq Bhamla
     * @since 11-Mar-2021
     */
    public static Map<String, Object> getVideoStreamArgs (final VideoStreamSetting streamSetting) {
        final Map<String, Object> args = new HashMap<> ();
        args.put ("host", streamSetting.getHost ());
        args.put ("port", streamSetting.getPort ());
        args.put ("width", streamSetting.getWidth ());
        args.put ("height", streamSetting.getHeight ());
        args.put ("quality", streamSetting.getQuality ()
            .getQuality ());
        args.put ("bitRate", streamSetting.getBitRate () * 100000);
        LOG.trace ("Video streaming args: {}", args);
        return args;
    }

    /**
     * @param content Video content
     * @param setting Video record settings
     *
     * @author wasiqb
     * @since Oct 13, 2018
     */
    public static void saveRecording (final String content, final RecordSetting setting) {
        final byte[] decode = Base64.getDecoder ()
            .decode (content);
        try {
            final String path = setting.getPath ();
            final String prefix = setting.getPrefix ();
            final SimpleDateFormat date = new SimpleDateFormat ("yyyyMMdd-HHmmss");
            final String timeStamp = date.format (Calendar.getInstance ()
                .getTime ());
            final String fileName = format ("{0}/{1}-{2}.{3}", path, prefix, timeStamp, "mp4");
            LOG.info ("Saving video recording to [{}] path...", fileName);
            writeByteArrayToFile (new File (fileName), decode);
        } catch (final IOException e) {
            LOG.error ("Error occurred while saving video recording...");
            LOG.catching (e);
        }
    }

    private ScreenRecorder () {
        // Utility class.
    }
}