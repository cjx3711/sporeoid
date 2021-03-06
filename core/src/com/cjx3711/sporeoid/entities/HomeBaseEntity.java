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

public class HomeBaseEntity extends ClickableEntity implements CollidableEntity {
    private int state;
    private int hitRadius;
    private int team;
    private float health;
    private TouchInfo touchInfo = null;
    public HomeBaseEntity(float x, float y, int team) {
        super(x, y);
        state = 0;
        radius = 50;
        hitRadius = 30;
        this.team = team;
        health = 100;
    }

    public int getTeam() {
        return team;
    }

    protected void initTouchCallback() {
        touchCallback = new TouchCallback() {

            @Override
            public void onStart(TouchInfo touch) {
                touched = true;
                touchInfo = touch;
            }

            @Override
            public void onMove(TouchInfo touch) {

            }

            @Override
            public void onEnd(TouchInfo touch) {
                touchInfo = null;
                touched = false;
                if ( state == 0 ) {
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
            }
        };
    }

    public int getState() {
        return state;
    }

    @Override
    public void calculate(float delta) {
        if ( touchInfo != null ) {
            if ( touchInfo.getElapsedTime() > 100 && touchInfo.getDelta().distanceSquared() < 50 * 50 ) {
                state = 1;
            }
        } else {
            state = 0;
        }

        health += 3 * delta;
        if ( health > 100 ) {
            health = 100;
        }

    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        if ( state == 1 ) {
            shapeRenderer.setColor(Color.GREEN);
        }
        else if ( !touched ) {
            shapeRenderer.setColor(team == 1 ? Color.RED : Color.BLUE);
        } else {
            shapeRenderer.setColor(team == 1 ? Color.MAROON : Color.NAVY);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.circle(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(getHitRadius()));
        shapeRenderer.end();
    }

    @Override
    public void hit(int team) {
        if ( this.team != team ) {
            health -= 10;
            if (health < 0) {
                health = 0;
            }
        }
    }

    @Override
    public float getHitRadius() {
        return hitRadius * ((health / 100) * 0.9f + 0.1f);
    }
}
