/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.easy.appium.config.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Different validation messages
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
@AllArgsConstructor
@Getter
public enum Message {
    APPUIM_CONFIG_PARAMETER_NOT_FOUND_ERROR ("Appium configuration parameter not found error"),
    APPIUM_SELECTOR_NOT_IMPLEMENTED_ERROR ("Appium selector not implemented error"),
    APPIUM_SERVER_ALREADY_RUNNING_ERROR ("Appium server already running error"),
    APPIUM_SERVER_LOG_FILE_ERROR ("Appium server log file error"),
    APPIUM_SERVER_NOT_RUNNING_ERROR ("Appium server not running error"),
    APPIUM_SERVER_NOT_STARTING_ERROR ("Appium server not starting error"),
    APPIUM_SERVER_NOT_STOPPING_ERROR ("Appium server not stopping error"),
    APPIUM_SERVER_STOPPED_ERROR ("Appium server stopped error"),
    COTEAFS_APPIUM_ERROR ("Coteafs Appium error"),
    DEVICE_APP_NOT_CLOSING_ERROR ("Device app not closing error"),
    DEVICE_APP_NOT_FOUND_ERROR ("Device app not found error"),
    DEVICE_DESIRED_CAPABILITIES_NOT_SET_ERROR ("Device desired capabilities not set error"),
    DEVICE_DRIVER_DEFAULT_WAIT_ERROR ("Device driver default wait error"),
    DEVICE_DRIVER_INITIALIZATION_FAILED_ERROR ("Device driver initialization failed error"),
    DEVICE_DRIVER_NOT_STARTING_ERROR ("Device driver not starting error"),
    DEVICE_DRIVER_NOT_STOPPING_ERROR ("Device driver not stopping error"),
    DEVICE_ELEMENT_DISABLED_ERROR ("Device element disabled error"),
    DEVICE_ELEMENT_FIND_TIMED_OUT_ERROR ("Device element find timed out error"),
    DEVICE_ELEMENT_NAME_NOT_FOUND_ERROR ("Device element name not found error"),
    DEVICE_ELEMENT_NOT_DISPLAYED_ERROR ("Device element not displayed error"),
    DEVICE_ELEMENT_NOT_FOUND_ERROR ("Device element not found error"),
    DEVICE_TYPE_NOT_SUPPORTED_ERROR ("Device type not supported error"),
    NOT_ENOUGH_BATTERY_CHARGE_ERROR ("Not enough battery charge error"),
    OPERATION_NOT_SUPPORTED_ERROR ("Opeartion is not suppported");
    private final String messageText;
}
