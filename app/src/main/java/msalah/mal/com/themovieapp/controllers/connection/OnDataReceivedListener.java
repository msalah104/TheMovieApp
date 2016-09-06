package msalah.mal.com.themovieapp.controllers.connection;

/**
 * Created by user on 8/5/16.
 */

public interface OnDataReceivedListener<T>{

    void onRequestSuccessWithData(T data, Object tag);

    void onRequestFailed(String error, Object tag);
}
