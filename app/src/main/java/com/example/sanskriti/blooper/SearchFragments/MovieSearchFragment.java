package com.example.sanskriti.blooper.SearchFragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sanskriti.blooper.Activities.SearchActivity;
import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Movies.NowShowingMoviesResponse;
import com.example.sanskriti.blooper.People.MovieOfThePersonAdapter;
import com.example.sanskriti.blooper.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSearchFragment extends android.support.v4.app.Fragment {

  String name;
  RecyclerView movies;
  List<AboutMovie> movieList = new ArrayList<>();
  MovieSearchAdapter movieSearchAdapter;
  ProgressBar progressBar;
  CardView cardView;

    public MovieSearchFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public MovieSearchFragment(String name) {
        this.name = name;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View output = inflater.inflate(R.layout.fragment_movie_search, container, false);
        movieWork(output);
        return output;


    }

    private void movieWork(View output) {

        progressBar = output.findViewById(R.id.progress);


        movies = output.findViewById(R.id.movieSearchRecyclerView);
        movieSearchAdapter = new MovieSearchAdapter(movieList, getContext());
        movies.setAdapter(movieSearchAdapter);
        movies.setItemAnimator(new DefaultItemAnimator());

        movies.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));



        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        movies.setLayoutManager(layoutManager);
        Retrofit retrofitSearchMovies = ApiBaseUrl.getClient();
        ApiInterface serviceSearchMovies = retrofitSearchMovies.create(ApiInterface.class);
        Call<SearchMovieResponse> callSearchMovies = serviceSearchMovies.getSearchMovieDetails("97f67791525ddbcc4192d10f245f2fb0", name);
        callSearchMovies.enqueue(new Callback<SearchMovieResponse>() {
            @Override
            public void onResponse(Call<SearchMovieResponse> call, Response<SearchMovieResponse> response) {
                List<AboutMovie> aboutMovies = response.body().getResults();
                movieList.addAll(aboutMovies);
                progressBar.setVisibility(View.INVISIBLE);
                movies.setVisibility(View.VISIBLE);
                movieSearchAdapter.notifyDataSetChanged();;
            }

            @Override
            public void onFailure(Call<SearchMovieResponse> call, Throwable t) {

            }
        });


    }

}
