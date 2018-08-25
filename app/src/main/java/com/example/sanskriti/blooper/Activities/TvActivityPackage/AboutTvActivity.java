package com.example.sanskriti.blooper.Activities.TvActivityPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.MovieCast;
import com.example.sanskriti.blooper.Movies.MovieCrew;



import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.Reviews.Reviews;
import com.example.sanskriti.blooper.Reviews.ReviewsResponse;
import com.example.sanskriti.blooper.TvShows.Genres;
import com.example.sanskriti.blooper.TvShows.GenresList;
import com.example.sanskriti.blooper.TvShows.SimilarTvShowsResponse;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.CastAdapter;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.CrewAdapter;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.ReviewAdapter;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.SimilarShowssAdapter;
import com.example.sanskriti.blooper.TvShows.SmallAdapters.VideoAdapter;
import com.example.sanskriti.blooper.TvShows.TvShowCast;
import com.example.sanskriti.blooper.TvShows.TvShowCreditResponse;
import com.example.sanskriti.blooper.TvShows.TvShowCrew;
import com.example.sanskriti.blooper.TvShows.TvShowDetails;
import com.example.sanskriti.blooper.Videos.Videos;
import com.example.sanskriti.blooper.Videos.VideosResponse;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AboutTvActivity extends AppCompatActivity {
    public static final String ID = "id";
    public static final String VOTEAVERAGE = "voteAverage";
    public static final String TITLE = "title";
    public static final String POSTERPATH = "posterPath";
    public static final String BACKDROPPATH = "backdropPath";
    public static final String OVERVIEW = "overview";
    public static final String FIRSTAIR = "firstAirDate";
    public static final String RUNTIME = "runtime";
    public static final String STATUS = "status";
    public static final String COUNTRY = "country";
    public ArrayList<Integer> genres = new ArrayList<>();

    ImageView borderPath;
    RoundedImageView posterPath;
    TextView overViewTextView;
    TextView genreList;
    TextView showName;
    TextView average;
    TextView releaseDate;
    public String genres_name = "";


    RecyclerView cast;
    RecyclerView videos;
    RecyclerView crew;
    RecyclerView reviews;
    RecyclerView similar;

    CastAdapter castAdapter;
    VideoAdapter videoAdapter;
    CrewAdapter crewAdapter;
    ReviewAdapter reviewAdapter;
    SimilarShowssAdapter showssAdapter;


    List<TvShowCast> castList = new ArrayList<>();
    List<Videos> videosList = new ArrayList<>();
    List<TvShowCrew> crewList = new ArrayList<>();
    List<Reviews> reviewsList = new ArrayList<>();
    List<TvShowDetails> similarList = new ArrayList<>();


    TextView trailersAnsClips;
    TextView Cast;
    TextView crewS;
    TextView reviewsS;
    TextView overView;
    TextView similarS;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_tv);

        trailersAnsClips = findViewById(R.id.trailersAndClips);
        overView = findViewById(R.id.overview);
        Cast = findViewById(R.id.Cast);
        crewS = findViewById(R.id.crew);
        reviewsS = findViewById(R.id.reviews);
        similarS = findViewById(R.id.similar);

        borderPath = findViewById(R.id.borderPathImage);
        posterPath = findViewById(R.id.posterPathImage);
        showName = findViewById(R.id.showNameTextView);
        genreList = findViewById(R.id.genresTextView);
        average = findViewById(R.id.averageTextView);
        overViewTextView = findViewById(R.id.overviewTextView);
        releaseDate = findViewById(R.id.releaseDate);

        Bundle bundle = getIntent().getExtras();
        getSupportActionBar().setTitle(bundle.getCharSequence(TITLE));

        Picasso.get().load("http://image.tmdb.org/t/p/w780" + bundle.getCharSequence(BACKDROPPATH))
                .resize(1200, 800).into(borderPath);
        Picasso.get().load("http://image.tmdb.org/t/p/w342" + bundle.getCharSequence(POSTERPATH)).into(posterPath);
        showName.setText(bundle.getCharSequence(TITLE));
        average.setText(bundle.getCharSequence(VOTEAVERAGE).toString() + "/10");
        overViewTextView.setText(bundle.getCharSequence(OVERVIEW));
        if(bundle.getCharSequence(FIRSTAIR) != null) {
            releaseDate.setText("First Air Date:-  " + bundle.getCharSequence(FIRSTAIR));
        }
        else{
            releaseDate.setText("First Air Date:-  N/A");
        }
        overView.setVisibility(View.VISIBLE);
        final ArrayList<Integer> list_of_genres= bundle.getIntegerArrayList("genres");

        Retrofit retrofitGenres = ApiBaseUrl.getClient();
        ApiInterface serviceGenres = retrofitGenres.create(ApiInterface.class);
        Call<GenresList> callGenres = serviceGenres.getshowGenresList("97f67791525ddbcc4192d10f245f2fb0");
        callGenres.enqueue(new Callback<GenresList>() {
            @Override
            public void onResponse(Call<GenresList> call, Response<GenresList> response) {
                List<Genres> genres_of_show = response.body().getGenres();


                int j = 0;
                for (int i = 0; i<genres_of_show.size(); i++){
                    if(j<list_of_genres.size() && genres_of_show.get(i).getId().equals(list_of_genres.get(j))){
                        genres_name = genres_name  + genres_of_show.get(i).getGenreName() + ", ";
                        j++;
                    }

                }

                String final_genre = "";

                for(int i = 0; i<genres_name.length()-2; i++){
                    final_genre = final_genre + genres_name.charAt(i);
                }
                genreList.setText(final_genre);




            }

            @Override
            public void onFailure(Call<GenresList> call, Throwable t) {

            }
        });

        cast = findViewById(R.id.castRecyclerView);
        castAdapter = new CastAdapter(castList, this);
        cast.setAdapter(castAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        cast.setLayoutManager(layoutManager);
        Retrofit retrofitCast = ApiBaseUrl.getClient();
        ApiInterface serviceCast = retrofitCast.create(ApiInterface.class);
        String  id_of_movie = bundle.getCharSequence(ID) + "";
        //Log.d("Genres" ,"abc" + id_of_movie);
        Call<TvShowCreditResponse> callCast = serviceCast.getTVShowCredits(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callCast.enqueue(new Callback<TvShowCreditResponse>() {
            @Override
            public void onResponse(Call<TvShowCreditResponse> call, Response<TvShowCreditResponse> response) {
                List<TvShowCast> cast_of_show = response.body().getCasts();
                castList.addAll(cast_of_show);
                if (castList.size() != 0){
                    Cast.setVisibility(View.VISIBLE);
                }
                cast.setVisibility(View.VISIBLE);
                castAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvShowCreditResponse> call, Throwable t) {

            }
        });

        reviews = findViewById(R.id.reviewRecyclerView);
        reviewAdapter = new ReviewAdapter(reviewsList, this);
        reviews.setAdapter(reviewAdapter);
        LinearLayoutManager layoutManagerReviews = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        reviews.setLayoutManager(layoutManagerReviews);
        Retrofit retrofitReviews = ApiBaseUrl.getClient();
        ApiInterface serviceReviews = retrofitReviews.create(ApiInterface.class);
        Call<ReviewsResponse> callReviews = serviceReviews.getshowReviews(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callReviews.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                List<Reviews> review_of_show = response.body().getReviews();
                reviewsList.addAll(review_of_show);
                if(reviewsList.size()!=0){
                    reviewsS.setVisibility(View.VISIBLE);
                }
                reviews.setVisibility(View.VISIBLE);
                reviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ReviewsResponse> call, Throwable t) {

            }
        });



        crew = findViewById(R.id.crewRecyclerView);
        crewAdapter = new CrewAdapter(crewList, this);
        crew.setAdapter(crewAdapter);
        LinearLayoutManager layoutManagerCrew = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        crew.setLayoutManager(layoutManagerCrew);
        Retrofit retrofitCrew = ApiBaseUrl.getClient();
        ApiInterface serviceCrew = retrofitCrew.create(ApiInterface.class);
        Call<TvShowCreditResponse> callCrew = serviceCrew.getTVShowCredits(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callCrew.enqueue(new Callback<TvShowCreditResponse>() {
            @Override
            public void onResponse(Call<TvShowCreditResponse> call, Response<TvShowCreditResponse> response) {
                List<TvShowCrew> crew_of_show = response.body().getCrews();
                crewList.addAll(crew_of_show);
                crew.setVisibility(View.VISIBLE);
                if(crewList.size()!=0){
                    crewS.setVisibility(View.VISIBLE);
                }
                crewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TvShowCreditResponse> call, Throwable t) {

            }
        });


        videos = findViewById(R.id.trailersAndClipsRecyclerView);
        videoAdapter = new VideoAdapter(videosList,this);
        videos.setAdapter(videoAdapter);
        LinearLayoutManager layoutManagerVideos = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        videos.setLayoutManager(layoutManagerVideos);
        Retrofit retrofitVideos = ApiBaseUrl.getClient();
        ApiInterface serviceVideo = retrofitVideos.create(ApiInterface.class);
        Call<VideosResponse> callVideos = serviceVideo.getTVShowVideos(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callVideos.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                List<Videos>videos_of_show = response.body().getVideos();
                videosList.addAll(videos_of_show);
                videos.setVisibility(View.VISIBLE);
                if (videosList.size()!=0){
                    trailersAnsClips.setVisibility(View.VISIBLE);
                }
                videoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {

            }
        });


        similar = findViewById(R.id.similarRecyclerView);
       showssAdapter = new SimilarShowssAdapter(similarList, this);
        similar.setAdapter(showssAdapter);
        LinearLayoutManager layoutManagerSimilar = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        similar.setLayoutManager(layoutManagerSimilar);
        Retrofit retrofitSimilar= ApiBaseUrl.getClient();
        ApiInterface serviceSimilar = retrofitSimilar.create(ApiInterface.class);
        Call<SimilarTvShowsResponse> callSimilar = serviceSimilar.getSimilarTVShows(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0", 1);
        callSimilar.enqueue(new Callback<SimilarTvShowsResponse>() {
            @Override
            public void onResponse(Call<SimilarTvShowsResponse> call, Response<SimilarTvShowsResponse> response) {
                List<TvShowDetails> similarShows = response.body().getResults();
                similarShows.size();
                similarList.addAll(similarShows);
                similar.setVisibility(View.VISIBLE);
                if (similarList.size()!=0){
                    similarS.setVisibility(View.VISIBLE);
                }
                showssAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SimilarTvShowsResponse> call, Throwable t) {

            }
        });







    }
}
