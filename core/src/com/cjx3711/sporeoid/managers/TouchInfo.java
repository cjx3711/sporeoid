package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Handles the touches
 */
public class TouchInfo {
    private float startX = 0;
    private float startY = 0;
    private float touchX = 0;
    private float touchY = 0;
    private boolean touched = false;
    private long startMills = 0;
    public TouchInfo() {

    }

    public void start(float x, float y) {
        startX = touchX = x;
        startY = touchY = y;
        startMills = TimeUtils.millis();
    }
    public void update(float x, float y) {
        touchX = x;
        touchY = y;
    }
    public long getElapsedTime() {
        return TimeUtils.millis() - startMills;
    }
    public float getDeltaX() {
        return touchX - startX;
    }
    public float getDeltaY() {
        return touchY - startY;
    }
    public void end() {
        startX = touchX = 0;
        startY = touchY = 0;
        touched = false;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getTouchX() {
        return touchX;
    }

    public float getTouchY() {
        return touchY;
    }

    public boolean isTouched() {
        return touched;
    }
}