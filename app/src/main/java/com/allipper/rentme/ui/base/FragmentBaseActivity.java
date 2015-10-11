package com.allipper.rentme.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by allipper on 2015/9/1.
 */
public class FragmentBaseActivity extends FragmentActivity {

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }

    public void back(View view){
        finish();
    }
}
