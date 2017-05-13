package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to control the input of the game
 */

public class GameInputProcessor implements InputProcessor {
    private Map<Integer,TouchInfo> touches = new HashMap<Integer,TouchInfo>();
    private int maxTouches = 6;
    private Vect2D temp;

    public GameInputProcessor() {
        temp = new Vect2D();
        for(int i = 0; i < 5; i++) {
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
        Gdx.app.debug("InputProcessor",  "Down: " + screenX + " " + screenY);
        if(pointer < maxTouches) {
            temp.set(screenX, screenY);
            temp = ScalingUtil.touchToStandard(temp);
            Gdx.app.debug("InputProcessor",  "Down: " + temp);
            touches.get(pointer).start(temp);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.debug("InputProcessor", "Up: " + screenX + " " + screenY);
        if(pointer < maxTouches) {
            temp.set(screenX, screenY);
            temp = ScalingUtil.touchToStandard(temp);
            Gdx.app.debug("InputProcessor",  "Up: " + temp);
            touches.get(pointer).update(temp);
            TouchInfo touch = touches.get(pointer);
            Vect2D start = touch.getStart();
            Vect2D delta = touch.getDelta();
            float time = touch.getElapsedTime();
            float distance = delta.distance();
            float speed = distance / (time / 1000);
            Gdx.app.debug("InputProcessor", "Distance: " + distance + " speed: " + speed + " px/s");

            if ( speed > 20 ) {
                GameSceneManager.getInstance().getGameScene().addProjectile(start, delta);
            }

            touches.get(pointer).end();
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(pointer < maxTouches){
            temp.set(screenX, screenY);
            touches.get(pointer).update(ScalingUtil.touchToStandard(temp));
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
