package com.example.sanskriti.blooper.Activities.MovieActivityPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Movies.Genre;
import com.example.sanskriti.blooper.Movies.GenreList;
import com.example.sanskriti.blooper.Movies.MovieCast;
import com.example.sanskriti.blooper.Movies.MovieCreditsResponse;
import com.example.sanskriti.blooper.Movies.MovieCrew;
import com.example.sanskriti.blooper.Movies.SimilarMoviesResponse;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.Reviews.Reviews;
import com.example.sanskriti.blooper.Reviews.ReviewsResponse;
import com.example.sanskriti.blooper.Movies.SmallAdapters.CastAdapter;
import com.example.sanskriti.blooper.Movies.SmallAdapters.CrewAdapter;
import com.example.sanskriti.blooper.Movies.SmallAdapters.ReviewAdapter;
import com.example.sanskriti.blooper.Movies.SmallAdapters.SimilarMoviesAdapter;
import com.example.sanskriti.blooper.Movies.SmallAdapters.VideoAdapter;
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

public class AboutMovieActivity extends AppCompatActivity {
    public static final String ID = "id";
    public static final String VOTEAVERAGE = "voteAverage";
    public static final String TITLE = "title";
    public static final String POSTERPATH = "posterPath";
    public static final String BACKDROPPATH = "backdropPath";
    public static final String OVERVIEW = "overview";
    public static final String RELEASEDATE = "releaseDate";
    public ArrayList<Integer> genres = new ArrayList<>();
    ImageView borderPath;
    RoundedImageView posterPath;
    TextView overViewTextView;
    TextView genreList;
    TextView movieName;
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
    SimilarMoviesAdapter similarMoviesAdapter;
    List<MovieCast> castList = new ArrayList<>();
    List<Videos> videosList = new ArrayList<>();
    List<MovieCrew> crewList = new ArrayList<>();
    List<Reviews> reviewsList = new ArrayList<>();
    List<AboutMovie> similarList = new ArrayList<>();



    TextView trailersAnsClips;
    TextView Cast;
    TextView crewS;
    TextView reviewsS;
    TextView overView;
    TextView similarS;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_movie);
        borderPath = findViewById(R.id.borderPathImage);
        posterPath = findViewById(R.id.posterPathImage);
        movieName = findViewById(R.id.movieNameTextView);
        genreList = findViewById(R.id.genresTextView);
        average = findViewById(R.id.averageTextView);
        overViewTextView = findViewById(R.id.overviewTextView);
        releaseDate = findViewById(R.id.releaseDate);

        trailersAnsClips = findViewById(R.id.trailersAndClips);
        Cast = findViewById(R.id.Cast);
        crewS = findViewById(R.id.crew);
        reviewsS = findViewById(R.id.reviews);
        overView = findViewById(R.id.overview);
        similarS = findViewById(R.id.similar);


        Bundle bundle = getIntent().getExtras();
        getSupportActionBar().setTitle(bundle.getCharSequence(TITLE));

        borderPath.setImageResource(R.drawable.no);
        posterPath.setImageResource(R.drawable.no);

        Picasso.get().load("http://image.tmdb.org/t/p/w780" + bundle.getCharSequence(BACKDROPPATH))
                .resize(1800, 800).into(borderPath);

        Picasso.get().load("http://image.tmdb.org/t/p/w342" + bundle.getCharSequence(POSTERPATH)).into(posterPath);
        movieName.setText(bundle.getCharSequence(TITLE));
        average.setText(bundle.getCharSequence(VOTEAVERAGE).toString() + "/10");
        overView.setVisibility(View.VISIBLE);
        overViewTextView.setText(bundle.getCharSequence(OVERVIEW));
        if(bundle.getCharSequence(RELEASEDATE) != null) {
            releaseDate.setText("Release Date:-  " + bundle.getCharSequence(RELEASEDATE));
        }
        else{
            releaseDate.setText("Release Date:-  N/A");
        }
        final ArrayList<Integer> list_of_genres= bundle.getIntegerArrayList("genres");

        Retrofit retrofitGenres = ApiBaseUrl.getClient();
        ApiInterface serviceGenres = retrofitGenres.create(ApiInterface.class);
        Call<GenreList> callGenres = serviceGenres.getMovieGenresList("97f67791525ddbcc4192d10f245f2fb0");
        callGenres.enqueue(new Callback<GenreList>() {
            @Override
            public void onResponse(Call<GenreList> call, Response<GenreList> response) {
                List<Genre> genres_of_movie = response.body().getGenres();


                int j = 0;
                for (int i = 0; i<genres_of_movie.size(); i++){
                    if(j<list_of_genres.size() && genres_of_movie.get(i).getId().equals(list_of_genres.get(j))){
                        genres_name = genres_name  + genres_of_movie.get(i).getGenreName() + ", ";
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
            public void onFailure(Call<GenreList> call, Throwable t) {

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
        Call<MovieCreditsResponse> callCast = serviceCast.getMovieCredits(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callCast.enqueue(new Callback<MovieCreditsResponse>() {
            @Override
            public void onResponse(Call<MovieCreditsResponse> call, Response<MovieCreditsResponse> response) {
                List<MovieCast> cast_of_movie = response.body().getCasts();
                castList.addAll(cast_of_movie);
                if(castList.size()!=0){
                    Cast.setVisibility(View.VISIBLE);
                }
                cast.setVisibility(View.VISIBLE);
                castAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieCreditsResponse> call, Throwable t) {

            }
        });


        videos = findViewById(R.id.trailersAndClipsRecyclerView);
        videoAdapter = new VideoAdapter(videosList,this);
        videos.setAdapter(videoAdapter);
        LinearLayoutManager layoutManagerVideos = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        videos.setLayoutManager(layoutManagerVideos);
        Retrofit retrofitVideos = ApiBaseUrl.getClient();
        ApiInterface serviceVideo = retrofitVideos.create(ApiInterface.class);
        Call<VideosResponse> callVideos = serviceVideo.getMovieVideos(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callVideos.enqueue(new Callback<VideosResponse>() {
            @Override
            public void onResponse(Call<VideosResponse> call, Response<VideosResponse> response) {
                List<Videos>videos_of_movie = response.body().getVideos();
                videosList.addAll(videos_of_movie);
                if(videosList.size() !=0){
                    trailersAnsClips.setVisibility(View.VISIBLE);
                }
                videos.setVisibility(View.VISIBLE);
                videoAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<VideosResponse> call, Throwable t) {

            }
        });


        crew = findViewById(R.id.crewRecyclerView);
        crewAdapter = new CrewAdapter(crewList, this);
        crew.setAdapter(crewAdapter);
        LinearLayoutManager layoutManagerCrew = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        crew.setLayoutManager(layoutManagerCrew);
        Retrofit retrofitCrew = ApiBaseUrl.getClient();
        ApiInterface serviceCrew = retrofitCrew.create(ApiInterface.class);
        Call<MovieCreditsResponse> callCrew = serviceCrew.getMovieCredits(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callCrew.enqueue(new Callback<MovieCreditsResponse>() {
            @Override
            public void onResponse(Call<MovieCreditsResponse> call, Response<MovieCreditsResponse> response) {
                List<MovieCrew> crew_of_movie = response.body().getCrews();
                crewList.addAll(crew_of_movie);
                if(crewList.size()!=0){
                    crewS.setVisibility(View.VISIBLE);
                }
                crew.setVisibility(View.VISIBLE);
                crewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieCreditsResponse> call, Throwable t) {

            }
        });


        reviews = findViewById(R.id.reviewRecyclerView);
        reviewAdapter = new ReviewAdapter(reviewsList, this);
        reviews.setAdapter(reviewAdapter);
        LinearLayoutManager layoutManagerReviews = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        reviews.setLayoutManager(layoutManagerReviews);
        Retrofit retrofitReviews = ApiBaseUrl.getClient();
        ApiInterface serviceReviews = retrofitReviews.create(ApiInterface.class);
        Call<ReviewsResponse> callReviews = serviceReviews.getMovieReviews(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0");
        callReviews.enqueue(new Callback<ReviewsResponse>() {
            @Override
            public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                List<Reviews> review_of_movie = response.body().getReviews();
                reviewsList.addAll(review_of_movie);
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

        similar = findViewById(R.id.similarRecyclerView);
        similarMoviesAdapter = new SimilarMoviesAdapter(similarList, this);
        similar.setAdapter(similarMoviesAdapter);
        LinearLayoutManager layoutManagerSimilar = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        similar.setLayoutManager(layoutManagerSimilar);
        Retrofit retrofitSimilar= ApiBaseUrl.getClient();
        ApiInterface serviceSimilar = retrofitSimilar.create(ApiInterface.class);
        Call<SimilarMoviesResponse> callSimilar = serviceSimilar.getSimilarMovies(Integer.parseInt(id_of_movie),"97f67791525ddbcc4192d10f245f2fb0", 1);
        callSimilar.enqueue(new Callback<SimilarMoviesResponse>() {
            @Override
            public void onResponse(Call<SimilarMoviesResponse> call, Response<SimilarMoviesResponse> response) {
                List<AboutMovie> similarMovies = response.body().getResults();
                similarMovies.size();
                similarList.addAll(similarMovies);
                if(similarList.size()!=0){
                    similarS.setVisibility(View.VISIBLE);
                }
               similar.setVisibility(View.VISIBLE);
                similarMoviesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SimilarMoviesResponse> call, Throwable t) {

            }
        });















    }
}
