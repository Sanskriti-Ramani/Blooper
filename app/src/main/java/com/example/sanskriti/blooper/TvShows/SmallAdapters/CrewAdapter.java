package com.example.sanskriti.blooper.TvShows.SmallAdapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sanskriti.blooper.Activities.CreditPageActivity;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.TvShows.TvShowCrew;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewViewHolder> {
    public CrewAdapter(List<TvShowCrew> mCrews, Context mContext) {
        this.mCrews = mCrews;
        this.mContext = mContext;
    }

    List<TvShowCrew> mCrews;
    Context mContext;



    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.crew_layout, parent,false);
        return new CrewViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        final TvShowCrew tvShowCrew = mCrews.get(position);
        holder.crewName.setText(tvShowCrew.getName());
        holder.jobName.setText(tvShowCrew.getJob());
        String image_url = tvShowCrew.getProfilePath();


        if(image_url != null) {
            Picasso.get().load("http://image.tmdb.org/t/p/w780" + image_url).resize(500, 500)
                    .into(holder.crewImage);
        }

        holder.crewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CreditPageActivity.class);
                Bundle b = new Bundle();
                b.putString(CreditPageActivity.ID, tvShowCrew.getId()+"");
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
        return mCrews.size();
    }
}
