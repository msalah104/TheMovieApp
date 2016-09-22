package msalah.mal.com.themovieapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.activity.MovieAppPreferencesActivity;
import msalah.mal.com.themovieapp.adaptor.DetailsListAdaptor;
import msalah.mal.com.themovieapp.controllers.ReviewsDataController;
import msalah.mal.com.themovieapp.controllers.TrailersDataController;
import msalah.mal.com.themovieapp.controllers.connection.OnDataReceivedListener;
import msalah.mal.com.themovieapp.data.MediaItem;
import msalah.mal.com.themovieapp.data.Movie;
import msalah.mal.com.themovieapp.data.Review;
import msalah.mal.com.themovieapp.data.Trailer;
import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.constants.AppUtil;
import msalah.mal.com.themovieapp.data.database.DatabaseHandler;


public class MovieDetailsFragment extends Fragment implements OnDataReceivedListener, View.OnClickListener {

    private static final String REQUEST_TYPE_REVIEWS = "reviews";

    private static final String REQUEST_TYPE_TRAILERS = "trailers";

    private TextView title;

    private TextView date;

    private TextView duration;

    private TextView rate;

    private Button fav;

    private TextView overview;

    private ImageView poster;

    private RecyclerView trailersAndReviewsList;

    private Movie selectedMovie;

    private ReviewsDataController reviewsDataController;

    private TrailersDataController trailersDataController;

    private List<MediaItem> mediaItems;

    private DetailsListAdaptor detailsListAdaptor;

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

        View header = inflater.inflate(R.layout.fragment_detials_list_header,null);

        reviewsDataController = new ReviewsDataController(selectedMovie.getId());
        trailersDataController = new TrailersDataController(selectedMovie.getId());

        trailersDataController.getDataList(this, REQUEST_TYPE_TRAILERS);
        reviewsDataController.getDataList(this, REQUEST_TYPE_REVIEWS);


        View rootView = inflater.inflate(R.layout.fragment_movie_details, container, false);

        detailsListAdaptor = new DetailsListAdaptor(getActivity(), this);

        // Initializing views
        title = (TextView) header.findViewById(R.id.detail_movie_title);
        date  = (TextView) header.findViewById(R.id.detail_movie_data);
        duration  = (TextView) header.findViewById(R.id.detail_movie_duration);
        rate  = (TextView) header.findViewById(R.id.detail_movie_rating);
//        fav  = (Button) header.findViewById(R.id.detail_movie_button_fav);
        overview  = (TextView) header.findViewById(R.id.detail_movie_overview);
        trailersAndReviewsList = (RecyclerView) rootView.findViewById(R.id.detail_movie_list_trailers);
        poster = (ImageView) header.findViewById(R.id.detail_movie_image);


        // Setting data

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        trailersAndReviewsList.setLayoutManager(llm);
        trailersAndReviewsList.setAdapter(detailsListAdaptor);
        trailersAndReviewsList.addItemDecoration(new HeaderDecoration(header, false, 0, 0, 1));
        title.setText(selectedMovie.getTitle());
        overview.setText(selectedMovie.getOverview());
        date.setText(selectedMovie.getReleaseDate().split("-")[0]);
        rate.setText(String.valueOf(selectedMovie.getVoteAverage()));
        Picasso.with(getActivity()).load(AppUtil.
                getImageFullUrlWithPath(selectedMovie.getBackdropPath())).
                into(poster);

//        final DatabaseHandler db = new DatabaseHandler(getActivity());
//        fav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                db.addMovie(selectedMovie);
//                fav.setBackgroundColor(getActivity().getResources().getColor(R.color.colorAccent));
////                fav.setClickable(false);
//            }
//        });

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

                mediaItems.addAll(reviews);

            } else if (tagValue.equalsIgnoreCase(REQUEST_TYPE_TRAILERS) ) {
                List <Trailer> trailers = (List<Trailer>) data;
                numberOfTrailers = trailers.size();

                for (Trailer trailer : trailers) {
                    Log.i("Trailer Name", trailer.getName());
                    Log.i("Trailer Key", trailer.getKey());
                }
                mediaItems.addAll(0, trailers);
            }
            detailsListAdaptor.setNumberOfTrailers(numberOfTrailers);
            detailsListAdaptor.setItems(mediaItems);
            detailsListAdaptor.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestFailed(String error, Object tag) {
        String tagValue = (String) tag;
        if (tagValue.equalsIgnoreCase(REQUEST_TYPE_REVIEWS)) {

        } else if (tagValue.equalsIgnoreCase(REQUEST_TYPE_TRAILERS) ) {

        }
    }

    @Override
    public void onClick(View v) {

        int viewPosition = trailersAndReviewsList.getChildAdapterPosition(v);
        if (v.getTag().equals(DetailsListAdaptor.REVIEW_ROW)) {
            Review review = (Review) mediaItems.get(viewPosition);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(review.getUrl()));
            startActivity(intent);
        }
        else {
            Trailer trailer = (Trailer) mediaItems.get(viewPosition);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(trailer.getFullYoutubeUrl()));
            startActivity(intent);
        }
    }


}
