package msalah.mal.com.themovieapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.activity.MainActivity;
import msalah.mal.com.themovieapp.activity.MoviesDetailsActivity;
import msalah.mal.com.themovieapp.adaptor.MovieListAdaptor;
import msalah.mal.com.themovieapp.controllers.MoviesDataController;
import msalah.mal.com.themovieapp.controllers.OnMovieSelected;
import msalah.mal.com.themovieapp.controllers.connection.OnDataReceivedListener;
import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.Movie;
import msalah.mal.com.themovieapp.data.database.DatabaseHandler;


public class MainFragment extends Fragment implements OnDataReceivedListener, View.OnClickListener {

    private static final int NUMBER_OF_VIEWS_IN_GRID_ROW = 2;

    RecyclerView recyclerView;
    MovieListAdaptor movieListAdaptor;
    MoviesDataController moviesDataController;
    private AppConstants.SortingType type;
    private List<Movie> movies;
    private GridLayoutManager lLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        lLayout = new GridLayoutManager(getActivity(), NUMBER_OF_VIEWS_IN_GRID_ROW);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.movies_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);


        movieListAdaptor = new MovieListAdaptor(new ArrayList<Movie>(), this);
        recyclerView.setAdapter(movieListAdaptor);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortingType = sharedPrefs.getString(
                getString(R.string.key_settings_list_title),
                getString(R.string.key_top_rated));
        AppConstants.SortingType currentType = AppConstants.SortingType.valueOf(sortingType);

        if (MainActivity.shownListType == MainActivity.FAVORITES) {
            DatabaseHandler db = new DatabaseHandler(getActivity());
            int numberOfMovies = db.getMoviesCount();
            if(numberOfMovies != 0)
            {
                //Toast.makeText(getActivity(), "Favourite list has "+numberOfMovies+" items", Toast.LENGTH_LONG).show();

                movies = new ArrayList<Movie>();
                movies = db.getAllMovies();
                movieListAdaptor.setMovies(movies);
                movieListAdaptor.notifyDataSetChanged();
            }
            else
            {
                Toast.makeText(getActivity(), "Favourite list is empty", Toast.LENGTH_LONG).show();
            }

        } else {
            if ((movies == null )|| currentType != type) {
                type = currentType;
                moviesDataController = new MoviesDataController(type);
                moviesDataController.getDataList(this, null);
            }
        }

    }


    @Override
    public void onRequestSuccessWithData(Object data, Object tag) {
        if ((data != null) && ((ArrayList)data).size() > 0){
            movies = (List<Movie>) data;
            movieListAdaptor.setMovies(movies);
            movieListAdaptor.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestFailed(String error, Object tag) {

    }


    @Override
    public void onClick(View view) {
        int viewPosition = recyclerView.getChildAdapterPosition(view);
        Movie selectedMovie = movies.get(viewPosition);
        ((OnMovieSelected)getActivity()).movieSelected(selectedMovie);
    }
}
