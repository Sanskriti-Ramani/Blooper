package com.example.sanskriti.blooper.Movies.LargeAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Activities.MovieActivityPackage.AboutMovieActivity;
import com.example.sanskriti.blooper.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
    List<AboutMovie> mMovies;
    Context mContext;

    public MoviesAdapter(List<AboutMovie> mMovies, Context mContext) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.full_screen_movie_layout, parent,false);
        return new MoviesViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        final AboutMovie aboutMovie = mMovies.get(position);
        holder.movieNameTextView.setText(aboutMovie.getOriginalTitle());
        String image_url = aboutMovie.getPosterPath();
       // Log.d("MainActivity", image_url);
        holder.movieImageView.setImageResource(R.drawable.no);

        Picasso.get().load("http://image.tmdb.org/t/p/w780" + image_url).resize(500, 650)
                .into(holder.movieImageView);

        holder.movieImageView.setOnClickListener(new View.OnClickListener() {
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
