package com.allipper.rentme.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.Constant;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.ui.IndexActivity;
import com.allipper.rentme.widget.MyEmptyViewHelper;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * Created by allipper on 2015/9/1.
 */
public class BaseActivity extends Activity implements View.OnClickListener {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        PushAgent.getInstance(mContext).onAppStart();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(mContext);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(mContext);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            processExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 请求数据
     *
     * @param isShowDialog 是否要显示等待框
     */
    protected void getDatas(boolean isShowDialog) {
    }


    protected void processExit() {
        finish();
    }

    public void retry(int tab) {
        if (tab == MyEmptyViewHelper.RETRY_TYPE_GOTO_INDEX) {
            Intent it = new Intent(this, IndexActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
            onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        if (view != null && view.getId() == R.id.back) {
            back(view);
        }
    }
}
