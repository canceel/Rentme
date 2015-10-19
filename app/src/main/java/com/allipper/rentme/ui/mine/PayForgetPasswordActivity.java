package com.allipper.rentme.ui.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.ui.base.BaseActivity;

public class PayForgetPasswordActivity extends BaseActivity {
    private static final String TAG = PayForgetPasswordActivity.class.getSimpleName();

    private ImageView backImageView;
    private TextView titleTextView;
    private Button security_codeButton;
    private EditText cellNoEditText;
    private TextView input_cellno_tvTextView;
    private EditText codeEditText;
    private EditText newPasswordEditText;
    private EditText confirmNewPasswordEditText;
    private TextView messageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_forget_password);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        security_codeButton = (Button) findViewById(R.id.security_code);
        cellNoEditText = (EditText) findViewById(R.id.cellNo);
        input_cellno_tvTextView = (TextView) findViewById(R.id.input_cellno_tv);
        codeEditText = (EditText) findViewById(R.id.code);
        newPasswordEditText = (EditText) findViewById(R.id.newPassword);
        confirmNewPasswordEditText = (EditText) findViewById(R.id.confirmNewPassword);
        messageTextView = (TextView) findViewById(R.id.message);

    }


    public void get_securitycode(View view) {
    }

    public void confirm(View view) {
        ToastUtils.show(mContext, "修改成功");
        finish();
    }


}

