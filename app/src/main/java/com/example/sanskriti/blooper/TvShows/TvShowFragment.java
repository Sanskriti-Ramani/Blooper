package com.example.sanskriti.blooper.TvShows;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sanskriti.blooper.Activities.TvActivityPackage.FullViewActivity;
import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Movies.NowShowingMoviesResponse;
import com.example.sanskriti.blooper.Movies.SmallAdapters.NowShowingAdapter;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.AiringTodayAdapter;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.OnAirAdapter;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.PopularAdapter;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.TopRatedAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment  extends android.support.v4.app.Fragment {
    RecyclerView airingToday;
    RecyclerView onAir;
    RecyclerView popular;
    RecyclerView topRated;
    AiringTodayAdapter airingTodayAdapter;
    OnAirAdapter onAirAdapter;
    PopularAdapter popularShowAdapter;
    TopRatedAdapter topRatedShowAdapter;
    List<TvShowDetails> airingTodayList = new ArrayList<>();
    List<TvShowDetails>onAirList = new ArrayList<>();
    List<TvShowDetails>popularList = new ArrayList<>();
    List<TvShowDetails>topRatedList = new ArrayList<>();
    Button airingTodayButton;
    Button onAirButton;
    Button popularShowButton;
    Button topRatedShowButton;

    ProgressBar progressBar;
    TextView airingTodayTextView;
    TextView onAirTextView;
    TextView popularTextView;
    TextView topRatedTextView;


    public TvShowFragment() {
        // Required empty public constructor
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View output = inflater.inflate(R.layout.fragment_tv_show, container, false);
        tvShowWork(output);
        return output; }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void tvShowWork(View view) {

        progressBar = view.findViewById(R.id.progress);
        airingTodayTextView = view.findViewById(R.id.airingTodayTextView);
        onAirTextView = view.findViewById(R.id.onAirTextView);
        popularTextView = view.findViewById(R.id.popularTextView);
        topRatedTextView = view.findViewById(R.id.topRatedTextView);

        //AiringToday
        airingTodayButton = view.findViewById(R.id.viewAllAiringToday);
        airingToday = view.findViewById(R.id.airingTodayRecyclerView);
        airingTodayAdapter = new AiringTodayAdapter(airingTodayList, getContext());
        airingToday.setAdapter(airingTodayAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        airingToday.setLayoutManager(layoutManager);
        final Retrofit retrofitAiringToday = ApiBaseUrl.getClient();
        ApiInterface serviceAiringToday = retrofitAiringToday.create(ApiInterface.class);
        Call<AiringTodayResponse> callAiringToday = serviceAiringToday.getAiringTodayTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
        callAiringToday.enqueue(new Callback<AiringTodayResponse>() {
            @Override
            public void onResponse(Call<AiringTodayResponse> call, Response<AiringTodayResponse> response) {
                response.body().getResults().size();
                List<TvShowDetails> airingTodayShows = response.body().getResults();
                airingTodayShows.size();
                airingTodayList.addAll(airingTodayShows);
                //upcomingList.size();

                progressBar.setVisibility(View.INVISIBLE);
                airingTodayTextView.setVisibility(View.VISIBLE);
                airingTodayButton.setVisibility(View.VISIBLE);
               airingToday.setVisibility(View.VISIBLE);
                airingTodayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AiringTodayResponse> call, Throwable t) {

            }
        });
        airingTodayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FullViewActivity.GET_ANSWER, 1+"");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });



        //onAir
       onAirButton = view.findViewById(R.id.viewAllOnAir);
        onAir = view.findViewById(R.id.onAirRecyclerView);
        onAirAdapter = new OnAirAdapter(onAirList, getContext());
        onAir.setAdapter(onAirAdapter);
        LinearLayoutManager layoutManageronAir = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        onAir.setLayoutManager(layoutManageronAir);
        Retrofit retrofitonAir = ApiBaseUrl.getClient();
        ApiInterface serviceonAir = retrofitonAir.create(ApiInterface.class);
        Call<OnAirShowsResponse> callonAir = serviceonAir.getOnTheAirTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
        callonAir.enqueue(new Callback<OnAirShowsResponse>() {
            @Override
            public void onResponse(Call<OnAirShowsResponse> call, Response<OnAirShowsResponse> response) {
                response.body().getResults().size();

                List<TvShowDetails> onAirShows = response.body().getResults();
                onAirShows.size();
                onAirList.addAll( onAirShows);
                //upcomingList.size();

                onAir.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.INVISIBLE);
                onAirTextView.setVisibility(View.VISIBLE);
                onAirButton.setVisibility(View.VISIBLE);
                onAirAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<OnAirShowsResponse> call, Throwable t) {

            }
        });
        onAirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FullViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FullViewActivity.GET_ANSWER, 2+"");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });



        //popular
        popularShowButton = view.findViewById(R.id.viewAllPopular);
        popular = view.findViewById(R.id.popularRecyclerView);
        popularShowAdapter = new PopularAdapter(popularList, getContext());
        popular.setAdapter(popularShowAdapter);
        LinearLayoutManager layoutManagerpopular = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        popular.setLayoutManager(layoutManagerpopular);
        final Retrofit retrofitpopular = ApiBaseUrl.getClient();
        ApiInterface servicepopular = retrofitpopular.create(ApiInterface.class);
        Call<PopularTvShowsResponse> callpopular = servicepopular.getPopularTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
        callpopular.enqueue(new Callback<PopularTvShowsResponse>() {
            @Override
            public void onResponse(Call<PopularTvShowsResponse> call, Response<PopularTvShowsResponse> response) {
                response.body().getResults().size();

                List<TvShowDetails> popularShows = response.body().getResults();
                popularShows.size();
                popularList.addAll(popularShows);
                //upcomingList.size();

                popular.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.INVISIBLE);
                popularTextView.setVisibility(View.VISIBLE);
             popularShowButton.setVisibility(View.VISIBLE);
                popularShowAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PopularTvShowsResponse> call, Throwable t) {

            }
        });
        popularShowButton.setOnClickListener(new View.OnClickListener() {
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
        topRatedShowButton = view.findViewById(R.id.viewAllTopRatedShows);
        topRated = view.findViewById(R.id.topRatedRecyclerView);
        topRatedShowAdapter = new TopRatedAdapter(topRatedList, getContext());
        topRated.setAdapter(topRatedShowAdapter);
        LinearLayoutManager layoutManagertopRated = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        topRated.setLayoutManager(layoutManagertopRated);
        final Retrofit retrofittopRated = ApiBaseUrl.getClient();
        ApiInterface servicetopRated = retrofittopRated.create(ApiInterface.class);
        Call<TopRatedTvShowsResponse> calltopRated = servicetopRated.getTopRatedTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
        calltopRated.enqueue(new Callback<TopRatedTvShowsResponse>() {
            @Override
            public void onResponse(Call<TopRatedTvShowsResponse> call, Response<TopRatedTvShowsResponse> response) {

                response.body().getResults().size();

                List<TvShowDetails> topRatedShows = response.body().getResults();
                topRatedShows.size();
                topRatedList.addAll(topRatedShows);
                //upcomingList.size();

                topRated.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.INVISIBLE);
               topRatedTextView.setVisibility(View.VISIBLE);
               topRatedShowButton.setVisibility(View.VISIBLE);
                topRatedShowAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopRatedTvShowsResponse> call, Throwable t) {

            }
        });
        topRatedShowButton.setOnClickListener(new View.OnClickListener() {
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

}
