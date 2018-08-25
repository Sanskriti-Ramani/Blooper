package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class SimilarMoviesViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView similarMovieName;
    RoundedImageView similarMovieImage;
    public SimilarMoviesViewHolder(View itemView) {
        super(itemView);
        this.itemView =itemView;
        similarMovieName = itemView.findViewById(R.id.similarTextView);
        similarMovieImage = itemView.findViewById(R.id.similarImage);
    }
}
