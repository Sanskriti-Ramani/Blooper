package com.example.sanskriti.blooper.TvShows.LargeAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class TvSHowViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView showNameTextView;
    RoundedImageView showImageView;
    public TvSHowViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        showNameTextView = itemView.findViewById(R.id.nameOfTheShowTextView);
        showImageView = itemView.findViewById(R.id.imageOfTheShow);
    }
}
