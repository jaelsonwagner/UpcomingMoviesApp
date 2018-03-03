package jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming;

import java.util.List;

import jaelson.wagner.silva.upcomingmovies.common.BasePresenterContract;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.Movie;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public interface UpcomingMoviesContract {

  interface View {

    /***
     * Call this method when onDetachView() is called.
     */
    void detachFromPresenter();

    void showLoading(boolean isLoading);

    void showUpcomingMovies(List<Movie> movies, int requestedPageNumber, int nextPageNumber);

  }

  interface Presenter extends BasePresenterContract<UpcomingMoviesContract.View> {

    /***
     * Detach view and unsubscribe any existent subscribers here.
     */
    void detachFromView();

    void fetchUpcomingMovies(int pageNumber, int previousPageNumber);

  }

}
