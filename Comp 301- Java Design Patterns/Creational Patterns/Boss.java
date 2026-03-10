package org.example;

public class Boss implements Enemy {
    protected Boss() {
        // intentionally empty
    }
    @Override
    public String getName() {
        return "Boss";
    }
    @Override
    public int getHitPoints() {
        return 250;
    }
    @Override
    public int getDamage() {
        return 25;
    }
}
