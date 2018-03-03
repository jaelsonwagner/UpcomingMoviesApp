package jaelson.wagner.silva.upcomingmovies.ui.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

  private final int numberOfRemainingItems;
  private AtomicBoolean isRequestInProgress = new AtomicBoolean();
  private LinearLayoutManager linearLayoutManager;

  public EndlessRecyclerViewScrollListener(LinearLayoutManager linearLayoutManager,
                                           int numberOfRemainingItems) {
    this.linearLayoutManager = linearLayoutManager;
    this.numberOfRemainingItems = numberOfRemainingItems;
    isRequestInProgress.set(false);
  }

  public boolean isRequestInProgress() {
    return isRequestInProgress.get();
  }

  public void setRequestInProgress(boolean isRequestInProgress) {
    this.isRequestInProgress.set(isRequestInProgress);
  }

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

    if (dy > 0 && !isRequestInProgress.get()) {

      int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
      int totalItemCount = linearLayoutManager.getItemCount();

      if (totalItemCount <= lastVisibleItemPosition + numberOfRemainingItems) {
        onLoadMore();
      }

    }

  }

  public abstract void onLoadMore();

}

