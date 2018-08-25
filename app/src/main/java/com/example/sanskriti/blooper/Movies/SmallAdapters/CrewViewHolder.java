package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class CrewViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    RoundedImageView crewImage;
    TextView crewName;
    TextView jobName;
    public CrewViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        crewImage = itemView.findViewById(R.id.crewImage);
        crewName = itemView.findViewById(R.id.crewMamberName);
        jobName = itemView.findViewById(R.id.jobName);
    }
}
