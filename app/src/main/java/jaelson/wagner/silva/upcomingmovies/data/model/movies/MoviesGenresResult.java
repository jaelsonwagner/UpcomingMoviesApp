package jaelson.wagner.silva.upcomingmovies.data.model.movies;

import com.squareup.moshi.Json;
import jaelson.wagner.silva.upcomingmovies.data.model.common.BaseResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class MoviesGenresResult extends BaseResult {
  private final static long serialVersionUID = -6159922782911364339L;
  @Json(name = "genres")
  public List<Genre> genres = new ArrayList<>();
}
