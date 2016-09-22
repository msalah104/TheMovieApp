package msalah.mal.com.themovieapp.adaptor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.data.constants.AppUtil;
import msalah.mal.com.themovieapp.data.Movie;
import msalah.mal.com.themovieapp.data.database.DatabaseHandler;

/**
 * Created by user on 8/10/16.
 */

public class MovieListAdaptor extends RecyclerView.Adapter<MovieListAdaptor.ViewHolder> {

    List <Movie> movies;
    private Context context;
    private Fragment holderFragment;
    private  DatabaseHandler db;

    public MovieListAdaptor(List<Movie> movies, Fragment fragment) {
        this.movies = movies;
        holderFragment = fragment;
        this.context = holderFragment.getActivity();
        db = new DatabaseHandler(holderFragment.getActivity());
    }

    @Override
    public MovieListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_fragment_main, parent, false);
        view.setOnClickListener((View.OnClickListener) holderFragment);
        return new ViewHolder(view);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public void onBindViewHolder(final MovieListAdaptor.ViewHolder holder, int position) {
       final Movie movie = movies.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieDate.setText(movie.getReleaseDate());
        Picasso.with(context).load(AppUtil.getImageFullUrlWithPath(movie.getPosterPath())).into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        TextView movieTitle;
        TextView movieDate;
        ImageView posterImageView;
        Button favButton;
        int position;

        public ViewHolder(View itemView) {
            super(itemView);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_item_title);
            movieDate = (TextView) itemView.findViewById(R.id.movie_item_date);
            posterImageView = (ImageView) itemView.findViewById(R.id.movie_item_poster);
        }
    }
}
