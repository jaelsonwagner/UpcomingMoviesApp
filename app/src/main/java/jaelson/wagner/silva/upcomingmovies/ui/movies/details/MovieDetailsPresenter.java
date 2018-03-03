package jaelson.wagner.silva.upcomingmovies.ui.movies.details;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jaelson.wagner.silva.upcomingmovies.ApplicationConfiguration;
import jaelson.wagner.silva.upcomingmovies.common.BasePresenter;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.MovieDetailsResult;
import jaelson.wagner.silva.upcomingmovies.service.TheMovieDbRepository;
import timber.log.Timber;

import javax.inject.Inject;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class MovieDetailsPresenter
    extends BasePresenter<MovieDetailsContract.View>
    implements MovieDetailsContract.Presenter {

  private TheMovieDbRepository repository;
  private MovieDetailsContract.View view;
  private Disposable disposable;

  @Inject
  public MovieDetailsPresenter(TheMovieDbRepository repository,
                               MovieDetailsContract.View view) {
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
  public void fetchMovieDetails(int movieId) {
    view.showLoading(true);

    repository.getMoviesApi()
        .getMovieDetails(movieId, ApplicationConfiguration.getApiKey(), null, null)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<MovieDetailsResult>() {
          @Override
          public void onSubscribe(Disposable disposable) {
            MovieDetailsPresenter.this.disposable = disposable;
          }

          @Override
          public void onNext(MovieDetailsResult movieDetailsResult) {
            view.showMovieDetails(movieDetailsResult);
          }

          @Override
          public void onError(Throwable e) {
            Timber.d("%s", e.getMessage());
          }

          @Override
          public void onComplete() {
            view.showLoading(false);
          }
        });
  }

}
