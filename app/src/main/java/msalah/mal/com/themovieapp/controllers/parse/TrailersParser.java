package msalah.mal.com.themovieapp.controllers.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import msalah.mal.com.themovieapp.data.Trailer;

public class TrailersParser implements Parser<List<Trailer>> {

    private static final String J_SITE = "site";

    private static final String J_ID = "id";

    private static final String J_KEY = "key";

    private static final String J_NAME = "name";

    private static final String J_Size = "size";

    private static final String J_RESULTS = "results";

    @Override
    public List<Trailer> parseJsonString(String jsonString) throws JSONException {

        List<Trailer> trailers = new ArrayList<>();

        JSONObject root = new JSONObject(jsonString);

        JSONArray arrayOfJsonTrailers = root.getJSONArray(J_RESULTS);
        int trailersCount = arrayOfJsonTrailers.length();

        for (int index = 0 ; index < trailersCount; index++) {
            JSONObject jsonTrailer = (JSONObject) arrayOfJsonTrailers.get(index);

            Trailer trailer = new Trailer();

            trailer.setId(jsonTrailer.getString(J_ID));
            trailer.setSite(jsonTrailer.getString(J_SITE));
            trailer.setSize(jsonTrailer.getInt(J_Size));
            trailer.setKey(jsonTrailer.getString(J_KEY));
            trailer.setName(jsonTrailer.getString(J_NAME));

            trailers.add(trailer);
        }

        return trailers;
    }
}
