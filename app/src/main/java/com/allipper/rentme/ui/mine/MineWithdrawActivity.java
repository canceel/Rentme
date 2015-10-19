package com.allipper.rentme.ui.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.ui.base.BaseActivity;

public class MineWithdrawActivity extends BaseActivity {
    private static final String TAG = MineWithdrawActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private TextView availableTextView;
    private EditText moneyEditText;
    private TextView messageTextView;
    private Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_withdraw);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        availableTextView = (TextView) findViewById(R.id.available);
        moneyEditText = (EditText) findViewById(R.id.money);
        messageTextView = (TextView) findViewById(R.id.message);
        confirmButton = (Button) findViewById(R.id.confirm);

    }


    public void confirm(View view) {
        ToastUtils.show(mContext, "已提交申请");
        finish();
    }


}

