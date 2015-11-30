package com.android.youhu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Stone on 2015/8/22.
 * Project:TimeDemo
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class MyCountDownTextView extends TextView implements  MyCountDownTimerCallback{

    private boolean isFinish;
    public MyCountDownTextView(Context context) {
        super(context);
    }

    public MyCountDownTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCountDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onTimeUpdated() {
        if(!isFinish) {
            if(getTag() == null){
                return;
            }
            CountDownMills curItem = (CountDownMills) this.getTag();
            setText(curItem.leftTime());
            if("00:00:00".equals(curItem.leftTime())){
                isFinish=true;
            }
        }else{
            setText("00:00:00");
        }
    }

}
