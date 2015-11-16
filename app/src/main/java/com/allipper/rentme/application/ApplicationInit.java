package com.allipper.rentme.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.allipper.rentme.database.DbManager;
import com.allipper.rentme.net.response.EnumEntity;
import com.allipper.rentme.net.response.ItemsEntity;
import com.allipper.rentme.net.response.SysEnumsResponse;
import com.umeng.fb.push.FeedbackPush;

import io.rong.imkit.RongIM;

/**
 * Created by allipper on 2015/9/1.
 */
public class ApplicationInit extends Application {

    public static Context baseContext;

    private static EnumEntity ageEntities;
    private static EnumEntity constellationEntities;
    private static EnumEntity genderEntities;
    private static EnumEntity heightEntities;
    private static EnumEntity weightEntities;
    private static EnumEntity interestEntities;
    private static EnumEntity jobEntities;
    private static EnumEntity rentRangeEntities;
    private static EnumEntity scheduleEntities;


    @Override
    public void onCreate() {
        super.onCreate();
        baseContext = getApplicationContext();
        FeedbackPush.getInstance(this).init(false);
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

    public static EnumEntity getAgeEntities() {
        if (ageEntities == null) {
            ageEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.AGE_RANGE);
        }
        return ageEntities;
    }

    public static EnumEntity getConstellationEntities() {
        if (constellationEntities == null) {
            constellationEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .CONSTELLATION);
        }
        return constellationEntities;
    }

    public static EnumEntity getGenderEntities() {
        if (genderEntities == null) {
            genderEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.GENDER);
        }
        return genderEntities;
    }

    public static EnumEntity getHeightEntities() {
        if (heightEntities == null) {
            heightEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .HEIGHT_RANGE);
        }
        return heightEntities;
    }

    public static EnumEntity getWeightEntities() {
        if (weightEntities == null) {
            weightEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .WEIGH_RANGE);
        }
        return weightEntities;
    }

    public static EnumEntity getInterestEntities() {
        if (interestEntities == null) {
            interestEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .INTERESTS);
        }
        return interestEntities;
    }

    public static EnumEntity getJobEntities() {
        if (jobEntities == null) {
            jobEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.JOB);
        }
        return jobEntities;
    }

    public static EnumEntity getRentRangeEntities() {
        if (rentRangeEntities == null) {
            rentRangeEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse.RENT);
        }
        return rentRangeEntities;
    }

    public static EnumEntity getScheduleEntities() {
        if (scheduleEntities == null) {
            scheduleEntities = new DbManager(baseContext).queryEnumEntity(SysEnumsResponse
                    .SCHEDULE);
        }
        return scheduleEntities;
    }

    public static String[] getFormatStringArray(String type) {
        String[] strs = null;
        if (type.equals(SysEnumsResponse.AGE_RANGE)) {
            strs = new String[getAgeEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getAgeEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.CONSTELLATION)) {
            strs = new String[getConstellationEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getConstellationEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.GENDER)) {
            strs = new String[getGenderEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getGenderEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.HEIGHT_RANGE)) {
            strs = new String[getHeightEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getHeightEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.WEIGH_RANGE)) {
            strs = new String[getWeightEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getWeightEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.INTERESTS)) {
            strs = new String[getInterestEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getInterestEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.JOB)) {
            strs = new String[getJobEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getJobEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.RENT)) {
            strs = new String[getRentRangeEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getRentRangeEntities().items) {
                strs[i++] = item.displayName;
            }
        } else if (type.equals(SysEnumsResponse.SCHEDULE)) {
            strs = new String[getScheduleEntities().items.size()];
            int i = 0;
            for (ItemsEntity item : getScheduleEntities().items) {
                strs[i++] = item.displayName;
            }
        }
        return strs;
    }
}
