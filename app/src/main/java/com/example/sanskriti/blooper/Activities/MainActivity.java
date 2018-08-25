package com.example.sanskriti.blooper.Activities;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.sanskriti.blooper.Movies.MovieFragment;
import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.SearchFragments.MovieSearchFragment;
import com.example.sanskriti.blooper.TvShows.TvShowFragment;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    public static String NAME = "abc";
    EditText searchName;
    Button searchButton;
    Button doneButton;
    Button canelButton;
    public String name;


    Toolbar toolbar;
    BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.movieList:
                    MovieFragment movieFragment = new MovieFragment();
                    transaction.replace(R.id.contentContainer, movieFragment);

                    break;

                case R.id.tvList:
                    TvShowFragment tvShowFragment = new TvShowFragment();
                    transaction.replace(R.id.contentContainer, tvShowFragment);
                    NAME = "TV Shows";
                    break;
//                case R.id.myFavourites:
//                    Toast.makeText(getApplicationContext(), "FavouriteListIsWorking", Toast.LENGTH_LONG).show();
                   }
            transaction.commit();
            return  true;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      toolbar  = findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        MovieFragment movieFragment = new MovieFragment();
        transaction.replace(R.id.contentContainer, movieFragment);
        transaction.commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        searchButton = findViewById(R.id.searchButton);
        doneButton = findViewById(R.id.doneButton);
        searchName= findViewById(R.id.searchName);
        canelButton = findViewById(R.id.cancelButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchName.setVisibility(View.VISIBLE);


                searchButton.setVisibility(View.INVISIBLE);
                toolbar.setVisibility(View.INVISIBLE);
                doneButton.setVisibility(View.VISIBLE);
                canelButton.setVisibility(View.VISIBLE);
                doneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                        name = searchName.getText().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString(SearchActivity.NAME, name);
                        intent.putExtras(bundle);
                        startActivity(intent);




                    }
                });
                canelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doneButton.setVisibility(View.INVISIBLE);
                        searchButton.setVisibility(View.VISIBLE);
                        searchName.setVisibility(View.INVISIBLE);
                        canelButton.setVisibility(View.INVISIBLE);
                        toolbar.setVisibility(View.VISIBLE);
                    }
                });

            }
        });









    }



}
