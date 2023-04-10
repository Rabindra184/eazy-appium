
package com.github.easy.appium.config.device.android;

import java.util.List;

import lombok.Data;


@Data
public class WebOptions {
    private List<String>              args;
    private WebPerformancePreferences performancePreferences = new WebPerformancePreferences ();
}