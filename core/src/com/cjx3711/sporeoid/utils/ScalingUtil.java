package com.cjx3711.sporeoid.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;

/**
 * Utilities to help with scaling issues
 * Scaling and calculations will be based off a standard screen height
 * User inputs should be converted to the standard for calculations and
 * converted back to actual screen pixels before rendering.
 */

public class ScalingUtil {
    // Remember, width is the long side
    private static float screenWidth;
    private static float screenHeight;

    // Standard screen size
    private static final float standardHeight = 400;
    private static float standardWidth;

    private static float screenToStandardScale;
    public static void init(Graphics g) {
        screenHeight = g.getHeight();
        screenWidth = g.getWidth();
        float aspectRatio = screenWidth / screenHeight;
        standardWidth = Math.round(standardHeight * aspectRatio);
        screenToStandardScale = standardHeight / screenHeight;
        Gdx.app.debug("ScalingUtil", screenWidth + " " + screenHeight);
        Gdx.app.debug("ScalingUtil", standardWidth + " " + standardHeight);

    }

    public static float touchToScreen(float touchY) {
        return screenWidth - touchY;
    }

    public static Vect2D touchToScreen(Vect2D touchPos) {
        return new Vect2D( touchPos.getX(), screenHeight - touchPos.getY());
    }

    public static Vect2D screenToStandard(Vect2D screenPos) {
        return new Vect2D(screenPos.getX() * screenToStandardScale, screenPos.getY() * screenToStandardScale);
    }
    public static Vect2D touchToStandard(Vect2D touchPos) {
        return new Vect2D( touchPos.getX() * screenToStandardScale, (screenHeight - touchPos.getY()) * screenToStandardScale);
    }

    public static float standardToScreen(float value) {
        return value / screenToStandardScale;
    }

    public static Vect2D standardToScreen(Vect2D value) {
        return new Vect2D(value.getX() / screenToStandardScale, value.getY() / screenToStandardScale);
    }

    public static float getStandardHeight() {
        return standardHeight;
    }

    public static float getStandardWidth() {
        return standardWidth;
    }

    public static float fromTop(float value) {
        return standardHeight - value;
    }

    public static float fromBottom(float value) {
        return value; // Umm
    }

    public static float fromRight(float value) {
        return standardWidth - value;
    }

    public static float fromLeft(float value) {
        return value; // Umm
    }

    public static float rightOfCentre(float value) {
        return standardWidth * 0.5f + value;
    }
    public static float leftOfCentre(float value) {
        return standardWidth * 0.5f - value;
    }
    public static float aboveCentre(float value) {
        return standardHeight * 0.5f + value;
    }
    public static float belowCentre(float value) {
        return standardHeight * 0.5f - value;
    }
}
