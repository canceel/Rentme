package com.allipper.rentme.ui.mine;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.MineRentAdapter;
import com.allipper.rentme.adapter.RentMeAdapter;
import com.allipper.rentme.bean.OrderInfo;
import com.allipper.rentme.bean.RentMeResponse;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.ui.base.SwipeRefreshBaseActivity;
import com.allipper.rentme.ui.dynamic.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class RentMeActivity extends SwipeRefreshBaseActivity {
    private static final String TAG = RentMeActivity.class.getSimpleName();

    private ImageView backImageView;
    private TextView titleTextView;
    private List<OrderInfo> list = new ArrayList<>();
    private RentMeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_me);
        findViews();
        setSwipeLayout();
        getDatas(false);
    }


    public void getDatas(boolean isShowDialog) {
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
        if (isShowDialog) {
            dialog.show();
        }
        HttpLoad.Order.getRentMine(TAG, Utils.getToken(mContext), pagination.currentPage + "",
                pagination.pageSize + "", new ResponseCallback<RentMeResponse>(mContext) {


                    @Override
                    public void onRequestSuccess(RentMeResponse result) {

                    }

                    @Override
                    public void onReuquestFailed(String error) {

                    }
                });
    }

    public void setDataToView(Dialog dialog) {
        if (adapter == null) {
            adapter = new RentMeAdapter(mContext, list);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(list);
        }
    }


    private void findViews() {
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(mContext, RentMeOrderDetailActivity.class);
                startActivity(it);
            }
        });
    }


}

