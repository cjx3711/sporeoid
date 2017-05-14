package com.cjx3711.sporeoid.callbacks;

import com.cjx3711.sporeoid.managers.TouchInfo;

/**
 * Callback for touch events.
 */

public interface TouchCallback {
    public void onStart(TouchInfo touch);
    public void onMove(TouchInfo touch);
    public void onEnd(TouchInfo touch);
}
