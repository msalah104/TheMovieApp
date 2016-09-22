package msalah.mal.com.themovieapp.adaptor;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import msalah.mal.com.themovieapp.R;
import msalah.mal.com.themovieapp.data.MediaItem;
import msalah.mal.com.themovieapp.data.Review;
import msalah.mal.com.themovieapp.data.Trailer;


public class DetailsListAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   public static final int TRAILER_ROW = 1;

    public static final int REVIEW_ROW = 2;


    List<MediaItem> items;
    private Context context;
    int numberOfTrailers;
    private Fragment holderFragment;

    public DetailsListAdaptor(Context context, Fragment holderFragment) {
        this.context = context;
        this.numberOfTrailers = 0;
        this.holderFragment = holderFragment;
        items = new ArrayList<>();
    }

    public void setItems(List<MediaItem> items) {
        this.items = items;
    }

    public void setNumberOfTrailers(int numberOfTrailers) {
        this.numberOfTrailers = numberOfTrailers;
    }

    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (position < numberOfTrailers) {
            return TRAILER_ROW;
        } else {
            return REVIEW_ROW;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;

        if (viewType == TRAILER_ROW){
            view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_video, parent, false);
            view.setOnClickListener((View.OnClickListener) holderFragment);
            return new TrailerViewHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_review, parent, false);
            view.setOnClickListener((View.OnClickListener) holderFragment);
            return new ReviewViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (items.size() == 0 ) {
            if (getItemViewType(position) == TRAILER_ROW) {
                TrailerViewHolder trailerViewHolder = (TrailerViewHolder) holder;
                trailerViewHolder.trailerName.setVisibility(View.GONE);
                trailerViewHolder.trailerSite.setVisibility(View.GONE);
                trailerViewHolder.trailerVideoSize.setVisibility(View.GONE);
                trailerViewHolder.position = position;
            } else  {
                ReviewViewHolder reviewViewHolder = (ReviewViewHolder) holder;
                reviewViewHolder.reviewAutor.setVisibility(View.GONE);
                reviewViewHolder.reviewContent.setVisibility(View.GONE);
                reviewViewHolder.position = position;
            }
        } else {
            if (getItemViewType(position) == TRAILER_ROW) {
                Trailer trailer = (Trailer) items.get(position);
                TrailerViewHolder trailerViewHolder = (TrailerViewHolder) holder;
                trailerViewHolder.trailerName.setText(trailer.getName());
                trailerViewHolder.trailerSite.setText(trailer.getSite());
                trailerViewHolder.trailerVideoSize.setText(String.valueOf(trailer.getSize()));
                trailerViewHolder.position = position;
            } else  {
                Review review = (Review) items.get(position);
                ReviewViewHolder reviewViewHolder = (ReviewViewHolder) holder;
                reviewViewHolder.reviewAutor.setText(review.getAuthor());
                reviewViewHolder.reviewContent.setText(review.getContent());
                reviewViewHolder.position = position;
            }
        }

    }

    @Override
    public int getItemCount() {

        if (items.size() == 0)
            return 1;
        else
            return items.size();
    }

    public class TrailerViewHolder extends RecyclerView.ViewHolder  {

        TextView trailerName;
        TextView trailerSite;
        TextView trailerVideoSize;
        int position;

        public TrailerViewHolder(View itemView) {
            super(itemView);
            trailerName = (TextView) itemView.findViewById(R.id.trailer_name);
            trailerSite = (TextView) itemView.findViewById(R.id.trailer_site);
            trailerVideoSize = (TextView) itemView.findViewById(R.id.trailer_size);
            itemView.setTag(TRAILER_ROW);
        }
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder  {

        TextView reviewAutor;
        TextView reviewContent;
        int position;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            itemView.setTag(REVIEW_ROW);
            reviewAutor = (TextView) itemView.findViewById(R.id.review_auther);
            reviewContent = (TextView) itemView.findViewById(R.id.review_content);
        }
    }
}
