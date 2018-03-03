package jaelson.wagner.silva.upcomingmovies.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import jaelson.wagner.silva.upcomingmovies.R;

/**
 * Created by jaelsonwagner@gmail.com.
 */

public abstract class BaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_base);
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment        The fragment to be added.
   */
  protected void replaceFragment(int containerViewId, Fragment fragment) {
    FragmentManager supportFragmentManager = getSupportFragmentManager();

    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
    fragmentTransaction.replace(containerViewId, fragment);
    fragmentTransaction.commit();
  }
}
