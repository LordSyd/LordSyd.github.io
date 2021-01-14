package com.almasb.fxglgames.drop;

import javafx.scene.Parent;

/**
 * This class instantiates ships that has been placed as well saving their orientation. Somewhat redundant at the moment,
 * a remnant from the example project that was used to plan the logic. But it is hooked into the main logic, so I
 * could not be bothered to integrate it's logic elsewhere - plus it maybe is useful for future-proofing.
 */

public class Ship extends Parent {
    public int type;
    public boolean vertical = true;

    private int health;

    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        health = type;


    }



    public void hit() {
        health--;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
