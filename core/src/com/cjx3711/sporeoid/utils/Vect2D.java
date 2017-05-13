package com.cjx3711.sporeoid.utils;

/**
 * Simple class to handle vector calculations
 */

public class Vect2D {
    private float x;
    private float y;

    public Vect2D() {
        x = y = 0;
    }
    public Vect2D(float x, float y) {
        set(x,y);
    }
    public Vect2D(Vect2D copy) {
        set(copy.x, copy.y);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vect2D vect2D = (Vect2D) o;

        if (Float.compare(vect2D.getX(), getX()) != 0) return false;
        return Float.compare(vect2D.getY(), getY()) == 0;

    }

    @Override
    public int hashCode() {
        int result = (getX() != +0.0f ? Float.floatToIntBits(getX()) : 0);
        result = 31 * result + (getY() != +0.0f ? Float.floatToIntBits(getY()) : 0);
        return result;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public void set(Vect2D pos) {
        this.x = pos.x;
        this.y = pos.y;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vect2D subtract(Vect2D subtractee) {
        return new Vect2D(x - subtractee.x, y - subtractee.y);
    }

    public Vect2D copy() {
        return new Vect2D(this);
    }

    public float distanceSquared() {
        return x * x + y * y;
    }
    public float distance() {
        return (float)Math.sqrt(distanceSquared());
    }


    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }


}
