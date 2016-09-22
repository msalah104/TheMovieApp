package msalah.mal.com.themovieapp.data.constants;



public class AppConstants {

   public enum SortingType{
        TOP_RATED("top_rated"),
        MOST_POPULAR("popular");

        private String value;

        SortingType (String v){value = v;}

        public String getValue () {return value;}
    }

    protected static final String API_BASE_URL = "http://api.themoviedb.org/3/movie/";

    protected static final String REVIEWS = "reviews";

    protected static final String VIDEOS = "videos";

    public static final String SELECTED_MOVIE_KEY = "selected_movie";

    protected static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w342";

    protected static final String API_KEY_VALUE = "60062ad42616666d14c556dc96573c7e";

    protected static final String API_KEY = "?api_key=";

}
