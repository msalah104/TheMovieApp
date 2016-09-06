package msalah.mal.com.themovieapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.controllers.ReviewsDataController;
import msalah.mal.com.themovieapp.controllers.TrailersDataController;
import msalah.mal.com.themovieapp.controllers.connection.OnDataReceivedListener;
import msalah.mal.com.themovieapp.data.MediaItem;
import msalah.mal.com.themovieapp.data.Movie;
import msalah.mal.com.themovieapp.data.Review;
import msalah.mal.com.themovieapp.data.Trailer;
import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.constants.AppUtil;


public class MovieDetailsFragment extends Fragment implements OnDataReceivedListener {

    private static final String REQUEST_TYPE_REVIEWS = "reviews";

    private static final String REQUEST_TYPE_TRAILERS = "trailers";

    private TextView title;

    private TextView date;

    private TextView duration;

    private TextView rate;

    private Button fav;

    private TextView overview;

    private ImageView poster;

    private ListView trailersList;

    private Movie selectedMovie;

    private ReviewsDataController reviewsDataController;

    private TrailersDataController trailersDataController;

    private List<MediaItem> mediaItems;

    int numberOfTrailers;

    public MovieDetailsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // get selected movie
        Bundle bundle = getArguments();
        selectedMovie = bundle.getParcelable(AppConstants.SELECTED_MOVIE_KEY);
        mediaItems = new ArrayList<>();
        numberOfTrailers = 0;

        reviewsDataController = new ReviewsDataController(selectedMovie.getId());
        trailersDataController = new TrailersDataController(selectedMovie.getId());

        trailersDataController.getDataList(this, REQUEST_TYPE_TRAILERS);
        reviewsDataController.getDataList(this, REQUEST_TYPE_REVIEWS);


        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);

        // Initializing views
        title = (TextView) rootView.findViewById(R.id.detail_movie_title);
        date  = (TextView) rootView.findViewById(R.id.detail_movie_data);
        duration  = (TextView) rootView.findViewById(R.id.detail_movie_duration);
        rate  = (TextView) rootView.findViewById(R.id.detail_movie_rating);
        fav  = (Button) rootView.findViewById(R.id.detail_movie_button_fav);
        overview  = (TextView) rootView.findViewById(R.id.detail_movie_overview);
        trailersList  = (ListView) rootView.findViewById(R.id.detail_movie_list_trailers);
        poster = (ImageView) rootView.findViewById(R.id.detail_movie_image);

        // Setting data
        title.setText(selectedMovie.getTitle());
        overview.setText(selectedMovie.getOverview());
        date.setText(selectedMovie.getReleaseDate().split("-")[0]);
        rate.setText(String.valueOf(selectedMovie.getVoteAverage()));
        Picasso.with(getActivity()).load(AppUtil.
                getImageFullUrlWithPath(selectedMovie.getBackdropPath())).
                into(poster);

        return rootView;
    }


    @Override
    public void onRequestSuccessWithData(Object data, Object tag) {
        if ((data != null) && ((ArrayList)data).size() > 0) {

            String tagValue = (String) tag;
            if (tagValue.equalsIgnoreCase(REQUEST_TYPE_REVIEWS)) {
                List <Review> reviews = (List<Review>) data;

                for (Review review : reviews) {
                    Log.i("Review URL", review.getUrl());
                    Log.i("Review Auth", review.getAuthor());
                }

            } else if (tagValue.equalsIgnoreCase(REQUEST_TYPE_TRAILERS) ) {
                List <Trailer> trailers = (List<Trailer>) data;
                numberOfTrailers = trailers.size();

                for (Trailer trailer : trailers) {
                    Log.i("Trailer Name", trailer.getName());
                    Log.i("Trailer Key", trailer.getKey());
                }
            }
        }
    }

    @Override
    public void onRequestFailed(String error, Object tag) {
        String tagValue = (String) tag;
        if (tagValue.equalsIgnoreCase(REQUEST_TYPE_REVIEWS)) {

        } else if (tagValue.equalsIgnoreCase(REQUEST_TYPE_TRAILERS) ) {

        }
    }
}
