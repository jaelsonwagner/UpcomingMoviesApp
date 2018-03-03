package jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import jaelson.wagner.silva.upcomingmovies.ApplicationConfiguration;
import jaelson.wagner.silva.upcomingmovies.R;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.Movie;
import jaelson.wagner.silva.upcomingmovies.ui.common.DateUtil;
import jaelson.wagner.silva.upcomingmovies.ui.common.ImageUtil;

/**
 * Created by jaelsonwagner@gmail.com.
 */

class MovieViewHolder extends RecyclerView.ViewHolder {
  private static final String MOVIE_RELEASE_DATE_FORMAT = "MMMM dd, YYYY";
  private static final String API_DATE_FORMAT = "yyyy-MM-dd";
  private View view;
  private ImageView movieThumbnail;
  private TextView movieTitle;
  private TextView releaseDate;
  private TextView genres;
  private ViewClickListener<Movie> viewClickListener;

  MovieViewHolder(View view, ViewClickListener<Movie> viewClickListener) {
    super(view);
    this.view = view;
    this.viewClickListener = viewClickListener;
    movieThumbnail = view.findViewById(R.id.image_view_movie_thumbnail);
    movieTitle = view.findViewById(R.id.text_view_movie_title);
    releaseDate = view.findViewById(R.id.text_view_movie_release_date);
    genres = view.findViewById(R.id.text_view_movie_genres);
  }

  void updateValues(Movie movie) {
    if (movie != null) {

      // Some times, movie.posterPath is null, so, no image will be loaded.
      String posterUrl = ImageUtil.getPosterUrl(movie.posterPath);

      Glide.with(view.getContext())
          .load(posterUrl)
          .error(R.color.colorAccent)
          .fallback(R.color.colorPrimary)
          //.placeholder(android.R.color.holo_green_light)
          .diskCacheStrategy(DiskCacheStrategy.SOURCE)
          .crossFade()
          .into(movieThumbnail);

      movieTitle.setText(movie.title);
      releaseDate.setText(formattedReleaseDate(movie.releaseDate));
      genres.setText(movie.genresNameCombined);

      view.setOnClickListener((View view) -> {
        if (viewClickListener != null) {
          viewClickListener.onMovieClicked(movie);
        }
      });

    }
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

}
