package jaelson.wagner.silva.upcomingmovies.ui.movies.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import jaelson.wagner.silva.upcomingmovies.R;
import jaelson.wagner.silva.upcomingmovies.ui.common.BaseActivity;

public class MovieDetailsActivity extends BaseActivity {

  public static final String MOVIE_ID = "movieId";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    int movieId = getIntent().getIntExtra(MOVIE_ID, 0);
    replaceFragment(R.id.view_content, MovieDetailsFragment.newInstance(movieId));
  }

}
