package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;

public class OnAirViewHolder extends RecyclerView.ViewHolder {
    TextView onAirTextView;
    ImageView onAirImage;
    View itemView;

    public OnAirViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        onAirTextView = itemView.findViewById(R.id.onAirShowTextView);
        onAirImage = itemView.findViewById(R.id.onAirImage);


    }
}
