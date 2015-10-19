package com.allipper.rentme.ui.mine;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.PocketDetailAdapter;
import com.allipper.rentme.adapter.RentMeAdapter;
import com.allipper.rentme.bean.BalanceInfo;
import com.allipper.rentme.bean.OrderInfo;
import com.allipper.rentme.ui.base.SwipeRefreshBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PocketDetailsActivity extends SwipeRefreshBaseActivity {
    private static final String TAG = PocketDetailsActivity.class.getSimpleName();

    private ImageView backImageView;
    private TextView titleTextView;

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
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);

    }



}

