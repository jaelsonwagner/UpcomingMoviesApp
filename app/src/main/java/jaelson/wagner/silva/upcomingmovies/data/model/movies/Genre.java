package jaelson.wagner.silva.upcomingmovies.data.model.movies;

import com.squareup.moshi.Json;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class Genre {
  private final static long serialVersionUID = -6015259598311002338L;
  @Json(name = "id")
  public int id;
  @Json(name = "name")
  public String name;
}