package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class SimilarShowsViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView similarShowName;
    RoundedImageView similarShowImage;
    public SimilarShowsViewHolder(View itemView) {
        super(itemView);
        this.itemView =itemView;
        similarShowName = itemView.findViewById(R.id.similarTextView);
        similarShowImage = itemView.findViewById(R.id.similarImage);
    }
}
