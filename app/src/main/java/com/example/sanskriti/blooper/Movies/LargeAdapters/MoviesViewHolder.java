package com.example.sanskriti.blooper.Movies.LargeAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class MoviesViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView movieNameTextView;
    RoundedImageView movieImageView;
    public MoviesViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        movieNameTextView = itemView.findViewById(R.id.nameOfTheMovieTextView);
        movieImageView = itemView.findViewById(R.id.imageOfTheMovie);
    }
}
