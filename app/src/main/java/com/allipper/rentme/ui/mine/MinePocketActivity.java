package com.allipper.rentme.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.widget.CircleImageView;
import com.allipper.rentme.widget.DrawableLeftCenterButton;

public class MinePocketActivity extends BaseActivity {
    private static final String TAG = MinePocketActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private CircleImageView headCircleImageView;
    private DrawableLeftCenterButton chargeDrawableLeftCenterButton;
    private DrawableLeftCenterButton withdrawDrawableLeftCenterButton;
    private LinearLayout cardLinearLayout;
    private TextView card_valueTextView;
    private LinearLayout pay_passwordLinearLayout;
    private TextView pay_pwdTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_pocket);
        findViews();
        getDatas(false);
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        headCircleImageView = (CircleImageView) findViewById(R.id.head);
        chargeDrawableLeftCenterButton = (DrawableLeftCenterButton) findViewById(R.id.charge);
        withdrawDrawableLeftCenterButton = (DrawableLeftCenterButton) findViewById(R.id.withdraw);
        cardLinearLayout = (LinearLayout) findViewById(R.id.card);
        card_valueTextView = (TextView) findViewById(R.id.card_value);
        pay_passwordLinearLayout = (LinearLayout) findViewById(R.id.pay_password);
        pay_pwdTextView = (TextView) findViewById(R.id.pay_pwd);

    }

    //���õ���¼�
    public void back(View view) {
    }

    public void gotoPocketDetails(View view) {
        Intent it = new Intent(mContext, PocketDetailsActivity.class);
        startActivity(it);
    }

    public void charge(View view) {
    }

    public void withdraw(View view) {
    }


    public void gotoMineCard(View view) {
    }

    public void gotoPayPwd(View view) {
    }


}

