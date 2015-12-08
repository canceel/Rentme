package com.android.youhu.ui.mine;


import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.adapter.PocketDetailAdapter;
import com.android.youhu.bean.BalanceInfo;
import com.android.youhu.ui.base.SwipeRefreshBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PocketDetailsActivity extends SwipeRefreshBaseActivity {
    private static final String TAG = PocketDetailsActivity.class.getSimpleName();


    private List<BalanceInfo> list = new ArrayList<>();
    private PocketDetailAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket_details);
        findViews();
        setSwipeLayout();
        getDatas(false);
    }

    public void getRealDatas(boolean isShowDialog) {

    }

    public void getTestDatas(boolean isShowDialog) {
        list.add(new BalanceInfo());
        list.add(new BalanceInfo());
        list.add(new BalanceInfo());
        list.add(new BalanceInfo());
        swipeLayout.setRefreshing(false);
    }

    public void setDataToView(Dialog dialog) {
        if (adapter == null) {
            adapter = new PocketDetailAdapter(mContext, list);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(list);
        }
    }

    private void findViews() {
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText("余额明细");
    }



}

