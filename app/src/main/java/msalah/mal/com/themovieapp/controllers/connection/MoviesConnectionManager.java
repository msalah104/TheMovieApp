package msalah.mal.com.themovieapp.controllers.connection;

import msalah.mal.com.themovieapp.controllers.parse.MoviesParser;
import msalah.mal.com.themovieapp.controllers.parse.Parser;
import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.constants.AppUtil;


public class MoviesConnectionManager extends ConnectionManager{

    private AppConstants.SortingType sortingType;

    public MoviesConnectionManager(OnDataReceivedListener onDataReceivedListener , Object tag) {
        super(onDataReceivedListener, tag);
        sortingType = AppConstants.SortingType.MOST_POPULAR;
    }

    public MoviesConnectionManager(AppConstants.SortingType sortingType , OnDataReceivedListener onDataReceivedListener, Object tag) {
        super(onDataReceivedListener, tag);
        this.sortingType = sortingType;
    }

    @Override
    public String getRequestUrl() {
        return AppUtil.getUrlRequestWithSortingType(sortingType);
    }

    @Override
    public Parser getRequestDataParser() {
        return new MoviesParser();
    }


}
