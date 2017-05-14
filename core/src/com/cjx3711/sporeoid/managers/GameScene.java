package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.entities.ClickableEntity;
import com.cjx3711.sporeoid.entities.HomeBaseEntity;
import com.cjx3711.sporeoid.entities.PlanetEntity;
import com.cjx3711.sporeoid.entities.ProjectileEntity;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manages the scene of the game
 */

public class GameScene {
    private ArrayList<ProjectileEntity> projectiles;
    private ArrayList<PlanetEntity> planets;

    private ClickableEntity button;

    public GameScene() {
        projectiles = new ArrayList<ProjectileEntity>();
        planets = new ArrayList<PlanetEntity>();

        planets.add(new PlanetEntity(ScalingUtil.rightOfCentre(50), ScalingUtil.aboveCentre(50), 30));
        planets.add(new PlanetEntity(ScalingUtil.leftOfCentre(50), ScalingUtil.belowCentre(50), 30));

        button = new HomeBaseEntity(ScalingUtil.fromRight(50), 200);
    }

    public void addProjectile(Vect2D pos, Vect2D vel, int team) {
        ProjectileEntity projectileEntity = new ProjectileEntity(pos, vel, team);
        projectiles.add(projectileEntity);
    }

    public void calculate(float delta) {
        for (PlanetEntity planet : planets) {
            planet.calculate(delta);
        }

        for (ProjectileEntity projectile : projectiles) {
            projectile.calculate(delta);
        }

        // Collisions
        for (PlanetEntity planet : planets) {
            for (ProjectileEntity projectile : projectiles) {
                if ( planet.sqDistFrom(projectile) < (planet.getRadius() + projectile.getRadius()) * (planet.getRadius() + projectile.getRadius()) ) {
                    projectile.destroy();
                    planet.hit(projectile.getTeam());
                }
            }
        }

        Iterator<ProjectileEntity> iter = projectiles.iterator();
        while (iter.hasNext()) {
            ProjectileEntity proj = iter.next();
            if (proj.isDestroyed()) iter.remove();
        }
    }

    public void render(ShapeRenderer renderer) {
        for (ProjectileEntity projectile : projectiles) {
            projectile.render(renderer);
        }

        for (PlanetEntity planet : planets) {
            planet.render(renderer);
        }

        button.render(renderer);
    }
}
