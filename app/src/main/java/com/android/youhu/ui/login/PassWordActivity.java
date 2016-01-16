package com.android.youhu.ui.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.youhu.R;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.SharedPre;
import com.android.youhu.common.util.SharedPreUtils;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.HttpLoad;
import com.android.youhu.net.ResponseCallback;
import com.android.youhu.net.response.LoginResult;
import com.android.youhu.net.response.RegistResult;
import com.android.youhu.net.response.ResponseMessageBean;
import com.android.youhu.ui.base.BaseLoginBusinessActivity;


public class PassWordActivity extends BaseLoginBusinessActivity {

    private static final String TAG = PassWordActivity.class.getSimpleName();

    private EditText edInputSecurityCode;
    private EditText edInputCellNo;
    private EditText edNewPassWord;
    private EditText edConforNewPassWord;
    private CountDownTimer time;
    private TextView tvInputCellNo;
    private Button btnSecurityCode;
    private String messageCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        edInputCellNo = (EditText) findViewById(R.id.input_cellno);
        edInputSecurityCode = (EditText) findViewById(R.id.input_securitycode);
        edNewPassWord = (EditText) findViewById(R.id.new_password);
        edConforNewPassWord = (EditText) findViewById(R.id.confor_newpassword);
        btnSecurityCode = (Button) findViewById(R.id.security_code);
        tvInputCellNo = (TextView) findViewById(R.id.input_cellno_tv);

        ((TextView)findViewById(R.id.title)).setText("修改密码");
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.security_code).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);

        time = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnSecurityCode.setClickable(false);
                btnSecurityCode.setText(millisUntilFinished / 1000 + "s");
                tvInputCellNo.setText(edInputCellNo.getText().toString());
                tvInputCellNo.setVisibility(View.VISIBLE);
                edInputCellNo.setVisibility(View.GONE);

            }

            @Override
            public void onFinish() {
                btnSecurityCode.setText("获取验证码");
                btnSecurityCode.setClickable(true);
                edInputCellNo.setVisibility(View.VISIBLE);
                tvInputCellNo.setVisibility(View.GONE);
            }
        };
    }

    //忘记密码修改密码
    public void amend(View view) {
        final String phoneNum = edInputCellNo.getText().toString();
        final String securityCode = edInputSecurityCode.getText().toString();
        final String newPassword = edNewPassWord.getText().toString();
        final String conforNewPassword = edConforNewPassWord.getText().toString();

        if (TextUtils.isEmpty(phoneNum)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(securityCode)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.isEmpty(messageCode) && !messageCode.equals(securityCode)) {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(conforNewPassword)) {
            Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
        } else if (!(newPassword.equals(conforNewPassword))) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isNetworkConnected(this)) {
                final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this, R.string.loading);
                mDialog.show();
                HttpLoad.UserModule.changePassword(TAG, phoneNum, newPassword, messageCode, new
                        ResponseCallback<RegistResult>(this) {

                            @Override
                            public void onRequestSuccess(RegistResult result) {
                                HttpLoad.UserModule.login(TAG, phoneNum,
                                        newPassword,
                                        new ResponseCallback<LoginResult>(PassWordActivity.this) {
                                            @Override
                                            public void onRequestSuccess(LoginResult result) {
                                                loginSuccess(result.data, new
                                                        LoginSuccessListener() {
                                                            @Override
                                                            public void onSuccessed(Dialog dialog) {
                                                                dialog.dismiss();
                                                                SharedPreUtils.putString(mContext,
                                                                        SharedPre.App
                                                                                .USER_TPE, "login");
                                                                Intent it = new Intent(mContext,
                                                                        LoginActivity.class);
                                                                it.setFlags(Intent
                                                                        .FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(it);
                                                                onBackPressed();
                                                            }
                                                        }, mDialog);
                                            }

                                            @Override
                                            public void onReuquestFailed(String error) {
                                                mDialog.dismiss();
                                                ToastUtils.show(PassWordActivity.this, error);
                                            }
                                        });
                            }

                            @Override
                            public void onReuquestFailed(String error) {
                                mDialog.dismiss();
                                ToastUtils.show(PassWordActivity.this, error);
                            }
                        });
            }
        }
    }

    public void get_securitycode(View view) {
        if (TextUtils.isEmpty(edInputCellNo.getText().toString())) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        } else if (!Utils.isMobile(edInputCellNo.getText().toString())) {
            Toast.makeText(this, "手机号码错误", Toast.LENGTH_SHORT).show();
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
            dialog.show();
            HttpLoad.UserModule.getMessageCode(TAG, edInputCellNo.getText().toString(), "1",
                    new ResponseCallback<ResponseMessageBean>(mContext) {
                        @Override
                        public void onRequestSuccess(ResponseMessageBean result) {
                            dialog.dismiss();
                            messageCode = result.data.captcha;
                            time.start();
                            ToastUtils.show(mContext, result.message);
                        }

                        @Override
                        public void onReuquestFailed(String error) {
                            dialog.dismiss();
                            ToastUtils.show(mContext, error);
                        }
                    });

        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.security_code:
                get_securitycode(view);
                break;
            case R.id.confirm:
                amend(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }
}
