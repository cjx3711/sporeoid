package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.cjx3711.sporeoid.utils.RandomUtil;
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
        if(pointer < maxTouches) {
            temp.set(screenX, screenY);
            temp = ScalingUtil.touchToStandard(temp);
            touches.get(pointer).start(temp);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(pointer < maxTouches) {
            temp.set(screenX, screenY);
            temp = ScalingUtil.touchToStandard(temp);
            touches.get(pointer).update(temp);
            TouchInfo touch = touches.get(pointer);
            Vect2D start = touch.getStart();
            Vect2D delta = touch.getDelta();
            float time = touch.getElapsedTime();
            float distance = delta.distance();
            float speed = distance / (time / 1000);

            if ( distance > 20 && speed > 20 ) {
                float posVar = 40.0f;
                float vecVar = 30.0f;
                int team = start.getX() > ScalingUtil.getStandardWidth() * 0.5f ? 1 : 2;
                for ( int i = 0; i < 15; i ++ ) {
                    Vect2D s = new Vect2D(start.getX() + RandomUtil.randomFloat(-posVar, posVar), start.getY() + RandomUtil.randomFloat(-posVar, posVar));
                    Vect2D d = new Vect2D(delta.getX() + RandomUtil.randomFloat(-vecVar, vecVar), delta.getY() + RandomUtil.randomFloat(-vecVar, vecVar));
                    d.scaleBy(0.4f);
                    GameSceneManager.getInstance().getGameScene().addProjectile(s, d, team);
                }

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
