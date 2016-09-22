package msalah.mal.com.themovieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.Movie;
import msalah.mal.com.themovieapp.data.database.DatabaseHandler;
import msalah.mal.com.themovieapp.fragment.MovieDetailsFragment;


public class MoviesDetailsActivity extends AppCompatActivity {

    Movie selectedMovie;

    List <Movie> favList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedMovie = getIntent().getParcelableExtra(AppConstants.SELECTED_MOVIE_KEY);

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        favList = databaseHandler.getAllMovies();

        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.SELECTED_MOVIE_KEY, selectedMovie);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, movieDetailsFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        boolean isFaved = false;

        for (Movie movie : favList) {
            if (movie.getId().equalsIgnoreCase(selectedMovie.getId())) {
                isFaved = true;
                break;
            }
        }

        if (isFaved) {
            getMenuInflater().inflate(R.menu.menu_detials_fav, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_detials, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_already_in_fav:
                return true;
            case R.id.menu_main_ads_favorites:
                addToFavorites();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }

    public void addToFavorites ()
    {
        final DatabaseHandler db = new DatabaseHandler(this);
        db.addMovie(selectedMovie);
    }

}
