package msalah.mal.com.themovieapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 8/10/16.
 */

public class Movie extends MediaItem {

    private String posterPath;
    private String overview;
    private String releaseDate;
    private String id;
    private String title;
    private String backdropPath;
    private double voteCount;
    private double popularity;
    private double voteAverage;

    public Movie() {
    }

    public Movie(String posterPath, String overview, String releaseDate, String id, String title, String backdropPath, double voteCount, double popularity, double voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.title = title;
        this.backdropPath = backdropPath;
        this.voteCount = voteCount;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(double voteCount) {
        this.voteCount = voteCount;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(posterPath);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(backdropPath);
        parcel.writeDouble(voteCount);
        parcel.writeDouble(voteAverage);
        parcel.writeDouble(popularity);
    }

    // Parcelling part
    public Movie(Parcel in){
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.voteCount = in.readDouble();
        this.voteAverage = in.readDouble();
        this.popularity = in.readDouble();


    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
