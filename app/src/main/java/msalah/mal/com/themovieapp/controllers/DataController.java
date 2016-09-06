package msalah.mal.com.themovieapp.controllers;

import msalah.mal.com.themovieapp.controllers.connection.ConnectionManager;
import msalah.mal.com.themovieapp.controllers.connection.OnDataReceivedListener;

public abstract class DataController implements OnDataReceivedListener {

    OnDataReceivedListener onDataReceivedListener;
    ConnectionManager connectionManager;

    @Override
    public void onRequestSuccessWithData(Object data, Object tag) {
        onDataReceivedListener.onRequestSuccessWithData(data, tag);
    }

    @Override
    public void onRequestFailed(String error, Object tag) {
        onDataReceivedListener.onRequestFailed(error, toString());
    }


    public abstract void getDataList(OnDataReceivedListener onDataReceivedListener, Object tag);
}
