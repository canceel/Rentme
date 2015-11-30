package com.android.youhu.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.ui.base.BaseActivity;

/**
 * Created by allipper on 2015/10/8.
 */
public class AboutActivity extends BaseActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tvContent = (TextView) findViewById(R.id.content);
        tvContent.setText("永辉生活家 是永辉超市旗下跨境移动端电商平台，提供母婴用品、国际美装、食品保健、电器数码、服装箱包、家居日用等海外商品特卖，100" +
                "%正品保证。用户可以足不出户，轻松淘到特价海外产品，更多产品敬请期待。");
    }

    public void back(View v) {
        onBackPressed();
    }
}
