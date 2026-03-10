package org.example;

//3. Factory - Your last task is to create enemies via a factory.
// Write a static factory method spawn(EnemyDifficulty difficulty)
// inside the Enemy class that creates and returns the appropriate
// enemy subtype based on the provided difficulty. If the difficulty
// is EASY, return a Grunt; if MEDIUM, return a Swarm; and if HARD,
// return a Boss, throwing an IllegalArgumentException if the difficulty is null.
//
//You are given the enum:
//
//EnemyDifficulty.javaDownload EnemyDifficulty.java
//
//And three classes of enemies:
//
//Boss.javaDownload Boss.java
//Swarm.javaDownload Swarm.java
//Grunt.javaDownload Grunt.java
//
//Write the parent class such that it is abstract and contains a static factory for Enemy Generation.

public abstract class Enemy {

    protected Enemy() {
        // Empty Constructor
    }

    public static Enemy spawn(EnemyDifficulty difficulty) {
        if (difficulty == null) {
            throw new IllegalArgumentException();
        }

        return switch (difficulty) {
            case EASY -> new Grunt();
            case MEDIUM -> new Swarm();
            case HARD -> new Boss();
        };

    }
    public abstract String getName();
    public abstract int getHitPoints();
    public abstract int getDamage();
}
