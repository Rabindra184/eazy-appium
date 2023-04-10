
package com.github.easy.appium.config.device;

import lombok.Data;


@Data
public class DelaySetting {
    private long afterSwipe;
    private long afterTap;
    private long beforeSwipe;
    private long beforeTap;
    private int  explicit = 30;
    private int  implicit = 30;
}