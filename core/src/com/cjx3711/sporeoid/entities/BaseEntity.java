package com.cjx3711.sporeoid.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Base game object class
 */

public class BaseEntity {
    protected Vect2D pos;

    BaseEntity(Vect2D pos) {
        this.pos = pos.copy();
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.circle(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(50));
        shapeRenderer.end();
    }

    public void calculate(float delta) {

    }
}
