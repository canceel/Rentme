package com.android.youhu.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.ui.base.BaseActivity;

public class ModifyInfoActivity extends BaseActivity {

    private static final String TAG = ModifyInfoActivity.class.getSimpleName();

    public static final String MODIFY_TYPE = "modify_type";
    public static final String MODIFY_VALUE = "modify_value";
    public static final int TYPE_NAME = 0;
    public static final int TYPE_STATUS = TYPE_NAME + 1;
    public static final int TYPE_FEE = TYPE_STATUS + 1;

    private RelativeLayout title_topRelativeLayout;
    private TextView backImageView;
    private TextView titleTextView;
    private EditText contentEditText;
    private TextView tipTextView;

    private int type;
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_info);
        findViews();
        getDatas(false);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.save:
                save(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    public void getDatas(boolean isShowDialog) {
        Intent it = getIntent();
        type = it.getIntExtra(MODIFY_TYPE, -1);
        value = it.getStringExtra(MODIFY_VALUE);
        if (!TextUtils.isEmpty(value)) {
            if ("设置昵称".equals(value) || "设置个性签名".equals(value) || "设置时薪".equals(value)) {
                contentEditText.setHint(it.getStringExtra(MODIFY_VALUE));
            } else {
                contentEditText.setText(it.getStringExtra(MODIFY_VALUE));
            }
        }
        switch (type) {
            case TYPE_NAME:
                titleTextView.setText("修改昵称");
                tipTextView.setText("好昵称可以让别人更容易记住你");
                break;
            case TYPE_STATUS:
                titleTextView.setText("修改个性签名");
                tipTextView.setVisibility(View.GONE);
                break;
            case TYPE_FEE:
                titleTextView.setText("修改时薪");
                tipTextView.setText("合理的时薪可以让别人更容易租到你");
                break;
            default:
                titleTextView.setText("修改");
                tipTextView.setVisibility(View.GONE);
        }
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        contentEditText = (EditText) findViewById(R.id.content);
        tipTextView = (TextView) findViewById(R.id.tip);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.save).setOnClickListener(this);
    }


    public void save(View view) {
        if (TextUtils.isEmpty(contentEditText.getText().toString())) {
            ToastUtils.show(mContext, "内容不能为空");
            return;
        }
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

