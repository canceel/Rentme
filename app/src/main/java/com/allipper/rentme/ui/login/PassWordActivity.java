package com.allipper.rentme.ui.login;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.ui.base.BaseLoginBusinessActivity;


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
        }
//        else if (!TextUtils.isEmpty(messageCode) && !messageCode.equals(securityCode) ) {
//            Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
//        }
        else if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(conforNewPassword)) {
            Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
        } else if (!(newPassword.equals(conforNewPassword))) {
            Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        } else {
            if (Utils.isNetworkConnected(this)) {
                final Dialog mDialog = LoadDialogUtil.createLoadingDialog(this, R.string.loading);
                mDialog.show();
//                HttpLoad.UserModule.resetPassword(this, TAG, phoneNum, newPassword, new
//                        ResponseCallback<ResponseBase>(this) {
//
//                            @Override
//                            public void onRequestSuccess(ResponseBase result) {
//                                HttpLoad.UserModule.login(PassWordActivity.this, TAG, phoneNum, newPassword, new ResponseCallback<LoginResult>(PassWordActivity.this) {
//
//                                    @Override
//                                    public void onRequestSuccess(LoginResult result) {
//                                        loginSuccess(result.customer.account_uid, phoneNum, result
//                                                .access_token, SharedPre.Constant.APP_USER, result.customer, mDialog, new LoginSuccessListener() {
//                                            @Override
//                                            public void onSuccessed(Dialog dialog) {
//                                                dialog.dismiss();
//                                                Intent intent = new Intent(PassWordActivity.this,
//                                                        LoginActivity
//                                                                .class);
//                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                                startActivity(intent);
//                                            }
//                                        });
//                                    }
//
//                                    @Override
//                                    public void onReuquestFailed(String error) {
//                                        mDialog.dismiss();
//                                        ToastUtils.show(PassWordActivity.this, error);
//                                    }
//                                });
//                            }
//
//                            @Override
//                            public void onReuquestFailed(String error) {
//                                mDialog.dismiss();
//                                ToastUtils.show(PassWordActivity.this, error);
//                            }
//                        });
            }
        }
    }

    public void get_securitycode(View view) {
        if (TextUtils.isEmpty(edInputCellNo.getText().toString())) {
            Toast.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
            dialog.show();
//            HttpLoad.UserModule.getMessageCode(mContext, TAG, edInputCellNo.getText().toString(), HttpLoad.UserModule.MESSAGE_MODE_ALIDATEOLDMOBILE, new ResponseCallback<ResponseMessageBean>(mContext) {
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
