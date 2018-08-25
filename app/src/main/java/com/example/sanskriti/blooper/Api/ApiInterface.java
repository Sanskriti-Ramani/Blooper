package com.example.sanskriti.blooper.Api;

import com.example.sanskriti.blooper.Movies.AboutMovie;
import com.example.sanskriti.blooper.Movies.GenreList;
import com.example.sanskriti.blooper.Movies.MovieCreditsResponse;
import com.example.sanskriti.blooper.Movies.MovieDetails;
import com.example.sanskriti.blooper.Movies.NowShowingMoviesResponse;
import com.example.sanskriti.blooper.Movies.PopularMoviesResponse;
import com.example.sanskriti.blooper.Movies.SimilarMoviesResponse;
import com.example.sanskriti.blooper.Movies.TopRatedMoviesResponse;
import com.example.sanskriti.blooper.Movies.UpcomingMoviesResponse;
import com.example.sanskriti.blooper.People.People;
import com.example.sanskriti.blooper.People.PersonMovies;
import com.example.sanskriti.blooper.People.PersonMoviesResponse;
import com.example.sanskriti.blooper.People.PersonShowsResponse;
import com.example.sanskriti.blooper.Reviews.ReviewsResponse;
import com.example.sanskriti.blooper.SearchFragments.SearchMovieResponse;
import com.example.sanskriti.blooper.SearchFragments.SearchShowResponse;
import com.example.sanskriti.blooper.TvShows.AboutTvShow;
import com.example.sanskriti.blooper.TvShows.AiringTodayResponse;
import com.example.sanskriti.blooper.TvShows.GenresList;
import com.example.sanskriti.blooper.TvShows.OnAirShowsResponse;
import com.example.sanskriti.blooper.TvShows.PopularTvShowsResponse;
import com.example.sanskriti.blooper.TvShows.SimilarTvShowsResponse;
import com.example.sanskriti.blooper.TvShows.TopRatedTvShowsResponse;
import com.example.sanskriti.blooper.TvShows.TvShowCreditResponse;
import com.example.sanskriti.blooper.Videos.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/now_playing")
    Call<NowShowingMoviesResponse> getNowShowingMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/popular")
    Call<PopularMoviesResponse> getPopularMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/upcoming")
    Call<UpcomingMoviesResponse> getUpcomingMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/top_rated")
    Call<TopRatedMoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") Integer page, @Query("region") String region);

    @GET("movie/{id}")
    Call<MovieDetails> getMovieDetails(@Path("id") Integer movieId, @Query("api_key") String apiKey);


    @GET("movie/{id}/videos")
    Call<VideosResponse> getMovieVideos(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/credits")
    Call<MovieCreditsResponse> getMovieCredits(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/similar")
   Call<SimilarMoviesResponse> getSimilarMovies(@Path("id") Integer movieId, @Query("api_key") String apiKey, @Query("page") Integer page);


    @GET("genre/movie/list")
    Call<GenreList> getMovieGenresList(@Query("api_key") String apiKey);
    @GET("movie/{id}/reviews")
    Call<ReviewsResponse> getMovieReviews(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("person/{person_id}")
    Call<People> getPeopleDetails(@Path("person_id") Integer personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/movie_credits")
    Call<PersonMoviesResponse> getMovieCreditDetails(@Path("person_id") Integer personId, @Query("api_key") String apiKey);


    @GET("search/movie")
    Call<SearchMovieResponse> getSearchMovieDetails(@Query("api_key") String apiKey , @Query("query") String query);



    //tvShows
    @GET("tv/{id}/reviews")
    Call<ReviewsResponse> getshowReviews(@Path("id") Integer showId, @Query("api_key") String apiKey);


    @GET("tv/airing_today")
    Call<AiringTodayResponse> getAiringTodayTVShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/on_the_air")
    Call<OnAirShowsResponse> getOnTheAirTVShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/popular")
    Call<PopularTvShowsResponse> getPopularTVShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/top_rated")
    Call<TopRatedTvShowsResponse> getTopRatedTVShows(@Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("tv/{id}")
    Call<AboutTvShow> getTVShowDetails(@Path("id") Integer tvShowId, @Query("api_key") String apiKey);

    @GET("tv/{id}/videos")
    Call<VideosResponse> getTVShowVideos(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("tv/{id}/credits")
    Call<TvShowCreditResponse> getTVShowCredits(@Path("id") Integer movieId, @Query("api_key") String apiKey);

    @GET("tv/{id}/similar")
    Call<SimilarTvShowsResponse> getSimilarTVShows(@Path("id") Integer movieId, @Query("api_key") String apiKey, @Query("page") Integer page);

    @GET("genre/tv/list")
    Call<GenresList> getshowGenresList(@Query("api_key") String apiKey);

    @GET("person/{person_id}/tv_credits")
    Call<PersonShowsResponse> getShowCreditDetails(@Path("person_id") Integer personId, @Query("api_key") String apiKey);


    @GET("search/tv")
    Call<SearchShowResponse> getSearchShowDetails(@Query("api_key") String apiKey , @Query("query") String query);


}
