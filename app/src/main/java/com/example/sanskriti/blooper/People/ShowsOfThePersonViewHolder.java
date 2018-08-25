package com.example.sanskriti.blooper.People;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class ShowsOfThePersonViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView nameOfThePerson;
    TextView characterOfThePerson;
    RoundedImageView imageOfTheShow;
    public ShowsOfThePersonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        nameOfThePerson = itemView.findViewById(R.id.showTextView);
        characterOfThePerson = itemView.findViewById(R.id.characterShowNameOfThePerson);
        imageOfTheShow = itemView.findViewById(R.id.showImage);
    }
}
