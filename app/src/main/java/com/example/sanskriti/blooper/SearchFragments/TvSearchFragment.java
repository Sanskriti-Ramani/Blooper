package com.example.sanskriti.blooper.SearchFragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.TvShows.TvShowDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvSearchFragment extends android.support.v4.app.Fragment{
    String name;
    RecyclerView shows;
    List<TvShowDetails> showList = new ArrayList<>();
    TvSearchAdapter tvSearchAdapter;
    ProgressBar progressBar;


    public TvSearchFragment() {
        // Requi
        // red empty public constructor
    }

    @SuppressLint("ValidFragment")
    public TvSearchFragment(String name) {
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output = inflater.inflate(R.layout.fragment_tv_search, container, false);
        showWork(output);
        return output;    }

    private void showWork(View output) {



        progressBar = output.findViewById(R.id.progress);

        shows = output.findViewById(R.id.movieSearchRecyclerView);
        tvSearchAdapter = new TvSearchAdapter(showList, getContext());
       shows.setAdapter(tvSearchAdapter);
        shows.setItemAnimator(new DefaultItemAnimator());

       shows.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        shows.setLayoutManager(layoutManager);
        Retrofit retrofitSearchMovies = ApiBaseUrl.getClient();
        ApiInterface serviceSearchMovies = retrofitSearchMovies.create(ApiInterface.class);
        Call<SearchShowResponse> callSearchMovies = serviceSearchMovies.getSearchShowDetails("97f67791525ddbcc4192d10f245f2fb0", name);
        callSearchMovies.enqueue(new Callback<SearchShowResponse>() {
            @Override
            public void onResponse(Call<SearchShowResponse> call, Response<SearchShowResponse> response) {
                List<TvShowDetails> aboutMovies = response.body().getResults();
                showList.addAll(aboutMovies);
                progressBar.setVisibility(View.INVISIBLE);
                shows.setVisibility(View.VISIBLE);
                tvSearchAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onFailure(Call<SearchShowResponse> call, Throwable t) {

            }
        });

    }

}
