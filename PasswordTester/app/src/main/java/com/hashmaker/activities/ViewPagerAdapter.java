package com.hashmaker.activities;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hashmaker.data.Hash;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final int TEXT_FRAGMENT = 0;
    private static final int GRAPH_FRAGMENT = 1;
    private ArrayList<Hash> hashes;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Hash> a) {
        super(fm);
        this.hashes = a;
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
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case TEXT_FRAGMENT:
                return "Text";
            case GRAPH_FRAGMENT:
                return "Graph";
            default:
                return "Nothing";
        }
    }

    public void getHashes(ArrayList<Hash> a) {
        this.hashes = a;
    }

}
