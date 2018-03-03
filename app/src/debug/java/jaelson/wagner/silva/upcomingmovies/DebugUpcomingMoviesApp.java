package jaelson.wagner.silva.upcomingmovies;

import com.facebook.stetho.Stetho;
import timber.log.Timber;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class DebugUpcomingMoviesApp extends UpcomingMoviesApp {
  @Override
  public void onCreate() {
    super.onCreate();

    Stetho.initialize(
        Stetho.newInitializerBuilder(this)
            //.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
            .build()
    );

    Timber.plant(new Timber.DebugTree());

  }
}
