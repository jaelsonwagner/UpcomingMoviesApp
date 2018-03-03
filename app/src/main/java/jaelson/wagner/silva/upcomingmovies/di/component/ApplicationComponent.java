package jaelson.wagner.silva.upcomingmovies.di.component;

import dagger.Component;
import jaelson.wagner.silva.upcomingmovies.di.module.ApplicationModule;
import jaelson.wagner.silva.upcomingmovies.di.module.MovieDetailsModule;
import jaelson.wagner.silva.upcomingmovies.di.module.NetworkModule;
import jaelson.wagner.silva.upcomingmovies.di.module.UpcomingMoviesModule;
import jaelson.wagner.silva.upcomingmovies.di.subcomponent.MovieDetailsSubComponent;
import jaelson.wagner.silva.upcomingmovies.di.subcomponent.UpcomingMoviesSubComponent;

import javax.inject.Singleton;

/**
 * Created by jaelsonwagner@gmail.com.
 */

@Singleton
@Component(modules = {
    ApplicationModule.class,
    NetworkModule.class
})
public interface ApplicationComponent {
  UpcomingMoviesSubComponent plus(UpcomingMoviesModule module);

  MovieDetailsSubComponent plus(MovieDetailsModule module);
}
