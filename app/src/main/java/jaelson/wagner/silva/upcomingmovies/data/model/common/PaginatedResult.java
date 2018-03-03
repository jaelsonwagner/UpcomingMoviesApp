package jaelson.wagner.silva.upcomingmovies.data.model.common;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

import jaelson.wagner.silva.upcomingmovies.data.model.movies.Dates;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public abstract class PaginatedResult<T> extends BaseResult {

  private final static long serialVersionUID = -4386317032292707927L;
  @Json(name = "page")
  public int page;
  @Json(name = "results")
  public List<T> results = new ArrayList<>();
  @Json(name = "dates")
  public Dates dates;
  @Json(name = "total_pages")
  public int totalPages;
  @Json(name = "total_results")
  public int totalResults;

}

