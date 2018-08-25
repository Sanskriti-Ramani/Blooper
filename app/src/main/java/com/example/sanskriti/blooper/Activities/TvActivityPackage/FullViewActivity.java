package com.example.sanskriti.blooper.Activities.TvActivityPackage;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Movies.LargeAdapters.MoviesAdapter;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.TvShows.AiringTodayResponse;
import com.example.sanskriti.blooper.TvShows.LargeAdapter.TvShowAdapter;
import com.example.sanskriti.blooper.TvShows.OnAirShowsResponse;
import com.example.sanskriti.blooper.TvShows.PopularTvShowsResponse;
import com.example.sanskriti.blooper.TvShows.TopRatedTvShowsResponse;
import com.example.sanskriti.blooper.TvShows.TvShowDetails;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FullViewActivity extends AppCompatActivity {

    public static final Integer AIRING_TODAY = 1;
    public static final Integer ON_AIR = 2;
    public static final Integer POPULAR = 3;
    public static final Integer TOP_RATED = 4;
    public static final String GET_ANSWER = "getAnswer";
    RecyclerView viewAllShows;
    TvShowAdapter tvShowAdapter;
    List<TvShowDetails> showList = new ArrayList<>();
    Integer answerId;
    boolean isScrolling = false;
    int currentItems;
    int totalItems;
    int scrollOutItems;
    int presentPage = 1;
    public static String NAME = "";
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view2);
        Bundle bundle = getIntent().getExtras();
        answerId = Integer.parseInt(bundle.getCharSequence(GET_ANSWER) + "");

        progressBar = findViewById(R.id.progress);

        viewAllShows = findViewById(R.id.viewAllTvRecyclerView);
        tvShowAdapter = new TvShowAdapter(showList, this);
        viewAllShows.setAdapter(tvShowAdapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        viewAllShows.setLayoutManager(layoutManager);

        if (answerId == 1) {
            NAME = "Airing Today";
            final Retrofit retrofitAiringToday = ApiBaseUrl.getClient();
            ApiInterface serviceAiringToday = retrofitAiringToday.create(ApiInterface.class);
            Call<AiringTodayResponse> callAiringToday = serviceAiringToday.getAiringTodayTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
            callAiringToday.enqueue(new Callback<AiringTodayResponse>() {
                @Override
                public void onResponse(Call<AiringTodayResponse> call, Response<AiringTodayResponse> response) {
                    response.body().getResults().size();
                    List<TvShowDetails> airingTodayShows = response.body().getResults();
                    airingTodayShows.size();
                    showList.addAll(airingTodayShows);
                    //upcomingList.size();

                    viewAllShows.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    tvShowAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<AiringTodayResponse> call, Throwable t) {

                }
            });

        }
        else if(answerId == 2){
            NAME = "On Air";
            Retrofit retrofitonAir = ApiBaseUrl.getClient();
            ApiInterface serviceonAir = retrofitonAir.create(ApiInterface.class);
            Call<OnAirShowsResponse> callonAir = serviceonAir.getOnTheAirTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
            callonAir.enqueue(new Callback<OnAirShowsResponse>() {
                @Override
                public void onResponse(Call<OnAirShowsResponse> call, Response<OnAirShowsResponse> response) {
                    response.body().getResults().size();

                    List<TvShowDetails> onAirShows = response.body().getResults();
                    onAirShows.size();
                    showList.addAll( onAirShows);
                    //upcomingList.size();

                    viewAllShows.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    tvShowAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<OnAirShowsResponse> call, Throwable t) {

                }
            });
        }
        else if(answerId == 3){
            NAME = "Popular";
            final Retrofit retrofitpopular = ApiBaseUrl.getClient();
            ApiInterface servicepopular = retrofitpopular.create(ApiInterface.class);
            Call<PopularTvShowsResponse> callpopular = servicepopular.getPopularTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
            callpopular.enqueue(new Callback<PopularTvShowsResponse>() {
                @Override
                public void onResponse(Call<PopularTvShowsResponse> call, Response<PopularTvShowsResponse> response) {
                    response.body().getResults().size();

                    List<TvShowDetails> popularShows = response.body().getResults();
                    popularShows.size();
                    showList.addAll(popularShows);
                    //upcomingList.size();

                    viewAllShows.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    tvShowAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<PopularTvShowsResponse> call, Throwable t) {

                }
            });
        }else if(answerId == 4){
            NAME = "Top Rated";
            final Retrofit retrofittopRated = ApiBaseUrl.getClient();
            ApiInterface servicetopRated = retrofittopRated.create(ApiInterface.class);
            Call<TopRatedTvShowsResponse> calltopRated = servicetopRated.getTopRatedTVShows("97f67791525ddbcc4192d10f245f2fb0", 1);
            calltopRated.enqueue(new Callback<TopRatedTvShowsResponse>() {
                @Override
                public void onResponse(Call<TopRatedTvShowsResponse> call, Response<TopRatedTvShowsResponse> response) {

                    response.body().getResults().size();

                    List<TvShowDetails> topRatedShows = response.body().getResults();
                    topRatedShows.size();
                    showList.addAll(topRatedShows);
                    //upcomingList.size();

                    viewAllShows.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    tvShowAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TopRatedTvShowsResponse> call, Throwable t) {

                }
            });

        }

        getSupportActionBar().setTitle((CharSequence)NAME);


        viewAllShows.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    presentPage++;
                    fetchData();


                }
            }
        });


    }

    private void fetchData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (answerId == 1){
                    final Retrofit retrofitAiringToday = ApiBaseUrl.getClient();
                    ApiInterface serviceAiringToday = retrofitAiringToday.create(ApiInterface.class);
                    Call<AiringTodayResponse> callAiringToday = serviceAiringToday.getAiringTodayTVShows("97f67791525ddbcc4192d10f245f2fb0", presentPage);
                    callAiringToday.enqueue(new Callback<AiringTodayResponse>() {
                        @Override
                        public void onResponse(Call<AiringTodayResponse> call, Response<AiringTodayResponse> response) {
                            response.body().getResults().size();
                            List<TvShowDetails> airingTodayShows = response.body().getResults();
                            airingTodayShows.size();
                            showList.addAll(airingTodayShows);
                            //upcomingList.size();

                            viewAllShows.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            tvShowAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<AiringTodayResponse> call, Throwable t) {

                        }
                    });
                }else if(answerId == 2){
                    Retrofit retrofitonAir = ApiBaseUrl.getClient();
                    ApiInterface serviceonAir = retrofitonAir.create(ApiInterface.class);
                    Call<OnAirShowsResponse> callonAir = serviceonAir.getOnTheAirTVShows("97f67791525ddbcc4192d10f245f2fb0", presentPage);
                    callonAir.enqueue(new Callback<OnAirShowsResponse>() {
                        @Override
                        public void onResponse(Call<OnAirShowsResponse> call, Response<OnAirShowsResponse> response) {
                            response.body().getResults().size();

                            List<TvShowDetails> onAirShows = response.body().getResults();
                            onAirShows.size();
                            showList.addAll( onAirShows);
                            //upcomingList.size();

                            viewAllShows.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            tvShowAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<OnAirShowsResponse> call, Throwable t) {

                        }
                    });
                }else if(answerId == 3){
                    final Retrofit retrofitpopular = ApiBaseUrl.getClient();
                    ApiInterface servicepopular = retrofitpopular.create(ApiInterface.class);
                    Call<PopularTvShowsResponse> callpopular = servicepopular.getPopularTVShows("97f67791525ddbcc4192d10f245f2fb0", presentPage);
                    callpopular.enqueue(new Callback<PopularTvShowsResponse>() {
                        @Override
                        public void onResponse(Call<PopularTvShowsResponse> call, Response<PopularTvShowsResponse> response) {
                            response.body().getResults().size();

                            List<TvShowDetails> popularShows = response.body().getResults();
                            popularShows.size();
                            showList.addAll(popularShows);
                            //upcomingList.size();

                            viewAllShows.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            tvShowAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<PopularTvShowsResponse> call, Throwable t) {

                        }
                    });
                }else if(answerId == 4){
                    final Retrofit retrofittopRated = ApiBaseUrl.getClient();
                    ApiInterface servicetopRated = retrofittopRated.create(ApiInterface.class);
                    Call<TopRatedTvShowsResponse> calltopRated = servicetopRated.getTopRatedTVShows("97f67791525ddbcc4192d10f245f2fb0", presentPage);
                    calltopRated.enqueue(new Callback<TopRatedTvShowsResponse>() {
                        @Override
                        public void onResponse(Call<TopRatedTvShowsResponse> call, Response<TopRatedTvShowsResponse> response) {

                            response.body().getResults().size();

                            List<TvShowDetails> topRatedShows = response.body().getResults();
                            topRatedShows.size();
                            showList.addAll(topRatedShows);
                            //upcomingList.size();

                            viewAllShows.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            tvShowAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<TopRatedTvShowsResponse> call, Throwable t) {

                        }
                    });
                }
            }
        }, 0000);
    }
}
