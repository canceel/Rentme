package com.android.youhu.widget;

import android.text.format.DateFormat;

/**
 * Created by king on 2015/8/24.
 */
public class CountDownMills {
    //    public String time;
    public long endTime;

    public CountDownMills(long endTime) {
        this.endTime = endTime;
    }

    public String leftTime() {
        if(endTime<=0){
            return "";
        }else {
            StringBuilder time = new StringBuilder();
            long systemMills = System.currentTimeMillis();
            long leftTime = endTime - systemMills;
            if (leftTime / 24 / 1000 / 3600 > 0) {
                time.append((leftTime / 24 / 1000 / 3600) + "天");
            }
            if (endTime / 1000 / 3600 > 0) {
                time.append(DateFormat.format("kk:mm:ss", leftTime).toString());
            } else {
                time.append(DateFormat.format("00:mm:ss", leftTime).toString());
            }
            return time.toString();
        }
    }
}
