package com.allipper.rentme.ui.mine;


import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.net.response.ResponseMessageBean;
import com.allipper.rentme.ui.base.BaseActivity;

public class MineAuthActivity extends BaseActivity {
    private static final String TAG = MineAuthActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
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
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        real_nameEditText = (EditText) findViewById(R.id.real_name);
        id_cardEditText = (EditText) findViewById(R.id.id_card);
        confirmButton = (Button) findViewById(R.id.confirm);
        backImageView.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
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
        } else if (Utils.isMobile(id_cardEditText.getText().toString())) {
            Toast.makeText(this, "请输入身份证", Toast.LENGTH_SHORT).show();
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
            dialog.show();
            HttpLoad.UserModule.getMessageCode(TAG, real_nameEditText.getText().toString(),
                    new ResponseCallback<ResponseMessageBean>(mContext) {
                        @Override
                        public void onRequestSuccess(ResponseMessageBean result) {
                            dialog.dismiss();
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

