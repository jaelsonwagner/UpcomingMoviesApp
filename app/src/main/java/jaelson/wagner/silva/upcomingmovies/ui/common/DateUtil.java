package jaelson.wagner.silva.upcomingmovies.ui.common;

import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class DateUtil {

  private DateUtil() {

  }

  @Nullable
  public static String formatDate(String date, String dateFormat, Locale locale, String newDateFormat) {
    Locale finalLocale = locale != null ? locale : Locale.getDefault();
    Date validDate = parseDate(date, dateFormat, finalLocale);
    return validDate != null
        ? new SimpleDateFormat(newDateFormat, finalLocale).format(validDate)
        : null;
  }

  private static Date parseDate(String date, String dateFormat, Locale locale) {
    try {
      DateFormat df = new SimpleDateFormat(dateFormat, locale);
      df.setLenient(false);
      return df.parse(date);
    } catch (ParseException e) {
      // TODO: log exception here
      return null;
    }
  }

}
