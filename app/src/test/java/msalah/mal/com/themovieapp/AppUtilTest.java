package msalah.mal.com.themovieapp;

import org.junit.Assert;
import org.junit.Test;

import msalah.mal.com.themovieapp.data.constants.AppConstants;
import msalah.mal.com.themovieapp.data.constants.AppUtil;

/**
 * Created by user on 8/10/16.
 */

public class AppUtilTest {


    @Test
    public void getUrlRequestWithSortingTypeTest () {

        AppConstants.SortingType sortingType = AppConstants.SortingType.TOP_RATED;

        String expectedUrl = "http://api.themoviedb.org/3/movie/top_rated?api_key";
        String actualUrl = AppUtil.getUrlRequestWithSortingType ( sortingType);
        actualUrl = actualUrl.split("=")[0];

        Assert.assertEquals(expectedUrl, actualUrl);


    }
}
