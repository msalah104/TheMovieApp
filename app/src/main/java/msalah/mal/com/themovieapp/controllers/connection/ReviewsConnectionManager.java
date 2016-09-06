package msalah.mal.com.themovieapp.controllers.connection;

import msalah.mal.com.themovieapp.controllers.parse.Parser;
import msalah.mal.com.themovieapp.controllers.parse.ReviewsParser;
import msalah.mal.com.themovieapp.data.constants.AppUtil;

/**
 * Created by user on 9/3/16.
 */

public class ReviewsConnectionManager extends ConnectionManager {

    private String movieID;

    public ReviewsConnectionManager(OnDataReceivedListener onDataReceivedListener, String movieID, Object tag) {
        super(onDataReceivedListener, tag);
        this.movieID = movieID;
    }

    @Override
    public String getRequestUrl() {
        return AppUtil.getMovieUrlReviewRequestWithSortingType(movieID);
    }

    @Override
    public Parser getRequestDataParser() {
        return new ReviewsParser();
    }
}
