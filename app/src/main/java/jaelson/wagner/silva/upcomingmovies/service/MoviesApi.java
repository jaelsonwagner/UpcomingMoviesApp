package jaelson.wagner.silva.upcomingmovies.service;

import io.reactivex.Observable;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.MovieDetailsResult;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.MoviesGenresResult;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.UpcomingMoviesResult;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public interface MoviesApi {

  @GET("/3/movie/upcoming")
  Observable<UpcomingMoviesResult> getUpcoming(@Query("api_key") String apiKey,
                                               @Query("language") String language,
                                               @Query("page") int page,
                                               @Query("region") String region);

  @GET("/3/genre/movie/list")
  Observable<MoviesGenresResult> getMoviesGenres(@Query("api_key") String apiKey,
                                                 @Query("language") String language);

  @GET("/3/movie/{movie_id}")
  Observable<MovieDetailsResult> getMovieDetails(@Path("movie_id") int movie_id,
                                                 @Query("api_key") String apiKey,
                                                 @Query("language") String language,
                                                 @Query("append_to_response") String append_to_response);


}
