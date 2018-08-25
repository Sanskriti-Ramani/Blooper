package com.example.sanskriti.blooper.Movies.SmallAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.Activities.ReviewActivity;
import com.example.sanskriti.blooper.Reviews.Reviews;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {
    List<Reviews> reviews;
    Context mContext;

    public ReviewAdapter(List<Reviews> reviews, Context mContext) {
        this.reviews = reviews;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.review_layout, parent,false);
        return new ReviewViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        final Reviews review_of_the_movie = reviews.get(position);
        String content_of_the_review = "";
        holder.name.setText(review_of_the_movie.getAuthor());
        content_of_the_review = review_of_the_movie.getContent();
        String[] contentArray = content_of_the_review.split(" ");
        if(contentArray.length<=100) {
            holder.content.setText(review_of_the_movie.getContent());
        }else{
           String initialContent = "";
           for (int i = 0; i<100; i++){
               initialContent = initialContent + " " + contentArray[i];
           }
           holder.content.setText(initialContent);
           holder.readMore.setVisibility(View.VISIBLE);
           holder.readMore.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(mContext, ReviewActivity.class);
                   Bundle b = new Bundle();
                   b.putString(ReviewActivity.NAME,review_of_the_movie.getAuthor());
                   b.putString(ReviewActivity.CONTENT, review_of_the_movie.getContent());
                   intent.putExtras(b);
                   mContext.startActivity(intent);
               }
           });
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}
