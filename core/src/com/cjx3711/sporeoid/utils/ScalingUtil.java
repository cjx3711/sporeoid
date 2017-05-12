package com.cjx3711.sporeoid.utils;

import com.badlogic.gdx.Graphics;

/**
 * Utilities to help with scaling issues
 */

public class ScalingUtil {
    // Remember, width is the long side
    public static int screenWidth;
    public static int screenHeight;
    public static void init(Graphics g) {
        screenHeight = g.getHeight();
        screenWidth = g.getWidth();
    }

    public static float touchToScreen(float touchY) {
        return screenWidth - touchY;
    }
}
