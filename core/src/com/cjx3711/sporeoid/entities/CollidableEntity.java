package com.cjx3711.sporeoid.entities;

import com.cjx3711.sporeoid.utils.Vect2D;

/**
 * Interface that determines which items are collidable
 */

public interface CollidableEntity {
    void hit(int team);
    float getHitRadius();
    Vect2D getPos();

}
