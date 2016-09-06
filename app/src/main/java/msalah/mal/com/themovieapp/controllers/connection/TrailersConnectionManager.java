package msalah.mal.com.themovieapp.controllers.connection;

import msalah.mal.com.themovieapp.controllers.parse.Parser;
import msalah.mal.com.themovieapp.controllers.parse.TrailersParser;
import msalah.mal.com.themovieapp.data.constants.AppUtil;


public class TrailersConnectionManager extends ConnectionManager {

    private String movieID;

    public TrailersConnectionManager(OnDataReceivedListener onDataReceivedListener, String movieID, Object tag) {
        super(onDataReceivedListener, tag);
        this.movieID = movieID;
    }

    @Override
    public String getRequestUrl() {
        return AppUtil.getMovieUrlVideoRequestWithSortingType(this.movieID);
    }

    @Override
    public Parser getRequestDataParser() {
        return new TrailersParser();
    }
}
