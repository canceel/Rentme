package com.android.youhu.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.ui.base.BaseActivity;

public class ConfirmMsgActivity extends BaseActivity {
    private static final String TAG = ConfirmMsgActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private TextView messageTextView;
    private EditText codeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_msg);
        findViews();
        getDatas(false);
    }

    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        messageTextView = (TextView) findViewById(R.id.message);
        codeEditText = (EditText) findViewById(R.id.code);
        titleTextView.setText("短信验证");
    }

    //���õ���¼�
    public void getSecuritycode(View view) {
    }


    public void confirm(View view) {
        ToastUtils.show(mContext, "绑定成功");
        Intent it = new Intent(mContext, MineCardActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(it);
    }
}

