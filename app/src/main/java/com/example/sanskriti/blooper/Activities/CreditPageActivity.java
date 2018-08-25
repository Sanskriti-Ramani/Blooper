package com.example.sanskriti.blooper.Activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanskriti.blooper.Api.ApiBaseUrl;
import com.example.sanskriti.blooper.Api.ApiInterface;
import com.example.sanskriti.blooper.Movies.GenreList;
import com.example.sanskriti.blooper.People.MovieOfThePersonAdapter;
import com.example.sanskriti.blooper.People.People;
import com.example.sanskriti.blooper.People.PersonMovies;
import com.example.sanskriti.blooper.People.PersonMoviesResponse;
import com.example.sanskriti.blooper.People.PersonShows;
import com.example.sanskriti.blooper.People.PersonShowsResponse;
import com.example.sanskriti.blooper.People.ShowsOfThePersonAdapter;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.Videos.Videos;
import com.example.sanskriti.blooper.Videos.VideosResponse;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreditPageActivity extends AppCompatActivity {
    public static final String ID = "id";
    RoundedImageView creditPicture;
    TextView creditName;
    TextView creditDOB;
    TextView birthPlace;
    TextView overView;
    String idOfTheCredit;

    RecyclerView  movieOfTheCredit;
    RecyclerView showsOfTheCredit;
    MovieOfThePersonAdapter movieOfThePersonAdapter;
    ShowsOfThePersonAdapter showsOfThePersonAdapter;
    List<PersonMovies> moviesList = new ArrayList<>();
    List<PersonShows> showsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_page);
        Bundle bundle = getIntent().getExtras();

        idOfTheCredit  = bundle.getCharSequence(ID)+"";
        creditPicture = findViewById(R.id.picture);
        creditName = findViewById(R.id.nameOfTheCredit);
        creditDOB = findViewById(R.id.ageOfThePerson);
        birthPlace = findViewById(R.id.birthPlaceOfThePerson);
        overView = findViewById(R.id.overviewTextView);
        Retrofit retrofitPeoples = ApiBaseUrl.getClient();
        ApiInterface servicePeoples = retrofitPeoples.create(ApiInterface.class);
        Call<People> callPeople = servicePeoples.getPeopleDetails(Integer.parseInt(idOfTheCredit),"97f67791525ddbcc4192d10f245f2fb0");
        callPeople.enqueue(new Callback<People>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
               // People peopleList =  response.body();
              creditName.setText(response.body().getName().toString());
                getSupportActionBar().setTitle((CharSequence) response.body().getName());
                Picasso.get().load("http://image.tmdb.org/t/p/w780" + response.body().getProfilePath()).
                        into(creditPicture);
                if(response.body().getDateOfBirth() != null) {
                    if(response.body().getDateOfDeath()!=null){
                        setAge(response.body().getDateOfBirth(),false);
                    }else{
                        setAge(response.body().getDateOfBirth(),true);
                    }


                }else{
                    creditDOB.setText("N/A");
                }

                if(response.body().getPlaceOfBirth() == null){
                    birthPlace.setText("N/A");
                    }else {
                    birthPlace.setText(response.body().getPlaceOfBirth());
                }

                if(response.body().getBiography()!=null){
                    overView.setText(response.body().getBiography());
                }else{
                    overView.setText("N/A");
                }


            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {

            }
        });


        movieOfTheCredit = findViewById(R.id.movieOfTheCredits);
        movieOfThePersonAdapter = new MovieOfThePersonAdapter(moviesList,this);
        movieOfTheCredit.setAdapter(movieOfThePersonAdapter);
        LinearLayoutManager layoutManagerMovies = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        movieOfTheCredit.setLayoutManager(layoutManagerMovies);
        final Retrofit retrofitMovies = ApiBaseUrl.getClient();
        ApiInterface serviceMovies = retrofitMovies.create(ApiInterface.class);
        Call<PersonMoviesResponse> callMovies = serviceMovies.getMovieCreditDetails(Integer.parseInt(idOfTheCredit),"97f67791525ddbcc4192d10f245f2fb0");
        callMovies.enqueue(new Callback<PersonMoviesResponse>() {
            @Override
            public void onResponse(Call<PersonMoviesResponse> call, Response<PersonMoviesResponse> response) {
                List<PersonMovies> personMoviesList = response.body().getCast();
                moviesList.addAll(personMoviesList);
                movieOfTheCredit.setVisibility(View.VISIBLE);
                movieOfThePersonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PersonMoviesResponse> call, Throwable t) {

            }
        });

        showsOfTheCredit = findViewById(R.id.showOfTheCredits);
        showsOfThePersonAdapter = new ShowsOfThePersonAdapter(showsList,this);
        showsOfTheCredit.setAdapter(showsOfThePersonAdapter);
        LinearLayoutManager layoutManagerShows = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
       showsOfTheCredit.setLayoutManager(layoutManagerShows);
        final Retrofit retrofitShows = ApiBaseUrl.getClient();
        ApiInterface serviceShows = retrofitShows.create(ApiInterface.class);
        Call<PersonShowsResponse> callShows = serviceShows.getShowCreditDetails(Integer.parseInt(idOfTheCredit),"97f67791525ddbcc4192d10f245f2fb0");
        callShows.enqueue(new Callback<PersonShowsResponse>() {
            @Override
            public void onResponse(Call<PersonShowsResponse> call, Response<PersonShowsResponse> response) {
                List<PersonShows> personShowsList = response.body().getCast();
                showsList.addAll(personShowsList);
                showsOfTheCredit.setVisibility(View.VISIBLE);
                showsOfThePersonAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PersonShowsResponse> call, Throwable t) {

            }
        });








    }

    private void setAge(String dateOfBirthString, boolean isDead) {
        if (dateOfBirthString != null && !dateOfBirthString.trim().isEmpty()) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
            try {

                Date releaseDate = sdf1.parse(dateOfBirthString);
                if(isDead){
                    creditDOB.setText((Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(sdf2.format(releaseDate))) + " ");
                }else{
                    creditDOB.setText((Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(sdf2.format(releaseDate))) + " " + "(dead)");
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


}
