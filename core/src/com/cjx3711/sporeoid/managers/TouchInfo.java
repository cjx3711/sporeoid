package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;
import com.cjx3711.sporeoid.callbacks.TouchCallback;
import com.cjx3711.sporeoid.entities.ClickableEntity;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Handles the touches
 */
public class TouchInfo {
    private Vect2D start;
    private Vect2D touch;
    private boolean touched = false;
    private long startMills = 0;
    private TouchCallback callback;
    public TouchInfo() {
        start = new Vect2D();
        touch = new Vect2D();
        callback = null;
    }

    public void start(Vect2D pos) {
        start.set(pos);
        startMills = TimeUtils.millis();
        ClickableEntity.processClick(this);
        if ( callback != null ) {
            callback.onStart(this);
        }
    }
    public void update(Vect2D pos) {
        touch.set(pos);
        if ( callback != null ) {
            callback.onMove(this);
        }
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
        if ( callback != null ) {
            callback.onEnd(this);
        }
        callback = null;
        start.set(0,0);
        touch.set(0,0);
        touched = false;
    }

    public void setTouchCallback(TouchCallback callback) {
        this.callback = callback;
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