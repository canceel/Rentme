package com.android.youhu.ui.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
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


public class RegisterActivity extends BaseLoginBusinessActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private EditText edInputCellon;
    private EditText edInputSecurityCode;
    private EditText edInputPassWord;
    private EditText edConforPassWord;
    private CountDownTimer time;
    private Button btnSecurityCode;
    private CheckBox cbServeRule;
    private TextView tvInputCellNO;
    private RelativeLayout agreementRl;

    private String messageCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edInputCellon = (EditText) findViewById(R.id.input_cellno);
        edInputSecurityCode = (EditText) findViewById(R.id.input_securitycode);
        edInputPassWord = (EditText) findViewById(R.id.input_password);
        edConforPassWord = (EditText) findViewById(R.id.confor_password);
        btnSecurityCode = (Button) findViewById(R.id.security_code);
        cbServeRule = (CheckBox) findViewById(R.id.serve_rule);
        tvInputCellNO = (TextView) findViewById(R.id.input_cellno_tv);
        agreementRl = (RelativeLayout) findViewById(R.id.agreementrl);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.security_code).setOnClickListener(this);
        findViewById(R.id.confirm).setOnClickListener(this);
        ((TextView)findViewById(R.id.title)).setText("注册");
        agreementRl.setOnClickListener(this);
        time = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnSecurityCode.setClickable(false);
                btnSecurityCode.setText(millisUntilFinished / 1000 + "s");
                tvInputCellNO.setVisibility(View.VISIBLE);
                tvInputCellNO.setText(edInputCellon.getText().toString());
                edInputCellon.setVisibility(View.GONE);

            }

            @Override
            public void onFinish() {
                btnSecurityCode.setClickable(true);
                btnSecurityCode.setText("获取验证码");
                edInputCellon.setVisibility(View.VISIBLE);
                tvInputCellNO.setVisibility(View.GONE);

            }
        };

    }

    //注册并登录
    public void register_enter(View view) {
        final String phoneNum = edInputCellon.getText().toString();
        final String securityCode = edInputSecurityCode.getText().toString();
        final String password = edInputPassWord.getText().toString();
        final String conforPassword = edConforPassWord.getText().toString();

        if (TextUtils.isEmpty(phoneNum)) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(securityCode)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
        } else if (!TextUtils.isEmpty(messageCode) && !messageCode.equals(securityCode)) {
            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(conforPassword)) {
            Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
        } else if (!(password.equals(conforPassword))) {
            Toast.makeText(this, "两次密码不相同", Toast.LENGTH_SHORT).show();
        } else if (!(cbServeRule.isChecked())) {
            Toast.makeText(this, "请阅读友乎APP服务条款", Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isNetworkConnected(this)) {
                final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this, R.string.loading);
                mDialog.show();
                HttpLoad.UserModule.registMobile(TAG, phoneNum, password, messageCode, new
                        ResponseCallback<RegistResult>(this) {

                            @Override
                            public void onRequestSuccess(RegistResult result) {
                                HttpLoad.UserModule.login(TAG, phoneNum,
                                        password,
                                        new ResponseCallback<LoginResult>(RegisterActivity.this) {


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
                                                ToastUtils.show(RegisterActivity.this, error);
                                            }
                                        });
                            }

                            @Override
                            public void onReuquestFailed(String error) {
                                mDialog.dismiss();
                                ToastUtils.show(RegisterActivity.this, error);
                            }
                        });
            }
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
                register_enter(view);
                break;
            case R.id.agreementrl:
                cbServeRule.setChecked(!cbServeRule.isChecked());
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    public void get_securitycode(View view) {
        if (TextUtils.isEmpty(edInputCellon.getText().toString())) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        } else if (!Utils.isMobile(edInputCellon.getText().toString())) {
            Toast.makeText(this, "手机号码错误", Toast.LENGTH_SHORT).show();
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
            dialog.show();
            HttpLoad.UserModule.getMessageCode(TAG, edInputCellon.getText().toString(), "0",
                    new ResponseCallback<ResponseMessageBean>(mContext) {
                        @Override
                        public void onRequestSuccess(ResponseMessageBean result) {
                            dialog.dismiss();
                            messageCode = result.data.captcha;
                            time.start();
                            ToastUtils.show(mContext, "短信验证码已发送，请注意查收");
                        }

                        @Override
                        public void onReuquestFailed(String error) {
                            dialog.dismiss();
                            ToastUtils.show(mContext, error);
                        }
                    });

        }
    }
}
