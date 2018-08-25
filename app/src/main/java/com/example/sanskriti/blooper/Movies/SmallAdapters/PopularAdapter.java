package com.example.sanskriti.blooper.Movies.SmallAdapters;

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
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder> {
    List<AboutMovie> mMovies;
    Context mContext;

    public PopularAdapter(List<AboutMovie> mMovies, Context mContext) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.popular_layout, parent,false);
        return new PopularViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        final AboutMovie aboutMovie = mMovies.get(position);
        holder.popularTextView.setText(aboutMovie.getOriginalTitle());
        holder.popularImage.setImageResource(R.drawable.no);

        String image_url = aboutMovie.getPosterPath();
        Log.d("MainActivity", image_url);

        Picasso.get().load("http://image.tmdb.org/t/p/w342" + image_url).resize(800, 700).into(holder.popularImage);


        holder.popularImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Screen is working", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, AboutMovieActivity.class);
                Bundle b = new Bundle();
                b.putString(AboutMovieActivity.ID, aboutMovie.getId()+"");
                b.putString(AboutMovieActivity.BACKDROPPATH, aboutMovie.getBackdropPath());
                b.putString(AboutMovieActivity.OVERVIEW, aboutMovie.getOverview());
                b.putString(AboutMovieActivity.POSTERPATH, aboutMovie.getPosterPath());
                b.putString(AboutMovieActivity.TITLE, aboutMovie.getTitle());
                b.putString(AboutMovieActivity.VOTEAVERAGE, aboutMovie.getVoteAverage()+"");
                b.putIntegerArrayList("genres", aboutMovie.getGenreIds());
                b.putString(AboutMovieActivity.RELEASEDATE,aboutMovie.getReleaseDate());
                intent.putExtras(b);

                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
