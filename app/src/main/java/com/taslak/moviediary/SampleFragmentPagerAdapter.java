package com.taslak.moviediary;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {



    private Context context;
    private String tabTitles[]=new String[]{"Favorite", "Seen", "All", "Search"};
    private int tabIcons[] = {R.drawable.ic_favorite_black_24dp,
            R.drawable.ic_visibility_black_24dp,
            R.drawable.ic_visibility_off_black_24dp,
            R.drawable.ic_search_black_24dp
    };


    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FavoriteFragment();
                break;
            case 1:
                fragment = new SeenFragment();
                break;
            case 2:
                fragment = new AllFragment();
                break;
            case 3:
                fragment = new SearchFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
    return tabTitles[position];
    }


}
