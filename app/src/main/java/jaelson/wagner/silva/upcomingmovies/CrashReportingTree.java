package jaelson.wagner.silva.upcomingmovies;

/**
 * Created by jaelsonwagner@gmail.com.
 */

import android.support.annotation.NonNull;
import android.util.Log;
import timber.log.Timber;

/**
 * A tree which logs important information for crash reporting.
 */
public class CrashReportingTree extends Timber.Tree {
  @Override
  protected void log(int priority, String tag, @NonNull String message, Throwable t) {
    if (priority == Log.VERBOSE || priority == Log.DEBUG) {
      return;
    }

    SomeCrashLibrary.log(priority, tag, message);

    if (t != null) {
      if (priority == Log.ERROR) {
        SomeCrashLibrary.logError(t);
      } else if (priority == Log.WARN) {
        SomeCrashLibrary.logWarning(t);
      }
    }
  }
}
