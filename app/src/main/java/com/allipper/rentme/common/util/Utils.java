package com.allipper.rentme.common.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.Toast;

import com.allipper.rentme.R;
import com.allipper.rentme.bean.Customer;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2015/7/8.
 */
public class Utils {

    /**
     * 手机网络检测
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return true;
            }
        }
        ToastUtils.show(context, "请检查您的网络连接！");
        return false;
    }

    //拨打电话
    public static void callPhone(Context context, String number) {
        if (number == null)
            return;
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + number));
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(context, "您的设备不支持拨打电话！", Toast.LENGTH_SHORT).show();


        }
    }

    /**
     * 保存应用账号
     *
     * @param context
     * @param account 登录账户（昵称、手机号码）
     */
    public static void saveAppInfo(Context context, String account) {
        SharedPreUtils.putString(context, SharedPre.App.ACCOUNT, account);
    }


    /**
     * 保存用户信息
     *
     * @param context
     * @param customer
     */
    public static void saveUserInfo(Context context, Customer customer) {
        SharedPreUtils.putString(context, SharedPre.User.AVATAR_URL, customer.avatar_url);
        SharedPreUtils.putString(context, SharedPre.User.ALIAS, customer.alias);
        SharedPreUtils.putString(context, SharedPre.User.ACCOUNT_UID, customer.account_uid);
        SharedPreUtils.putString(context, SharedPre.User.EMAIL, customer.email);
        SharedPreUtils.putString(context, SharedPre.User.GENDER, customer.gender);
        SharedPreUtils.putString(context, SharedPre.User.ID_NUMBER, customer.idnumber);
        SharedPreUtils.putString(context, SharedPre.User.ID_TYPE, customer.idtype);
        SharedPreUtils.putString(context, SharedPre.User.IS_CHECK_MAIL, customer.ischeckmail);
        SharedPreUtils.putString(context, SharedPre.User.IS_CHECK_MOBILE, customer.ischeckmobile);
        SharedPreUtils.putString(context, SharedPre.User.IS_CHECKID, customer.ischeckid);
        SharedPreUtils.putString(context, SharedPre.User.MEMBER_ID, customer.member_id);
        SharedPreUtils.putString(context, SharedPre.User.MEMBER_LEVEL, customer.member_level);
        SharedPreUtils.putString(context, SharedPre.User.MEMBER_STATUS, customer.member_status);
        SharedPreUtils.putString(context, SharedPre.User.UPDATE_TIME, customer.update_time);
        SharedPreUtils.putString(context, SharedPre.User.MOBILE, customer.mobile);
        SharedPreUtils.putString(context, SharedPre.User.NAME, customer.name);
    }


    /**
     * 获取用户信息
     *
     * @param context
     */
    public static Customer getUserInfo(Context context) {
        if (TextUtils.isEmpty(SharedPreUtils.getString(context, SharedPre.User.ACCOUNT_UID))) {
            return null;
        }
        Customer customer = new Customer();
        customer.avatar_url = SharedPreUtils.getString(context, SharedPre.User.AVATAR_URL);
        customer.alias = SharedPreUtils.getString(context, SharedPre.User.ALIAS);
        customer.account_uid = SharedPreUtils.getString(context, SharedPre.User.ACCOUNT_UID);
        customer.email = SharedPreUtils.getString(context, SharedPre.User.EMAIL);
        customer.gender = SharedPreUtils.getString(context, SharedPre.User.GENDER);
        customer.idnumber = SharedPreUtils.getString(context, SharedPre.User.ID_NUMBER);
        customer.idtype = SharedPreUtils.getString(context, SharedPre.User.ID_TYPE);
        customer.ischeckmail = SharedPreUtils.getString(context, SharedPre.User.IS_CHECK_MAIL);
        customer.ischeckmobile = SharedPreUtils.getString(context, SharedPre.User.IS_CHECK_MOBILE);
        customer.ischeckid = SharedPreUtils.getString(context, SharedPre.User.IS_CHECKID);
        customer.member_id = SharedPreUtils.getString(context, SharedPre.User.MEMBER_ID);
        customer.member_level = SharedPreUtils.getString(context, SharedPre.User.MEMBER_LEVEL);
        customer.member_status = SharedPreUtils.getString(context, SharedPre.User.MEMBER_STATUS);
        customer.update_time = SharedPreUtils.getString(context, SharedPre.User.UPDATE_TIME);
        customer.mobile = SharedPreUtils.getString(context, SharedPre.User.MOBILE);
        customer.name = SharedPreUtils.getString(context, SharedPre.User.NAME);
        return customer;
    }


    //清除SP中用户的信息
    public static void cleanUserInfo(Context context) {
        SharedPreUtils.removeSharedKey(context, SharedPre.User.AVATAR_URL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.ALIAS);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.ACCOUNT_UID);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.EMAIL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.GENDER);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.ID_TYPE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.IS_CHECK_MAIL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.IS_CHECK_MOBILE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.IS_CHECKID);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.MEMBER_ID);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.MEMBER_LEVEL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.MEMBER_STATUS);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.UPDATE_TIME);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.MOBILE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.NAME);
    }


    //检查身份证ID是否符合规则
    public static boolean checkIdcard(Context context, String value) {
        if (TextUtils.isEmpty(value)) {
            ToastUtils.show(context, "身份证不能为空");
            return false;
        }
        if (value.length() != 15 && value.length() != 18) {
            ToastUtils.show(context, "身份证位数必须是15位或者18位");
            return false;
        }
        return true;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }


        /**
         * 保存当前的位置信息
         *
         * @param context
         * @param acronymName
         */
    public static void saveLocationCode(Context context, String acronymName) {
        SharedPreUtils.putString(context, SharedPre.Location.ACRONYM_NAME, acronymName);
    }

    /**
     * 获取手机屏幕的高宽
     *
     * @param context
     * @return
     */
    public static Point getScreenPoint(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }


    public static boolean isGuestUser(Context context) {
        return TextUtils.isEmpty(SharedPreUtils.getString(context, SharedPre.App.USER_TPE));
//        return (TextUtils.isEmpty(SharedPreUtils.getString(context, SharedPre.App.USER_TPE)) ||
//                SharedPre.Constant.GUEST_USER.equals(SharedPreUtils.getString(context, SharedPre
//                        .App.USER_TPE)));
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * @param pxValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @param dipValue
     *            （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @param pxValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }


    /**
     * 获取不同尺寸的默认图片
     *
     * @param resId 资源ID
     * @return
     */
    public static int getDefaultImage(int resId) {
        if (resId == -1) {
            return R.mipmap.icon_image_default;
        } else {
            return resId;
        }
    }

    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    /**
     * 电话号码验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null,p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if(str.length() >9)
        {	m = p1.matcher(str);
            b = m.matches();
        }else{
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
