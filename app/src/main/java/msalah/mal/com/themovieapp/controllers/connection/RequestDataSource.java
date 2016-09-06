package msalah.mal.com.themovieapp.controllers.connection;

import msalah.mal.com.themovieapp.controllers.parse.Parser;

/**
 * Created by user on 8/5/16.
 */

public interface RequestDataSource {

    String getRequestUrl() ;

    Object getRequestTag();

    Parser getRequestDataParser();

    OnDataReceivedListener getReceiverListener();
}
