package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Handles the touches
 */
public class TouchInfo {
    private Vect2D start;
    private Vect2D touch;
    private boolean touched = false;
    private long startMills = 0;
    public TouchInfo() {
        start = new Vect2D();
        touch = new Vect2D();
    }

    public void start(Vect2D pos) {
        start.set(pos);
        startMills = TimeUtils.millis();
    }
    public void update(Vect2D pos) {

        touch.set(pos);
    }
    public long getElapsedTime() {
        return TimeUtils.millis() - startMills;
    }
    public float getDeltaX() {
        return touch.getX() - start.getX();
    }
    public float getDeltaY() {
        return touch.getY() - start.getY();
    }
    public Vect2D getDelta() {
        return touch.subtract(start);
    }

    public void end() {
        start.set(0,0);
        touch.set(0,0);
        touched = false;
    }

    public Vect2D getStart() {
        return start.copy();
    }

    public Vect2D getTouch() {
        return touch.copy();
    }
    public boolean isTouched() {
        return touched;
    }
}