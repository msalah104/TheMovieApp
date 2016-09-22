package msalah.mal.com.themovieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ListMenuItemView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.List;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.controllers.OnMovieSelected;
import msalah.mal.com.themovieapp.data.Movie;
import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.database.DatabaseHandler;
import msalah.mal.com.themovieapp.fragment.MainFragment;
import msalah.mal.com.themovieapp.fragment.MovieDetailsFragment;

public class MainActivity extends AppCompatActivity implements OnMovieSelected {

    public static final String FULL_LIST = "list";

    public static final String FAVORITES = "fav";

    public volatile static String shownListType = FULL_LIST;

    LinearLayout movieDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new MainFragment()).commit();

        movieDetails = (LinearLayout) findViewById(R.id.movies_detail);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_settings:
                //Toast.makeText(this, "ADD!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MovieAppPreferencesActivity.class);
                startActivity(i);
                return true;
            case R.id.menu_main_list:
                 MainActivity.shownListType = FULL_LIST;
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new MainFragment()).commit();
                return super.onOptionsItemSelected(item);
            case R.id.menu_main_favorites:
                MainActivity.shownListType = FAVORITES;
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new MainFragment()).commit();
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void movieSelected(Movie movie) {


        // check for tablet ui
        if (movieDetails != null) {


            DatabaseHandler databaseHandler = new DatabaseHandler(this);
            List<Movie> favList = databaseHandler.getAllMovies();

            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstants.SELECTED_MOVIE_KEY, movie);
            MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
            movieDetailsFragment.setArguments(bundle);

            for (Movie mov : favList) {
                if (mov.getId().equalsIgnoreCase(movie.getId())) {
                    MoviesDetailsActivity.isFaved = true;
                    break;
                }
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.movies_detail, movieDetailsFragment).commit();
        } else {
            Intent i = new Intent(this, MoviesDetailsActivity.class);
            i.putExtra(AppConstants.SELECTED_MOVIE_KEY, movie);
            startActivity(i);
        }

    }
}
