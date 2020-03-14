package com.hashmaker.activities;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hashmaker.data.Hash;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ViewPagerAdapter";

    private static final int TEXT_FRAGMENT = 0;
    private static final int GRAPH_FRAGMENT = 1;
    private static final int EXPOSED_FRAGMENT = 2;

    private ArrayList<Hash> hashes;
    private String[] exposedHash;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Hash> a, String[] exposedHash) {
        super(fm);
        this.hashes = a;
        this.exposedHash = exposedHash;
        Log.i(TAG, "hash: " + exposedHash[0]);

    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case TEXT_FRAGMENT:
                TextFragment textFragment = new TextFragment(hashes);
                return textFragment;
            case GRAPH_FRAGMENT:
                GraphFragment graphFragment = new GraphFragment(hashes);
                return graphFragment;
            case EXPOSED_FRAGMENT:
                ExposedFragment exposedFragment = new ExposedFragment(exposedHash);
                return exposedFragment;
                default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case TEXT_FRAGMENT:
                return "Text";
            case GRAPH_FRAGMENT:
                return "Graph";
            case EXPOSED_FRAGMENT:
                return "Exposed";
            default:
                return "Nothing";
        }
    }

    public void getHashes(ArrayList<Hash> a) {
        this.hashes = a;
    }

}
