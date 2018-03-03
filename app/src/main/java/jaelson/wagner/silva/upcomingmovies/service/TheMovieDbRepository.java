package jaelson.wagner.silva.upcomingmovies.service;

import javax.inject.Inject;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class TheMovieDbRepository {

  private MoviesApi moviesApi;

  @Inject
  public TheMovieDbRepository(MoviesApi moviesApi) {
    this.moviesApi = moviesApi;
  }

  public MoviesApi getMoviesApi() {
    return moviesApi;
  }
}
