package jaelson.wagner.silva.upcomingmovies.ui.movies.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import jaelson.wagner.silva.upcomingmovies.ApplicationConfiguration;
import jaelson.wagner.silva.upcomingmovies.R;
import jaelson.wagner.silva.upcomingmovies.UpcomingMoviesApp;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.MovieDetailsResult;
import jaelson.wagner.silva.upcomingmovies.di.module.MovieDetailsModule;
import jaelson.wagner.silva.upcomingmovies.ui.common.DateUtil;
import jaelson.wagner.silva.upcomingmovies.ui.common.ImageUtil;

import javax.inject.Inject;

public class MovieDetailsFragment extends Fragment implements MovieDetailsContract.View {

  public final static String MOVIE_ID = "movieId";
  private static final String MOVIE_RELEASE_DATE_FORMAT = "MMMM dd, YYYY";
  private static final String API_DATE_FORMAT = "yyyy-MM-dd";
  @Inject
  MovieDetailsContract.Presenter presenter;
  private TextView movieTitle;
  private ImageView movieThumbnail;
  private TextView movieReleaseDate;
  private TextView movieGenres;
  private TextView movieOverview;

  public static MovieDetailsFragment newInstance(int movieId) {
    Bundle args = new Bundle();
    args.putInt(MOVIE_ID, movieId);

    MovieDetailsFragment fragment = new MovieDetailsFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_movie_details, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    UpcomingMoviesApp.getApplicationComponent()
        .plus(new MovieDetailsModule(this))
        .inject(this);

    setupUI();

    int movieId = getArguments().getInt(MOVIE_ID, 0);
    presenter.fetchMovieDetails(movieId);
  }

  private void setupUI() {
    if (getView() == null) return;

    movieTitle = getView().findViewById(R.id.text_view_movie_title);
    movieThumbnail = getView().findViewById(R.id.image_view_movie_thumbnail);
    movieReleaseDate = getView().findViewById(R.id.text_view_movie_release_date);
    movieGenres = getView().findViewById(R.id.text_view_movie_genres);
    movieOverview = getView().findViewById(R.id.text_view_movie_overview);
  }

  @Override
  public void detachFromPresenter() {
    if (presenter != null) {
      presenter.detachFromView();
    }
  }

  @Override
  public void showLoading(boolean isLoading) {

  }

  @Override
  public void showMovieDetails(MovieDetailsResult movieDetails) {
    if (movieDetails == null)
      return;

    // Some times, movie.posterPath is null, so, no image will be loaded.
    String posterUrl = ImageUtil.getPosterUrl(movieDetails.posterPath, ImageUtil.ImageSize.W_500);

    Glide.with(getActivity())
        .load(posterUrl)
        .error(R.color.colorAccent)
        .fallback(R.color.colorPrimary)
        //.placeholder(android.R.color.holo_green_light)
        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        .crossFade()
        .into(movieThumbnail);

    movieTitle.setText(movieDetails.title);
    movieReleaseDate.setText(formattedReleaseDate(movieDetails.releaseDate));

    StringBuilder combinedGenres = new StringBuilder();
    int length = movieDetails.genres.size();

    for (int i = 0; i < length; i++) {
      combinedGenres.append(movieDetails.genres.get(i).name).append(" ");
    }

    movieGenres.setText(combinedGenres.toString().trim());

    movieOverview.setText(movieDetails.overview);

  }

  private String formattedReleaseDate(String movieReleaseDate) {
    String formatted = DateUtil.formatDate(
        movieReleaseDate,
        API_DATE_FORMAT,
        ApplicationConfiguration.getLocale(),
        MOVIE_RELEASE_DATE_FORMAT
    );

    return formatted != null
        ? formatted.substring(0, 1).toUpperCase() + formatted.substring(1).toLowerCase()
        : null;
  }

  @Override
  public void onDestroyView() {
    detachFromPresenter();
    super.onDestroyView();
  }
}
