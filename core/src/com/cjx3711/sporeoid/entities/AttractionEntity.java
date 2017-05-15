package com.cjx3711.sporeoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Shows the attraction of one entity to another.
 */

public class AttractionEntity extends BaseEntity {
    private Vect2D to;

    AttractionEntity(float x, float y, float toX, float toY) {
        super(x, y);
        to = new Vect2D(toX, toY);
    }

    public AttractionEntity(Vect2D pos, Vect2D to) {
        super(pos);
        this.to = to.copy();
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        Gdx.gl.glLineWidth(ScalingUtil.standardToScreen(1));

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.valueOf("#AAAAAA"));

        shapeRenderer.line(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(to.getX()),
                ScalingUtil.standardToScreen(to.getY()));
        shapeRenderer.end();
    }
}
