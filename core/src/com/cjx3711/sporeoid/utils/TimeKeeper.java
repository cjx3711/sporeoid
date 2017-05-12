package com.cjx3711.sporeoid.utils;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Keeps the time and helps to regulate movement independent of framerates
 */

public class TimeKeeper {
    private static long previousMills = 0;
    private static long currentMills = 0;
    private static float deltaMills = 0;
    private static float delta = 0;

    public static void init() {
        previousMills = currentMills = TimeUtils.millis();
    }
    public static void calculate() {
        currentMills = TimeUtils.millis();
        deltaMills = currentMills - previousMills;
        previousMills = currentMills;
        delta = deltaMills / 1000.0f;
    }

    public static float getDelta() {
        return delta;
    }
}