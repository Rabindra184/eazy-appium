
package com.github.easy.appium.service;

import static com.github.easy.appium.checker.ServerChecker.checkServerConfigParams;
import static com.github.easy.appium.utils.ErrorHandler.handleAndThrow;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.BOOTSTRAP_PORT_NUMBER;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.CHROME_DRIVER_EXECUTABLE;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.CHROME_DRIVER_PORT;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.REBOOT;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.ALLOW_INSECURE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.ASYNC_TRACE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.CALLBACK_ADDRESS;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.CALLBACK_PORT;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.CONFIGURATION_FILE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.DEBUG_LOG_SPACING;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOCAL_TIMEZONE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_NO_COLORS;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_TIMESTAMP;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.PRE_LAUNCH;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.STRICT_CAPS;
import static io.appium.java_client.service.local.flags.IOSServerFlag.BACK_END_RETRIES;
import static io.appium.java_client.service.local.flags.IOSServerFlag.DEFAULT_DEVICE;
import static io.appium.java_client.service.local.flags.IOSServerFlag.IPA_ABSOLUTE_PATH;
import static io.appium.java_client.service.local.flags.IOSServerFlag.SAFARI;
import static io.appium.java_client.service.local.flags.IOSServerFlag.TRACE_DIRECTORY_ABSOLUTE_PATH;
import static io.appium.java_client.service.local.flags.IOSServerFlag.WEBKIT_DEBUG_PROXY_PORT;
import static java.lang.String.join;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;

import com.github.easy.appium.config.AppiumSetting;
import com.github.easy.appium.config.enums.Message;
import com.github.easy.appium.config.server.AndroidSetting;
import com.github.easy.appium.config.server.IOSSetting;
import com.github.easy.appium.config.server.LogSetting;
import com.github.easy.appium.config.server.ServerSetting;
import com.github.parse.DataSource;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Rabindra Biswal
 * @since 08-11-2020
 */
public final class AppiumServer {
    private static final Logger log = LogManager.getLogger ();

    private       AppiumServiceBuilder     builder;
    private       AppiumDriverLocalService service;
    private final ServerSetting            setting;

    /**
     * @param name Server settings block name
     *
     * @author Rabindra Biswal
     * @since 08-11-2020
     */
    public AppiumServer (final String name) {
        this.setting = DataSource.parse (AppiumSetting.class)
            .getServer (name);
        System.out.println (this.setting);
        if (!this.setting.isExternal ()) {
            this.builder = new AppiumServiceBuilder ();
            buildService ();
        }
    }

    /**
     * @return url
     *
     * @author Rabindra Biswal
     * @since 08-11-2020
     */
    public URL getServiceUrl () {
        log.trace ("Fetching Appium Service URL...");
        if (!this.setting.isExternal () && !isCloud ()) {
            return this.service.getUrl ();
        }
        final String url = format ("{0}/wd/hub", getUrl ());
        try {
            return new URL (url);
        } catch (final MalformedURLException e) {
            log.error ("Error occurred while getting service url...");
            log.catching (e);
        }
        return null;
    }

    /**
     * @return cloud settings
     *
     * @author Wasiq Bhamla
     * @since Mar 13, 2021
     */
    public boolean isCloud () {
        return this.setting.getCloud () != null && isNotEmpty (this.setting.getUserName ()) && isNotEmpty (
            this.setting.getPassword ());
    }

    /**
     * @return isRunning
     *
     * @author Rabindra Biswal
     * @since 08-11-2020
     */
    public boolean isRunning () {
        if (isCloud ()) {
            return true;
        }
        if (!this.setting.isExternal ()) {
            log.trace ("Checking if Appium Service is running...");
            return this.service.isRunning ();
        }
        final SocketAddress address = new InetSocketAddress (this.setting.getHost (), this.setting.getPort ());
        try (final Socket socket = new Socket ()) {
            socket.connect (address, 2000);
        } catch (final IOException e) {
            handleAndThrow (Message.APPIUM_SERVER_NOT_RUNNING_ERROR, e,"Error connecting to Server...");
        }
        return true;
    }

    /**
     * @author Rabindra Biswal
     * @since 08-11-2020
     */
    public void start () {
        log.trace ("Starting Appium Service...");
        if (!this.setting.isExternal () && !isCloud ()) {
            boolean failure = false;
            this.service = AppiumDriverLocalService.buildService (this.builder);
            try {
                this.service.start ();
                System.out.println (service.isRunning ());
            } catch (final AppiumServerHasNotBeenStartedLocallyException e) {
                failure = true;
                handleAndThrow (Message.APPIUM_SERVER_NOT_STARTING_ERROR, e,"Error occured while starting Appium server");
            } catch (final Exception e) {
                failure = true;
                handleAndThrow (Message.APPIUM_SERVER_ALREADY_RUNNING_ERROR,e, "Appium server is running already.");
            } finally {
                if (failure) {
                    stop ();
                }
            }
            log.trace ("Appium Service Started...");
        } else {
            if (isRunning ()) {
                log.trace ("Appium Service is already running...");
            }
        }
    }

    /**
     * @author Rabindra Biswal
     * @since 08-11-2020
     */
    public void stop () {
        log.trace ("Trying to stop Appium Service...");
        if (!this.setting.isExternal () && !isCloud ()) {
            try {
                this.service.stop ();
            } catch (final Exception e) {
                handleAndThrow (Message.APPIUM_SERVER_NOT_STOPPING_ERROR,e, "Error occured while stopping the server.");
            }
            this.service = null;
            log.trace ("Appium Service Stopped...");
        } else {
            log.trace ("Appium Service can only be stopped from the tool you started with...");
        }
    }

    private void buildService () {
        System.out.println ("I am in buildservice");
        log.trace ("Building Appium Service started...");
        this.builder.withIPAddress (getHost ())
            .withTimeout (Duration.ofSeconds (this.setting.getStartUpTimeout ()));
        setPort ();
        setLogFile ();
        setAppiumJS ();
        setNodeExe ();
        setArguments ();
        setEnvironmentVariables ();
        log.trace ("Building Appium Service done...");
    }

    private String getHost () {
        if (this.setting.getCloud () != null) {
            return this.setting.getCloud ()
                .getUrl ();
        }
        checkServerConfigParams ("IP Host Address", this.setting.getHost ());
        return this.setting.getHost ();
    }

    private String getUrl () {
        final StringBuilder sb = new StringBuilder (this.setting.getProtocol ()
            .getName ()).append ("://");
        if (isCloud ()) {
            checkServerConfigParams ("User Name", this.setting.getUserName ());
            checkServerConfigParams ("Password", this.setting.getPassword ());
            sb.append (this.setting.getUserName ())
                .append (":")
                .append (this.setting.getPassword ())
                .append ("@")
                .append (this.setting.getCloud ()
                    .getUrl ());
        } else {
            sb.append (this.setting.getHost ());
        }
        if (this.setting.getPort () > 0) {
            sb.append (":")
                .append (this.setting.getPort ());
        }
        return sb.toString ();
    }

    private void setAndroidArguments () {
        final AndroidSetting android = this.setting.getAndroid ();
        System.out.println (android);
        if (android != null) {
            System.out.println ("I am in android");
            setArgument (BOOTSTRAP_PORT_NUMBER, android.getBootstrapPort ());
            setArgument (CHROME_DRIVER_PORT, android.getChromeDriverPort ());
            setArgument (REBOOT, android.isReboot ());
            setArgument (CHROME_DRIVER_EXECUTABLE, android.getChromeDriverPath ());
            setArgument (SUPPRESS_ADB_KILL_SERVER, android.isSuppressAdbKill ());
        }
    }

    private void setAppiumJS () {
        if (this.setting.getAppiumPath () != null) {
            final File appJs = new File (this.setting.getAppiumPath ());
            this.builder.withAppiumJS (appJs);
        }
    }

    private void setArgument (final ServerArgument flag, final boolean value) {
        if (value) {
            this.builder.withArgument (flag);
        }
    }

    private void setArgument (final ServerArgument flag, final int value) {
        if (value > 0) {
            this.builder.withArgument (flag, Integer.toString (value));
        }
    }

    private void setArgument (final ServerArgument flag, final String value) {
        if (StringUtils.isNoneEmpty (value)) {
            this.builder.withArgument (flag, value);
        }
    }

    private void setArguments () {
        setIosArguments ();
        setAndroidArguments ();
        setLogArguments ();
        setCommonArguments ();
    }

    private void setCommonArguments () {
        setArgument (() -> "--allow-cors", this.setting.isAllowCors ());
        setArgument (STRICT_CAPS, this.setting.isStrictCapabilities ());
        setArgument (RELAXED_SECURITY, this.setting.isRelaxedSecurity ());
        setArgument (PRE_LAUNCH, this.setting.isPreLaunch ());
        setArgument (SESSION_OVERRIDE, this.setting.isSessionOverride ());
        setArgument (CONFIGURATION_FILE, this.setting.getNodeConfig ());
        setArgument (CALLBACK_ADDRESS, this.setting.getCallbackIp ());
        setArgument (CALLBACK_PORT, this.setting.getCallbackPort ());
        if (this.setting.getAllowInsecure () != null && !this.setting.getAllowInsecure ()
            .isEmpty ()) {
            setArgument (ALLOW_INSECURE, join (",", this.setting.getAllowInsecure ()));
        }
    }

    private void setEnvironmentVariables () {
        this.builder.withEnvironment (this.setting.getEnvironments ());
    }

    private void setIosArguments () {
        final IOSSetting ios = this.setting.getIos ();
         System.out.println (ios);
        if (ios != null) {
            System.out.println ("I am in ios");
            setArgument (BACK_END_RETRIES, ios.getBackendRetries ());
            setArgument (IPA_ABSOLUTE_PATH, ios.getIpaPath ());
            setArgument (SAFARI, ios.isSafari ());
            setArgument (DEFAULT_DEVICE, ios.getDefaultDevice ());
            setArgument (TRACE_DIRECTORY_ABSOLUTE_PATH, ios.getTracePath ());
            setArgument (WEBKIT_DEBUG_PROXY_PORT, ios.getWkdProxyPort ());
            //setArgument (() -> "--webdriveragent-port", ios.getWdaPort ());
        }
    }

    private void setLogArguments () {
        final LogSetting logs = this.setting.getLogs ();
        setArgument (LOG_LEVEL, logs.getLevel ()
            .toString ());
        setArgument (LOG_NO_COLORS, logs.isNoColors ());
        setArgument (ASYNC_TRACE, logs.isAsyncTrace ());
        setArgument (DEBUG_LOG_SPACING, logs.isDebugSpacing ());
        setArgument (LOG_TIMESTAMP, logs.isTimestamp ());
        setArgument (LOCAL_TIMEZONE, logs.isLocalTimezone ());
    }

    private void setLogFile () {
        final String logFilePath = this.setting.getLogs ()
            .getPath ();
        if (logFilePath != null) {
            final File logFile = new File (logFilePath);
            try {
                if (logFile.exists ()) {
                    Files.delete (logFile.toPath ());
                }
            } catch (final IOException e) {
                handleAndThrow (Message.APPIUM_SERVER_LOG_FILE_ERROR, e,"Error while deleting log file!", e);
            }
            this.builder.withLogFile (logFile);
        }
    }

    private void setNodeExe () {
        if (this.setting.getNodePath () != null) {
            final File nde = new File (this.setting.getNodePath ());
            this.builder.usingDriverExecutable (nde);
        }
    }

    private void setPort () {
        if (this.setting.getPort () > 0) {
            this.builder.usingPort (this.setting.getPort ());
        } else {
            this.builder.usingAnyFreePort ();
        }
    }
}