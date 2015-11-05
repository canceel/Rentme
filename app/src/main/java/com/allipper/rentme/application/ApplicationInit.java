package com.allipper.rentme.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.allipper.rentme.database.DbManager;
import com.allipper.rentme.net.response.SysEnumsResponse;

import io.rong.imkit.RongIM;

/**
 * Created by allipper on 2015/9/1.
 */
public class ApplicationInit extends Application {

    public static Context baseContext;

    private static SysEnumsResponse.DataEntity.EnumEntity ageEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity constellationEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity genderEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity heightEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity weightEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity interestEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity jobEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity rentRangeEntities;
    private static SysEnumsResponse.DataEntity.EnumEntity scheduleEntities;


    @Override
    public void onCreate() {
        super.onCreate();
        baseContext = getApplicationContext();
        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getAgeEntities() {
        if (ageEntities == null) {
            ageEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.AGE);
        }
        return ageEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getConstellationEntities() {
        if (constellationEntities == null) {
            constellationEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .CONSTELLATION);
        }
        return constellationEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getGenderEntities() {
        if (genderEntities == null) {
            genderEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.GENDER);
        }
        return genderEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getHeightEntities() {
        if (heightEntities == null) {
            heightEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.HEIGHT);
        }
        return heightEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getWeightEntities() {
        if (weightEntities == null) {
            weightEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.WEIGHT);
        }
        return weightEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getInterestEntities() {
        if (interestEntities == null) {
            interestEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .INTEREST);
        }
        return interestEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getJobEntities() {
        if (jobEntities == null) {
            jobEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.JOB);
        }
        return jobEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getRentRangeEntities() {
        if (rentRangeEntities == null) {
            rentRangeEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.RENT);
        }
        return rentRangeEntities;
    }

    public static SysEnumsResponse.DataEntity.EnumEntity getScheduleEntities() {
        if (scheduleEntities == null) {
            scheduleEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .SCHEDULE);
        }
        return scheduleEntities;
    }

    public static String[] getFormatStringArray(String type) {
        String[] strs = null;
        if (type.equals(SysEnumsResponse.AGE)) {
            strs = new String[getAgeEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getAgeEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.CONSTELLATION)) {
            strs = new String[getConstellationEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getConstellationEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.GENDER)) {
            strs = new String[getGenderEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getGenderEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.HEIGHT)) {
            strs = new String[getHeightEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getHeightEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.WEIGHT)) {
            strs = new String[getWeightEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getWeightEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.INTEREST)) {
            strs = new String[getInterestEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getInterestEntities().items) {
                strs[i++] = item.displayName;
            }
        }else if (type.equals(SysEnumsResponse.JOB)) {
            strs = new String[getJobEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getJobEntities().items) {
                strs[i++] = item.displayName;
            }
        }else if (type.equals(SysEnumsResponse.RENT)) {
            strs = new String[getRentRangeEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getRentRangeEntities().items) {
                strs[i++] = item.displayName;
            }
        }else if (type.equals(SysEnumsResponse.SCHEDULE)) {
            strs = new String[getScheduleEntities().items.size()];
            int i = 0;
            for (SysEnumsResponse.DataEntity.ItemsEntity item : getScheduleEntities().items) {
                strs[i++] = item.displayName;
            }
        }
        return strs;
    }
}
