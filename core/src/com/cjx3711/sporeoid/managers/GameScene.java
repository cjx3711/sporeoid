package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.entities.BaseEntity;
import com.cjx3711.sporeoid.entities.ClickableEntity;
import com.cjx3711.sporeoid.entities.CollidableEntity;
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
    private ArrayList<CollidableEntity> collidables; // Contains PlanetEntities and HomeBaseEntities

    private HomeBaseEntity leftBase;
    private HomeBaseEntity rightBase;

    public GameScene() {
        projectiles = new ArrayList<ProjectileEntity>();
        planets = new ArrayList<PlanetEntity>();
        collidables = new ArrayList<CollidableEntity>();


        addPlanet(ScalingUtil.rightOfCentre(50), ScalingUtil.aboveCentre(50), 30);
        addPlanet(ScalingUtil.leftOfCentre(50), ScalingUtil.belowCentre(50), 30);

        leftBase = addHomeBase(ScalingUtil.fromLeft(50), 200);
        rightBase = addHomeBase(ScalingUtil.fromRight(50), 200);
    }

    public void addPlanet(float x, float y, float rad) {
        PlanetEntity p = new PlanetEntity(x,y,rad);
        planets.add(p);
        collidables.add(p);
    }
    public HomeBaseEntity addHomeBase(float x, float y) {
        HomeBaseEntity h = new HomeBaseEntity(x, y);
        collidables.add(h);
        return h;
    }

    public void addProjectile(Vect2D pos, Vect2D vel, int team) {
        ProjectileEntity projectileEntity = new ProjectileEntity(pos, vel, team);
        projectiles.add(projectileEntity);
    }

    void attract(ProjectileEntity projectile, float delta) {
        // Search for nearest collidable
        float minSqDist = 99999999;
        BaseEntity nearest = null;
        for (BaseEntity e : planets) {
            float sqDist = e.sqDistFrom(projectile);
            if ( sqDist < minSqDist ) {
                minSqDist = sqDist;
                nearest = e;
            }
        }
        // Apply delta to planet to projectile
        if ( nearest != null ) {
            Vect2D toPlanet = nearest.getPos().subtract(projectile.getPos());
            toPlanet.scaleBy(0.6f);
            toPlanet.scaleBy(delta);
            projectile.adjustVelocity(toPlanet);
        }
    }
    void calculate(float delta) {
        leftBase.calculate(delta);
        rightBase.calculate(delta);

        // Drift towards nearest collidable

        for (ProjectileEntity projectile : projectiles) {
            if ( projectile.getTeam() == 1 && rightBase.getState() == 1 ) {
                attract(projectile, delta);
            } else if (projectile.getTeam() == 2 && leftBase.getState() == 1) {
                attract(projectile, delta);
            }
        }


        for (PlanetEntity planet : planets) {
            planet.calculate(delta);
        }

        for (ProjectileEntity projectile : projectiles) {
            projectile.calculate(delta);
        }



        // Collisions
        for (CollidableEntity collidable : collidables) {
            for (ProjectileEntity projectile : projectiles) {
                float sqdist = collidable.getPos().distanceSquared(projectile.getPos());
                float rad = collidable.getRadius() + projectile.getRadius();
                if ( sqdist < rad * rad ) {
                    projectile.destroy();
                    collidable.hit(projectile.getTeam());
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

        leftBase.render(renderer);
        rightBase.render(renderer);
    }
}
