package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.entities.BaseEntity;
import com.cjx3711.sporeoid.entities.ProjectileEntity;
import com.cjx3711.sporeoid.utils.Vect2D;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manages the scene of the game
 */

public class GameScene {
    private ArrayList<ProjectileEntity> projectiles;
    public GameScene() {
        projectiles = new ArrayList();
    }

    public void addProjectile(Vect2D pos, Vect2D vel) {
        ProjectileEntity projectileEntity = new ProjectileEntity(pos, vel);
        projectiles.add(projectileEntity);
    }

    public void calculate(float delta) {
        for (ProjectileEntity projectile : projectiles) {
            projectile.calculate(delta);
        }

        Iterator<ProjectileEntity> iter = projectiles.iterator();
        while (iter.hasNext()) {
            ProjectileEntity proj = iter.next();
            if (proj.isOutOfBounds()) iter.remove();
        }
    }

    public void render(ShapeRenderer renderer) {
        for (ProjectileEntity projectile : projectiles) {
            projectile.render(renderer);
        }
    }
}
