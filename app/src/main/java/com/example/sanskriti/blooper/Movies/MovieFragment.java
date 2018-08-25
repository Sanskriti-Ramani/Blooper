package com.example.sanskriti.blooper.Movies;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.example.sanskriti.blooper.Activities.MainActivity;
import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Activities.MovieActivityPackage.FullViewActivity;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.Movies.SmallAdapters.NowShowingAdapter;
import com.example.sanskriti.blooper.Movies.SmallAdapters.PopularAdapter;
import com.example.sanskriti.blooper.Movies.SmallAdapters.TopRatedAdapter;
import com.example.sanskriti.blooper.Movies.SmallAdapters.UpcomingAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends android.support.v4.app.Fragment {
    RecyclerView upcoming;
    RecyclerView nowShowing;
    RecyclerView popular;
    RecyclerView topRated;


    UpcomingAdapter upcomingAdapter;
    NowShowingAdapter nowShowingAdapter;
    PopularAdapter popularAdapter;
    TopRatedAdapter topRatedAdapter;



    List<AboutMovie> nowShowingList = new ArrayList<>();
    List<AboutMovie> upcomingList = new ArrayList<>();
    List<AboutMovie> popularList = new ArrayList<>();
    List<AboutMovie> topRatedList = new ArrayList<>();



    Button upcomingButton;
    Button nowShowingButton;
    Button popularButton;
    Button topRatedButton;



    TextView nowShowingMoviesTextView;
    TextView popularMoviesTextView;
    TextView upcomingMoviesTextView;
    TextView topRatedMoviesTextView;

    ProgressBar progressBar;


    public MovieFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //getActivity().setTitle("your title");

        View output = inflater.inflate(R.layout.fragment_movie, container, false);
        movieWork(output);
        return output;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void movieWork(View view) {
        nowShowingMoviesTextView = view.findViewById(R.id.nowShowingMoviesTextView);
        topRatedMoviesTextView = view.findViewById(R.id.topRatedMoviesTextView);
        popularMoviesTextView = view.findViewById(R.id.popularMoviesTextView);
        upcomingMoviesTextView = view.findViewById(R.id.upcomingMoviesTextView);
        progressBar = view.findViewById(R.id.progress);

        //NowShowing
        nowShowingButton = view.findViewById(R.id.viewAllNowShowing);
        nowShowing = view.findViewById(R.id.nowShowingMoviesRecyclerView);
        nowShowingAdapter = new NowShowingAdapter(nowShowingList, getContext());
        nowShowing.setAdapter(nowShowingAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        nowShowing.setLayoutManager(layoutManager);
        Retrofit retrofitNowShowing = ApiBaseUrl.getClient();
        ApiInterface serviceNowShowing = retrofitNowShowing.create(ApiInterface.class);
        Call<NowShowingMoviesResponse> callNowShowing = serviceNowShowing.getNowShowingMovies("97f67791525ddbcc4192d10f245f2fb0", 1, null);
        callNowShowing.enqueue(new Callback<NowShowingMoviesResponse>() {
            @Override
            public void onResponse(Call<NowShowingMoviesResponse> call, Response<NowShowingMoviesResponse> response) {
                response.body().getResults().size();
                List<AboutMovie> nowShowingMovies = response.body().getResults();
                nowShowingMovies.size();
                nowShowingList.addAll(nowShowingMovies);
                //upcomingList.size();

                nowShowing.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                nowShowingMoviesTextView.setVisibility(View.VISIBLE);
                nowShowingButton.setVisibility(View.VISIBLE);
                nowShowingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NowShowingMoviesResponse> call, Throwable t) {

            }
        });

        nowShowingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FullViewActivity.GET_ANSWER, 1+"");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });




        //Popular
        popular = view.findViewById(R.id.popularMoviesRecyclerView);
        popularButton = view.findViewById(R.id.viewAllPopular);
        popularAdapter = new PopularAdapter(popularList,getContext());
        popular.setAdapter(popularAdapter);
        LinearLayoutManager layoutManagerPopular = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        popular.setLayoutManager(layoutManagerPopular);
        Retrofit retrofitPopular = ApiBaseUrl.getClient();
        ApiInterface servicePopular = retrofitPopular.create(ApiInterface.class);
        Call<PopularMoviesResponse> callPopular = servicePopular.getPopularMovies("97f67791525ddbcc4192d10f245f2fb0", 1, null);
        callPopular.enqueue(new Callback<PopularMoviesResponse>() {
            @Override
            public void onResponse(Call<PopularMoviesResponse> call, Response<PopularMoviesResponse> response) {
                response.body().getResults().size();

                List<AboutMovie> popularMovies = response.body().getResults();
                popularMovies.size();
                popularList.addAll(popularMovies);
                progressBar.setVisibility(View.INVISIBLE);
                popular.setVisibility(View.VISIBLE);
                popularMoviesTextView.setVisibility(View.VISIBLE);
                popularButton.setVisibility(View.VISIBLE);
                popularAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PopularMoviesResponse> call, Throwable t) {

            }
        });
        popularButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FullViewActivity.GET_ANSWER, 2+"");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });



        //Upcoming
        upcoming = view.findViewById(R.id.upcomingMoviesRecyclerView);
        upcomingButton = view.findViewById(R.id.viewAllUpcoming);
        upcomingAdapter = new UpcomingAdapter(upcomingList, getContext());
        upcoming.setAdapter(upcomingAdapter);
        LinearLayoutManager layoutManagerUpcoming = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        upcoming.setLayoutManager(layoutManagerUpcoming);
        Retrofit retrofitUpcoming = ApiBaseUrl.getClient();
        ApiInterface serviceUpcoming = retrofitUpcoming.create(ApiInterface.class);
        Call<UpcomingMoviesResponse> callUpcoming = serviceUpcoming.getUpcomingMovies("97f67791525ddbcc4192d10f245f2fb0", 1, null);
        callUpcoming.enqueue(new Callback<UpcomingMoviesResponse>() {
            @Override
            public void onResponse(Call<UpcomingMoviesResponse> call, Response<UpcomingMoviesResponse> response) {

                response.body().getResults().size();

                List<AboutMovie> upcomingMovies = response.body().getResults();
                upcomingMovies.size();


                upcomingList.addAll(upcomingMovies);
                //upcomingList.size();
                progressBar.setVisibility(View.INVISIBLE);
                upcoming.setVisibility(View.VISIBLE);
                upcomingMoviesTextView.setVisibility(View.VISIBLE);
                upcomingButton.setVisibility(View.VISIBLE);
                upcomingAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<UpcomingMoviesResponse> call, Throwable t) {

            }
        });

        upcomingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FullViewActivity.GET_ANSWER, 3+"");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });



        //topRated
        topRated = view.findViewById(R.id.topRatedMoviesRecyclerView);
        topRatedButton = view.findViewById(R.id.viewAllTopRated);
        topRatedAdapter = new TopRatedAdapter(topRatedList, getContext());
        topRated.setAdapter(topRatedAdapter);
        LinearLayoutManager layoutManagerTopRated = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topRated.setLayoutManager(layoutManagerTopRated);
        Retrofit retrofitTopRated = ApiBaseUrl.getClient();
        ApiInterface serviceTopRated = retrofitTopRated.create(ApiInterface.class);
        Call<TopRatedMoviesResponse> callTopRated = serviceTopRated.getTopRatedMovies("97f67791525ddbcc4192d10f245f2fb0", 1, null);
        callTopRated.enqueue(new Callback<TopRatedMoviesResponse>() {
            @Override
            public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {
                response.body().getResults().size();

                List<AboutMovie> topRatedMovies = response.body().getResults();
                topRatedMovies.size();


                topRatedList.addAll(topRatedMovies);
                //upcomingList.size();
                progressBar.setVisibility(View.INVISIBLE);
                topRated.setVisibility(View.VISIBLE);
                topRatedMoviesTextView.setVisibility(View.VISIBLE);
                topRatedButton.setVisibility(View.VISIBLE);
                topRatedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {

            }
        });
        topRatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FullViewActivity.GET_ANSWER, 4+"");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}


