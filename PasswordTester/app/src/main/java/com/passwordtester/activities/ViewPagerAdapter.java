package com.passwordtester.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final int TEXT_FRAGMENT = 0;
    private static final int GRAPH_FRAGMENT = 1;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case TEXT_FRAGMENT:
                TextFragment textFragment = new TextFragment();
                return textFragment;
            case GRAPH_FRAGMENT:
                GraphFragment graphFragment = new GraphFragment();
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
}
