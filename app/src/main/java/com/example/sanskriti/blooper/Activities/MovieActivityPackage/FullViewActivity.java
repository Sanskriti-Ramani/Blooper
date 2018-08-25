package com.example.sanskriti.blooper.Activities.MovieActivityPackage;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.LargeAdapters.MoviesAdapter;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Movies.NowShowingMoviesResponse;
import com.example.sanskriti.blooper.Movies.PopularMoviesResponse;
import com.example.sanskriti.blooper.Movies.TopRatedMoviesResponse;
import com.example.sanskriti.blooper.Movies.UpcomingMoviesResponse;
import com.example.sanskriti.blooper.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FullViewActivity extends AppCompatActivity {
    public static final Integer NOW_SHOWING = 1;
    public static final Integer  POPULAR  = 2;
    public static final Integer UPCOMING = 3;
    public static final Integer TOP_RATED= 4;
    public static final String GET_ANSWER = "getAnswer";
    RecyclerView viewAllMovies;
    MoviesAdapter moviesAdapter;
    List<AboutMovie> movieList = new ArrayList<>();
    private Call<NowShowingMoviesResponse> callNowShowing;
    Integer answerId;
    boolean isScrolling = false;
    int currentItems;
    int totalItems;
    int scrollOutItems;
    int presentPage =1;

    ProgressBar progressBar;

    public static String NAME = "";








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);


        Bundle bundle = getIntent().getExtras();
        answerId = Integer.parseInt(bundle.getCharSequence(GET_ANSWER)+"");
        progressBar = findViewById(R.id.progress);


        viewAllMovies = findViewById(R.id.viewAllRecyclerView);
        moviesAdapter = new MoviesAdapter(movieList, this);
        viewAllMovies.setAdapter(moviesAdapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        viewAllMovies.setLayoutManager(layoutManager);



        if(answerId == 1){


            NAME = "Now Showing";
            Retrofit retrofitMovies = ApiBaseUrl.getClient();
            ApiInterface serviceMovies = retrofitMovies.create(ApiInterface.class);
            callNowShowing = serviceMovies.getNowShowingMovies("97f67791525ddbcc4192d10f245f2fb0",1, null);
            callNowShowing.enqueue(new Callback<NowShowingMoviesResponse>() {
                @Override
                public void onResponse(Call<NowShowingMoviesResponse> call, Response<NowShowingMoviesResponse> response) {

                    if (response.body() == null) return;
                    if (response.body().getResults() == null) return;
                    List<AboutMovie> nowShowingMovies = response.body().getResults();
                    nowShowingMovies.size();
                    movieList.addAll(nowShowingMovies);

                    viewAllMovies.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    moviesAdapter.notifyDataSetChanged();



                }

                @Override
                public void onFailure(Call<NowShowingMoviesResponse> call, Throwable t) {

                }
            });



        }
      else if(answerId == 2){
            NAME = "Popular";
            Retrofit retrofitPopular = ApiBaseUrl.getClient();
            ApiInterface servicePopular = retrofitPopular.create(ApiInterface.class);
            Call<PopularMoviesResponse> callPopular = servicePopular.getPopularMovies("97f67791525ddbcc4192d10f245f2fb0", 1, null);
            callPopular.enqueue(new Callback<PopularMoviesResponse>() {
                @Override
                public void onResponse(Call<PopularMoviesResponse> call, Response<PopularMoviesResponse> response) {
                    response.body().getResults().size();

                    List<AboutMovie> popularMovies = response.body().getResults();
                    popularMovies.size();
                    movieList.addAll(popularMovies);

                    viewAllMovies.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    moviesAdapter.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<PopularMoviesResponse> call, Throwable t) {

                }
            });
        }else if(answerId == 3){
            NAME = "Upcoming";
            Retrofit retrofitUpcoming = ApiBaseUrl.getClient();
            ApiInterface serviceUpcoming = retrofitUpcoming.create(ApiInterface.class);
            Call<UpcomingMoviesResponse> callUpcoming = serviceUpcoming.getUpcomingMovies("97f67791525ddbcc4192d10f245f2fb0", 1, null);
            callUpcoming.enqueue(new Callback<UpcomingMoviesResponse>() {
                @Override
                public void onResponse(Call<UpcomingMoviesResponse> call, Response<UpcomingMoviesResponse> response) {

                    response.body().getResults().size();

                    List<AboutMovie> upcomingMovies = response.body().getResults();
                    upcomingMovies.size();


                    movieList.addAll(upcomingMovies);
                    progressBar.setVisibility(View.INVISIBLE);

                    viewAllMovies.setVisibility(View.VISIBLE);
                    moviesAdapter.notifyDataSetChanged();


                }

                @Override
                public void onFailure(Call<UpcomingMoviesResponse> call, Throwable t) {

                }
            });


        }
        else if(answerId == 4){
            NAME = "Top Rated";
            Retrofit retrofitTopRated = ApiBaseUrl.getClient();
            ApiInterface serviceTopRated = retrofitTopRated.create(ApiInterface.class);
            Call<TopRatedMoviesResponse> callTopRated = serviceTopRated.getTopRatedMovies("97f67791525ddbcc4192d10f245f2fb0", 1, null);
            callTopRated.enqueue(new Callback<TopRatedMoviesResponse>() {
                @Override
                public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {
                    response.body().getResults().size();

                    List<AboutMovie> topRatedMovies = response.body().getResults();
                    topRatedMovies.size();


                    movieList.addAll(topRatedMovies);
                    progressBar.setVisibility(View.INVISIBLE);

                    viewAllMovies.setVisibility(View.VISIBLE);
                    moviesAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {

                }
            });
        }



        getSupportActionBar().setTitle((CharSequence)NAME);

        viewAllMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems+scrollOutItems == totalItems)){
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

                Retrofit retrofitMovies = ApiBaseUrl.getClient();
                ApiInterface serviceMovies = retrofitMovies.create(ApiInterface.class);



                if(answerId == 1) {

                    callNowShowing = serviceMovies.getNowShowingMovies("97f67791525ddbcc4192d10f245f2fb0", presentPage, null);
                    callNowShowing.enqueue(new Callback<NowShowingMoviesResponse>() {
                        @Override
                        public void onResponse(Call<NowShowingMoviesResponse> call, Response<NowShowingMoviesResponse> response) {

                            if (response.body() == null) return;
                            if (response.body().getResults() == null) return;
                            List<AboutMovie> nowShowingMovies = response.body().getResults();
                            nowShowingMovies.size();
                            movieList.addAll(nowShowingMovies);
                            //upcomingList.size();

                            viewAllMovies.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            moviesAdapter.notifyDataSetChanged();


                        }

                        @Override
                        public void onFailure(Call<NowShowingMoviesResponse> call, Throwable t) {

                        }
                    });
                }else if(answerId == 2){
                    Retrofit retrofitPopular = ApiBaseUrl.getClient();
                    ApiInterface servicePopular = retrofitPopular.create(ApiInterface.class);
                    Call<PopularMoviesResponse> callPopular = servicePopular.getPopularMovies("97f67791525ddbcc4192d10f245f2fb0", presentPage, null);
                    callPopular.enqueue(new Callback<PopularMoviesResponse>() {
                        @Override
                        public void onResponse(Call<PopularMoviesResponse> call, Response<PopularMoviesResponse> response) {
                            response.body().getResults().size();

                            List<AboutMovie> popularMovies = response.body().getResults();
                            popularMovies.size();
                            movieList.addAll(popularMovies);

                            viewAllMovies.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            moviesAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<PopularMoviesResponse> call, Throwable t) {

                        }
                    });

                }else if(answerId == 3){
                    Retrofit retrofitUpcoming = ApiBaseUrl.getClient();
                    ApiInterface serviceUpcoming = retrofitUpcoming.create(ApiInterface.class);
                    Call<UpcomingMoviesResponse> callUpcoming = serviceUpcoming.getUpcomingMovies("97f67791525ddbcc4192d10f245f2fb0", presentPage, null);
                    callUpcoming.enqueue(new Callback<UpcomingMoviesResponse>() {
                        @Override
                        public void onResponse(Call<UpcomingMoviesResponse> call, Response<UpcomingMoviesResponse> response) {

                            response.body().getResults().size();

                            List<AboutMovie> upcomingMovies = response.body().getResults();
                            upcomingMovies.size();


                            movieList.addAll(upcomingMovies);

                            viewAllMovies.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            moviesAdapter.notifyDataSetChanged();



                        }

                        @Override
                        public void onFailure(Call<UpcomingMoviesResponse> call, Throwable t) {

                        }
                    });

                }else if(answerId == 4){
                    Retrofit retrofitTopRated = ApiBaseUrl.getClient();
                    ApiInterface serviceTopRated = retrofitTopRated.create(ApiInterface.class);
                    Call<TopRatedMoviesResponse> callTopRated = serviceTopRated.getTopRatedMovies("97f67791525ddbcc4192d10f245f2fb0", presentPage, null);
                    callTopRated.enqueue(new Callback<TopRatedMoviesResponse>() {
                        @Override
                        public void onResponse(Call<TopRatedMoviesResponse> call, Response<TopRatedMoviesResponse> response) {
                            response.body().getResults().size();

                            List<AboutMovie> topRatedMovies = response.body().getResults();
                            topRatedMovies.size();


                            movieList.addAll(topRatedMovies);

                            viewAllMovies.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            moviesAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<TopRatedMoviesResponse> call, Throwable t) {

                        }
                    });
                }






            }
        },0000);
    }
}
