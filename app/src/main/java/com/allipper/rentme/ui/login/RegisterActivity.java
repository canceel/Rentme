package com.allipper.rentme.ui.login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.ui.base.BaseLoginBusinessActivity;


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
        }
//        else if (!TextUtils.isEmpty(messageCode) && !messageCode.equals(securityCode) ) {
//            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
//        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(conforPassword)) {
            Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
        } else if (!(password.equals(conforPassword))) {
            Toast.makeText(this, "两次密码不相同", Toast.LENGTH_SHORT).show();
        } else if (!(cbServeRule.isChecked())) {
            Toast.makeText(this, "请阅读海外购APP服务条款", Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isNetworkConnected(this)) {
                final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this, R.string.loading);
                mDialog.show();
//                HttpLoad.UserModule.registMobile(this, TAG, phoneNum, password, SharedPreUtils.getString(mContext, SharedPre.Location.ACRONYM_NAME), new
//                        ResponseCallback<RegistResult>(this) {
//
//                            @Override
//                            public void onRequestSuccess(RegistResult result) {
//                                HttpLoad.UserModule.login(RegisterActivity.this, TAG, phoneNum, password,
//                                        new ResponseCallback<LoginResult>(RegisterActivity.this) {
//
//
//                                            @Override
//                                            public void onRequestSuccess(LoginResult result) {
//                                                loginSuccess(result.customer.account_uid, phoneNum, result.access_token, SharedPre.Constant.APP_USER, result.customer, mDialog, new LoginSuccessListener() {
//                                                    @Override
//                                                    public void onSuccessed(Dialog dialog) {
//                                                        dialog.dismiss();
//                                                        Intent intent = new Intent(RegisterActivity.this,
//                                                                LoginActivity
//                                                                        .class);
//                                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                                        startActivity(intent);
//                                                    }
//                                                });
//                                            }
//
//                                            @Override
//                                            public void onReuquestFailed(String error) {
//                                                mDialog.dismiss();
//                                                ToastUtils.show(RegisterActivity.this, error);
//                                            }
//                                        });
//                            }
//
//                            @Override
//                            public void onReuquestFailed(String error) {
//                                mDialog.dismiss();
//                                ToastUtils.show(RegisterActivity.this, error);
//                            }
//                        });
            }
        }
    }

    public void back(View view) {
        onBackPressed();

    }

    public void get_securitycode(View view) {
        if (TextUtils.isEmpty(edInputCellon.getText().toString())) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
            dialog.show();
//            HttpLoad.UserModule.getMessageCode(mContext, TAG, edInputCellon.getText().toString(), HttpLoad.UserModule.MESSAGE_MODE_REGISTCELLPHONE, new ResponseCallback<ResponseMessageBean>(mContext) {
//                @Override
//                public void onRequestSuccess(ResponseMessageBean result) {
//                    dialog.dismiss();
//                    messageCode = result.orderCode;
//                    time.start();
//                    ToastUtils.show(mContext, "短信验证码已发送，请注意查收");
//                }
//
//                @Override
//                public void onReuquestFailed(String error) {
//                    dialog.dismiss();
//                    ToastUtils.show(mContext, error);
//                }
//            });

        }
    }
}
