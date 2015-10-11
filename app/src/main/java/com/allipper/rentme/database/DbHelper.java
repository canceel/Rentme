package com.allipper.rentme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/7/9.
 */
public class DbHelper extends SQLiteOpenHelper {

    /**
     * 数据库名称 *
     */
    public static final String DATABASE_NAME = "yonghuigo.db";

    /**
     * 数据库版本 *
     */
    public static final int DATABASE_VERSION = 1;


    /**
     * 创建城市表*
     */
    private static final String SQL_CREATE_ENTRIES_COUNTRY = "CREATE TABLE "
            + CountryEntry.TABLE_NAME + " (" + CountryEntry._ID
            + " INTEGER PRIMARY KEY," + CountryEntry.COLUMN_PROVINCE
            + " TEXT NOT NULL," + CountryEntry.COLUMN_CITY
            + " TEXT NOT NULL," + CountryEntry.COLUMN_DISTRICT
            + " TEXT NOT NULL," + CountryEntry.COLUMN_DISTRICT_CODE
            + " TEXT," + CountryEntry.COLUMN_ACRONYM_NAME
            + " TEXT NOT NULL," + CountryEntry.COLUMN_ACRONYM
            + " TEXT NOT NULL, UNIQUE("
            + CountryEntry.COLUMN_PROVINCE
            + "," + CountryEntry.COLUMN_CITY
            + "," + CountryEntry.COLUMN_DISTRICT + "))";


    /**
     * 删除城市表
     */
    private static final String SQL_DELETE_ENTRIES_COUNTRY = "DROP TABLE IF EXISTS "
            + CountryEntry.TABLE_NAME;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES_COUNTRY);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
