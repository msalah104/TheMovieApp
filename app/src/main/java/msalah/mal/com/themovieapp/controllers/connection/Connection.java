package msalah.mal.com.themovieapp.controllers.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import msalah.mal.com.themovieapp.controllers.exception.ExceptionUtil;
import msalah.mal.com.themovieapp.controllers.exception.NetworkException;
import msalah.mal.com.themovieapp.controllers.parse.Parser;

/**
 * Created by user on 8/5/16.
 */

public class Connection extends AsyncTask{

    private static final String TAG = "Connection";

    RequestDataSource requestDataSource;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    Object tag;

    @Override
    protected Object doInBackground(Object[] objects) {

        requestDataSource = (RequestDataSource) objects[0];
        tag = requestDataSource.getRequestTag();
        // Create the request to OpenWeatherMap, and open the connection
        try {
            URL url =  new URL(requestDataSource.getRequestUrl());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            Log.i(TAG , "Url: " + requestDataSource.getRequestUrl());

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            String response = buffer.toString();
            Parser parser = requestDataSource.getRequestDataParser();
            List items = (List) parser.parseJsonString(response);

            return items;

        } catch (IOException e) {

            ExceptionUtil.getSharedInstance().throwException(NetworkException.class);
        } catch (JSONException e) {
            ExceptionUtil.getSharedInstance().throwException(NetworkException.class);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        OnDataReceivedListener onDataReceivedListener = requestDataSource.getReceiverListener();
        onDataReceivedListener.onRequestSuccessWithData(o, tag);
    }
}
