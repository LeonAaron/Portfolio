package edu.unc.comp301;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UNCBuildingApiImpl implements UNCBuildingApi {
  private final Map<String, Building> buildings = new HashMap<>();

  @Override
  public Building getBuilding(String name) {
    if (name == null) {
      return null;
    }
    return buildings.get(name.toLowerCase());
  }

  @Override
  public List<String> getAllBuildingNames() {
    List<String> names = new ArrayList<>();
    for (Building b : buildings.values()) {
      names.add(b.getName());
    }
    return names;
  }

  @Override
  public void getDirections(String startBuilding, String endBuilding) {}

  public void add(String name, String desc) {
    Building b = new BuildingImpl(name, desc);
    buildings.put(name.toLowerCase(), b);
  }
}

// CHECKED
