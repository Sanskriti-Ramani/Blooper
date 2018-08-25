package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;

public class AiringTodayViewHolder extends RecyclerView.ViewHolder {

    TextView airingTodayTextView;
    ImageView airingTodayImage;

    View itemView;
    public AiringTodayViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        airingTodayTextView = itemView.findViewById(R.id.airingTodayShowTextView);
        airingTodayImage = itemView.findViewById(R.id.airingTodayImage);

    }
}
