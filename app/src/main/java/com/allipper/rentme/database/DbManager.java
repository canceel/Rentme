package com.allipper.rentme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.allipper.rentme.bean.BeanCountry;
import com.allipper.rentme.common.util.Logger;
import com.allipper.rentme.net.response.SysEnumsResponse;
import com.allipper.rentme.ui.login.CurrentCityActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/9.
 */
public class DbManager {
    private static DbHelper helper;
    private SQLiteDatabase db;

    public DbManager(Context context) {
        if (helper == null) {
            helper = new DbHelper(context);
        }
    }

    /**
     * 初始化城市列表数据
     */
    public void insertCities(List<BeanCountry> country) {
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (BeanCountry countryEntry : country) {
                ContentValues values = new ContentValues();
                values.put(CountryEntry.COLUMN_PROVINCE, countryEntry.province);
                values.put(CountryEntry.COLUMN_CITY, countryEntry.city);
                values.put(CountryEntry.COLUMN_DISTRICT, countryEntry.district);
                values.put(CountryEntry.COLUMN_ACRONYM_NAME, countryEntry.acronymName);
                values.put(CountryEntry.COLUMN_ACRONYM, countryEntry.acronym);
                db.replace(CountryEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 初始化系统枚举列表数据
     */
    public void insertSysEnums(SysEnumsResponse.DataEntity.EnumEntity enumEntity, String type) {
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (SysEnumsResponse.DataEntity.ItemsEntity item : enumEntity.items) {
                ContentValues values = new ContentValues();
                values.put(SysEnumEntry.COLUMN_TYPE, type);
                values.put(SysEnumEntry.COLUMN_MULTI, enumEntity.multi);
                values.put(SysEnumEntry.COLUMN_DISPLAY_NAME, item.displayName);
                values.put(SysEnumEntry.COLUMN_NAME, item.name);
                values.put(SysEnumEntry.COLUMN_VALUE, item.value);
                db.replace(SysEnumEntry.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }

    /**
     * 查询制定类型枚举
     *
     * @param type
     * @return
     */

    public SysEnumsResponse.DataEntity.EnumEntity queryEnumEntity(String type) {
        db = helper.getReadableDatabase();
        SysEnumsResponse.DataEntity.EnumEntity enumEntity = new SysEnumsResponse.DataEntity
                .EnumEntity();
        String[] columns = {SysEnumEntry.COLUMN_MULTI, SysEnumEntry.COLUMN_DISPLAY_NAME,
                SysEnumEntry.COLUMN_NAME, SysEnumEntry.COLUMN_VALUE};
        String orderBy = SysEnumEntry.COLUMN_VALUE + " ASC ";
        String[] select = {type};
        String whereCla = SysEnumEntry.COLUMN_TYPE + " = ?";
        Cursor cursor = db.query(true, SysEnumEntry.TABLE_NAME, columns, whereCla, select, null,
                null,
                orderBy, null);
        List<SysEnumsResponse.DataEntity.ItemsEntity> items = new ArrayList<>();
        boolean isInit = false;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            if (!isInit) {
                enumEntity.multi = cursor.getInt(cursor.getColumnIndex(SysEnumEntry
                        .COLUMN_DISPLAY_NAME)) == 1 ? true : false;
                isInit = true;
            }
            SysEnumsResponse.DataEntity.ItemsEntity item = new SysEnumsResponse.DataEntity
                    .ItemsEntity();
            item.displayName = cursor.getString(cursor.getColumnIndex(SysEnumEntry
                    .COLUMN_DISPLAY_NAME));
            item.value = cursor.getInt(cursor.getColumnIndex(SysEnumEntry.COLUMN_VALUE));
            item.name = cursor.getString(cursor.getColumnIndex(SysEnumEntry.COLUMN_NAME));
            items.add(item);
        }
        enumEntity.items = items;
        cursor.close();
        db.close();
        return enumEntity;
    }


    /**
     * 查询所有城市，用于城市排序列表
     */
    public List<BeanCountry> queryAllCities() {
        db = helper.getReadableDatabase();
        db.beginTransaction();
        List<BeanCountry> datas = new ArrayList<>();
        String[] columns = {CountryEntry.COLUMN_ACRONYM_NAME, CountryEntry.COLUMN_ACRONYM};
        String orderBy = CountryEntry.COLUMN_ACRONYM + " ASC ";
        Cursor cursor = db.query(true, CountryEntry.TABLE_NAME, columns, null, null, null, null,
                orderBy, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            BeanCountry beanCountry = new BeanCountry();
            beanCountry.acronymName = cursor.getString(cursor.getColumnIndex(CountryEntry
                    .COLUMN_ACRONYM_NAME));
            beanCountry.acronym = cursor.getString(cursor.getColumnIndex(CountryEntry
                    .COLUMN_ACRONYM));
            datas.add(beanCountry);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return datas;
    }

    /**
     * 根据列表选择或定位返回一个最优的BeanCountry对象，默认返回福州市鼓楼区
     */
    public BeanCountry queryCountryByCondition(String searchCondition, int type) {
        Logger.d("CONDITION START", searchCondition);
        db = helper.getReadableDatabase();
        db.beginTransaction();
        List<BeanCountry> datas = new ArrayList<>();
        String selection = null;
        String[] selectionArgs = null;
        if (type == CurrentCityActivity.TYPE_LOCATION) {
            selection = CountryEntry.COLUMN_DISTRICT_CODE + " LIKE ?";
            selectionArgs = new String[]{"%" + searchCondition + "%"};
        } else if (type == CurrentCityActivity.TYPE_SELECT) {
            selection = CountryEntry.COLUMN_ACRONYM_NAME + " = ?";
            selectionArgs = new String[]{searchCondition};
        }
        String[] columns = {CountryEntry.COLUMN_ACRONYM_NAME,
                CountryEntry.COLUMN_DISTRICT_CODE};
        Cursor cursor = db.query(true, CountryEntry.TABLE_NAME, columns, selection,
                selectionArgs, null, null, null, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            BeanCountry beanCountry = new BeanCountry();
            beanCountry.acronymName = cursor.getString(cursor.getColumnIndex(CountryEntry
                    .COLUMN_ACRONYM_NAME));
            datas.add(beanCountry);
            break;
        }

        BeanCountry beanCountry;
        if (datas.size() == 0) {
            Logger.d("LOCATION", "DEFAULT LOCATION");
            beanCountry = new BeanCountry();
            beanCountry.acronymName = "福州市";
        } else {
            beanCountry = datas.get(0);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        Logger.d("CONDITION END", "---");
        return beanCountry;
    }


}
