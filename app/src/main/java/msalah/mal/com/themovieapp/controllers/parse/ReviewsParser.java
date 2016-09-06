package msalah.mal.com.themovieapp.controllers.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import msalah.mal.com.themovieapp.data.Review;

/**
 * Created by user on 9/3/16.
 */

public class ReviewsParser implements Parser<List<Review>> {

    private static final String J_AUTHOR = "author";

    private static final String J_ID = "id";

    private static final String J_CONTENT = "content";

    private static final String J_URL = "url";

    private static final String J_RESULTS = "results";

    @Override
    public List<Review> parseJsonString(String jsonString) throws JSONException {

        List<Review> reviews = new ArrayList<>();

        JSONObject root = new JSONObject(jsonString);

        JSONArray arrayOfJsonReviews = root.getJSONArray(J_RESULTS);
        int reviewsCount = arrayOfJsonReviews.length();

        for (int index = 0 ; index < reviewsCount; index++) {
            JSONObject jsonReview = (JSONObject) arrayOfJsonReviews.get(index);

            Review review = new Review();

            review.setId(jsonReview.getString(J_ID));
            review.setAuthor(jsonReview.getString(J_AUTHOR));
            review.setContent(jsonReview.getString(J_CONTENT));
            review.setUrl(jsonReview.getString(J_URL));

            reviews.add(review);
        }

        return reviews;
    }
}
