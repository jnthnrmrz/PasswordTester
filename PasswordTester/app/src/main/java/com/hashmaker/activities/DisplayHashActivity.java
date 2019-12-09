package com.hashmaker.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.hashmaker.service.AdvancedAlgo;
import com.hashmaker.service.AdvancedHashing;
import com.hashmaker.service.Algorithm;
import com.hashmaker.service.HashAlgo;
import com.hashmaker.R;
import com.hashmaker.data.Hash;

import java.util.ArrayList;

public class DisplayHashActivity extends AppCompatActivity {
    private static final String TAG = "DisplayHashActivity";

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private String password;
    private ArrayList<Hash> hashes = new ArrayList<Hash>();

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
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), hashes);
        viewPager.setAdapter(adapter);


        //Tabs
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Intent intent = getIntent();
        password = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);
        createHashes();
    }

    private void createHashes() {
        HashAlgo hashAlgo;
        AdvancedHashing advancedHashing;
        for (Algorithm a : Algorithm.values()) {
            hashAlgo = HashAlgo.getInstance(a);
            hashes.add(hashAlgo.getHashInfo(password));
        }
        Log.i(TAG, "password: " + password);
        for(AdvancedAlgo aa : AdvancedAlgo.values()) {
                advancedHashing = new AdvancedHashing(aa);
                advancedHashing.hash(password);
                hashes.add(advancedHashing.getHashInfo());

            }

        Log.i(TAG, "This is the hash " + hashes.get(0).toString());
        Log.i(TAG, "This is the hash for advanced: " + "");

    }
}


