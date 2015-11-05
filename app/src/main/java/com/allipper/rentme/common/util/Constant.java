package com.allipper.rentme.common.util;

/**
 * Created by Administrator on 2015/7/8.
 */
public class Constant {

    /**
     * 正式环境的参数配置
     */
//    //系统日志Debug模式开关，默认打开，可以打印日志，发布时设置为false
//    public static final boolean IS_DEBUG_MODE = false;
//    //测试用，后续改掉:分配至各渠道的唯一ID
//    public static final String CLIENT_ID = "mobile_android";
//    //测试用，后续改掉
//    public static final String CLIENT_SECRET = "a34Eb90e";
//    //匿名用户的默认ID
//    public static final String DEFUALT_USER_ID = "anonymous";

    /**
     * 测试环境的参数配置
     */
    //系统日志Debug模式开关，默认打开，可以打印日志，发布时设置为false
    public static final boolean IS_DEBUG_MODE = true;
    //测试用，后续改掉:分配至各渠道的唯一ID
    public static final String CLIENT_ID = "mobile_android";
    //    //测试用，后续改掉
//    public static final String CLIENT_SECRET = "a34Eb90e";
    //    //测试用，后续改掉:分配至各渠道的唯一ID
//    public static final String CLIENT_ID = "weixin_client";
    //测试用，后续改掉
    public static final String CLIENT_SECRET = "secret";
    //匿名用户的默认ID
    public static final String DEFUALT_USER_ID = "anonymous";

    //第三方登录 平台 传递参数
    public static final String THRID_PLATFORM_TPChannel_WX = "WEIXIN";
    //第三方登录
    public static final String THRID_PLATFORM_TPChannel_QQ = "QQ";
    //默认地区
    public static final String DEFAULT_LOCATION = "福州市";


    /**
     * 微信APP_ID与APP_SECRET
     */
    public static final String WX_APP_ID = "wxc177c1b1fe609442";
    public static final String WX_APP_SECRET = "002f79cac3e5f81819e367773f4c9e1c";
    //商户号
    public static final String WX_MCH_ID = "1261927201";
    //  API密钥，在商户平台设置
    public static final String WX_API_KEY = "q7D5HcmDy8IJXvE6dmyvqdBZqDGnMIqL";

    /**
     * QQ的APP_ID与APP_KEY
     */
    public static final String QQ_APP_ID = "1104722171";
    public static final String QQ_APP_KEY = "zj0islgVrwWws6zF";

    /**
     * 连连支付Referer
     */
    public static final String LIANLIAN_REFERER = "http://g.yonghuigo.com/";

    /**
     * 友盟
     */
    public static final String UMENG_APP_KEY = "55dbe7f867e58e5585002161";


    /**
     * 网络请求API
     */
    //测试环境
    public static final String BASE_HOST = "http://www.zu5ba.com";
    public static final String BASE_URL = BASE_HOST + "/api/";
//    public static final String BASE_URL = "https://10.1.6.205:9002/yonghuio2oocc/";
    public static final String EDECA_URL = "http://10.0.140.103:8006/yonghuio2o-assembler/json";
    public static final String SHARE_HOST = "http://sit1.yonghuigo.com";

    //正式环境
//    public static final String BASE_HOST = "https://occ.yonghuigo.com";
//    public static final String BASE_URL = BASE_HOST + "/yonghuio2oocc/";
//    public static final String EDECA_URL = "http://sm.yonghuigo.com/yonghuio2o-assembler/json";
//    public static final String SHARE_HOST = "http://occ.yonghuigo.com/";

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
            "account/getregcaptcha.json?mobile=%1$s";
    /**
     * 上传头像
     */
    public static final String API_USER_UPLOAD_HEADIMG = BASE_URL +
            "account/uploadavatar.json?token=%1$s&timestamp=%2$s&sign=%3$s";
    //用户注册
    public static final String API_USER_REGIST = BASE_URL + "account/register.json";
    //用户登录
    public static final String API_USER_LOGIN = BASE_URL + "account/login.json";
    //刷新token
    public static final String API_REFRESH_TOKEN = BASE_URL + "account/gettoken.json";
    //重置用户密码
    public static final String API_USER_RESET_PASSWORD = BASE_URL +
            "v2/yonghuio2o/userstoken/changepassword";
    //修改用户信息
    public static final String API_USER_MODIFY_INFO = BASE_URL +
            "account/update.json?token=%1$s&timestamp=%2$s&sign=%3$s";
    //修改用户密码
    public static final String API_USER_MODIFY_PASSWORD = BASE_URL +
            "v2/yonghuio2o/userstoken/changepassword";
    //获取用户信息
    public static final String API_USER_INFO = BASE_URL + "v2/yonghuio2o/userstoken/getuser";


    // 获取系统的枚举配置项
    public static final String API_GET_SYS_ENUMS = BASE_URL + "common/getsysenums.json";
}
