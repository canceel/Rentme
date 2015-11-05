package com.allipper.rentme.database;

import android.provider.SyncStateContract;

/**
 * Created by Administrator on 2015/7/11.
 */
public class SysEnumEntry implements SyncStateContract.Columns {
    public static final String TABLE_NAME = "sysenum";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_MULTI = "multi";
    public static final String COLUMN_DISPLAY_NAME = "displayName";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_VALUE = "value";
}
