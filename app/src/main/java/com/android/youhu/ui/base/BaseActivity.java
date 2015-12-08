package com.android.youhu.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.common.skin.SkinChanger;
import com.android.youhu.ui.IndexActivity;
import com.android.youhu.widget.MyEmptyViewHelper;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * Created by allipper on 2015/9/1.
 */
public class BaseActivity extends Activity implements View.OnClickListener {

    protected Context mContext;
    protected TextView backImageView;
    protected TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        PushAgent.getInstance(mContext).onAppStart();
        enterAnimation();
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
        exitAnimation();
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
        exitAnimation();
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

    protected void enterAnimation() {
        overridePendingTransition(R.anim.in_from_right, R.anim.hold);
    }

    protected void exitAnimation() {
        overridePendingTransition(R.anim.hold, R.anim.out_to_right);
    }
}
