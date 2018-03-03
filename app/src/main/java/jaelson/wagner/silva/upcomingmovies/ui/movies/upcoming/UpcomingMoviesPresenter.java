package jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming;

import android.support.annotation.NonNull;
import android.util.SparseArray;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jaelson.wagner.silva.upcomingmovies.ApplicationConfiguration;
import jaelson.wagner.silva.upcomingmovies.common.BasePresenter;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.Genre;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.Movie;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.MoviesGenresResult;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.UpcomingMoviesResult;
import jaelson.wagner.silva.upcomingmovies.service.TheMovieDbRepository;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class UpcomingMoviesPresenter
    extends BasePresenter<UpcomingMoviesContract.View>
    implements UpcomingMoviesContract.Presenter {

  private TheMovieDbRepository repository;
  private UpcomingMoviesContract.View view;
  private SparseArray<String> moviesGenresMapping = new SparseArray<>();
  private Disposable disposable;

  @Inject
  public UpcomingMoviesPresenter(TheMovieDbRepository repository,
                                 UpcomingMoviesContract.View view) {
    this.repository = repository;
    this.view = view;
  }

  @Override
  public void detachFromView() {
    if (disposable != null && !disposable.isDisposed()) {
      disposable.dispose();
    }
    super.detachView();
  }

  @Override
  public void fetchUpcomingMovies(int pageNumber, int previousPageNumber) {
    view.showLoading(true);

    Observable<MoviesGenresResult> moviesGenresResultObservable = repository.getMoviesApi()
        .getMoviesGenres(ApplicationConfiguration.getApiKey(), null);

    Observable<UpcomingMoviesResult> upcomingMoviesResultObservable = repository.getMoviesApi()
        .getUpcoming(ApplicationConfiguration.getApiKey(), null, pageNumber, null);

    Observable<UpcomingMoviesResult> finalUpcomingMoviesResultObservable = moviesGenresMapping.size() == 0
        ? getFinalUpcomingMoviesResultObservable(moviesGenresResultObservable, upcomingMoviesResultObservable)
        : combinedAllMovieDataResult(upcomingMoviesResultObservable);

    finalUpcomingMoviesResultObservable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<UpcomingMoviesResult>() {
          @Override
          public void onSubscribe(Disposable disposable) {
            UpcomingMoviesPresenter.this.disposable = disposable;
          }

          @Override
          public void onNext(UpcomingMoviesResult response) {
            int requestedPageNumber = response.page;

            int totalPages = response.totalPages;

            // There is a bug in TMDB Api.
            // When reaching total of pages, the API behaves strange, some times, response.body().totalPages
            // it's incorrectly and response.body().results is empty.
            // So, this workaround checking if !response.results.isEmpty() solves that.

            int nextPageNumber = requestedPageNumber < totalPages && !response.results.isEmpty()
                ? requestedPageNumber + 1
                : requestedPageNumber;

            view.showUpcomingMovies(response.results, pageNumber, nextPageNumber);

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {
            view.showLoading(false);
          }
        });


  }

  @NonNull
  private Observable<UpcomingMoviesResult> combinedAllMovieDataResult(Observable<UpcomingMoviesResult> upcomingMoviesResultObservable) {
    return upcomingMoviesResultObservable.flatMap(upcomingMoviesResult -> Observable.just(getUpcomingMoviesWithGenresResult(upcomingMoviesResult)));
  }

  private Observable<UpcomingMoviesResult> getFinalUpcomingMoviesResultObservable(Observable<MoviesGenresResult> moviesGenresResultObservable,
                                                                                  Observable<UpcomingMoviesResult> upcomingMoviesResultObservable) {
    return Observable.zip(moviesGenresResultObservable,
        upcomingMoviesResultObservable,
        this::combinedAllMovieDataResult);
  }

  @NonNull
  private UpcomingMoviesResult combinedAllMovieDataResult(MoviesGenresResult moviesGenresResult,
                                                          UpcomingMoviesResult upcomingMoviesResult) {
    int length = moviesGenresResult.genres.size();

    for (int i = 0; i < length; i++) {
      Genre genre = moviesGenresResult.genres.get(i);
      moviesGenresMapping.put(genre.id, genre.name);
    }

    return getUpcomingMoviesWithGenresResult(upcomingMoviesResult);
  }

  @NonNull
  private UpcomingMoviesResult getUpcomingMoviesWithGenresResult(UpcomingMoviesResult upcomingMoviesResult) {
    List<Movie> movies = upcomingMoviesResult.results;
    int moviesLength = movies.size();

    for (int i = 0; i < moviesLength; i++) {

      Movie movie = movies.get(i);

      Collections.sort(movie.genreIds);

      StringBuilder sb = new StringBuilder();

      int movieGenresLength = movie.genreIds.size();

      for (int j = 0; j < movieGenresLength; j++) {
        String genreName = moviesGenresMapping.get(movie.genreIds.get(j));
        sb.append(genreName).append(" ");
      }

      movie.genresNameCombined = sb.toString().trim();

    }
    return upcomingMoviesResult;
  }
}
