package com.android.youhu.ui.mine;


import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.youhu.R;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.HttpLoad;
import com.android.youhu.net.ResponseCallback;
import com.android.youhu.net.response.ResponseBase;
import com.android.youhu.ui.base.BaseActivity;

public class MineAuthActivity extends BaseActivity {
    private static final String TAG = MineAuthActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private EditText real_nameEditText;
    private EditText id_cardEditText;
    private Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_auth);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        real_nameEditText = (EditText) findViewById(R.id.real_name);
        id_cardEditText = (EditText) findViewById(R.id.id_card);
        confirmButton = (Button) findViewById(R.id.confirm);
        backImageView.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        titleTextView.setText("我的认证");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.confirm:
                submit(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    private void submit(View view) {
        if (TextUtils.isEmpty(real_nameEditText.getText().toString())) {
            Toast.makeText(this, "请输入真实姓名", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(id_cardEditText.getText().toString())) {
            Toast.makeText(this, "请输入身份证", Toast.LENGTH_SHORT).show();
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
            dialog.show();
            HttpLoad.UserModule.auth(TAG, real_nameEditText.getText().toString(), id_cardEditText
                            .getText().toString(), Utils.getToken(mContext),
                    new ResponseCallback<ResponseBase>(mContext) {
                        @Override
                        public void onRequestSuccess(ResponseBase result) {
                            dialog.dismiss();
                            ToastUtils.show(mContext, "已验证");
                            onBackPressed();
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

