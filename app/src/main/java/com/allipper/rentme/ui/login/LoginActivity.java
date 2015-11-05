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
import com.allipper.rentme.common.util.SharedPre;
import com.allipper.rentme.common.util.SharedPreUtils;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.net.response.LoginResult;
import com.allipper.rentme.ui.base.BaseLoginBusinessActivity;


public class LoginActivity extends BaseLoginBusinessActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText edCellNo;
    private EditText edPassWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edCellNo = (EditText) findViewById(R.id.cell_no);
        edPassWord = (EditText) findViewById(R.id.password);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.forget).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
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
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isNetworkConnected(this)) {
                final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this, R.string.loading);
                mDialog.show();
                HttpLoad.UserModule.login(TAG, account, password, new
                        ResponseCallback<LoginResult>(this) {

                            @Override
                            public void onRequestSuccess(LoginResult result) {
                                loginSuccess(result.data, new LoginSuccessListener() {
                                    @Override
                                    public void onSuccessed(Dialog dialog) {
                                        dialog.dismiss();
                                        SharedPreUtils.putString(mContext, SharedPre.App.USER_TPE, "login");
                                    }
                                }, mDialog);
                            }

                            @Override
                            public void onReuquestFailed(String error) {
                                mDialog.dismiss();
                                ToastUtils.show(LoginActivity.this, error);
                            }
                        });
            }
        }
        onBackPressed();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.login:
                enter(view);
                break;
            case R.id.register:
                register(view);
                break;
            case R.id.forget:
                forget_password(view);
                break;
            default:
                super.onClick(view);
                break;
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