package com.allipper.rentme.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.allipper.rentme.R;
import com.allipper.rentme.bean.BeanCountry;
import com.allipper.rentme.common.util.Logger;
import com.allipper.rentme.common.util.SharedPre;
import com.allipper.rentme.common.util.SharedPreUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.database.DbManager;
import com.allipper.rentme.ui.base.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by allipper on 2015/9/1.
 */
public class StartActivity extends BaseActivity implements Animation.AnimationListener {

    private static final String TAG = StartActivity.class.getSimpleName();

    private ImageView ibStartPage;

    private DbManager manager;
    private int version_code_old;
    private int version_code_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        MobclickAgent.updateOnlineConfig(mContext);
        AnalyticsConfig.enableEncrypt(true);
        PushAgent mPushAgent = PushAgent.getInstance(mContext);
        if(SharedPreUtils.getBoolean(mContext, SharedPre.App.MESSAGE_PUSH, true)){
            mPushAgent.enable();
        }
        initView();
    }

    private void initView() {
        manager = new DbManager(this);
        version_code_old = SharedPreUtils.getInt(this, SharedPre.App.VERSION, 0);
        ibStartPage = (ImageView) findViewById(R.id.start_page);
        AlphaAnimation animation = new AlphaAnimation(0.8f, 1.0f);
        animation.setDuration(2000);
        animation.setAnimationListener(this);
        ibStartPage.startAnimation(animation);

    }

    @Override
    public void onAnimationStart(Animation animation) {
//        setView();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        new DatabasesAsyncTask().execute();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    class DatabasesAsyncTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                updateCity();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            enter();
        }
    }


    private void enter() {
        boolean isGuide = SharedPreUtils.getBoolean(this, SharedPre.App.ISGUIDE, false);
        Intent intent = new Intent(StartActivity.this, isGuide ? IndexActivity.class :
                GuideActivity.class);
        startActivity(intent);
        StartActivity.this.finish();
    }

    private void updateCity() throws Exception {
        long start = System.currentTimeMillis();
        version_code_now = this.getPackageManager().getPackageInfo(getPackageName(),
                PackageManager.GET_CONFIGURATIONS).versionCode;
        Logger.d(TAG, "[old: " + version_code_old + "]  [now: " + version_code_now + "]");
        if (version_code_now > version_code_old) {
            Gson gson = new Gson();
            List<BeanCountry> countries = gson.fromJson(new BufferedReader(new InputStreamReader
                    (getAssets().open("country.json"))), new TypeToken<List<BeanCountry>>() {
            }.getType());
            manager.insertCities(countries);
            SharedPreUtils.putInt(this, SharedPre.App.VERSION, version_code_now);
            Logger.d(TAG, "读取city文件成功！！");
        }
        Logger.d(TAG, "耗时：" + (System.currentTimeMillis() - start));
    }

//    /**
//     * 获取广告图片并设置到启动页
//     */
//    private void setView() {
//        if (Utils.isNetworkConnected(this)) {
//            HttpLoad.StartModule.getStartTheme(mContext, TAG, new
//                    ResponseCallback<ResponseStartTheme>(mContext) {
//                        @Override
//                        public void onRequestSuccess(ResponseStartTheme result) {
//                            SharedPreUtils.putString(mContext, SharedPre.App.START_IMAGE, result
//                                    .page.largeImage);
//                        }
//
//                        @Override
//                        public void onReuquestFailed(String error) {
//
//                        }
//                    });
//        }
//
//        HttpLoad.getImage(this, SharedPreUtils.getString
//                (mContext, SharedPre.App.START_IMAGE), R.mipmap.icon_start_page, ibStartPage);
//    }
}
