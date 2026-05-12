package edu.unc.comp301;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class WalkingDirectionsService {
  private final String apiKey;
  private final OkHttpClient client = new OkHttpClient();

  private final String userAgent = "UNCGeocoder (leoa@unc.edu)";

  private final String baseURL = "https://api.openrouteservice.org/v2/directions/foot-walking";

  public WalkingDirectionsService(String apiKey) {
    this.apiKey = apiKey;
  }

  public String createURL(double lat1, double lon1, double lat2, double lon2) {
    String slon1 = Double.toString(lon1);
    String slat1 = Double.toString(lat1);
    String slon2 = Double.toString(lon2);
    String slat2 = Double.toString(lat2);

    return HttpUrl.parse(baseURL)
        .newBuilder()
        .addQueryParameter("api_key", apiKey)
        .addQueryParameter("start", slon1 + "," + slat1)
        .addQueryParameter("end", slon2 + "," + slat2)
        .addQueryParameter("steps", "true")
        .build()
        .toString();
  }

  public Request buildRequest(String url) {
    return new Request.Builder().url(url).header("User-Agent", userAgent).get().build();
  }

  public String sendRequest(Request request) throws IOException {

    try (Response resp = client.newCall(request).execute()) {
      if (!resp.isSuccessful() || resp.body() == null) {
        throw new IOException();
      }
      return resp.body().string();
    }
  }

  public DirectionsResult parseDirections(String json) {
    JSONObject obj = new JSONObject(json);
    JSONArray segments =
        obj.getJSONArray("features")
            .getJSONObject(0)
            .getJSONObject("properties")
            .getJSONArray("segments");

    List<Step> stepList = new ArrayList<>();
    double totalDistance = 0.0;
    double totalTime = 0.0;

    // Use regular for loop for index
    for (int i = 0; i < segments.length(); i++) {
      // Get steps JSONArray from every individual segment
      JSONArray steps = segments.getJSONObject(i).getJSONArray("steps");

      for (int j = 0; j < steps.length(); j++) {
        JSONObject step = steps.getJSONObject(j);

        String instruction = step.getString("instruction");
        double distance = step.getDouble("distance");
        double duration = step.getDouble("duration");

        stepList.add(new Step(instruction, distance, duration));
        totalDistance += distance;
        totalTime += duration;
      }
    }

    return new DirectionsResult(stepList, totalDistance, totalTime);
  }

  public void getWalkingDirections(double lat1, double lon1, double lat2, double lon2) {
    String url = createURL(lat1, lon1, lat2, lon2);
    Request request = buildRequest(url);

    try {
      String result = sendRequest(request);
      DirectionsResult directionsResult = parseDirections(result);

      int turn = 1;
      for (Step step : directionsResult.getSteps()) {
        System.out.println(
            turn
                + ". "
                + step.getInstruction()
                + " - ("
                + step.getDistance()
                + " meters, "
                + step.getDuration()
                + " seconds)");
        System.out.println("Total distance: " + directionsResult.getTotalDistance() + " m");
        System.out.println("Total time: " + directionsResult.getTotalDuration() + " s");
        turn++;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

// WORKS
