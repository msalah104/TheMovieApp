package msalah.mal.com.themovieapp.controllers.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import msalah.mal.com.themovieapp.data.Movie;

/**
 * Created by user on 8/10/16.
 */

public class MoviesParser implements Parser<List<Movie>> {

    private static final String J_TITLE= "title";

    private static final String J_POSTER_BATH = "poster_path";

    private static final String J_BG_BATH = "backdrop_path";

    private static final String J_ID = "id";

    private static final String J_OVERVIEW = "overview";

    private static final String J_RELEASE_DATE = "release_date";

    private static final String J_POPULARITY = "popularity";

    private static final String J_VOTE_COUNT = "vote_count";

    private static final String J_VOTE_AVERAGE = "vote_average";

    private static final String J_RESULTS = "results";





    @Override
    public List<Movie> parseJsonString(String jsonString) throws JSONException {

        List<Movie> movies = new ArrayList<>();

        JSONObject root = new JSONObject(jsonString);

        JSONArray arrayOfJsonMovies = root.getJSONArray(J_RESULTS);
        int moviesCount = arrayOfJsonMovies.length();

        for (int index = 0 ; index < moviesCount; index++) {
            JSONObject jsonMovie = (JSONObject) arrayOfJsonMovies.get(index);

            Movie movie = new Movie();
            movie.setTitle(jsonMovie.getString(J_TITLE));
            movie.setId(jsonMovie.getString(J_ID));
            movie.setOverview(jsonMovie.getString(J_OVERVIEW));
            movie.setPosterPath(jsonMovie.getString(J_POSTER_BATH));
            movie.setBackdropPath(jsonMovie.getString(J_BG_BATH));
            movie.setPopularity(jsonMovie.getDouble(J_POPULARITY));
            movie.setVoteAverage(jsonMovie.getDouble(J_VOTE_AVERAGE));
            movie.setVoteCount(jsonMovie.getDouble(J_VOTE_COUNT));
            movie.setReleaseDate(jsonMovie.getString(J_RELEASE_DATE));

            movies.add(movie);
        }

        return movies;
    }
}
