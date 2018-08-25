package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;

public class TopRatedViewHolder extends RecyclerView.ViewHolder {
    TextView topRatedTextView;
    ImageView topRatedImage;
    View itemView;
    public TopRatedViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        topRatedTextView = itemView.findViewById(R.id.topRatedTextView);
        topRatedImage = itemView.findViewById(R.id.topRatedImage);
    }
}
