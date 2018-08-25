package com.example.sanskriti.blooper.SearchFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import static android.widget.Toast.*;

public class SearchPagerAdapter extends FragmentStatePagerAdapter {

    int totalNoOfTabs;

    String name;

    public SearchPagerAdapter(FragmentManager fm, int totalNoOfTabs) {
        super(fm);
        this.totalNoOfTabs = totalNoOfTabs;
    }

    public SearchPagerAdapter(FragmentManager fm, int totalNoOfTabs, String name) {
        super(fm);
        this.totalNoOfTabs = totalNoOfTabs;
        this.name = name;


    }



    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0: MovieSearchFragment movieSearchFragment = new MovieSearchFragment(name);

            return movieSearchFragment;


            case 1: TvSearchFragment tvSearchFragment = new TvSearchFragment(name);
            return tvSearchFragment;
            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return totalNoOfTabs;
    }


}
