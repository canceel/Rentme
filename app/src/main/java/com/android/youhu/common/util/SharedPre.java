package com.android.youhu.common.util;


/**
 *
 */
public class SharedPre {

    public static abstract class Constant {
        public static final String GUEST_USER = "guest_user";
        public static final String Third_Platform_USER = "third_platform_user";
        public static final String APP_USER = "app_user";
    }

    public static abstract class App {
        public static final String VERSION = "app_version";//版本信息
        public static final String ACCESS_TOKEN = "app_access_token";//
        public static final String ISGUIDE = "app_isguide";//
        public static final String USER_TPE = "user_type";//用户类型 ,  GUEST_USER 游客
        public static final String MESSAGE_PUSH = "message_push";//消息推送
        public static final String EXPIRES_IN = "expires_in";//过期时间
        public static final String START_IMAGE = "start_image";//启动页
        public static final String DEVICE_TOKEN = "device_token";
    }

    public static abstract class User {
        public static final String USERID = "userId";//用户帐号ID
        public static final String AVATARURL = "avatarUrl";//用户帐号ID
        public static final String MOBILE = "mobile";//用户手机号啊
        public static final String REALNAME = "realName";
        public static final String USERDETAIL = "userDetail";
        public static final String USERDETAIL_VALUE = "userDetail_value";
        public static final String NICKNAME = "nickName";
        public static final String NICKNAME_VALUE = "nickName_value";
        public static final String CONSTELLATION = "constellation";
        public static final String CONSTELLATION_VALUE = "constellation_value";
        public static final String GENDER = "gender";
        public static final String GENDER_VALUE = "gender_value";
        public static final String JOB = "job";
        public static final String JOB_VALUE = "job_value";
        public static final String AGERANGE = "ageRange";
        public static final String AGERANGE_VALUE = "ageRange_value";
        public static final String HEIGHTRANGE = "heightRange";
        public static final String HEIGHTRANGE_VALUE = "heightRange_value";
        public static final String WEIGHTRANGE = "weightRange";
        public static final String WEIGHTRANGE_VALUE = "weightRange_value";
        public static final String INTERESTS = "interests";//用户邮箱
        public static final String INTERESTS_VALUE = "interests_value";//用户邮箱
        public static final String ALBUM = "album";//用户邮箱是否验证
        public static final String BACKGROUDIMG = "backgroudimg";//
        public static final String RYACCESS_TOKEN = "ry_token";
    }

    public static abstract class Location {
        public static final String ACRONYM_NAME = "location_acronym_name"; //显示的城市
    }

}
