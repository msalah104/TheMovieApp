package msalah.mal.com.themovieapp.controllers.parse;

import org.json.JSONException;

/**
 * Created by user on 8/10/16.
 */

public interface Parser <T>{

    T parseJsonString (String jsonString) throws JSONException;
}
