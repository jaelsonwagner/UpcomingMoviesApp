package jaelson.wagner.silva.upcomingmovies.data.model.movies;

import com.squareup.moshi.Json;
import jaelson.wagner.silva.upcomingmovies.data.model.common.BaseResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class MovieDetailsResult extends BaseResult {

  private final static long serialVersionUID = 3176472394883624377L;
  @Json(name = "poster_path")
  public String posterPath;
  @Json(name = "adult")
  public boolean adult;
  @Json(name = "overview")
  public String overview;
  @Json(name = "release_date")
  public String releaseDate;
  @Json(name = "id")
  public int id;
  @Json(name = "original_title")
  public String originalTitle;
  @Json(name = "original_language")
  public String originalLanguage;
  @Json(name = "title")
  public String title;
  @Json(name = "backdrop_path")
  public String backdropPath;
  @Json(name = "popularity")
  public float popularity;
  @Json(name = "vote_count")
  public int voteCount;
  @Json(name = "video")
  public boolean video;
  @Json(name = "vote_average")
  public float voteAverage;
  @Json(name = "belongs_to_collection")
  public Object belongsToCollection;
  @Json(name = "budget")
  public int budget;
  @Json(name = "genres")
  public List<Genre> genres = new ArrayList<>();
  @Json(name = "homepage")
  public String homepage;
  @Json(name = "imdb_id")
  public String imdbId;
  @Json(name = "production_companies")
  public List<ProductionCompany> productionCompanies = new ArrayList<>();
  @Json(name = "production_countries")
  public List<ProductionCountry> productionCountries = new ArrayList<>();
  @Json(name = "revenue")
  public int revenue;
  @Json(name = "runtime")
  public int runtime;
  @Json(name = "spoken_languages")
  public List<SpokenLanguage> spokenLanguages = new ArrayList<>();
  @Json(name = "status")
  public String status;
  @Json(name = "tagline")
  public String tagline;
}
