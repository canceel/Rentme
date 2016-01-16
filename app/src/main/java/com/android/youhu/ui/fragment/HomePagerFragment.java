package com.android.youhu.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.youhu.R;
import com.android.youhu.adapter.HomePageAdapter;
import com.android.youhu.adapter.HomePagerViewPagerAdapter;
import com.android.youhu.bean.Pagination;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.SharedPre;
import com.android.youhu.common.util.SharedPreUtils;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.HttpLoad;
import com.android.youhu.net.ResponseCallback;
import com.android.youhu.net.response.PulishInfoResponse;
import com.android.youhu.ui.IndexActivity;
import com.android.youhu.ui.base.ParameterConstant;
import com.android.youhu.ui.dynamic.PublishInfoActivity;
import com.android.youhu.widget.AutoScrollViewPager;
import com.android.youhu.widget.SwipeRefreshLayout;
import com.android.youhu.widget.SwipyRefreshLayoutDirection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


public class HomePagerFragment extends Fragment implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = HomePagerFragment.class.getCanonicalName();
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private View emptyView;
    private HomePageAdapter homePageAdapter;
    // 是否是刷新，刷新需要清空数据
    protected boolean isRefresh = true;
    // 分页
    protected Pagination pagination;

    private List<PulishInfoResponse.DataEntity.ItemsEntity> datas;
    private int previousPointEnale;

    public void setParam(String param) {
        this.param = param;
    }

    private String param = "&gender=0&height=0&weight=0";

    private IndexActivity indexActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pager, container, false);
        indexActivity = (IndexActivity) getActivity();
        initPageInfo();
        initView(view);
        return view;
    }

    /**
     * 初始化分页信息
     */
    protected void initPageInfo() {
        pagination = new Pagination();
        pagination.currentPage = 1;
        pagination.pageSize = 10;
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
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light);
        listView = (ListView) view.findViewById(R.id.datas_lv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getActivity(), PublishInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ParameterConstant.PARAM_ITEM_DATA, datas.get(i - 1));
                it.putExtras(bundle);
                getActivity().startActivity(it);
                getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.hold);
            }
        });
        View headView = View.inflate(getActivity(), R
                .layout.banner_layout, null);
        AutoScrollViewPager bannerLayout = (AutoScrollViewPager) headView.findViewById(R.id
                .banner_asvp);
        LinearLayout ll = (LinearLayout) headView.findViewById(R.id.ll_carousel);
        initVP(ll, bannerLayout);
        headView.setLayoutParams(new AbsListView.LayoutParams(AbsListView
                .LayoutParams
                .MATCH_PARENT, Utils.dip2px(getActivity(), 100f)));
        listView.addHeaderView(headView);
//        LinearLayout footerLL = new LinearLayout(getActivity());
//        footerLL.setBackgroundColor(getResources().getColor(R.color.white));
//        footerLL.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams
//                .MATCH_PARENT, Utils.dip2px(getActivity(), 46f)));
//        listView.addFooterView(footerLL);
        final List<String> banners = new ArrayList<>(3);
        banners.add("logg");
        banners.add("logg");
        banners.add("logg");
        HomePagerViewPagerAdapter adapter = new HomePagerViewPagerAdapter(getActivity(),
                banners);
        adapter.setInfiniteLoop(true);
        bannerLayout.setAdapter(adapter);
        String cache = SharedPreUtils.getString(getActivity(), SharedPre.App.HOME_CACHE);
        if(!TextUtils.isEmpty(cache)){
            datas = new Gson().fromJson(cache, new
                            TypeToken<List<PulishInfoResponse.DataEntity.ItemsEntity>>() {
                            }.getType());
            homePageAdapter = new HomePageAdapter(getActivity(), datas,
                    indexActivity.pictrues, indexActivity.adapter,
                    indexActivity.pictrueBgRl, indexActivity
                    .horizontalScrollView);
            listView.setAdapter(homePageAdapter);
        }
        getHomePage(true);
    }

    public void initVP(final LinearLayout ll, AutoScrollViewPager vp) {
        ll.removeAllViews();
        RelativeLayout.LayoutParams carouselParam = new RelativeLayout.LayoutParams
                (RelativeLayout
                        .LayoutParams.MATCH_PARENT, Utils.getScreenPoint(getActivity()).x
                        / 2);
        vp.setLayoutParams(carouselParam);

        LinearLayout.LayoutParams param;
        for (int i = 0; i < 3; i++) {
            View point = new View(getActivity());
            point.setBackgroundResource(R.drawable.point_background);
            param = new LinearLayout.LayoutParams(15, 15);
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
    public void getHomePage(final boolean isShowDialog) {
        if (Utils.isNetworkConnected(getActivity())) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(getActivity(), R.string
                    .loading);
            if (isShowDialog) {
                dialog.show();
            }
            HttpLoad.HomePage.getHomepage(TAG,
                    pagination.currentPage + "",
                    pagination.pageSize + "",
                    param,
                    new ResponseCallback<PulishInfoResponse>
                            (getActivity()) {
                        @Override
                        public void onRequestSuccess(PulishInfoResponse result) {
                            dialog.dismiss();
                            swipeRefreshLayout.setRefreshing(false);
                            if (isRefresh) {
                                isRefresh = false;
                                datas = result.data.items;
                                pagination = result.data.pager;
                                if(datas != null && datas.size() > 0){
                                    SharedPreUtils.putString(indexActivity, SharedPre.App.HOME_CACHE,new Gson().toJson(datas));
                                }
                            } else {
                                datas.addAll(result.data.items);
                            }
                            if (homePageAdapter == null) {
                                homePageAdapter = new HomePageAdapter(getActivity(), datas,
                                        indexActivity.pictrues, indexActivity.adapter,
                                        indexActivity.pictrueBgRl, indexActivity
                                        .horizontalScrollView);
                                listView.setAdapter(homePageAdapter);
                            } else {
                                homePageAdapter.setData(datas);
                            }
                        }

                        @Override
                        public void onReuquestFailed(String error) {
                            dialog.dismiss();
                            swipeRefreshLayout.setRefreshing(false);
                            ToastUtils.show(getActivity(), error);
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
        if (direction == SwipyRefreshLayoutDirection.TOP) {//上拉则是刷新；需要设置页码为0，并且设置是刷新
            isRefresh = true;
            pagination.currentPage = 1;
            getHomePage(false);
        } else {//下拉则是加载更多；需要设置页码加1
            isRefresh = false;
            if (pagination.isLastPage()) {//判断是否是最后一页，最后一页提示没有数据
                swipeRefreshLayout.setRefreshing(false);
                ToastUtils.show(getActivity(), R.string.loadmore_foot_no_data_tip);
                return;
            }
            pagination.currentPage++;
            getHomePage(false);
        }
    }

}
