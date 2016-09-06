package msalah.mal.com.themovieapp.controllers;

import msalah.mal.com.themovieapp.controllers.connection.OnDataReceivedListener;
import msalah.mal.com.themovieapp.controllers.connection.TrailersConnectionManager;


public class TrailersDataController extends DataController {

    String targetedMovieID;

    public TrailersDataController(String targetedMovieID) {
        this.targetedMovieID = targetedMovieID;
    }

    @Override
    public void getDataList(OnDataReceivedListener onDataReceivedListener, Object tag) {
        this.onDataReceivedListener = onDataReceivedListener;
        connectionManager = new TrailersConnectionManager(this, targetedMovieID, tag);
        connectionManager.fireRequest();
    }
}
