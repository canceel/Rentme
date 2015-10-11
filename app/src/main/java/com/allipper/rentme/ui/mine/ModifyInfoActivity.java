package com.allipper.rentme.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.ui.base.BaseActivity;

public class ModifyInfoActivity extends BaseActivity {

    private static final String TAG = ModifyInfoActivity.class.getSimpleName();

    public static final String MODIFY_TYPE = "modify_type";
    public static final String MODIFY_VALUE = "modify_value";
    public static final int TYPE_NAME = 0;
    public static final int TYPE_STATUS = 1;
    public static final int TYPE_CAREER = 2;
    public static final int TYPE_SCHEDULE = 3;

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
        getData(false);
    }

    private void getData(boolean isShowDialog) {
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
            case TYPE_CAREER:
                titleTextView.setText("修改职业");
                tipTextView.setVisibility(View.GONE);
                break;
            case TYPE_SCHEDULE:
                titleTextView.setText("修改档期");
                tipTextView.setText("合理档期可以让别人更容易租到你");
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

    //���õ���¼�
    public void back(View view) {
        super.back(view);
    }

    public void save(View view) {
        onSuccessed();
    }

    private void onSuccessed() {
        Intent it = new Intent();
        it.putExtra(MODIFY_TYPE, type);
        it.putExtra(MODIFY_VALUE, contentEditText.getText().toString());
        setResult(RESULT_OK, it);
        back(null);
    }


}

