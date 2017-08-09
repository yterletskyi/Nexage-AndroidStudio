//
//  MainActivity.java
//
//  Copyright (c) 2014 Nexage. All rights reserved.
//

package org.nexage.sourcekit.vastdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vastdemo.adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VASTLog.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.pager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(false);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new MainPagerAdapter(supportFragmentManager);

        viewPager.setAdapter(pagerAdapter);

    }
}
