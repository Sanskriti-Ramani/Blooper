package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;

public class PopularViewHolder extends RecyclerView.ViewHolder {
    TextView popularTextView;
    ImageView popularImage;
    View itemView;

    public PopularViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        popularTextView = itemView.findViewById(R.id.popularShowTextView);
        popularImage = itemView.findViewById(R.id.popularImage);

    }
}
