package org.example;

//2. Multiton - Write a multiton that holds templates of different
// GameMaps available for use.  The name of the environment should
// be the key, and the GameMap object should be the value.
// The constructor should take in a String id and save it to an
// instance variable that is used to create and identify the GameMap instance.

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private static Map<String, GameMap> directory = new HashMap<>();

    private String id;

    private GameMap(String id) {
        this.id = id;
    }

    public static GameMap create(String id) {
        if (!directory.containsKey(id)) {
            directory.put(id, new GameMap(id));
        }
        return directory.get(id);
    }
}
