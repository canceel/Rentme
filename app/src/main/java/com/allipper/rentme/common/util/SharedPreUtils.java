package com.allipper.rentme.common.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/7/8.
 */
public class SharedPreUtils {
    private static final String SP_NAME = "yuehu";

    /**
     * 返回一个SharedPrefrence编辑对象
     *
     * @param context
     * @param mode
     * @return
     */
    public static SharedPreferences.Editor getEditor(Context context, int mode) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, mode);
        return sharedPreferences.edit();
    }

    /**
     * 返回一个私有的编辑对象
     *
     * @param context
     * @return
     */
    public static SharedPreferences.Editor getEditor(Context context) {
        return getEditor(context, Context.MODE_PRIVATE);
    }

    /**
     * 返回一个私有的SharedPreference对象
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    /**
     * 返回String对象
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, "");
    }

    /**
     * 写入sharedPreferences
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    /**
     * 返回Boolean
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    /**
     * 写入一个boolean
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    /**
     * 返回Integer
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String key, int defaultValue) {
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    /**
     * 写入一个Integer
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putInt(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    /**
     * 清除SharedPre中的数据
     *
     * @param context
     */
    public static void clearSharedPre(Context context) {
        getEditor(context).clear().commit();
    }

    /**
     * 清除对应key的值
     *
     * @param context
     * @param key
     */
    public static void removeSharedKey(Context context, String key) {
        getEditor(context).remove(key).commit();
    }
}
