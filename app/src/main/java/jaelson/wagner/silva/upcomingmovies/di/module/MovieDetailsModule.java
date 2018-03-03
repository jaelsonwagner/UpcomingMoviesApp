package jaelson.wagner.silva.upcomingmovies.di.module;

import dagger.Module;
import dagger.Provides;
import jaelson.wagner.silva.upcomingmovies.di.scope.FragmentScope;
import jaelson.wagner.silva.upcomingmovies.service.MoviesApi;
import jaelson.wagner.silva.upcomingmovies.service.TheMovieDbRepository;
import jaelson.wagner.silva.upcomingmovies.ui.movies.details.MovieDetailsContract;
import jaelson.wagner.silva.upcomingmovies.ui.movies.details.MovieDetailsPresenter;
import retrofit2.Retrofit;

/**
 * Created by jaelsonwagner@gmail.com.
 */

@Module
public class MovieDetailsModule {

  private MovieDetailsContract.View view;

  public MovieDetailsModule(MovieDetailsContract.View view) {
    this.view = view;
  }

  @Provides
  MoviesApi provideMoviesApi(Retrofit retrofit) {
    return retrofit.create(MoviesApi.class);
  }

  @Provides
  TheMovieDbRepository provideRepository(MoviesApi moviesApi) {
    return new TheMovieDbRepository(moviesApi);
  }

  @Provides
  @FragmentScope
  MovieDetailsContract.Presenter providePresenter(TheMovieDbRepository theMovieDbRepository) {
    return new MovieDetailsPresenter(theMovieDbRepository, view);
  }
}
