package com.allipper.rentme.widget;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stone on 2015/8/22.
 * Project:TimeDemo
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */


public class MyCountDownTimer extends CountDownTimer {
    List<MyCountDownTimerCallback> callbacks= new ArrayList<>();
    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    public List<MyCountDownTimerCallback> getCallbacks() {
        return callbacks;
    }

    public void addCallback(MyCountDownTimerCallback callback) {
        callbacks.remove(callback);
        callbacks.add(callback);
    }


    @Override
    public void onTick(long millisUntilFinished) {
        for (MyCountDownTimerCallback callback : callbacks) {
            callback.onTimeUpdated();
        }
    }

    @Override
    public void onFinish() {
    }

    public void onDestroy(){
        callbacks.clear();
        callbacks = null;
    }
}