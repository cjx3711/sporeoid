package com.cjx3711.sporeoid.entities;

import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * The base class of a game object that can move
 */

public class DynamicEntity extends BaseEntity {
    protected Vect2D vel;

    public DynamicEntity(Vect2D pos) {
        super(pos);
    }

    public void setVelocity(Vect2D vel) {
        this.vel = vel.copy();
    }

    @Override
    public void calculate(float delta) {
        pos.setX(pos.getX() + vel.getX() * delta);
        pos.setY(pos.getY() + vel.getY() * delta);
    }
}
