package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView name;
    TextView content;
    Button readMore;
    public ReviewViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        name  = itemView.findViewById(R.id.nameOfThePerson);
        content =  itemView.findViewById(R.id.contentOfTheReview);
        readMore = itemView.findViewById(R.id.readMoreButton);
    }
}
