package msalah.mal.com.themovieapp.data.constants;

/**
 * Created by user on 8/10/16.
 */

public class AppUtil extends AppConstants {

    private static final String URL_BUILD_FORMAT = "%s%s%s%s";

    private static final String MOVIE_URL_BUILD_FORMAT = "%s/%s";


    public static String getUrlRequestWithSortingType (AppConstants.SortingType type){
        String url;

        url = buildURLWithType(type.getValue());

        return url;
    }

    public static String getMovieUrlReviewRequestWithSortingType (String movieID){
        String url;

        url = buildMovieURLWithType(REVIEWS, movieID);

        return url;
    }

    public static String getMovieUrlVideoRequestWithSortingType (String movieID){
        String url;

        url = buildMovieURLWithType(VIDEOS, movieID);

        return url;
    }

    private static String buildMovieURLWithType (String type, String movieID){
        String url;

        type = String.format(MOVIE_URL_BUILD_FORMAT, movieID, type);

        url = String.format(URL_BUILD_FORMAT, API_BASE_URL, type, API_KEY, API_KEY_VALUE);

        return url;
    }


    private static String buildURLWithType (String type){
        String url;

        url = String.format(URL_BUILD_FORMAT, API_BASE_URL, type, API_KEY, API_KEY_VALUE);

        return url;
    }

    public static String getImageFullUrlWithPath(String path){
        return IMAGE_BASE_URL + path;
    }

}
