package edu.unc.comp301;

import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class NominatimGeocoder {
  private final OkHttpClient client = new OkHttpClient();
  private final String userAgent;

  private final String baseURL = "https://nominatim.openstreetmap.org/search";

  public NominatimGeocoder(String userAgent) {
    this.userAgent = userAgent;
  }

  public String createURL(String address) {

    // .toString() to make string
    return HttpUrl.parse(baseURL)
        .newBuilder()
        .addQueryParameter("q", address)
        .addQueryParameter("format", "json")
        .addQueryParameter("limit", "1")
        .build()
        .toString();
  }

  public Request buildRequest(String url) {
    return new Request.Builder().url(url).header("User-Agent", userAgent).get().build();
  }

  public String sendRequest(Request request) throws IOException {

    try (Response resp = client.newCall(request).execute()) {
      if (!resp.isSuccessful()) {
        throw new IOException();
      }
      if (resp.body() == null) {
        throw new IOException();
      }

      return resp.body().string();
    }
  }

  public Location parseLocation(String json) throws IOException {
    JSONArray results = new JSONArray(json);

    if (results.isEmpty()) {
      return null;
    }

    JSONObject object = results.getJSONObject(0);
    double longitude = object.getDouble("lon");
    double latitude = object.getDouble("lat");

    return new Location(latitude, longitude);
  }

  public Location geocode(String address) throws IOException {
    String url = createURL(address);
    Request request = buildRequest(url);
    String result = sendRequest(request);
    return parseLocation(result);
  }
}

// CHECKED
