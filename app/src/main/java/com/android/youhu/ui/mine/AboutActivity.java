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
        tvContent.setText("友乎是一款社交类平台软件，用户可以在" +
                "平台上发布自己的专业技能和空闲时间，也可以租到对应专业技能的用户的帮助，使用户能学习到适合自己的技能。");
        titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText("关于");
    }

    public void back(View v) {
        onBackPressed();
    }
}
