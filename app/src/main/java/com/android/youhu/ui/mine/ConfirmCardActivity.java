package com.android.youhu.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.ui.base.BaseActivity;

public class ConfirmCardActivity extends BaseActivity {
    private static final String TAG = ConfirmCardActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private TextView messageTextView;
    private TextView cardNameTextView;
    private TextView cardNoTextView;
    private TextView cellTextView;
    private TextView confirmMsgTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_card);
        findViews();
        getDatas(false);
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        messageTextView = (TextView) findViewById(R.id.message);
        cardNameTextView = (TextView) findViewById(R.id.cardName);
        cardNoTextView = (TextView) findViewById(R.id.cardNo);
        cellTextView = (TextView) findViewById(R.id.cell);
        confirmMsgTextView = (TextView) findViewById(R.id.confirmMsg);
        titleTextView.setText("验证银行卡信息");
    }


    public void confirm(View view) {
        Intent it = new Intent(mContext, ConfirmMsgActivity.class);
        startActivity(it);
    }


}

