package msalah.mal.com.themovieapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.Movie;
import msalah.mal.com.themovieapp.fragment.MovieDetailsFragment;


public class MoviesDetailsActivity extends AppCompatActivity {

    Movie selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedMovie = getIntent().getParcelableExtra(AppConstants.SELECTED_MOVIE_KEY);

        Bundle bundle = new Bundle();
        bundle.putParcelable(AppConstants.SELECTED_MOVIE_KEY, selectedMovie);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(bundle);


        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, movieDetailsFragment).commit();
    }

}
