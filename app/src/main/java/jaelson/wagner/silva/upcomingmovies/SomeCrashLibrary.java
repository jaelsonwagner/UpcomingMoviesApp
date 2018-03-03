package jaelson.wagner.silva.upcomingmovies;

/**
 * Created by jaelsonwagner@gmail.com.
 */


/**
 * Not a real crash reporting library!
 */
public class SomeCrashLibrary {

  private SomeCrashLibrary() {
    throw new AssertionError("No instances.");
  }

  public static void log(int priority, String tag, String message) {
    // TODO add log entry to circular buffer.
  }

  public static void logWarning(Throwable t) {
    // TODO report non-fatal warning.
  }

  public static void logError(Throwable t) {
    // TODO report non-fatal error.
  }
}