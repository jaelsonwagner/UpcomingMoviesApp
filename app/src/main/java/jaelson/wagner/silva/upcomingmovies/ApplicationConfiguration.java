package jaelson.wagner.silva.upcomingmovies;

import android.support.annotation.IntDef;
import android.util.SparseArray;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

import static jaelson.wagner.silva.upcomingmovies.ApplicationConfiguration.ApplicationConfigurationParameter.TMDB_API_KEY;
import static jaelson.wagner.silva.upcomingmovies.ApplicationConfiguration.ApplicationConfigurationParameter.TMDB_BASE_URL;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class ApplicationConfiguration {

  private static final SparseArray<String> applicationParameterCacheMap = new SparseArray<>();

  static {
    applicationParameterCacheMap.put(ApplicationConfigurationParameter.TMDB_BASE_URL, BuildConfig.TMDB_BASE_URL);
    applicationParameterCacheMap.put(ApplicationConfigurationParameter.TMDB_API_KEY, BuildConfig.TMDB_API_KEY);
  }

  public static String getBaseUrl() {
    return applicationParameterCacheMap.get(ApplicationConfigurationParameter.TMDB_BASE_URL);
  }

  public static String getApiKey() {
    return applicationParameterCacheMap.get(ApplicationConfigurationParameter.TMDB_API_KEY);
  }

  public static Locale getLocale() {
    return Locale.getDefault();
  }

  @IntDef({TMDB_BASE_URL, TMDB_API_KEY})
  @Retention(RetentionPolicy.SOURCE)
  @interface ApplicationConfigurationParameter {
    int TMDB_BASE_URL = 0;
    int TMDB_API_KEY = 1;
  }

}
