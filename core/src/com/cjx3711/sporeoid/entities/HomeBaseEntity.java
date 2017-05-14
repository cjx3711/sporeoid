package com.cjx3711.sporeoid.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.callbacks.TouchCallback;
import com.cjx3711.sporeoid.managers.GameSceneManager;
import com.cjx3711.sporeoid.managers.TouchInfo;
import com.cjx3711.sporeoid.utils.RandomUtil;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Button for Player's base
 */

public class HomeBaseEntity extends ClickableEntity {
    public HomeBaseEntity(float x, float y) {
        super(x, y);
    }

    HomeBaseEntity(Vect2D pos) {
        super(pos);
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        if ( touched ) {
            shapeRenderer.setColor(Color.GREEN);
        } else {
            shapeRenderer.setColor(Color.FOREST);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.circle(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(radius));
        shapeRenderer.end();
    }

    protected void initTouchCallback() {
        touchCallback = new TouchCallback() {

            @Override
            public void onStart(TouchInfo touch) {
                touched = true;
            }

            @Override
            public void onMove(TouchInfo touch) {

            }

            @Override
            public void onEnd(TouchInfo touch) {
                touched = false;
                Vect2D start = pos;
                Vect2D delta = touch.getDelta();
                float time = touch.getElapsedTime();
                float distance = delta.distance();
                float speed = distance / (time / 1000);

                if (distance > 20 && speed > 20) {
                    float posVar = 40.0f;
                    float vecVar = 30.0f;
                    int team = start.getX() > ScalingUtil.getStandardWidth() * 0.5f ? 1 : 2;
                    for (int i = 0; i < 15; i++) {
                        Vect2D s = new Vect2D(start.getX() + RandomUtil.randomFloat(-posVar, posVar), start.getY() + RandomUtil.randomFloat(-posVar, posVar));
                        Vect2D d = new Vect2D(delta.getX() + RandomUtil.randomFloat(-vecVar, vecVar), delta.getY() + RandomUtil.randomFloat(-vecVar, vecVar));
                        d.scaleBy(0.4f);
                        GameSceneManager.getInstance().getGameScene().addProjectile(s, d, team);
                    }

                }
            }
        };
    }
}
