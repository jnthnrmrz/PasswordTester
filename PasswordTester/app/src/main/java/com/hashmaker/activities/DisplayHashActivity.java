package com.hashmaker.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.hashmaker.service.AdvancedAlgo;
import com.hashmaker.service.AdvancedHashing;
import com.hashmaker.service.Algorithm;
import com.hashmaker.service.HashAlgo;
import com.hashmaker.R;
import com.hashmaker.data.Hash;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DisplayHashActivity extends AppCompatActivity {
    private static final String TAG = "DisplayHashActivity";

    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;
    private String password;
    private ArrayList<Hash> hashes = new ArrayList<Hash>();
    private String[] exposedHash = {"", "0"};

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
        Intent intent = getIntent();
        password = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);
        createHashes();
        Log.i(TAG, "Prefix: " + getPrefix() + " Suffix: " + getSuffix());
        sendRequest();
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

    }

    public void sendRequest() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.pwnedpasswords.com/range/" + getPrefix();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                exposedHash = checkHashFound(divideString(response));
                viewPager = findViewById(R.id.pager);
                adapter = new ViewPagerAdapter(getSupportFragmentManager(), hashes, exposedHash);
                viewPager.setAdapter(adapter);


                //Tabs
                tabLayout = findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                exposedHash[0] = "ERROR";
                viewPager = findViewById(R.id.pager);
                adapter = new ViewPagerAdapter(getSupportFragmentManager(), hashes, exposedHash);
                viewPager.setAdapter(adapter);


                //Tabs
                tabLayout = findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        queue.add(stringRequest);
    }

    /**
     * Creates an array of the response received and divides it by line
     * @param response
     * @return
     */
    private String[] divideString(String response) {
        String[] result = response.split("\\r?\\n");
        Log.i(TAG, "Last result: " + result[result.length-1]);
        return result;
    }

    /**
     * Gets the first 5 values of SHA hash for request
     * @return
     */
    private String getPrefix() {
        String sha = hashes.get(1).getValue();
        String prefix = sha.substring(0,5);
        return prefix;
    }

    /**
     * Gets the rest of the values of hash after first 5 for comparison with response
     * @return
     */
    private String getSuffix() {
        String sha = hashes.get(1).getValue();
        String suffix = sha.substring(5, sha.length());
        return suffix.toUpperCase();
    }

    /**
     * Check to see if hash is found in response
     * @param hashList
     * @return
     */
    private String[] checkHashFound(String[] hashList) {
        String[] split = {"", "0"}; //default is 0

        for(int i = 0; i < hashList.length; i++) {
            if(hashList[i].contains(getSuffix())) {
                split = hashList[i].split(":");
            }
        }
        split[0] = getPrefix() + getSuffix();
        Log.i(TAG, "Hash: " + split[0] + " has been found a total of : " + split[1] + " times");
        return split;
    }
}


