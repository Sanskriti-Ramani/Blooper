package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.Videos.Videos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    List<Videos> movieVideos;
    Context mContext;

    public VideoAdapter(List<Videos> movieVideos, Context mContext) {
        this.movieVideos = movieVideos;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.trailer_and_clip_layout, parent,false);
        return new VideoViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        final Videos videosOfTheMovie = movieVideos.get(position);
        holder.clipTextView.setText(videosOfTheMovie.getName());
        final String source = videosOfTheMovie.getKey();
        Picasso.get().load("http://img.youtube.com/vi/" + source + "/hqdefault.jpg").resize(800, 400)
                .into(holder.clipImage);

        holder.clipImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + source));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return  movieVideos.size();
    }
}
