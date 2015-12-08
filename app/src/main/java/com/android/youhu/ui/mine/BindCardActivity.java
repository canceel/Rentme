package com.android.youhu.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.ui.base.BaseActivity;

public class BindCardActivity extends BaseActivity {
    private static final String TAG = BindCardActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private EditText cardNoEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_card);
        findViews();
        getDatas(false);
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        cardNoEditText = (EditText) findViewById(R.id.cardNo);
        titleTextView.setText("绑定银行卡");
    }


    public void confirm(View view) {
        Intent it = new Intent(mContext, ConfirmCardActivity.class);
        startActivity(it);
    }


}

