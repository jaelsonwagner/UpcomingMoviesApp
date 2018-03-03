package jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import jaelson.wagner.silva.upcomingmovies.R;
import jaelson.wagner.silva.upcomingmovies.UpcomingMoviesApp;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.Movie;
import jaelson.wagner.silva.upcomingmovies.di.module.UpcomingMoviesModule;
import jaelson.wagner.silva.upcomingmovies.ui.common.EndlessRecyclerViewScrollListener;
import jaelson.wagner.silva.upcomingmovies.ui.movies.details.MovieDetailsActivity;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class UpcomingMoviesFragment
    extends Fragment
    implements UpcomingMoviesContract.View {

  private static final int FIRST_PAGE = 1;
  private static final int SPAN_COUNT_2 = 2;
  private static final int SPAN_COUNT_3 = 3;
  private static final int NUMBER_OF_REMAINING_ITEMS = 4;
  @Inject
  UpcomingMoviesContract.Presenter presenter;
  private RecyclerView recyclerView;
  private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
  private int nextPageNumber = FIRST_PAGE;
  private int previousPageNumber;
  private UpcomingMoviesAdapter upcomingMoviesAdapter;
  private SwipeRefreshLayout swipeRefreshLayout;

  public static UpcomingMoviesFragment newInstance() {
    return new UpcomingMoviesFragment();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_upcoming_movies, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    UpcomingMoviesApp.getApplicationComponent()
        .plus(new UpcomingMoviesModule(this))
        .inject(this);

    setupUI();

    presenter.fetchUpcomingMovies(FIRST_PAGE, previousPageNumber);
  }

  private void setupUI() {
    // setup adapter
    upcomingMoviesAdapter = new UpcomingMoviesAdapter((Movie movie) ->
    {
      Timber.d("%s", movie.title);
      Intent intent = new Intent(getContext(), MovieDetailsActivity.class);
      intent.putExtra(MovieDetailsActivity.MOVIE_ID, movie.id);
      startActivity(intent);
    });

    // setup layout manager
    int spanCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE
        ? SPAN_COUNT_3
        : SPAN_COUNT_2;

    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), spanCount);

    // setup endless scroll listener
    endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager, NUMBER_OF_REMAINING_ITEMS) {
      @Override
      public void onLoadMore() {
        if (nextPageNumber != previousPageNumber) {
          this.setRequestInProgress(true);
          presenter.fetchUpcomingMovies(nextPageNumber, previousPageNumber);
        }
      }
    };

    if (getView() == null)
      throw new NullPointerException("UI should not be configured. getView() method returns null.");

    // setup recycler view
    recyclerView = getView().findViewById(R.id.recycler_view_upcoming_movies);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
    recyclerView.setAdapter(upcomingMoviesAdapter);
    recyclerView.setHasFixedSize(true);

    // setup swipe refresh
    swipeRefreshLayout = getView().findViewById(R.id.swipe_refresh_layout);
    swipeRefreshLayout.setOnRefreshListener(() -> {
      if (!endlessRecyclerViewScrollListener.isRequestInProgress()) {
        presenter.fetchUpcomingMovies(FIRST_PAGE, previousPageNumber);
      }
    });
  }

  @Override
  public void showLoading(boolean isLoading) {
    swipeRefreshLayout.setRefreshing(isLoading);
  }

  @Override
  public void showUpcomingMovies(List<Movie> movies, int requestedPageNumber, int nextPageNumber) {
    if (!movies.isEmpty()) {

      if (requestedPageNumber == FIRST_PAGE) {
        upcomingMoviesAdapter.clear();
      }

      upcomingMoviesAdapter.appendData(movies);

      // Using post method to avoid exception.
      // Cannot call this method in a scroll callback. Scroll callbacks might be run during a measure
      // & layout pass where you cannot change the RecyclerView data.
      // Any method call that might change the structure of the android.support.v7.widget.RecyclerView
      // or the upcomingMoviesAdapter contents should be postponed to the next frame.
      recyclerView.post(upcomingMoviesAdapter::notifyDataSetChanged);

    }

    previousPageNumber = requestedPageNumber;
    this.nextPageNumber = nextPageNumber;
    endlessRecyclerViewScrollListener.setRequestInProgress(false);
  }

  @Override
  public void onDestroyView() {
    detachFromPresenter();
    super.onDestroyView();
  }

  @Override
  public void detachFromPresenter() {
    if (presenter != null) {
      presenter.detachFromView();
    }
    endlessRecyclerViewScrollListener.setRequestInProgress(false);
  }
}
