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
    public static final String BASE_HOST = "https://sit1.yonghuigo.com";
    public static final String BASE_URL = BASE_HOST + "/yonghuio2oocc/";
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
     * 启动页面配置
     */
    public static final String API_GET_STARTA_THEME = BASE_URL +
            "v2/yonghuio2o/pages/startup/images";
    /**
     * 支持城市列表
     */
    public static final String API_GET_SUPPORT_CITY_CODES = BASE_URL +
            "v2/yonghuio2o/yhproduct/pointofservice";

    /**
     * 用户相关模块
     */
    //匿名用户登录
    public static final String API_ANONYMITY_USER_LOGIN = BASE_URL +
            "oauth/token?client_id=%1$s&client_secret=%2$s&grant_type=%3$s";
    //用户注册
    public static final String API_USER_REGIST = BASE_URL + "v2/yonghuio2o/users";
    //用户登录
    public static final String API_USER_LOGIN = BASE_URL + "oauth/token";
    //重置用户密码
    public static final String API_USER_RESET_PASSWORD = BASE_URL +
            "v2/yonghuio2o/userstoken/changepassword";
    //修改用户信息
    public static final String API_USER_MODIFY_INFO = BASE_URL +
            "v2/yonghuio2o/userstoken/updateprofile";
    //修改用户密码
    public static final String API_USER_MODIFY_PASSWORD = BASE_URL +
            "v2/yonghuio2o/userstoken/changepassword";
    //获取用户信息
    public static final String API_USER_INFO = BASE_URL + "v2/yonghuio2o/userstoken/getuser";
    /**
     * 上传头像
     */
    public static final String API_USER_UPLOAD_HEADIMG = BASE_URL +
            "v2/yonghuio2o/users/current/upload?access_token=%1$s";

    /**
     * 商品相关模块
     */
    //获取商品分类列表
    public static final String API_PRODUCT_CATEGORY = BASE_URL +
            "v2/yonghuio2o/yhCatalogs/yonghuio2oProductCatalog/Online/categories/haitao";
    //获取商品详情
    public static final String API_PRODUCT_DETAIL = BASE_URL +
            "v2/yonghuio2o/yhproduct/products/%1$s?fields=FULL";
    //获取商品编码
    public static final String API_PRODUCT_CODE = BASE_URL +
            "v2/yonghuio2o/yhproduct/%1$s/exchange?storeCode=%2$s";
    //获取商品是否有货
    public static final String API_PRODUCT_STOCK_STATUS = BASE_URL +
            "v2/yonghuio2o/yhproduct/%1$s/stock?regionCode=%2$s" +
            "&cityCode=%3$s&districtCode=%4$s&storeCode=%5$s";
    //添加商品到购物车
    public static final String API_PRODUCT_ADD_TO_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/entries";
    //立即购买
    public static final String API_BUY_PRODUCT_NOW = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/buynow";
    //为指定商品添加评论
    public static final String API_PRODUCT_CONMMENT_ADD = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhreview/%2$s/reviews?access_token=%3$s";
    //获取指定商品评论列表
    public static final String API_PRODUCT_COMMENT_LIST = BASE_URL +
            "v2/yonghuio2o/yhproduct/%1$s/reviews?access_token=%2$s&page=%3$s&pageSize" +
            "=%4$s";
    //我的商品列表
    public static final String API_MINE_COMMENT_LIST = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhreview/customerReviews?access_token=%2$s";

    /**
     * 购物车相关模块
     */
    //获取匿名购物车GUID
    public static final String API_ANONYMITY_SHOPPING_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts";
    //获取匿名购物车中的商品
    public static final String API_ANONYMITY_CART_PRODUCT = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s?access_token=%3$s";
    //合并购物车
    public static final String API_MERG_SHOPPING_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts?access_token=%2$s";
    //获取购物车
    public static final String API_SHOPPING_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/?access_token=%2$s";
    //勾选（取消）购物车中的单个商品
    public static final String API_SELECT_CART_PRODUCT_SINGLE = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/select";
    //勾选（取消）购物车中的所有商品
    public static final String API_SELECT_CART_PRODUCT_ALL = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/selectall";
    //从购物车移除商品
    public static final String API_DELETE_CART_PRODUCT = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/entries/%3$s?access_token=%4$s";
    //更新购物车商品数量
    public static final String API_UPDATE_CART_PRODUCT_NUMBER = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/entries/%3$s";
    //购物车结算
    public static final String API_SHOPPING_CART_SETTLEMENT = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/step2?access_token=%3$s";
    //设置地址到购物车
    public static final String API_SET_ADDRESS_TO_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/setAddresses/delivery";
    //添加实名认证信息
    public static final String API_ADD_REAL_NAME = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhRealNames";
    //修改实名认证信息
    public static final String API_UPDATE_REAL_NAME = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhRealNames";
    //获取用户的实名认证信息列表
    public static final String API_GET_REAL_NAME_LIST = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhRealNames?access_token=%2$s";
    //设置实名认证信息到购物车
    public static final String API_SET_REAL_NAME_TO_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/addRealNameAuthToCart";

    /**
     * 收货人地址相关模块
     */
    //获取收货人地址列表
    public static final String API_GET_CONSIGNEE_ADDRESS_LIST = BASE_URL +
            "v2/yonghuio2o/yhAddress/addresses?access_token=%1$s";
    //新增收货人地址
    public static final String API_ADD_CONSIGNEE_ADDRESS = BASE_URL +
            "v2/yonghuio2o/yhAddress/addAddress";
    //新增收货人地址
    public static final String API_EDIT_CONSIGNEE_ADDRESS = BASE_URL +
            "v2/yonghuio2o/yhAddress/editAddress";
    //新增收货人地址
    public static final String API_DELETE_CONSIGNEE_ADDRESS = BASE_URL +
            "v2/yonghuio2o/yhAddress/deleteAddress";

    /**
     * 支付相关模块
     */
    //获取支付方式列表
    public static final String API_GET_PAYMENT_LIST = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/paymentmodes?access_token=%3$s";
    //设置支付方式到购物车
    public static final String API_SET_PAYMENT_TO_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/addpaymentmode";
    //获取订单支付数据
    public static final String API_GET_PAYINFO = BASE_URL +
            "v2/yonghuio2o/payment/order/%1$s?access_token=%2$s";

    /**
     * 订单相关模块
     */
    //创建订单
    public static final String API_CREATE_ORDER = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhorder/placeOrder";

    //获取待付款、待发货、已完成与全部订单的数量
    public static final String API_GET_ORDER_NUMBER = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhorder/ordersCount?access_token=%2$s";
    //获取订单列表
    public static final String API_GET_ORDER_LIST = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhorder/orders?access_token=%2$s&page=%3$s"
            + "&pageSize=%4$s&orderStatus=%5$s";
    //获取订单详情
    public static final String API_GET_ORDER_DETAIL = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhorder/orderDetail/%2$s?access_token=%3$s&fields=status";
    //获取退货订单详情
    public static final String API_GET_REJECT_ORDER_DETAIL = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhorder/getReturnRequestDetail/%2$s?access_token=%3$s" +
            "&returnRequestCode=%4$s";
    //获取订单尚未评论商品列表
    public static final String API_GET_ORDER_PRODUCTS_FOR_REVIEW = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhreview/productsReadyForReview?access_token=%2$s" +
            "&orderCode=%3$s";
    //获取订单支付方式列表
    public static final String API_GET_ORDER_PAYMENT_METHODS = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhorder/orders/%2$s/paymentmodes?access_token=%3$s";
    //设置订单支付方式
    public static final String API_SET_ORDER_PAYMENT_METHOD = BASE_URL +
            "v2/yonghuio2o/payment/pay/%1$s/order/%2$s?access_token=%3$s";
    //获取订单物流信息（待发货状态才有物流信息）
    public static final String API_GET_ORDER_LOGISTICS = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhorder/Consignment/%2$s?access_token=%3$s";
    //取消订单
    public static final String API_CANCEL_ORDER = BASE_URL +
            "v2/yonghuio2o/users/current/yhorder/cancelOrder?access_token=%2$s&orderCode=%3$s" +
            "&cancelReason=PULL_BACK";


    /**
     * 商品收藏相关模块
     */
    //获取订单尚未评论商品列表
    public static final String API_GET_PROCDUCT_COLLECTION = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhwishlist?access_token=%2$s&page=%3$s&pageSize=%4$s";
    //添加商品
    public static final String API_ADD_PROCDUCT_COLLECTION = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhwishlist/add/%2$s";
    //删除商品
    public static final String API_DELETE_PROCDUCT_COLLECTION = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhwishlist/remove/%2$s";
    //商品收藏状态是否已被收藏
    public static final String API_IS_PROCDUCT_COLLECTED = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhwishlist/exist/%2$s?access_token=%3$s";
    /**
     * 留言反馈相关模块
     */
    public static final String API_CALL_BACK = BASE_URL +
            "v2/yonghuio2o/yhcomment/comments";
    //第三方登录
    public static final String API_THIRD_PLATFORM_LOGIN = BASE_URL +
            "v2/yonghuio2o/users/anonymous/checkOpenIdLogin?openId=%1$s&TPToken=%2$s&TPChannel" +
            "=%3$s&client_id=" + CLIENT_ID + "&client_secret=" +
            CLIENT_SECRET + "&oauthConsumerKey=%4$s&expires_in=%5$s&refresh_token=%6$s";

    //第三方登录 绑定手机密码
    public static final String API_THIRD_PLATFORM_LOGIN_MODIFY_INFO = BASE_URL +
            "v2/yonghuio2o/userstoken/updatePasswordAndUidForOpenId?client_id=" +
            CLIENT_ID + "&client_secret=" + CLIENT_SECRET +
            "&login_id=%1$s&password=%2$s&rePassword=%3$s&access_token=%4$s";

    //获取注册和修改密码时短信
    public static final String API_GET_MESSAGE_CODE = BASE_URL + "/v2/yonghuio2o/sendDyncPwd";

    /**
     * 优惠券相关
     */
    //获取购物车优惠券信息列表
    public static final String API_GET_COUPONS = BASE_URL +
            "v2/yonghuio2o/users/current/received-vouchers?access_token=%1$s&page=%2$s";
    //添加优惠券到购物车
    public static final String API_ADD_COUPONS_TO_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/applyRedeemVoucherToCart";
    //删除购物车里的优惠券
    public static final String API_DELETE_COUPONS_FORM_CART = BASE_URL +
            "v2/yonghuio2o/users/%1$s/yhcarts/%2$s/releaseRedeemVoucherFromCart";

    /**
     * 版本更新
     */
    public static final String API_VERSION_UPDATE = BASE_URL + "v2/yonghuio2o/getNewAppVersion";
}
