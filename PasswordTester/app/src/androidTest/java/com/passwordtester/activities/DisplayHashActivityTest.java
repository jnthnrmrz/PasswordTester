package com.passwordtester.activities;

import android.app.Activity;
import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.passwordtester.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class DisplayHashActivityTest {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TabLayout tabLayout;

    @Rule
    public ActivityTestRule<DisplayHashActivity> mActivityTestRule = new ActivityTestRule<DisplayHashActivity>(DisplayHashActivity.class);

    private DisplayHashActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() {

    }

    @Test
    public void displayHash() {
    }
}