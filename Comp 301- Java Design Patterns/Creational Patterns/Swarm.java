package org.example;

public class Swarm implements Enemy {

    protected Swarm() {
        // intentionally empty
    }

    @Override
    public String getName() {
        return "Swarm";
    }

    @Override
    public int getHitPoints() {
        return 20;
    }

    @Override
    public int getDamage() {
        return 3;
    }
}
