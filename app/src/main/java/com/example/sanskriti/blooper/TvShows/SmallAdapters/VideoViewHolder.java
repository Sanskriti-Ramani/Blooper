package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    RoundedImageView clipImage;
    TextView clipTextView;
    public VideoViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        clipImage = itemView.findViewById(R.id.clipImageView);
        clipTextView = itemView.findViewById(R.id.clipTextView);
    }
}
