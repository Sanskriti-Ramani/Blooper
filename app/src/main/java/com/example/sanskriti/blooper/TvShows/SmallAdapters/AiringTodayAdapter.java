package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanskriti.blooper.Activities.MovieActivityPackage.AboutMovieActivity;
import com.example.sanskriti.blooper.Activities.TvActivityPackage.AboutTvActivity;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Movies.SmallAdapters.UpcomingViewHolder;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.TvShows.TvShowDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AiringTodayAdapter extends RecyclerView.Adapter<AiringTodayViewHolder> {

    List<TvShowDetails> mTvShow;
    Context mContext;

    public AiringTodayAdapter(List<TvShowDetails> mTvShow, Context mContext) {
        this.mTvShow = mTvShow;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AiringTodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.airing_today_layout, parent,false);
        return new AiringTodayViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull AiringTodayViewHolder holder, int position) {
              final TvShowDetails tvShowDetails  = mTvShow.get(position);
        holder.airingTodayTextView.setText(tvShowDetails.getName());
        String image_url = tvShowDetails.getPosterPath();
//        Log.d("MainActivity", image_url);

        Picasso.get().load("http://image.tmdb.org/t/p/w780" + image_url).resize(700, 600)
                .into(holder.airingTodayImage);

        holder.airingTodayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        return mTvShow.size();
    }
}
