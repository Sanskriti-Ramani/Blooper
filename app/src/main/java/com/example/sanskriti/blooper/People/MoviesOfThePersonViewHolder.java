package com.example.sanskriti.blooper.People;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class MoviesOfThePersonViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView nameOfThePerson;
    TextView characterOfThePerson;
    RoundedImageView imageOfTheMovie;

    public MoviesOfThePersonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        nameOfThePerson = itemView.findViewById(R.id.movieTextView);
        characterOfThePerson = itemView.findViewById(R.id.characterNameOfThePerson);
        imageOfTheMovie = itemView.findViewById(R.id.movieImage);
    }
}
