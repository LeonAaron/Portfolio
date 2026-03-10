package org.example;

public class Grunt extends Enemy {

    protected Grunt() {
        // intentionally empty
    }
    @Override
    public String getName() {
        return "Grunt";
    }
    @Override
    public int getHitPoints() {
        return 30;
    }
    @Override
    public int getDamage() {
        return 5;
    }
}
