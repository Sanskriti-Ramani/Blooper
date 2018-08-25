package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class CastViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    RoundedImageView castImage;
    TextView actorName;
    TextView characterName;


    public CastViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        castImage = itemView.findViewById(R.id.castImage);
        actorName = itemView.findViewById(R.id.actorName);
        characterName = itemView.findViewById(R.id.characterName);
    }
}
