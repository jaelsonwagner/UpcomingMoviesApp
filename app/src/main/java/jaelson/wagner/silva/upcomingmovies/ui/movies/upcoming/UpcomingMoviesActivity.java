package jaelson.wagner.silva.upcomingmovies.ui.movies.upcoming;

import android.os.Bundle;
import android.support.annotation.Nullable;

import jaelson.wagner.silva.upcomingmovies.R;
import jaelson.wagner.silva.upcomingmovies.ui.common.BaseActivity;

public class UpcomingMoviesActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    replaceFragment(R.id.view_content, UpcomingMoviesFragment.newInstance());
  }

}
