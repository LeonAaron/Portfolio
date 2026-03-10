package org.example;

//You are working on the infrastructure for the next Game of the Year video game…
//
//        1. Singleton - Write a singleton that holds all of the player’s settings
//        ( so that it can be accessed in any part of the game.
//        The singleton should hold the following settings with associated
//        setters and getters:
//
//        int volume
//        boolean subtitlesEnabled

public class PlayerSettings {
    private static PlayerSettings singleton;

    private int volume;
    private boolean subtitlesEnabled;

    private PlayerSettings() {
        this.volume = 50;
        this.subtitlesEnabled = false;
    }

    public static PlayerSettings create() {
        if (singleton == null) {
            singleton = new PlayerSettings();
        }
        return singleton;
    }

    public int getVolume() {
        return volume;
    }

    public boolean getSubtitlesEnabled() {
        return subtitlesEnabled;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setSubtitlesEnabled(boolean enabled) {
        subtitlesEnabled = enabled;
    }
}
