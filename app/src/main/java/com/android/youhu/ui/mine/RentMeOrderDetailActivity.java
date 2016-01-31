package com.android.youhu.ui.mine;


import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.bean.RentMeResponse;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.HttpLoad;
import com.android.youhu.net.ResponseCallback;
import com.android.youhu.net.response.ResponseBase;
import com.android.youhu.ui.base.BaseActivity;
import com.android.youhu.ui.base.ParameterConstant;
import com.android.youhu.widget.CircleImageView;

public class RentMeOrderDetailActivity extends BaseActivity {
    private static final String TAG = RentMeOrderDetailActivity.class.getSimpleName();

    private TextView numberTextView;
    private TextView nameTextView;
    private TextView constellationTextView;
    private TextView locationTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private TextView durationTextView;
    private TextView telphoneTextView;
    private TextView cityTextView;
    private TextView addressTextView;
    private TextView costTextView;
    private TextView orderDateTextView;
    private TextView total_feeTextView;
    private CircleImageView headCircleIamgeView;
    private Button datingButton;
    private RelativeLayout bottomRelativeLayout;

    private RentMeResponse.DataEntity.ItemsEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_me_order_detail);
        findViews();
        getData();
        setDataToView();
    }

    private void getData() {
        data = getIntent().getExtras().getParcelable(ParameterConstant.PARAM_ITEM_DATA);
    }


    private void findViews() {
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        numberTextView = (TextView) findViewById(R.id.number);
        nameTextView = (TextView) findViewById(R.id.name);
        constellationTextView = (TextView) findViewById(R.id.constellation);
        locationTextView = (TextView) findViewById(R.id.location);
        dateTextView = (TextView) findViewById(R.id.date);
        timeTextView = (TextView) findViewById(R.id.time);
        durationTextView = (TextView) findViewById(R.id.duration);
        telphoneTextView = (TextView) findViewById(R.id.telphone);
        cityTextView = (TextView) findViewById(R.id.city);
        addressTextView = (TextView) findViewById(R.id.address);
        costTextView = (TextView) findViewById(R.id.cost);
        orderDateTextView = (TextView) findViewById(R.id.orderDate);
        total_feeTextView = (TextView) findViewById(R.id.total_fee);
        bottomRelativeLayout = (RelativeLayout) findViewById(R.id.bottomRl);
        datingButton = (Button) findViewById(R.id.dating);
        headCircleIamgeView = (CircleImageView) findViewById(R.id.head);
        titleTextView.setText("预约详情");
        datingButton.setOnClickListener(this);
        backImageView.setOnClickListener(this);
    }


    private void setDataToView() {
        numberTextView.setText(data.orderId);
        orderDateTextView.setText(data.createTime);
        nameTextView.setText(data.nickName);
        dateTextView.setText(data.meetTime);
        timeTextView.setText(data.meetTime);
        durationTextView.setText("2小时");
        telphoneTextView.setText(data.mobile);
        addressTextView.setText(data.meetAddress);
        costTextView.setText("￥" + data.totalPrice);
        total_feeTextView.setText("￥" + data.totalPrice);
//        HttpLoad.getImage(data.a, headCircleIamgeView);
        String gender = data.gender;
        if ("男".equals(gender)) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.sex_girl);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            nameTextView.setCompoundDrawables(null, null, drawable, null);
        } else if ("女".equals(gender)) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.sex_man);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            nameTextView.setCompoundDrawables(null, null, drawable, null);
        } else {
            nameTextView.setCompoundDrawables(null, null, null, null);
        }
        if ("拒绝预约".equals(data.state) || "接受预约".equals(data.state)) {
            findViewById(R.id.bottom).setVisibility(View.GONE);
        } else {
            findViewById(R.id.bottom).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.dating:
                processOrder("2");
                break;
            case R.id.cancel:
                processOrder("3");
                break;
            default:
                super.onClick(view);
        }
    }

    public void processOrder(final String status) {
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
        dialog.show();
        HttpLoad.Order.processOrder(TAG, Utils.getToken(mContext), data.orderId, status, new
                ResponseCallback<ResponseBase>(mContext) {

                    @Override
                    public void onRequestSuccess(ResponseBase result) {
                        dialog.dismiss();
                        if ("2".equals(status)) {
                            ToastUtils.show(mContext, "预约成功");
                        } else {
                            ToastUtils.show(mContext, "订单已拒绝");
                        }
                        onBackPressed();
                    }

                    @Override
                    public void onReuquestFailed(String error) {
                        dialog.dismiss();
                        ToastUtils.show(mContext, error);
                    }
                });
    }
}

