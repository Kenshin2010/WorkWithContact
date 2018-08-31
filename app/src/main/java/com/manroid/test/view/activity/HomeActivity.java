package com.manroid.test.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.manroid.test.R;
import com.manroid.test.adapter.ViewPagerAdapter;
import com.manroid.test.view.fragment.ConvertTo10Fragment;
import com.manroid.test.view.fragment.ConvertTo11Fragment;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupViewPager();
    }


    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ConvertTo10Fragment(), getString(R.string.to10));
        adapter.addFragment(new ConvertTo11Fragment(), getString(R.string.to11));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
