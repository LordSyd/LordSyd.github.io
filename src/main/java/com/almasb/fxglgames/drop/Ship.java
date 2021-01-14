package com.almasb.fxglgames.drop;

import javafx.scene.Parent;

/**
 * This class instantiates ships that has been placed as well saving their orientation. Somewhat redundant at the moment,
 * a remnant from the example project that was used to plan the logic. But it is hooked into the main logic, so I
 * could not be bothered to integrate it's logic elsewhere - plus it maybe is useful for future-proofing.
 */

public class Ship extends Parent {
    private int type;
    private boolean vertical = true;
    private double x;
    private double y;

    private int health;

    public Ship(int type, boolean vertical,  double x, double y) {
        this.type = type;
        this.vertical = vertical;
        health = type;
        this.x = x;
        this.y = y;

    }


    public int getType() {
        return type;
    }



    public boolean isVertical() {
        return vertical;
    }


    public double getX() {
        return x;
    }



    public double getY() {
        return y;
    }



    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
