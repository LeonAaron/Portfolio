package edu.unc.comp301;

import java.util.List;

public class WalkingDirectionsServiceAdapter implements UNCBuildingApi {
  private WalkingDirectionsService wds;
  private UNCBuildingApi uncBuildingAPIImpl;

  public WalkingDirectionsServiceAdapter(
      WalkingDirectionsService wds, UNCBuildingApi uncBuildingAPIImpl) {
    this.wds = wds;
    this.uncBuildingAPIImpl = uncBuildingAPIImpl;
  }

  @Override
  public Building getBuilding(String name) {
    return uncBuildingAPIImpl.getBuilding(name);
  }

  @Override
  public List<String> getAllBuildingNames() {
    return uncBuildingAPIImpl.getAllBuildingNames();
  }

  @Override
  public void getDirections(String startBuilding, String endBuilding) {
    Building b1 = getBuilding(startBuilding);
    Building b2 = getBuilding(endBuilding);

    double lon1 = b1.getLongitude();
    double lat1 = b1.getLatitude();
    double lon2 = b2.getLongitude();
    double lat2 = b2.getLatitude();

    wds.getWalkingDirections(lat1, lon1, lat2, lon2);
  }
}
// CHECKED
