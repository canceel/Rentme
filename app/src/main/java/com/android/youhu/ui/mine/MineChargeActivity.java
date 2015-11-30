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

public class MineChargeActivity extends BaseActivity {
    private static final String TAG = MineChargeActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private EditText sumMoneyEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_charge);
        findViews();
        getDatas(false);
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        sumMoneyEditText = (EditText) findViewById(R.id.sumMoney);

    }


    public void confirm(View view) {
        Intent it = new Intent(mContext, MinePayActivity.class);
        it.putExtra("isCharged",true);
        startActivity(it);
    }


}

