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
import com.example.sanskriti.blooper.Movies.MovieCrew;
import com.example.sanskriti.blooper.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewViewHolder> {
    List<MovieCrew> movieCrews;
    Context mContext;

    public CrewAdapter(List<MovieCrew> movieCrews, Context mContext) {
        this.movieCrews = movieCrews;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.crew_layout, parent,false);
        return new CrewViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        final MovieCrew movieCrew = movieCrews.get(position);
        holder.crewName.setText(movieCrew.getName());
        holder.jobName.setText(movieCrew.getJob());
        String image_url = movieCrew.getProfilePath();


        if(image_url != null) {
            Picasso.get().load("http://image.tmdb.org/t/p/w780" + image_url).resize(500, 500)
                    .into(holder.crewImage);
        }

        holder.crewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CreditPageActivity.class);
                Bundle b = new Bundle();
                b.putString(CreditPageActivity.ID, movieCrew.getId()+"");
                intent.putExtras(b);
                mContext.startActivity(intent);
            }

        });
//        }else {
//            holder.crewImage.setImageResource(R.drawable.male);
//
//
//        }


    }

    @Override
    public int getItemCount() {
        return movieCrews.size();
    }
}
