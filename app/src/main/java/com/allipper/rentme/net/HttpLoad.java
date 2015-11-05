package com.allipper.rentme.net;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.allipper.rentme.common.util.Constant;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.request.GsonRequest;
import com.allipper.rentme.net.response.LoginResult;
import com.allipper.rentme.net.response.RefresTockenResponse;
import com.allipper.rentme.net.response.RegistResult;
import com.allipper.rentme.net.response.ResponseAppVersion;
import com.allipper.rentme.net.response.ResponseMessageBean;
import com.allipper.rentme.net.response.SysEnumsResponse;
import com.allipper.rentme.net.response.UpdateUserInforResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

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
    public static void getImage(String url, int defaultRes, ImageView
            imageView, int width, int height) {
        if (TextUtils.isEmpty(url)) {
            imageView.setImageResource(Utils.getDefaultImage(defaultRes));
            return;
        }
        HttpUtils.getInstance()
                .getImageLoader()
                .get(
                        url,
                        ImageLoader.getImageListener(imageView,
                                Utils.getDefaultImage(defaultRes),
                                Utils.getDefaultImage(defaultRes)),
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
        getImage(url, -1, imageView);
    }

    //获取图片，指定特定的默认图片，指定高宽
    public static void getImage(String url, ImageView imageView, int width,
                                int height) {
        getImage(url, -1, imageView, width, height);
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
         * @param tag
         * @param mobile
         * @param callback
         */
        public static void getMessageCode(
                String tag, String mobile,
                ResponseCallback<ResponseMessageBean> callback) {
            String url = String.format(Constant.API_USER_GET_MESSAGE_CODE, mobile);
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
            params.put("password", password);
            GsonRequest<LoginResult> request = new GsonRequest<>(
                    Request.Method.POST, Constant.API_USER_LOGIN,
                    LoginResult.class,
                    null,
                    params,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }

        /**
         * 更新用户信息
         */

        public static void updateUserInfor(
                String tag, String type,
                String value,
                String token,
                ResponseCallback<UpdateUserInforResponse> callback) {
            final Map<String, String> params = new HashMap<>();
            params.put(type, value);
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
    }
}
