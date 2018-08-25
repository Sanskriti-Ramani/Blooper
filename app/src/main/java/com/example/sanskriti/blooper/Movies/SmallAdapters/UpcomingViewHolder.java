package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;

public class UpcomingViewHolder extends RecyclerView.ViewHolder {

    TextView upcomingTextView;
    ImageView upcomingImage;
    View itemView;

    public UpcomingViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        upcomingImage = (ImageView)itemView.findViewById(R.id.upcomingImage);
        Log.d("MainActivity", upcomingImage.toString());
        upcomingTextView = itemView.findViewById(R.id.upcomingTextView);
    }
}
