package jaelson.wagner.silva.upcomingmovies.di.subcomponent;

import dagger.Subcomponent;
import jaelson.wagner.silva.upcomingmovies.di.module.UpcomingMoviesModule;
import jaelson.wagner.silva.upcomingmovies.di.scope.FragmentScope;
import jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming.UpcomingMoviesFragment;

/**
 * Created by jaelsonwagner@gmail.com.
 */

@FragmentScope
@Subcomponent(modules = {UpcomingMoviesModule.class})
public interface UpcomingMoviesSubComponent {
  void inject(UpcomingMoviesFragment fragment);
}
