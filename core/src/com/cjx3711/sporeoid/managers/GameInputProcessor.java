package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.cjx3711.sporeoid.utils.ScalingUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to control the input of the game
 */

public class GameInputProcessor implements InputProcessor {
    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
    private int maxTouches = 6;

    public GameInputProcessor() {
        for(int i = 0; i < 5; i++){
            touches.put(i, new TouchInfo());
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(pointer < maxTouches){
            touches.get(pointer).start(screenX, screenY);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer < maxTouches){
            TouchInfo touch = touches.get(pointer);
            float startX = touch.getStartX();
            float startY = ScalingUtil.touchToScreen(touch.getStartY());
            float deltaX = touch.getDeltaX();
            float deltaY = -touch.getDeltaY();
            float time = touch.getElapsedTime();
            float distance = (float)Math.sqrt(deltaX * deltaX + deltaY * deltaY);
            float speed = distance / (time / 1000);
            Gdx.app.debug("GameMain", "Distance: " + distance + " speed: " + speed + " px/s");
            touches.get(pointer).end();
            if ( speed > 20 ) {
                GameSceneManager.getInstance().getGameScene().addProjectile(startX, startY, deltaX, deltaY);
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(pointer < maxTouches){
            touches.get(pointer).update(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
