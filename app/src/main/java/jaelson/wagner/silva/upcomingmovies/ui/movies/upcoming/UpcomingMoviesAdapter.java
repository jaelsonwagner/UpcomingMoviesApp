package jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import jaelson.wagner.silva.upcomingmovies.R;
import jaelson.wagner.silva.upcomingmovies.data.model.movies.Movie;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public class UpcomingMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<Movie> movies = new ArrayList<>(0);
  private ViewClickListener<Movie> viewClickListener;

  UpcomingMoviesAdapter(ViewClickListener<Movie> viewClickListener) {
    this.viewClickListener = viewClickListener;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_upcoming_movie, parent, false);
    return new MovieViewHolder(view, viewClickListener);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (!movies.isEmpty() && position != RecyclerView.NO_POSITION) {
      MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
      movieViewHolder.updateValues(movies.get(position));
    }
  }

  @Override
  public int getItemCount() {
    return movies.size();
  }

  void appendData(List<Movie> movies) {
    if (!movies.isEmpty()) {
      this.movies.removeAll(movies);
      this.movies.addAll(movies);
    }
  }

  void clear() {
    movies.clear();
  }

}
