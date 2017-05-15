package com.cjx3711.sporeoid.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Entity Representing a planet
 */

public class PlanetEntity extends BaseEntity implements CollidableEntity {
    protected float radius;
    protected float health;
    protected int team;

    public PlanetEntity(float x, float y, float radius) {
        super(x, y);
        this.radius = radius;
        health = 100;
    }
    PlanetEntity(Vect2D pos) {
        super(pos);
    }

    @Override
    public void hit(int team) {
        boolean friendly = this.team == team;
        health += friendly ? 5 : -5;
        if ( health <= 0 ) {
            this.team = team;
            health = 0;
        }
    }

    @Override
    public void calculate(float delta) {
        health += 4 * delta;
        if ( health >= 100 ) health = 100;
    }
    @Override
    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        switch (team) {
            case 1:
                shapeRenderer.setColor(Color.valueOf("#FF0000"));
                break;
            case 2:
                shapeRenderer.setColor(Color.valueOf("#0000FF"));
                break;
            default:
                shapeRenderer.setColor(Color.valueOf("#DDDDDD"));
        }

        shapeRenderer.circle(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(radius + 2));

        shapeRenderer.setColor(Color.valueOf("#999999"));


        shapeRenderer.circle(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(radius));

        switch (team) {
            case 1:
                shapeRenderer.setColor(Color.valueOf("#FF0000"));
                break;
            case 2:
                shapeRenderer.setColor(Color.valueOf("#0000FF"));
                break;
            default:
                shapeRenderer.setColor(Color.valueOf("#DDDDDD"));
        }
        shapeRenderer.circle( // Render health
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(radius * health * 0.01f));
        shapeRenderer.end();


    }

    public float getRadius() {
        return radius;
    }
}
