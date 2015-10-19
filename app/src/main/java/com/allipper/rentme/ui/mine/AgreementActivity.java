package com.allipper.rentme.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.ui.base.BaseActivity;

/**
 * Created by allipper on 2015/10/8.
 */
public class AgreementActivity extends BaseActivity {
    private TextView tvContent;
    private TextView titleContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tvContent = (TextView) findViewById(R.id.content);
        titleContent = (TextView) findViewById(R.id.title);
        tvContent.setText("1.不乱搞\n" +
                "2.不乱来\n3.诚实\n");
        titleContent.setText("服务条款");
    }

    public void back(View v) {
        onBackPressed();
    }
}
