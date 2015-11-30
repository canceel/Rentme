package com.android.youhu.ui.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.ui.base.BaseActivity;

public class PayPasswordActivity extends BaseActivity {
    private static final String TAG = PayPasswordActivity.class.getSimpleName();

    private ImageView backImageView;
    private TextView titleTextView;
    private TextView messageTextView;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_password);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        messageTextView = (TextView) findViewById(R.id.message);
        passwordEditText = (EditText) findViewById(R.id.password);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPassword);

    }


    public void confirm(View view) {
        ToastUtils.show(mContext, "设置成功");
        finish();
    }


}

