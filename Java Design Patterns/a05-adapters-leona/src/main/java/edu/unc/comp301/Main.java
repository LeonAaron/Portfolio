package edu.unc.comp301;

import java.io.InputStream;
import java.util.Properties;

public class Main {
  public static void main(String[] args) {
    // OpenRoutesService API Key
    Properties cfg = new Properties();
    try (InputStream in = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
      cfg.load(in);
    } catch (Exception e) {
      // Error
    }
    String apiKey = cfg.getProperty("ors.api.key");

    UNCBuildingApiImpl unc = new UNCBuildingApiImpl();
    unc.add("sitterson hall", "computer science");
    unc.add("hamilton hall", "humanities");

    try {
      WalkingDirectionsService wds = new WalkingDirectionsService(cfg.getProperty("ors.api.key"));
      WalkingDirectionsServiceAdapter adapter = new WalkingDirectionsServiceAdapter(wds, unc);
      adapter.getDirections("sitterson hall", "hamilton hall");
    } catch (Exception e) {
      // Error
    }
  }
}
