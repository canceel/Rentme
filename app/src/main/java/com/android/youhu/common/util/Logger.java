package com.android.youhu.common.util;

import android.util.Log;

/**
 * Created by allipper on 2015/9/1.
 */
public class Logger {

    private static final String PREFIX = "[Logger]-->";

    public static void d(String tag, String msg) {
        if (com.android.youhu.common.Constant.IS_DEBUG_MODE)
            Log.d(PREFIX + tag, msg);
    }

    public static void e(String tag, String msg) {
        if (com.android.youhu.common.Constant.IS_DEBUG_MODE)
            Log.e(PREFIX + tag, msg);
    }

    public static void i(String tag, String msg) {
        if (com.android.youhu.common.Constant.IS_DEBUG_MODE)
            Log.i(PREFIX + tag, msg);
    }

    public static void v(String tag, String msg) {
        if (com.android.youhu.common.Constant.IS_DEBUG_MODE)
            Log.v(PREFIX + tag, msg);
    }

    public static void w(String tag, String msg) {
        if (com.android.youhu.common.Constant.IS_DEBUG_MODE)
            Log.w(PREFIX + tag, msg);
    }

    public static void wtf(String tag, String msg) {
        if (com.android.youhu.common.Constant.IS_DEBUG_MODE)
            Log.wtf(PREFIX + tag, msg);
    }
}
