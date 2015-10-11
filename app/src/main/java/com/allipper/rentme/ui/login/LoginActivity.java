package com.allipper.rentme.ui.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.ui.base.BaseLoginBusinessActivity;

import java.util.Map;



public class LoginActivity extends BaseLoginBusinessActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText edCellNo;
    private EditText edPassWord;

    private boolean isWXInstalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edCellNo = (EditText) findViewById(R.id.cell_no);
        edPassWord = (EditText) findViewById(R.id.password);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        switch (intent.getFlags()) {
            case Intent.FLAG_ACTIVITY_CLEAR_TOP:
                onBackPressed();
                break;
        }
    }

    //登录
    public void enter(View view) {
        final String account = edCellNo.getText().toString();
        final String password = edPassWord.getText().toString();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "请输入手机号或者昵称", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isNetworkConnected(this)) {
                final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this, R.string.loading);
                mDialog.show();
//                HttpLoad.UserModule.login(this, TAG, account, password, new ResponseCallback<LoginResult>(this) {
//
//                    @Override
//                    public void onRequestSuccess(LoginResult result) {
//                        loginSuccess(result.customer.account_uid, account, result
//                                .access_token, SharedPre.Constant.APP_USER, result.customer, mDialog, new LoginSuccessListener() {
//                            @Override
//                            public void onSuccessed(Dialog dialog) {
//                                dialog.dismiss();
//                                onBackPressed();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onReuquestFailed(String error) {
//                        mDialog.dismiss();
//                        ToastUtils.show(LoginActivity.this, error);
//                    }
//                });      HttpLoad.UserModule.login(this, TAG, account, password, new ResponseCallback<LoginResult>(this) {
//
//                    @Override
//                    public void onRequestSuccess(LoginResult result) {
//                        loginSuccess(result.customer.account_uid, account, result
//                                .access_token, SharedPre.Constant.APP_USER, result.customer, mDialog, new LoginSuccessListener() {
//                            @Override
//                            public void onSuccessed(Dialog dialog) {
//                                dialog.dismiss();
//                                onBackPressed();
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onReuquestFailed(String error) {
//                        mDialog.dismiss();
//                        ToastUtils.show(LoginActivity.this, error);
//                    }
//                });
            }

        }
    }

    //忘记密码
    public void forget_password(View view) {
        Intent intentPassWordActivity = new Intent(this, PassWordActivity.class);
        startActivity(intentPassWordActivity);
    }

    //用户注册
    public void register(View view) {
        Intent intentRegisterActivity = new Intent(this, RegisterActivity.class);
        startActivity(intentRegisterActivity);
    }

}