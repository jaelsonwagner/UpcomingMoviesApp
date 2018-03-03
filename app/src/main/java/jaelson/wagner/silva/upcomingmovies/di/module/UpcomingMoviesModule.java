package jaelson.wagner.silva.upcomingmovies.di.module;

import dagger.Module;
import dagger.Provides;
import jaelson.wagner.silva.upcomingmovies.di.scope.FragmentScope;
import jaelson.wagner.silva.upcomingmovies.service.MoviesApi;
import jaelson.wagner.silva.upcomingmovies.service.TheMovieDbRepository;
import jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming.UpcomingMoviesContract;
import jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming.UpcomingMoviesPresenter;
import retrofit2.Retrofit;

/**
 * Created by jaelsonwagner@gmail.com.
 */

@Module
public class UpcomingMoviesModule {

  private UpcomingMoviesContract.View view;

  public UpcomingMoviesModule(UpcomingMoviesContract.View view) {
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
  UpcomingMoviesContract.Presenter providePresenter(TheMovieDbRepository theMovieDbRepository) {
    return new UpcomingMoviesPresenter(theMovieDbRepository, view);
  }
}
