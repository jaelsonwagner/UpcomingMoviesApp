package jaelson.wagner.silva.upcomingmovies.ui.common;

import android.support.annotation.StringDef;
import android.text.TextUtils;

import javax.annotation.Nonnull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static jaelson.wagner.silva.upcomingmovies.ui.common.ImageUtil.ImageSize.*;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class ImageUtil {

  private static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/";

  private ImageUtil() {
  }

  public static String getPosterUrl(String posterPath) {
    if (TextUtils.isEmpty(posterPath)) {
      return null;
    }

    return BASE_POSTER_URL + W_342 + "/" + posterPath;
  }

  public static String getPosterUrl(String posterPath, @Nonnull @ImageSize String imageSize) {
    if (TextUtils.isEmpty(posterPath)) {
      return null;
    }

    return BASE_POSTER_URL + imageSize + "/" + posterPath;
  }

  @StringDef({W_185, W_342, W_500, W_780})
  @Retention(RetentionPolicy.SOURCE)
  public @interface ImageSize {
    String W_185 = "w185";
    String W_342 = "w342";
    String W_500 = "w500";
    String W_780 = "w780";
  }
}
