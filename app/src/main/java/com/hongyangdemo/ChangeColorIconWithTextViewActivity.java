package com.hongyangdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewConfiguration;

import com.hongyangdemo.widget.ChangeColorIconWithTextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ChangeColorIconWithTextViewActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager mViewPager;
    private List<Fragment> mTabs = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    private String[] mTitles = new String[]{"First Fragment!",
            "Second Fragment!", "Third Fragment!", "Fourth Fragment!"};
    private List<ChangeColorIconWithTextView> mTabIndicator = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color_icon_with_text_view);

        setOverflowShowingAlways();
        if (getActionBar() != null)
            getActionBar().setDisplayShowHomeEnabled(false);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        initDatas();

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDatas() {
        for (String title : mTitles) {
            TabFragment tabFragment = new TabFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            tabFragment.setArguments(args);
            mTabs.add(tabFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mTabs.get(arg0);
            }
        };

        initTabIndicator();
    }

    private void initTabIndicator() {
        ChangeColorIconWithTextView one = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_one);
        ChangeColorIconWithTextView two = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_two);
        ChangeColorIconWithTextView three = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_three);
        ChangeColorIconWithTextView four = (ChangeColorIconWithTextView) findViewById(R.id.id_indicator_four);

        mTabIndicator.add(one);
        mTabIndicator.add(two);
        mTabIndicator.add(three);
        mTabIndicator.add(four);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);

        one.setIconAlpha(1.0f);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset > 0) {
            ChangeColorIconWithTextView left = mTabIndicator.get(position);
            ChangeColorIconWithTextView right = mTabIndicator.get(position + 1);

            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        resetOtherTabs();

        switch (v.getId()) {
            case R.id.id_indicator_one:
                mTabIndicator.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mTabIndicator.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mTabIndicator.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mTabIndicator.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;

        }
    }

    private void resetOtherTabs() {
        for (int i = 0; i < mTabIndicator.size(); i++) {
            mTabIndicator.get(i).setIconAlpha(0);
        }
    }

    private void setOverflowShowingAlways() {
        try {
            // true if a permanent menu key is present, false otherwise.
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
