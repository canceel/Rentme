package com.android.youhu.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.ui.base.BaseActivity;

public class MinePayPwdActivity extends BaseActivity {
    private static final String TAG = MinePayPwdActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private TextView backImageView;
    private TextView titleTextView;
    private TextView modifyTextView;
    private TextView findbackTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_pay_pwd);
        findViews();
        getDatas(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        modifyTextView = (TextView) findViewById(R.id.modify);
        findbackTextView = (TextView) findViewById(R.id.findback);
        titleTextView.setText("支付密码");
    }


    public void changePwd(View view) {
        Intent it = new Intent(mContext, PayModifyPasswordActivity.class);
        startActivity(it);
    }

    public void findBackPwd(View view) {
        Intent it = new Intent(mContext, PayForgetPasswordActivity.class);
        startActivity(it);
    }


}

