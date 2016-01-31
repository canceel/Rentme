package com.android.youhu.common.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.youhu.R;
import com.android.youhu.net.response.LoginResult;
import com.android.youhu.net.response.UserInfo;
import com.android.youhu.net.response.UserInfoEntity;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
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
     * 保存登录的信息
     *
     * @param context
     * @param dataEntity
     */
    public static void saveLoginResult(Context context, LoginResult.DataEntity dataEntity) {
        SharedPreUtils.putString(context, SharedPre.App.ACCESS_TOKEN, dataEntity.token);
        saveUserInfor(context, dataEntity.userInfo);
    }

    private static <T> void saveListData(Context context, List<T> data, String name) {
        if (data != null && data.size() > 0) {
            StringBuffer album = new StringBuffer();
            for (T str : data
                    ) {
                album.append(";").append(str);
            }
            if (album.length() > 0) {
                SharedPreUtils.putString(context, name, album.deleteCharAt(0)
                        .toString());
            }
        }
    }

    private static <T> List<T> getListData(Context context, String name) {
        String str = SharedPreUtils.getString(context, name);
        if (!TextUtils.isEmpty(str)) {
            String[] strs = str.split(",");
            return (List<T>) Arrays.asList(strs);
        }
        return null;
    }


    /**
     * 获取用户信息
     *
     * @param context
     */
    public static UserInfo getUserInfo(Context context) {
        if (SharedPreUtils.getInt(context, SharedPre.User.USERID, 0) == 0) {
            return null;
        }
        UserInfo customer = new UserInfo();
        customer.album = getListData(context, SharedPre.User.ALBUM);
        customer.interests = SharedPreUtils.getString(context, SharedPre.User.INTERESTS);
        customer.backgroudImage = SharedPreUtils.getString(context, SharedPre.User.BACKGROUDIMG);
        customer.interestsValue = SharedPreUtils.getString(context, SharedPre.User.INTERESTS_VALUE);
        customer.ageRange = SharedPreUtils.getString(context, SharedPre.User.AGERANGE);
        customer.ageRangeValue = SharedPreUtils.getInt(context, SharedPre.User.AGERANGE_VALUE, 0);
        customer.constellation = SharedPreUtils.getString(context, SharedPre.User.CONSTELLATION);
        customer.constellationValue = SharedPreUtils.getInt(context, SharedPre.User
                .CONSTELLATION_VALUE, 0);
        customer.heightRange = SharedPreUtils.getString(context, SharedPre.User.HEIGHTRANGE);
        customer.heightRangeValue = SharedPreUtils.getInt(context, SharedPre.User
                .HEIGHTRANGE_VALUE, 0);
        customer.nickName = SharedPreUtils.getString(context, SharedPre.User.NICKNAME);
        customer.nickNameValue = SharedPreUtils.getString(context, SharedPre.User.NICKNAME_VALUE);
        customer.realName = SharedPreUtils.getString(context, SharedPre.User.REALNAME);
        customer.userDetail = SharedPreUtils.getString(context, SharedPre.User.USERDETAIL);
        customer.userDetailValue = SharedPreUtils.getString(context, SharedPre.User
                .USERDETAIL_VALUE);
        customer.weightRange = SharedPreUtils.getString(context, SharedPre.User.WEIGHTRANGE);
        customer.weightRangeValue = SharedPreUtils.getInt(context, SharedPre.User
                .WEIGHTRANGE_VALUE, 0);
        customer.gender = SharedPreUtils.getString(context, SharedPre.User.GENDER);
        customer.genderValue = SharedPreUtils.getInt(context, SharedPre.User.GENDER_VALUE, 0);
        customer.mobile = SharedPreUtils.getString(context, SharedPre.User.MOBILE);
        customer.job = SharedPreUtils.getString(context, SharedPre.User.JOB);
        customer.jobValue = SharedPreUtils.getInt(context, SharedPre.User.JOB_VALUE, 0);
        customer.avatarUrl = SharedPreUtils.getString(context, SharedPre.User.AVATARURL);
        customer.isAuth = SharedPreUtils.getInt(context, SharedPre.User.IS_AUTH, 0);
        return customer;
    }

    /**
     * 用户信息不完整
     *
     * @param userInfo
     * @return
     */

    public static boolean isUserInfoNoneComplete(UserInfo userInfo) {
        boolean result = TextUtils.isEmpty(userInfo.nickNameValue)
                || (userInfo.constellationValue == 0)
                || TextUtils.isEmpty(userInfo.userDetailValue)
                || (userInfo.jobValue == 0)
                || (userInfo.genderValue == 0)
                || (userInfo.ageRangeValue == 0)
                || (userInfo.heightRangeValue == 0)
                || (userInfo.weightRangeValue == 0)
                || TextUtils.isEmpty(userInfo.interestsValue);
        return result;
    }


    //清除SP中用户的信息
    public static void cleanUserInfo(Context context) {
        SharedPreUtils.removeSharedKey(context, SharedPre.User.ALBUM);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.INTERESTS);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.INTERESTS_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.AGERANGE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.AGERANGE_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.CONSTELLATION);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.CONSTELLATION_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.HEIGHTRANGE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.HEIGHTRANGE_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.NICKNAME);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.NICKNAME_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.REALNAME);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.USERDETAIL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.USERDETAIL_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.WEIGHTRANGE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.WEIGHTRANGE_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.GENDER);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.GENDER_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.MOBILE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.JOB);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.IS_AUTH);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.BACKGROUDIMG);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.JOB_VALUE);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.USERID);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.AVATARURL);
        SharedPreUtils.removeSharedKey(context, SharedPre.User.RYACCESS_TOKEN);
        SharedPreUtils.removeSharedKey(context, SharedPre.App.ACCESS_TOKEN);
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
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
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
     * @param pxValue （DisplayMetrics类中属性density）
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
     * @param dipValue （DisplayMetrics类中属性density）
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
     * @param pxValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
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
            return R.mipmap.pic_dir;
        } else {
            return resId;
        }
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^0?(13[0-9]|15[0-35-9]|18[0236789]|14[57])[0-9]{8}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                'E', 'F'};
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

    public static String getToken(Context context) {
        return SharedPreUtils.getString(context, SharedPre.App.ACCESS_TOKEN);
    }

    public static void saveUserInfor(Context context, UserInfoEntity userInfoEntity) {
        UserInfo userInfo = userInfoEntity.entityToInfo();
        SharedPreUtils.putString(context, SharedPre.User.AGERANGE, userInfo.ageRange);
        SharedPreUtils.putString(context, SharedPre.User.BACKGROUDIMG, userInfo.backgroudImage);
        SharedPreUtils.putInt(context, SharedPre.User.AGERANGE_VALUE, userInfo.ageRangeValue);
        saveListData(context, userInfoEntity.album, SharedPre.User.ALBUM);
        SharedPreUtils.putString(context, SharedPre.User.CONSTELLATION, userInfo.constellation);
        SharedPreUtils.putInt(context, SharedPre.User.CONSTELLATION_VALUE, userInfo
                .constellationValue);
        SharedPreUtils.putInt(context, SharedPre.User.IS_AUTH, userInfo
                .isAuth);
        SharedPreUtils.putString(context, SharedPre.User.GENDER, userInfo.gender);
        SharedPreUtils.putInt(context, SharedPre.User.GENDER_VALUE, userInfo.genderValue);
        SharedPreUtils.putString(context, SharedPre.User.HEIGHTRANGE, userInfo.heightRange);
        SharedPreUtils.putInt(context, SharedPre.User.HEIGHTRANGE_VALUE, userInfo.heightRangeValue);
        SharedPreUtils.putString(context, SharedPre.User.WEIGHTRANGE, userInfo.weightRange);
        SharedPreUtils.putInt(context, SharedPre.User.WEIGHTRANGE_VALUE, userInfo.weightRangeValue);
        SharedPreUtils.putString(context, SharedPre.User.INTERESTS, userInfo.interests);
        SharedPreUtils.putString(context, SharedPre.User.INTERESTS_VALUE, userInfo.interestsValue);
        SharedPreUtils.putString(context, SharedPre.User.JOB, userInfo.job);
        SharedPreUtils.putInt(context, SharedPre.User.JOB_VALUE, userInfo.jobValue);
        SharedPreUtils.putString(context, SharedPre.User.MOBILE, userInfo.mobile);
        SharedPreUtils.putString(context, SharedPre.User.NICKNAME, userInfo.nickName);
        SharedPreUtils.putString(context, SharedPre.User.NICKNAME_VALUE, userInfo.nickNameValue);
        SharedPreUtils.putString(context, SharedPre.User.REALNAME, userInfo.realName);
        SharedPreUtils.putString(context, SharedPre.User.USERDETAIL, userInfo.userDetail);
        SharedPreUtils.putString(context, SharedPre.User.USERDETAIL_VALUE, userInfo
                .userDetailValue);
        SharedPreUtils.putString(context, SharedPre.User.AVATARURL, userInfo.avatarUrl);
        SharedPreUtils.putInt(context, SharedPre.User.USERID, userInfo.userId);
    }
}
