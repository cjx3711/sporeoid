package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.entities.ProjectileEntity;

/**
 * Manages the scene of the game
 */

public class GameScene {
    private EntityManager projectiles;
    public GameScene() {
        projectiles = new EntityManager();
    }

    public void addProjectile(float x, float y, float vX, float vY) {
        ProjectileEntity projectileEntity = new ProjectileEntity(x, y);
        projectileEntity.setVelocity(vX, vY);
        projectiles.addEntity(projectileEntity);
    }

    public void calculate(float delta) {
        projectiles.calculate(delta);
    }

    public void render(ShapeRenderer renderer) {
        projectiles.render(renderer);
    }
}
