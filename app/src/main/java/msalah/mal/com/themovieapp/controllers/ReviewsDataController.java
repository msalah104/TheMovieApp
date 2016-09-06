package msalah.mal.com.themovieapp.controllers;

import msalah.mal.com.themovieapp.controllers.connection.OnDataReceivedListener;
import msalah.mal.com.themovieapp.controllers.connection.ReviewsConnectionManager;


public class ReviewsDataController extends DataController {
    String targetedMovieID;

    public ReviewsDataController(String targetedMovieID) {
        this.targetedMovieID = targetedMovieID;
    }

    @Override
    public void getDataList(OnDataReceivedListener onDataReceivedListener, Object tag) {
        this.onDataReceivedListener = onDataReceivedListener;
        connectionManager = new ReviewsConnectionManager(this, targetedMovieID, tag);
        connectionManager.fireRequest();
    }
}
