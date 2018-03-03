package jaelson.wagner.silva.upcomingmovies.data.model.movies;

import com.squareup.moshi.Json;

import java.io.Serializable;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class SpokenLanguage implements Serializable {

  private final static long serialVersionUID = 1532454103128403218L;
  @Json(name = "iso_639_1")
  public String iso6391;
  @Json(name = "name")
  public String name;

}
