package com.android.youhu.net;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.youhu.bean.RentMeResponse;
import com.android.youhu.common.util.Constant;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.request.GsonRequest;
import com.android.youhu.net.response.GetPublishInfoResponse;
import com.android.youhu.net.response.LoginResult;
import com.android.youhu.net.response.PulishInfoResponse;
import com.android.youhu.net.response.RefresTockenResponse;
import com.android.youhu.net.response.RegistResult;
import com.android.youhu.net.response.ResponseAppVersion;
import com.android.youhu.net.response.ResponseBase;
import com.android.youhu.net.response.ResponseMessageBean;
import com.android.youhu.net.response.ResponseRyToken;
import com.android.youhu.net.response.SysEnumsResponse;
import com.android.youhu.net.response.UpdateUserInforResponse;
import com.android.youhu.net.response.UserInfo;
import com.sea_monster.common.Md5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP所有请求方法管理类
 * <p/>
 * Created by allipper on 2015/10/8.
 */

public class HttpLoad {

    //根据TAG取消网络请求
    public static void loadCancel(String tag) {
        HttpUtils.cancelAll(tag);
    }

    //获取图片，并指定默认图片的资源ID
    public static void getImage(String url, final int defaultRes, final ImageView
            imageView, int width, int height) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(Utils.getDefaultImage(defaultRes));
            return;
        }
        HttpUtils.getInstance()
                .getImageLoader()
                .get(
                        url,
                        new ImageLoader.ImageListener() {
                            public void onErrorResponse(VolleyError error) {
                                int errorImageResId = Utils.getDefaultImage(defaultRes);
                                if (errorImageResId != 0) {
                                    imageView.setImageResource(errorImageResId);
                                } else {
                                    imageView.setBackgroundColor(0xffffffff);
                                }
                            }

                            public void onResponse(ImageLoader.ImageContainer response, boolean
                                    isImmediate) {
                                int defaultImageResId = Utils.getDefaultImage(defaultRes);
                                if (response.getBitmap() != null) {
                                    imageView.setImageBitmap(response.getBitmap());
                                } else if (defaultImageResId != 0) {
                                    imageView.setImageResource(defaultImageResId);
                                } else {
                                    imageView.setBackgroundColor(0xffffffff);
                                }

                            }
                        },
                        width,
                        height);

    }

    //获取图片，用控件的尺寸
    public static void getImage(String url, int defaultRes, ImageView
            imageView) {
        getImage(url, defaultRes, imageView, imageView.getWidth(), imageView.getHeight());
    }

    //获取图片，并使用特定的默认图片
    public static void getImage(String url, ImageView imageView) {
        getImage(url, 0, imageView);
    }

    //获取图片，指定特定的默认图片，指定高宽
    public static void getImage(String url, ImageView imageView, int width,
                                int height) {
        getImage(url, 0, imageView, width, height);
    }

    public static void downloadImage(String url, final String filePath, final
    ImageDownload imageDownload) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(filePath)) {
            return;
        }
        HttpUtils.getInstance().getImageLoader().get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageContainer.getBitmap() != null) {
                    File file = new File(filePath);
                    if (file.exists()) {
                        file.delete();
                    }
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        imageContainer.getBitmap().compress(Bitmap.CompressFormat.PNG, 90, out);
                        out.flush();
                        out.close();
                        imageDownload.onSuccess();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
    }

    public interface ImageDownload {
        void onSuccess();
    }

    public static String signUrl(String token, String timestamp) {
        return Utils.MD5(token + timestamp + "7f99261538576fec");
    }

    public static abstract class AppVersion {
        /**
         * 获取最新版本
         *
         * @param tag
         * @param callback
         */
        public static void getAppVersion(
                String tag,
                ResponseCallback<ResponseAppVersion> callback) {
            GsonRequest<ResponseAppVersion> request = new GsonRequest<>(
                    Request.Method.GET,
//                    Constant.API_VERSION_UPDATE,
                    "",
                    ResponseAppVersion.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }
    }

    public static abstract class SysModule {
        public static void getSysEnum(
                String tag,
                ResponseCallback<SysEnumsResponse> callback) {
            GsonRequest<SysEnumsResponse> request = new GsonRequest<>(
                    Request.Method.GET,
                    Constant.API_GET_SYS_ENUMS,
                    SysEnumsResponse.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }
    }

    public static abstract class UserModule {

        /**
         * 刷新Token
         *
         * @param tag
         * @param token
         * @param timestamp
         * @param callback
         */
        public static void getRyToken(
                String tag, String token,
                String timestamp,
                ResponseCallback<ResponseRyToken> callback) {
            String url = String.format(Constant.API_RY_TOKEN, token, timestamp, signUrl
                    (token,
                            timestamp));
            GsonRequest<ResponseRyToken> request = new GsonRequest<>(
                    Request.Method.GET, url,
                    ResponseRyToken.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * @param tag
         * @param mobile
         * @param callback
         */
        public static void getMessageCode(
                String tag, String mobile, String type,
                ResponseCallback<ResponseMessageBean> callback) {
            String url = String.format(Constant.API_USER_GET_MESSAGE_CODE, mobile, type);
            GsonRequest<ResponseMessageBean> request = new GsonRequest<>(
                    Request.Method.GET, url,
                    ResponseMessageBean.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 注册
         *
         * @param tag
         * @param mobile
         * @param password
         * @param messageCode
         * @param callback
         */
        public static void registMobile(
                String tag, String mobile,
                String password, String messageCode,
                ResponseCallback<RegistResult> callback) {
            final Map<String, String> params = new HashMap<>();
            params.put("mobile", mobile);
            params.put("password", Utils.MD5(password));
            params.put("captcha", messageCode);
            GsonRequest<RegistResult> request = new GsonRequest<>(
                    Request.Method.POST, Constant.API_USER_REGIST,
                    RegistResult.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 修改密码
         *
         * @param tag
         * @param mobile
         * @param password
         * @param messageCode
         * @param callback
         */
        public static void changePassword(
                String tag, String mobile,
                String password, String messageCode,
                ResponseCallback<RegistResult> callback) {
            final Map<String, String> params = new HashMap<>();
            params.put("mobile", mobile);
            params.put("password", Utils.MD5(password));
            params.put("captcha", messageCode);
            GsonRequest<RegistResult> request = new GsonRequest<>(
                    Request.Method.POST, Constant.API_USER_CHANGE_PWD,
                    RegistResult.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 登录
         *
         * @param tag
         * @param mobile
         * @param password
         * @param callback
         */

        public static void login(
                String tag, String mobile,
                String password,
                ResponseCallback<LoginResult> callback) {
            final Map<String, String> params = new HashMap<>();
            params.put("mobile", mobile);
            params.put("password", Md5.encode(password));
            GsonRequest<LoginResult> request = new GsonRequest<>(
                    Request.Method.POST, Constant.API_USER_LOGIN,
                    LoginResult.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        public static void deletePhoto(String tag, String url, String token,
                                       ResponseCallback<ResponseBase> callback) {
            final Map<String, String> params = new HashMap<>();
            String timestamp = String.valueOf(System.currentTimeMillis());
            params.put("albumUrl", url);
            String url1 = String.format(Constant.API_DELETE_PHOTO, token, timestamp, signUrl(token,
                    timestamp));
            GsonRequest<ResponseBase> request = new GsonRequest<>(
                    Request.Method.POST, url1,
                    ResponseBase.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 更新用户信息
         * <p/>
         * {
         * "nickName": "Air",
         * "userDetail":"我的生活,我做主!",
         * "gender":2,
         * "constellation":11,
         * "job":1,
         * "ageRange":3,
         * "heightRange":7,
         * "weighRange":6,
         * "interests":"1,2,4,6"
         * }
         */

        public static void updateUserInfor(
                String tag,
                UserInfo userInfo,
                String token,
                ResponseCallback<UpdateUserInforResponse> callback) {
            final Map<String, String> params = new HashMap<>();
            params.put("nickName", userInfo.nickNameValue);
            params.put("userDetail", userInfo.userDetailValue);
            params.put("gender", String.valueOf(userInfo.genderValue));
            params.put("constellation", String.valueOf(userInfo.constellationValue));
            params.put("job", String.valueOf(userInfo.jobValue));
            params.put("ageRange", String.valueOf(userInfo.ageRangeValue));
            params.put("heightRange", String.valueOf(userInfo.heightRangeValue));
            params.put("weighRange", String.valueOf(userInfo.weightRangeValue));
            params.put("interests", userInfo.interestsValue);
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_USER_MODIFY_INFO, token, timestamp, signUrl
                    (token,
                            timestamp));
            GsonRequest<UpdateUserInforResponse> request = new GsonRequest<>(
                    Request.Method.POST, url,
                    UpdateUserInforResponse.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 刷新Token
         *
         * @param tag
         * @param token
         * @param timestamp
         * @param callback
         */
        public static void refreshToken(
                String tag, String token,
                String timestamp,
                ResponseCallback<RefresTockenResponse> callback) {
            final Map<String, String> params = new HashMap<>();
            params.put("token", token);
            params.put("timestamp", timestamp);
            params.put("sign", signUrl(token, timestamp));
            GsonRequest<RefresTockenResponse> request = new GsonRequest<>(
                    Request.Method.POST, Constant.API_REFRESH_TOKEN,
                    RefresTockenResponse.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 发布信息
         */

        public static void publishInfo(
                String tag, String rentRange,
                String schedule,
                String perHourPrice,
                String token,
                ResponseCallback<ResponseBase> callback) {
            final Map<String, String> params = new HashMap<>();
            params.put("rentRange", rentRange);
            params.put("schedule", schedule);
            params.put("perHourPrice", perHourPrice);
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_PUBLISH_INFO, token, timestamp, signUrl
                    (token,
                            timestamp));
            GsonRequest<ResponseBase> request = new GsonRequest<>(
                    Request.Method.POST, url,
                    ResponseBase.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 取消信息
         */

        public static void cancelInfo(
                String tag,
                String token,
                ResponseCallback<ResponseBase> callback) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_CANCEL_INFO, token, timestamp, signUrl
                    (token,
                            timestamp));
            GsonRequest<ResponseBase> request = new GsonRequest<>(
                    Request.Method.POST, url,
                    ResponseBase.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 获取信息
         */

        public static void getPublishInfo(
                String tag,
                String token,
                ResponseCallback<GetPublishInfoResponse> callback) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_GET_PUBLISH_INFO, token, timestamp, signUrl
                    (token,
                            timestamp));
            GsonRequest<GetPublishInfoResponse> request = new GsonRequest<>(
                    Request.Method.POST, url,
                    GetPublishInfoResponse.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

    }

    public abstract static class Order {

        /**
         * 创建订单
         */
        public static void createOrder(
                String tag,
                String token,
                String providerUserId,
                String targetUserId,
                String perHourPrice,
                String meetTime,
                String meetAddress,
                String totalPrice,
                ResponseCallback<ResponseBase> callback) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_ORDER_CREATE, token, timestamp, signUrl
                    (token,
                            timestamp));
            final Map<String, String> params = new HashMap<>();
            params.put("providerUserId", providerUserId);
            params.put("targetUserId", targetUserId);
            params.put("perHourPrice", perHourPrice);
            params.put("meetTime", meetTime);
            params.put("meetAddress", meetAddress);
            params.put("totalPrice", totalPrice);
            GsonRequest<ResponseBase> request = new GsonRequest<>(
                    Request.Method.POST, url,
                    ResponseBase.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 获取我租到的
         *
         * @param tag
         * @param token
         * @param pageIndex
         * @param pageSize
         */
        public static void getMineRent(String tag,
                                       String token,
                                       String pageIndex,
                                       String pageSize, ResponseCallback<RentMeResponse> callback) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_ORDER_GET_MINE_RENT, token, timestamp, signUrl
                    (token,
                            timestamp), pageIndex, pageSize);
            GsonRequest<RentMeResponse> request = new GsonRequest<>(
                    Request.Method.GET, url,
                    RentMeResponse.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 获取我租到的
         *
         * @param tag
         * @param token
         * @param pageIndex
         * @param pageSize
         */
        public static void getRentMine(String tag,
                                       String token,
                                       String pageIndex,
                                       String pageSize, ResponseCallback<RentMeResponse> callback) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_ORDER_GET_RENT_MINE, token, timestamp, signUrl
                    (token,
                            timestamp), pageIndex, pageSize);
            GsonRequest<RentMeResponse> request = new GsonRequest<>(
                    Request.Method.GET, url,
                    RentMeResponse.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 处理订单
         *
         * @param tag
         * @param token
         * @param orderId
         * @param status
         */
        public static void processOrder(String tag,
                                        String token,
                                        String orderId,
                                        String status, ResponseCallback<ResponseBase> callback) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String url = String.format(Constant.API_ORDER_PROCESS, token, timestamp, signUrl
                    (token,                            timestamp));
            final Map<String, String> params = new HashMap<>();
            params.put("orderId", orderId);
            params.put("status", status);
            GsonRequest<ResponseBase> request = new GsonRequest<>(
                    Request.Method.POST, url,
                    ResponseBase.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }
    }

    public abstract static class HomePage {

        public static void getHomepage(String tag,
                                       String pageIndex,
                                       String pageSize,
                                       String param,
                                       ResponseCallback<PulishInfoResponse>
                                               callback) {
            String url = String.format(Constant.API_HOME_GET_RENT, pageIndex, pageSize, param);
            GsonRequest<PulishInfoResponse> request = new GsonRequest<>(
                    Request.Method.GET, url,
                    PulishInfoResponse.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }
    }
}
