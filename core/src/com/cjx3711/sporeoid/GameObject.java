package com.cjx3711.sporeoid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Base game object class
 */

public class GameObject {
    protected float posX, posY;

    GameObject(float x, float y) {
        posX = x;
        posY = y;
    }

    public void render(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(posX, posY, 50);
        shapeRenderer.end();
    }

    public void calculate(float delta) {

    }
}
