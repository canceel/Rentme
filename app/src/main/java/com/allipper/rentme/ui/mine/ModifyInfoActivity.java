package com.allipper.rentme.ui.mine;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.SharedPre;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.net.response.UpdateUserInforResponse;
import com.allipper.rentme.ui.base.BaseActivity;

public class ModifyInfoActivity extends BaseActivity {

    private static final String TAG = ModifyInfoActivity.class.getSimpleName();

    public static final String MODIFY_TYPE = "modify_type";
    public static final String MODIFY_VALUE = "modify_value";
    public static final int TYPE_NAME = 0;
    public static final int TYPE_STATUS = TYPE_NAME + 1;
    public static final int TYPE_SCHEDULE = TYPE_STATUS + 1;
    public static final int TYPE_FEE = TYPE_SCHEDULE + 1;

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private EditText contentEditText;
    private TextView tipTextView;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);
        findViews();
        getDatas(false);
    }

    public void getDatas(boolean isShowDialog) {
        Intent it = getIntent();
        type = it.getIntExtra(MODIFY_TYPE, -1);
        contentEditText.setText(it.getStringExtra(MODIFY_VALUE));
        switch (type) {
            case TYPE_NAME:
                titleTextView.setText("修改姓名");
                tipTextView.setText("好姓名可以让别人更容易记住你");
                break;
            case TYPE_STATUS:
                titleTextView.setText("修改个性签名");
                tipTextView.setVisibility(View.GONE);
                break;
            default:
                titleTextView.setText("修改");
                tipTextView.setVisibility(View.GONE);
        }
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        contentEditText = (EditText) findViewById(R.id.content);
        tipTextView = (TextView) findViewById(R.id.tip);

    }


    public void save(View view) {
        if (TextUtils.isEmpty(contentEditText.getText().toString())) {
            ToastUtils.show(mContext, "内容不能为空");
            return;
        }
        switch (type) {
            case TYPE_NAME:
                updateUserInfo(SharedPre.User.NICKNAME, contentEditText.getText().toString());
                break;
            case TYPE_STATUS:
                updateUserInfo(SharedPre.User.USERDETAIL, contentEditText.getText().toString());
                break;
        }


        onSuccessed();
    }

    private void updateUserInfo(String type, String value) {
        if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.updating);
            dialog.show();
            HttpLoad.UserModule.updateUserInfor(TAG, type, value, Utils.getToken
                    (mContext), new
                    ResponseCallback<UpdateUserInforResponse>(mContext) {

                        @Override
                        public void onRequestSuccess(UpdateUserInforResponse result) {
                            Utils.saveUserInfor(mContext, result.data);
                            dialog.dismiss();
                            onSuccessed();
                        }

                        @Override
                        public void onReuquestFailed(String error) {
                            dialog.dismiss();
                            ToastUtils.show(mContext, error);
                        }
                    });

        }
    }

    private void onSuccessed() {
        Intent it = new Intent();
        it.putExtra(MODIFY_TYPE, type);
        it.putExtra(MODIFY_VALUE, contentEditText.getText().toString());
        setResult(RESULT_OK, it);
        back(null);
    }


}

