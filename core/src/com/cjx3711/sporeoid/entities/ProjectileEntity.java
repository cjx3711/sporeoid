package com.cjx3711.sporeoid.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Entity representing a projectile
 */

public class ProjectileEntity extends DynamicEntity {
    protected boolean outOfBounds = false;
    protected float radius = 5;

    public ProjectileEntity(Vect2D pos, Vect2D vel) {
        super(pos);
        setVelocity(vel);
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.circle(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(radius));
        shapeRenderer.end();
    }

    @Override
    public void calculate(float delta) {
        super.calculate(delta);

        if ( !outOfBounds && (pos.getX() + radius < 0 || pos.getY() + radius < 0 || pos.getY() - radius > ScalingUtil.getStandardHeight() || pos.getX() - radius > ScalingUtil.getStandardWidth()) ) {
            outOfBounds = true;
        }
    }


}
