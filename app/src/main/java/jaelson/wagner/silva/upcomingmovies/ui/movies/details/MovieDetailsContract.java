package jaelson.wagner.silva.upcomingmovies.ui.movies.details;

import jaelson.wagner.silva.upcomingmovies.common.BasePresenterContract;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.MovieDetailsResult;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public interface MovieDetailsContract {

  interface View {

    /***
     * Call this method when onDetachView() is called.
     */
    void detachFromPresenter();

    void showLoading(boolean isLoading);

    void showMovieDetails(MovieDetailsResult movieDetails);

  }

  interface Presenter extends BasePresenterContract<MovieDetailsContract.View> {

    /***
     * Detach view and unsubscribe any existent subscribers here.
     */
    void detachFromView();

    void fetchMovieDetails(int movieId);

  }

}
