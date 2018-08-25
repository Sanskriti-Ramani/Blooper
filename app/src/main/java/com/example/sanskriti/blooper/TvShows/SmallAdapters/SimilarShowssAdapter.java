package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanskriti.blooper.Activities.MovieActivityPackage.AboutMovieActivity;
import com.example.sanskriti.blooper.Activities.TvActivityPackage.AboutTvActivity;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.TvShows.TvShowDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SimilarShowssAdapter extends RecyclerView.Adapter<SimilarShowsViewHolder> {
    List<TvShowDetails> mShows;
    Context mContext;

    public SimilarShowssAdapter(List<TvShowDetails> mShows, Context mContext) {
        this.mShows = mShows;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SimilarShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.similar_layout, parent,false);
        return new SimilarShowsViewHolder(rowLayout);

    }

    @Override
    public void onBindViewHolder(@NonNull SimilarShowsViewHolder holder, int position) {
        final TvShowDetails tvShowDetails = mShows.get(position);
        holder.similarShowName.setText(tvShowDetails.getName());
        String image_url = tvShowDetails.getPosterPath();


        Picasso.get().load("http://image.tmdb.org/t/p/w780" + image_url).resize(500, 600)
                .into(holder.similarShowImage);

        holder.similarShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Screen is working", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, AboutTvActivity.class);
                Bundle b = new Bundle();
                b.putString(AboutTvActivity.ID, tvShowDetails.getId()+"");
                b.putString(AboutTvActivity.BACKDROPPATH, tvShowDetails.getBackdropPath());
                b.putString(AboutTvActivity.OVERVIEW, tvShowDetails.getOverview());
                b.putString(AboutTvActivity.POSTERPATH, tvShowDetails.getPosterPath());
                b.putString(AboutTvActivity.TITLE, tvShowDetails.getName());
                b.putString(AboutTvActivity.VOTEAVERAGE, tvShowDetails.getVoteAverage()+"");
                b.putIntegerArrayList("genres", (ArrayList<Integer>) tvShowDetails.getGenreIds());
                b.putString(AboutTvActivity.FIRSTAIR,tvShowDetails.getFirstAirDate());
                intent.putExtras(b);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mShows.size();
    }
}
