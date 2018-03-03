package jaelson.wagner.silva.upcomingmovies.di.subcomponent;

import dagger.Subcomponent;
import jaelson.wagner.silva.upcomingmovies.di.module.MovieDetailsModule;
import jaelson.wagner.silva.upcomingmovies.di.scope.FragmentScope;
import jaelson.wagner.silva.upcomingmovies.ui.movies.details.MovieDetailsFragment;

/**
 * Created by jaelsonwagner@gmail.com.
 */

@FragmentScope
@Subcomponent(modules = {MovieDetailsModule.class})
public interface MovieDetailsSubComponent {
  void inject(MovieDetailsFragment fragment);
}
