package com.android.youhu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.youhu.R;
import com.android.youhu.common.util.SharedPre;
import com.android.youhu.common.util.SharedPreUtils;
import com.android.youhu.ui.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by allipper on 2015/9/1.
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    /**
     * ViewPager
     */
    private ViewPager viewPager;

    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;
    /**
     * 装置layout的list
     */

    private ArrayList<View> viewContainter = new ArrayList<>();

    public static final int USE_HELPER = 0;
    private int type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guide);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 1);
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_guide_pageone, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_guide_pagetwo, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.layout_guide_pagethree, null);
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        //将导航点加入到ViewGroup中
        tips = new ImageView[viewContainter.size()];
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(40, 40));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup
                    .LayoutParams(30,
                    30));
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            group.addView(imageView, layoutParams);
        }
        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置导航点点的背景
        viewPager.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setImageBackground(i % viewContainter.size());

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewContainter.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewContainter.get(position));

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewContainter.get(position));
            return viewContainter.get(position);
        }

    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
        }
    }

    public void enter(View view) {
        if (type == USE_HELPER) {
            onBackPressed();
        } else {

            Intent intentIndexActivity = new Intent(this, IndexActivity.class);
            startActivity(intentIndexActivity);
            SharedPreUtils.putBoolean(this, SharedPre.App.ISGUIDE, true);
            onBackPressed();
        }
    }

}
