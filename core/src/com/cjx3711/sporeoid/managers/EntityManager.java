package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.entities.BaseEntity;

import java.util.ArrayList;

/**
 * Contains a list of objects.
 * Todo: Collision detection, scene management etc.
 */

public class EntityManager {
    private ArrayList<BaseEntity> entities;
    public EntityManager() {
        entities = new ArrayList<BaseEntity>();
    }

    public void addEntity(BaseEntity baseEntity) {
        entities.add(baseEntity);
    }

    public void calculate(float delta) {
        for ( BaseEntity baseEntity : entities) {
            baseEntity.calculate(delta);
        }
    }
    public void render(ShapeRenderer shapeRenderer) {
        for ( BaseEntity baseEntity : entities) {
            baseEntity.render(shapeRenderer);
        }
    }
}
