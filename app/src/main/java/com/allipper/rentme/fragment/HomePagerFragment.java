package com.allipper.rentme.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.HomePageAdapter;
import com.allipper.rentme.adapter.HomePagerViewPagerAdapter;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.response.PulishInfoResponse;
import com.allipper.rentme.ui.dynamic.PublishInfoActivity;
import com.allipper.rentme.widget.AutoScrollViewPager;
import com.allipper.rentme.widget.SwipeRefreshLayout;
import com.allipper.rentme.widget.SwipyRefreshLayoutDirection;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


public class HomePagerFragment extends Fragment implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private View emptyView;
    private HomePageAdapter homePageAdapter;

    private List<PulishInfoResponse> datas;
    private int previousPointEnale;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pager, container, false);
        initView(view);
        return view;
    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainScreen"); //统计页面
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainScreen");
    }

    /**
     * 初始化界面
     *
     * @param view
     */
    private void initView(View view) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setDirection(SwipyRefreshLayoutDirection.TOP);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light);
        listView = (ListView) view.findViewById(R.id.datas_lv);
        View headView = View.inflate(getActivity(), R
                .layout.banner_layout, null);
        AutoScrollViewPager bannerLayout = (AutoScrollViewPager) headView.findViewById(R.id
                .banner_asvp);
        LinearLayout ll = (LinearLayout) headView.findViewById(R.id.ll_carousel);
        initVP(ll, bannerLayout);
        headView.setLayoutParams(new AbsListView.LayoutParams(AbsListView
                .LayoutParams
                .MATCH_PARENT, Utils.dip2px(getActivity(), 180f)));
        listView.addHeaderView(headView);
        LinearLayout footerLL = new LinearLayout(getActivity());
        footerLL.setBackgroundColor(getResources().getColor(R.color.white));
        footerLL.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams
                .MATCH_PARENT, Utils.dip2px(getActivity(), 46f)));
        listView.addFooterView(footerLL);
        final List<String> banners = new ArrayList<>(3);
        banners.add("logg");
        banners.add("logg");
        banners.add("logg");
        HomePagerViewPagerAdapter adapter = new HomePagerViewPagerAdapter(getActivity(), banners);
        adapter.setInfiniteLoop(true);
        bannerLayout.setAdapter(adapter);
        getHomePage(false);
    }

    public void initVP(final LinearLayout ll, AutoScrollViewPager vp) {
        ll.removeAllViews();
        RelativeLayout.LayoutParams carouselParam = new RelativeLayout.LayoutParams
                (RelativeLayout
                        .LayoutParams.MATCH_PARENT, Utils.getScreenPoint(getActivity()).x / 2);
        vp.setLayoutParams(carouselParam);

        LinearLayout.LayoutParams param;
        for (int i = 0; i < 3; i++) {
            View point = new View(getActivity());
            point.setBackgroundResource(R.drawable.point_background);
            param = new LinearLayout.LayoutParams(20, 20);
            param.rightMargin = 10;
            point.setLayoutParams(param);
            point.setEnabled(false);
            ll.addView(point);
        }
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int newPosition = position % 3;
                ll.getChildAt(previousPointEnale).setEnabled(false);
                ll.getChildAt(newPosition).setEnabled(true);
                previousPointEnale = newPosition;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        vp.setCurrentItem(500);
        vp.startAutoScroll();
        vp.setStopScrollWhenTouch(true);
    }

    /**
     * 获取首页配置信息
     */
    public void getHomePage(final boolean isRefresh) {
        if (Utils.isNetworkConnected(getActivity())) {
//            final Dialog dialog = LoadDialogUtil.createLoadingDialog(getActivity(),R.string
// .loading);
//            if(!isRefresh){
//                dialog.show();
//            }
            swipeRefreshLayout.setRefreshing(false);
            datas = new ArrayList<>(3);
            datas.add(new PulishInfoResponse());
            datas.add(new PulishInfoResponse());
            datas.add(new PulishInfoResponse());
            homePageAdapter = new HomePageAdapter(getActivity(), datas);
            listView.setAdapter(homePageAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it = new Intent(getActivity(), PublishInfoActivity.class);
                    getActivity().startActivity(it);
                }
            });
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 10) {
                //选择城市的回调
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        getHomePage(true);
    }

}
