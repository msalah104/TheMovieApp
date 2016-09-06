package msalah.mal.com.themovieapp.controllers;

import msalah.mal.com.themovieapp.controllers.connection.MoviesConnectionManager;
import msalah.mal.com.themovieapp.controllers.connection.OnDataReceivedListener;
import msalah.mal.com.themovieapp.data.constants.AppConstants;


public class MoviesDataController extends DataController{

    AppConstants.SortingType type;

    public MoviesDataController (AppConstants.SortingType type){
        this.type = type;
    }

    @Override
    public void getDataList(OnDataReceivedListener onDataReceivedListener, Object tag){
        this.onDataReceivedListener = onDataReceivedListener;
        connectionManager = new MoviesConnectionManager(type, this, tag);
        connectionManager.fireRequest();

    }


}
