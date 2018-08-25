package com.example.sanskriti.blooper.SearchFragments;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class TvSearchViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView movieName;
    TextView genres;
    TextView releaseDate;
    RoundedImageView movieImage;
    CardView cardView;
    public TvSearchViewHolder(View itemView) {
        super(itemView);

        this.itemView  = itemView;
        movieImage = itemView.findViewById(R.id.searchMovieImage);
        movieName = itemView.findViewById(R.id.searchMovieName);
        genres = itemView.findViewById(R.id.Genres);
        releaseDate = itemView.findViewById(R.id.releaseDate);
        cardView = itemView.findViewById(R.id.card);
    }
}
