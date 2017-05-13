package com.cjx3711.sporeoid.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Entity representing a projectile
 */

public class ProjectileEntity extends DynamicEntity {
    protected boolean destroyed = false;
    protected float radius = 4;
    protected int team;

    public ProjectileEntity(Vect2D pos, Vect2D vel, int team) {
        super(pos);
        setVelocity(vel);
        this.team = team;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public float getRadius() {
        return radius;
    }

    public int getTeam() {
        return team;
    }

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        switch (team) {
            case 1:
                shapeRenderer.setColor(Color.RED);
            break;
            case 2:
                shapeRenderer.setColor(Color.BLUE);
        }
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

        if ( !destroyed && (pos.getX() + radius < 0 || pos.getY() + radius < 0 || pos.getY() - radius > ScalingUtil.getStandardHeight() || pos.getX() - radius > ScalingUtil.getStandardWidth()) ) {
            destroyed = true;
        }
    }


    public void destroy() {
        destroyed = true;
    }
}
