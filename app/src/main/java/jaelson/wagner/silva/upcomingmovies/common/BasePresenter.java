package jaelson.wagner.silva.upcomingmovies.common;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public abstract class BasePresenter<V> implements BasePresenterContract<V> {

  private WeakReference<V> mView;

  @UiThread
  @Override
  public void attachView(@NonNull V view) {
    mView = new WeakReference<>(view);
  }

  @UiThread
  @Override
  public V getViewOrThrow() {
    V view = getView();
    if (view == null) {
      throw new IllegalStateException("The view must be attached! You must call attachView().");
    }
    return view;
  }

  @UiThread
  @Override
  public V getView() {
    return mView != null ? mView.get() : null;
  }

  @UiThread
  @Override
  public void detachView() {
    if (mView != null) {
      mView.clear();
      mView = null;
    }
  }

  @UiThread
  @Override
  public boolean isViewAttached() {
    return getView() != null;
  }
}
