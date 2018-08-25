package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class NowShowingViewHolder extends RecyclerView.ViewHolder {
    TextView nowShowingTextView;
    RoundedImageView nowShowingImage;
    View itemView;

    public NowShowingViewHolder(View itemView) {
        super(itemView);
        this.itemView= itemView;
        nowShowingTextView = (TextView)itemView.findViewById(R.id.nowShowingTextView);
        nowShowingImage = (RoundedImageView) itemView.findViewById(R.id.nowShowingImage);
    }
}
