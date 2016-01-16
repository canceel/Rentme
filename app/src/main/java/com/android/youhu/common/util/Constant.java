package com.android.youhu.common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/7/8.
 */
public class Constant {

    private static final String CONFIG_PATH = "test.json";
    private static boolean  IS_TEST_URL = false;
    private static String TEST_BASE_HOST;

    static {
        File file = CropUtils.getOrCreateFileInExternalStorage();
        String testStr = file.getAbsolutePath() + File.separator + CONFIG_PATH;
        File testFile = new File(testStr);
        if (testFile != null && testFile.exists()) {
            try {
                ConfigBean bean = new Gson().fromJson(new BufferedReader(new InputStreamReader
                        (new FileInputStream(testFile))), new TypeToken<ConfigBean>() {
                }.getType());
                if (bean != null) {
                    TEST_BASE_HOST = "http://" + bean.baseUrl;
                    IS_TEST_URL = true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试环境的参数配置
     */
    //系统日志Debug模式开关，默认打开，可以打印日志，发布时设置为false
    public static final boolean IS_DEBUG_MODE = true;
    //测试用，后续改掉:分配至各渠道的唯一ID
    public static final String CLIENT_ID = "mobile_android";



    //测试环境
    public static String BASE_HOST = IS_TEST_URL ? TEST_BASE_HOST: "http://www.zu5ba.com";
    public static final String BASE_URL = BASE_HOST + "/api/";



    /**
     * 首页相关模块
     */
    //获取可配置页面信息
    public static final String API_GET_INDEX_THEME = BASE_URL +
            "v2/yonghuio2o/pages/%1$s?userId=%2$s";


    /**
     * 用户相关模块
     */
    //获取验证码
    public static final String API_USER_GET_MESSAGE_CODE = BASE_URL +
            "account/getcaptcha.json?mobile=%1$s&type=%2$s";
    /**
     * 上传头像
     */
    public static final String API_USER_UPLOAD_HEADIMG = BASE_URL +
            "account/uploadavatar.json?token=%1$s&timestamp=%2$s&sign=%3$s";
    /**
     * 上传背景
     */
    public static final String API_USER_UPLOAD_BACKGROUD = BASE_URL +
            "account/uploadbackgroud.json?token=%1$s&timestamp=%2$s&sign=%3$s";

    /**
     * 上传相册
     */
    public static final String API_USER_UPLOAD_PICTURE = BASE_URL +
            "account/uploadalbum.json?token=%1$s&timestamp=%2$s&sign=%3$s";
    //用户注册
    public static final String API_USER_REGIST = BASE_URL + "account/register.json";
    //修改密码
    public static final String API_USER_CHANGE_PWD = BASE_URL + "account/resetpassword.json";
    //用户登录
    public static final String API_USER_LOGIN = BASE_URL + "account/login.json";
    //刷新token
    public static final String API_REFRESH_TOKEN = BASE_URL + "account/gettoken.json";
    //刷新token
    public static final String API_RY_TOKEN = BASE_URL + "account/getrctoken" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s";
    //修改用户信息
    public static final String API_USER_MODIFY_INFO = BASE_URL +
            "account/update.json?token=%1$s&timestamp=%2$s&sign=%3$s";
    //修改用户密码
    public static final String API_USER_MODIFY_PASSWORD = BASE_URL +
            "v2/yonghuio2o/userstoken/changepassword";
    //获取用户信息
    public static final String API_USER_INFO = BASE_URL + "v2/yonghuio2o/userstoken/getuser";
    //发布信息
    public static final String API_PUBLISH_INFO = BASE_URL + "rentinfo/release" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s";
    //取消发布信息
    public static final String API_CANCEL_INFO = BASE_URL + "rentinfo/unrelease" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s";
    //获取发布信息
    public static final String API_GET_PUBLISH_INFO = BASE_URL + "rentinfo/getinfo" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s";

    // 获取系统的枚举配置项
    public static final String API_GET_SYS_ENUMS = BASE_URL + "common/getsysenums.json";

    // 创建订单
    public static final String API_ORDER_CREATE = BASE_URL + "orderInfo/create" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s";
    // 查询我租到的
    public static final String API_ORDER_GET_MINE_RENT = BASE_URL + "orderInfo/myTarget" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s&pageIndex=%4$s&pageSize=%5$s";
    // 查询租到我的
    public static final String API_ORDER_GET_RENT_MINE = BASE_URL + "orderInfo/myProvider" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s&pageIndex=%4$s&pageSize=%5$s";
    // 处理订单
    public static final String API_ORDER_PROCESS = BASE_URL + "orderInfo/ChangeOrder" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s";

    // 首页
    public static final String API_HOME_GET_RENT = BASE_URL + "rentInfo/getRentList" +
            ".json?pageIndex=%1$s&pageSize=%2$s%3$s";
    // 删除用户相册
    public static final String API_DELETE_PHOTO = BASE_URL + "Account/DeleteAlbum" +
            ".json?token=%1$s&timestamp=%2$s&sign=%3$s";
}
