package edu.unc.comp301;

import java.io.IOException;

public class BuildingImpl implements Building {
  private final String name;
  private final String description;
  // POSSIBLY ADD ARGUMENT LIKE IN INSTRUCTIONS
  private final NominatimGeocoder geocoder = new NominatimGeocoder("UNCGeocoder (leoa@unc.edu)");
  private Location loc;

  public BuildingImpl(String name, String description) {
    this.name = name;
    this.description = description;

    String address = name + ", Chapel Hill, North Carolina";

    try {
      this.loc = geocoder.geocode(address);
    } catch (IOException e) {
      System.err.println("IO Exception");
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public double getLatitude() {
    return this.loc.getLatitude();
  }

  @Override
  public double getLongitude() {
    return this.loc.getLongitude();
  }
}
