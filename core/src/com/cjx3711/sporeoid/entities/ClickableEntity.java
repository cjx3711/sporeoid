package com.cjx3711.sporeoid.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.cjx3711.sporeoid.callbacks.TouchCallback;
import com.cjx3711.sporeoid.managers.TouchInfo;
import com.cjx3711.sporeoid.utils.ScalingUtil;
import com.cjx3711.sporeoid.utils.Vect2D;

import java.util.ArrayList;

/**
 * This also contains it's own list to keep track of all clickable things.
 * Entity that allows clickyness
 */

public abstract class ClickableEntity extends BaseEntity {
    private static ArrayList<ClickableEntity> clickables = new ArrayList<ClickableEntity>();
    public static void processClick(TouchInfo touch) {
        for (ClickableEntity entity: clickables) {
            if ( entity.processSingleClick(touch) ) {
                break;
            }
        }
    }
    protected TouchCallback touchCallback;
    protected boolean touched = false;
    protected float radius;

    public ClickableEntity(float x, float y) {
        super(x, y);
        radius = 30;
        initTouchCallback();
        clickables.add(this);
    }

    ClickableEntity(Vect2D pos) {
        super(pos);
        radius = 30;
        initTouchCallback();
        clickables.add(this);
    }

    protected abstract void initTouchCallback();

    @Override
    public void render(ShapeRenderer shapeRenderer) {
        if ( touched ) {
            shapeRenderer.setColor(Color.GREEN);
        } else {
            shapeRenderer.setColor(Color.BLACK);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.circle(
                ScalingUtil.standardToScreen(pos.getX()),
                ScalingUtil.standardToScreen(pos.getY()),
                ScalingUtil.standardToScreen(radius));
        shapeRenderer.end();
    }

    public boolean processSingleClick(TouchInfo touch) {
        float diffX = touch.getStart().getX() - pos.getX();
        float diffY = touch.getStart().getY() - pos.getY();
        if ( diffX * diffX + diffY * diffY < radius * radius ) {
            touch.setTouchCallback(touchCallback);
            return true;
        }
        return false;
    }
}
