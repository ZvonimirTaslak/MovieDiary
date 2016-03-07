package com.taslak.moviediary;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initwidges();
        setupFragment();
    }

    private void setupFragment() {
        SampleFragmentPagerAdapter adapter=new SampleFragmentPagerAdapter(getSupportFragmentManager(),this);
        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);
    }

    private void initwidges() {
        vp=(ViewPager)findViewById(R.id.vp);
        tl=(TabLayout)findViewById(R.id.tl);
    }
}
