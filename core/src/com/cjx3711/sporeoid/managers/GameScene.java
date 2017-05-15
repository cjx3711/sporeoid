package com.cjx3711.sporeoid.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.entities.AttractionEntity;
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
    private ArrayList<AttractionEntity> attractions; // Contains the lines

    private HomeBaseEntity leftBase;
    private HomeBaseEntity rightBase;

    public GameScene() {
        projectiles = new ArrayList<ProjectileEntity>();
        planets = new ArrayList<PlanetEntity>();
        collidables = new ArrayList<CollidableEntity>();
        attractions = new ArrayList<AttractionEntity>();


        addPlanet(ScalingUtil.rightOfCentre(50), ScalingUtil.aboveCentre(50), 30);
        addPlanet(ScalingUtil.leftOfCentre(50), ScalingUtil.belowCentre(50), 30);

        rightBase = addHomeBase(ScalingUtil.fromRight(50), 200, 1);
        leftBase = addHomeBase(ScalingUtil.fromLeft(50), 200, 2);
    }

    public PlanetEntity addPlanet(float x, float y, float rad) {
        PlanetEntity p = new PlanetEntity(x,y,rad);
        planets.add(p);
        collidables.add(p);
        return p;
    }
    public HomeBaseEntity addHomeBase(float x, float y, int team) {
        HomeBaseEntity h = new HomeBaseEntity(x, y, team);
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
        CollidableEntity nearest = null;
        for (CollidableEntity c : collidables) {
            float sqDist = c.getPos().distanceSquared(projectile.getPos());
            if ( sqDist < minSqDist ) {
                minSqDist = sqDist;
                nearest = c;
            }
        }
        // Apply delta to planet to projectile
        if ( nearest != null ) {
            Vect2D toPlanet = nearest.getPos().subtract(projectile.getPos());
            toPlanet.toUnit();
            toPlanet.scaleBy(100);
            toPlanet.scaleBy(delta);
            projectile.adjustVelocity(toPlanet);
            attractions.add(new AttractionEntity(projectile.getPos(), nearest.getPos()));
        }
    }
    void calculate(float delta) {
        attractions.clear();
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

        // Calculate planet health and stuff
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
                float rad = collidable.getHitRadius() + projectile.getRadius();
                if ( sqdist < rad * rad ) {
                    if ( collidable instanceof HomeBaseEntity ) {
                        HomeBaseEntity home = (HomeBaseEntity)collidable;
                        if ( home.getTeam() != projectile.getTeam() ) {
                            projectile.destroy();
                            collidable.hit(projectile.getTeam());
                        }
                    } else {
                        projectile.destroy();
                        collidable.hit(projectile.getTeam());
                    }

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
        for (AttractionEntity attract : attractions) {
            attract.render(renderer);
        }

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
