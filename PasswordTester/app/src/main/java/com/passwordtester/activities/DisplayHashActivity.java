package com.passwordtester.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.passwordtester.R;

public class DisplayHashActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_hash);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        //Upward Navigation
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Setting up the pager
        viewPager = findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //Tabs
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        String password = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);

    }


}
