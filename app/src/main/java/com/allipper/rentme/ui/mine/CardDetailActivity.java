package com.allipper.rentme.ui.mine;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.widget.AutoHeightListView;
import com.allipper.rentme.widget.CircleImageView;

public class CardDetailActivity extends BaseActivity {
    private static final String TAG = CardDetailActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private CircleImageView cardIconCircleImageView;
    private TextView nameTextView;
    private TextView typeTextView;
    private TextView cardNoTextView;
    private AutoHeightListView listAutoHeightListView;
    private TextView onceLimitTextView;
    private TextView dayLimitTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        findViews();
        getDatas(false);
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        cardIconCircleImageView = (CircleImageView) findViewById(R.id.cardIcon);
        nameTextView = (TextView) findViewById(R.id.name);
        typeTextView = (TextView) findViewById(R.id.type);
        cardNoTextView = (TextView) findViewById(R.id.cardNo);
        listAutoHeightListView = (AutoHeightListView) findViewById(R.id.list);
        onceLimitTextView = (TextView) findViewById(R.id.onceLimit);
        dayLimitTextView = (TextView) findViewById(R.id.dayLimit);

    }


    public void unbind(View view) {
        ToastUtils.show(mContext, "解绑定成功");
        finish();
    }

}

