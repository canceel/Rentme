package com.allipper.rentme.ui.base;


import android.os.Bundle;
import android.widget.ListView;

import com.allipper.rentme.R;
import com.allipper.rentme.bean.Pagination;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.widget.MyEmptyViewHelper;
import com.allipper.rentme.widget.SwipeRefreshLayout;
import com.allipper.rentme.widget.SwipyRefreshLayoutDirection;


/**
 * 基础类
 * 下拉刷新和上拉加载更多
 * Created by allipper on 2015/9/22.
 */
public abstract class SwipeRefreshBaseActivity extends BaseActivity implements SwipeRefreshLayout
        .OnRefreshListener {

    // 刷新类swipe
    protected SwipeRefreshLayout swipeLayout;
    // 数据ListView
    protected ListView listView;

    // 是否是刷新，刷新需要清空数据
    protected boolean isRefresh;
    // 分页
    protected Pagination pagination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPageInfo();
    }

    /**
     * 设置swipeView 并设置刷新方向
     */
    protected void setSwipeLayout() {
        setSwipeLayout(null);
    }

    /**
     * 设置swipeView 并设置刷新方向
     *
     * @param direction
     */
    protected void setSwipeLayout(SwipyRefreshLayoutDirection direction) {
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeLayout.setOnRefreshListener(this);
        if (direction == null) {
            swipeLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        } else {
            swipeLayout.setDirection(direction);
        }
        // set style
        swipeLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color
                        .holo_green_light,
                android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        listView = (ListView) findViewById(R.id.list);
    }


    /**
     * 初始化分页信息
     */
    protected void initPageInfo() {
        pagination = new Pagination();
        pagination.setCurrentPage(0);
        pagination.setPageSize(10);
    }

    /**
     * 相应刷新时间
     *
     * @param direction
     */
    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {//上拉则是刷新；需要设置页码为0，并且设置是刷新
            isRefresh = true;
            pagination.setCurrentPage(0);
            getDatas(false);
        } else {//下拉则是加载更多；需要设置页码加1
            isRefresh = false;
            if (pagination.isLastPage()) {//判断是否是最后一页，最后一页提示没有数据
                swipeLayout.setRefreshing(false);
                ToastUtils.show(mContext, R.string.loadmore_foot_no_data_tip);
                return;
            }
            pagination.setCurrentPage(pagination.getCurrentPage() + 1);
            getDatas(false);
        }
    }

    /**
     * 重试方法
     *
     * @param tab
     */
    @Override
    public void retry(int tab) {
        if (tab == MyEmptyViewHelper.RETRY_TYPE_NETWORK_ERROR) {
            isRefresh = true;
            getDatas(true);
        } else {
            super.retry(tab);
        }
    }
}
