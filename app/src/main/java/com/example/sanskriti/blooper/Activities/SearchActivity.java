package com.example.sanskriti.blooper.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanskriti.blooper.R;
import com.example.sanskriti.blooper.SearchFragments.MovieSearchFragment;
import com.example.sanskriti.blooper.SearchFragments.SearchPagerAdapter;

public class SearchActivity extends AppCompatActivity  {

    public static final String NAME = "name";
    TabLayout tabLayout;
    ViewPager viewPager;
    SearchPagerAdapter pagerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle bundle = getIntent().getExtras();

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.pager);
        pagerAdapter = new SearchPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount(), bundle.getCharSequence(NAME).toString());

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));




    }

}
