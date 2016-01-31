package com.android.youhu.application;


import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.android.volley.toolbox.NetworkImageView;
import com.android.youhu.R;
import com.android.youhu.ui.base.BaseActivity;

public class PhotoActivity extends BaseActivity {
    private static final String TAG = PhotoActivity.class.getSimpleName();

    private NetworkImageView iconImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {
        Uri uri = (Uri) getIntent().getExtras().get("photo");
        if (uri != null) {
            iconImageView.setImageURI(uri);
        }

    }

    private void findViews() {
        iconImageView = (NetworkImageView) findViewById(R.id.icon);

    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        finish();
        return true;
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        finish();
        return true;
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        finish();
        return true;
    }


}

