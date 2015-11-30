package com.android.youhu.database;

import android.provider.SyncStateContract;

/**
 * Created by Administrator on 2015/7/11.
 */
public class CountryEntry implements SyncStateContract.Columns {
    public static final String TABLE_NAME = "country";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_DISTRICT = "district";
    public static final String COLUMN_DISTRICT_CODE = "district_code";
    public static final String COLUMN_ACRONYM = "acronym";
    public static final String COLUMN_ACRONYM_NAME = "acronym_name";
}
