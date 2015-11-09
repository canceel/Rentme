package com.allipper.rentme.ui.mine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.CropUtils;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.SharedPre;
import com.allipper.rentme.common.util.SharedPreUtils;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.HttpUtils;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.net.response.ResponseAppVersion;
import com.allipper.rentme.ui.IndexActivity;
import com.allipper.rentme.ui.base.BaseActivity;
import com.umeng.message.PushAgent;

/**
 * 系统设置
 * Created by Administrator on 2015/7/11.
 */
public class SysSettingActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = SysSettingActivity.class.getSimpleName();
    public static final String EXIT_CURRENT_USER = "exit_current_user";

    private TextView tvAppVersions;
    private ToggleButton messagePushBtn;
    private Button btnAbout;
    private Button btnCheckVersion;
    private Button btnClearCache;
    private Button btnExit;
    private int versionCode = 1;
    private boolean isMessagePushOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_setting);
        tvAppVersions = (TextView) findViewById(R.id.app_versions);
        messagePushBtn = (ToggleButton) findViewById(R.id.message_push_btn);
        btnAbout = (Button) findViewById(R.id.about);
        btnCheckVersion = (Button) findViewById(R.id.check_version);
        btnClearCache = (Button) findViewById(R.id.clear_cache);
        btnExit = (Button) findViewById(R.id.exit);
        btnAbout.setOnClickListener(this);
        btnClearCache.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        isMessagePushOn = SharedPreUtils.getBoolean(mContext, SharedPre.App.MESSAGE_PUSH, true);
        messagePushBtn.setChecked(isMessagePushOn);
        messagePushBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                messagePushBtn.setChecked(isChecked);
                SharedPreUtils.putBoolean(mContext, SharedPre.App.MESSAGE_PUSH, isChecked);
                PushAgent mPushAgent = PushAgent.getInstance(mContext);
                if (SharedPreUtils.getBoolean(mContext, SharedPre.App.MESSAGE_PUSH, false)) {
                    mPushAgent.enable();
                } else {
                    mPushAgent.disable();
                }
            }

        });
        btnCheckVersion.setOnClickListener(this);
        getAppversions();
    }

    public void back(View v) {
        onBackPressed();
    }

    @Override
    public void onClick(View v) {
        final int id = v.getId();
        if (R.id.about == id) {
            Intent intentAbout = new Intent(SysSettingActivity.this, AboutActivity.class);
            startActivity(intentAbout);
        }
        if (R.id.exit == id) {
            new AlertDialog.Builder(this, R.style.CommonDialog).setMessage("确定要注销当前账号吗？")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {


                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            SharedPreUtils.removeSharedKey(mContext, SharedPre.App.USER_TPE);
                            //删除SharedPreference中的用户信息
                            Utils.cleanUserInfo(SysSettingActivity.this);
                            //删除缓存文件
                            CropUtils.clearCache();
                            //更新用户信息
                            sendBroadcast(new Intent(IndexActivity.UPDATE_USER_INFO));
                            Intent intent = new Intent(SysSettingActivity.this, IndexActivity
                                    .class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra(EXIT_CURRENT_USER, true);
                            startActivity(intent);
                            back(null);
                        }
                    }).setPositiveButton("取消", null).show();
        }
        if (id == R.id.check_version) {
            if (Utils.isNetworkConnected(this)) {
//                final Dialog dialog = LoadDialogUtil.createLoadingDialog(this, R.string.loading);
//                dialog.show();
//                HttpLoad.AppVersion.getAppVersion(this, TAG, new
//                        ResponseCallback<ResponseAppVersion>(SysSettingActivity.this) {
//
//                            @Override
//                            public void onRequestSuccess(ResponseAppVersion result) {
//                                dialog.dismiss();
//                                if (result.versionCode > versionCode) {
//                                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                                    intent.setData(Uri.parse(result.url));
//                                    startActivity(intent);
//                                } else {
//                                    ToastUtils.show(SysSettingActivity.this, "目前已经是最新版本");
//                                }
//                            }
//
//                            @Override
//                            public void onReuquestFailed(String error) {
//                                dialog.dismiss();
//                                ToastUtils.show(SysSettingActivity.this, error);
//                            }
//                        });
            }
        }
        if (id == R.id.clear_cache) {
            new AlertDialog.Builder(this, R.style.CommonDialog).setMessage("确定要清除缓存吗？")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HttpUtils.getInstance().clearCache();
                        }
                    }).setPositiveButton("取消", null).show();
        }
    }

    private void getAppversions() {
        PackageManager manager;
        PackageInfo info;
        manager = this.getPackageManager();
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            tvAppVersions.setText("版本号：" + info.versionName);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
