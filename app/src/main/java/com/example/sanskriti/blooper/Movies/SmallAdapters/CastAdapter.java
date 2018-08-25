package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanskriti.blooper.Activities.CreditPageActivity;
import com.example.sanskriti.blooper.Movies.MovieCast;
import com.example.sanskriti.blooper.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastViewHolder> {
    List<MovieCast> movieCasts;
    Context mContext;

    public CastAdapter(List<MovieCast> movieCasts, Context mContext) {
        this.movieCasts = movieCasts;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.cast_layout, parent,false);
        return new CastViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        final MovieCast movieCast = movieCasts.get(position);
        holder.actorName.setText(movieCast.getName());
        holder.characterName.setText(movieCast.getCharacter());
        String image_url = movieCast.getProfilePath();


        if(image_url != null) {
            Picasso.get().load("http://image.tmdb.org/t/p/w780" + image_url).resize(400, 800)
                    .into(holder.castImage);
        }else {

              holder.castImage.setImageResource(R.drawable.male);
        }

        holder.castImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CreditPageActivity.class);
                Bundle b = new Bundle();
                b.putString(CreditPageActivity.ID, movieCast.getId()+"");
                intent.putExtras(b);
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return  movieCasts.size();
    }
}
