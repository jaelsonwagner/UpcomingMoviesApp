package jaelson.wagner.silva.upcomingmovies.data.model.movies;

import com.squareup.moshi.Json;

import java.io.Serializable;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class ProductionCountry implements Serializable {
  private final static long serialVersionUID = 8509713394709464965L;
  @Json(name = "iso_3166_1")
  public String iso31661;
  @Json(name = "name")
  public String name;
}
