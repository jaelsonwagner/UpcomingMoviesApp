package jaelson.wagner.silva.upcomingmovies.data.model.movies;

import com.squareup.moshi.Json;

import java.io.Serializable;


/**
 * Created by jaelsonwagner@gmail.com.
 */
public class ProductionCompany implements Serializable {

  private final static long serialVersionUID = 6429775753194117285L;
  @Json(name = "name")
  public String name;
  @Json(name = "id")
  public int id;

}
