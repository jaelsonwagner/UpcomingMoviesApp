package jaelson.wagner.silva.upcomingmovies.common;

import android.support.annotation.NonNull;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public interface BasePresenterContract<V> {

  void attachView(@NonNull V view);

  V getViewOrThrow();

  V getView();

  void detachView();

  boolean isViewAttached();

}

