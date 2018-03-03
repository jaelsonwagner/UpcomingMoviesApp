package jaelson.wagner.silva.upcomingmovies.data.model.movies;

import com.squareup.moshi.Json;

import java.io.Serializable;

public class Dates implements Serializable {
  private final static long serialVersionUID = 9053476674906532092L;
  @Json(name = "maximum")
  public String maximum;
  @Json(name = "minimum")
  public String minimum;
}