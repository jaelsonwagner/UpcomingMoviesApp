package jaelson.wagner.silva.upcomingmovies;

import android.app.Application;
import jaelson.wagner.silva.upcomingmovies.di.component.ApplicationComponent;
import jaelson.wagner.silva.upcomingmovies.di.component.DaggerApplicationComponent;
import jaelson.wagner.silva.upcomingmovies.di.module.NetworkModule;
import timber.log.Timber;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class UpcomingMoviesApp extends Application {

  private static ApplicationComponent applicationComponent;

  public static ApplicationComponent getApplicationComponent() {
    if (applicationComponent == null) {
      throw new NullPointerException(
          String.format(
              "%s has not been properly initialized!",
              ApplicationComponent.class.getSimpleName()
          )
      );
    }

    return applicationComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    applicationComponent = DaggerApplicationComponent.builder()
        .networkModule(new NetworkModule())
        .build();

    Timber.plant(new CrashReportingTree());

  }
}
