package com.cjx3711.sporeoid.entities;

/**
 * The base class of a game object that can move
 */

public class DynamicEntity extends BaseEntity {
    protected float velX, velY;

    public DynamicEntity(float x, float y) {
        super(x,y);
    }

    public void setVelocity(float x, float y) {
        velX = x;
        velY = y;
    }

    @Override
    public void calculate(float delta) {
        posX += velX * delta;
        posY += velY * delta;
    }
}
