package com.allipper.rentme.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.allipper.rentme.common.util.Constant;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.request.GsonRequest;
import com.allipper.rentme.net.response.ResponseAppVersion;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * HTTP所有请求方法管理类
 *
 * Created by allipper on 2015/10/8.
 */

public class HttpLoad {

    //根据TAG取消网络请求
    public static void loadCancel(String tag) {
        HttpUtils.cancelAll(tag);
    }

    //获取图片，并指定默认图片的资源ID
    public static void getImage(Context context, String url, int defaultRes, ImageView
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
    public static void getImage(Context context, String url, int defaultRes, ImageView
            imageView) {
        getImage(context, url, defaultRes, imageView, imageView.getWidth(), imageView.getHeight());
    }

    //获取图片，并使用特定的默认图片
    public static void getImage(Context context, String url, ImageView imageView) {
        getImage(context, url, -1, imageView);
    }

    //获取图片，指定特定的默认图片，指定高宽
    public static void getImage(Context context, String url, ImageView imageView, int width,
                                int height) {
        getImage(context, url, -1, imageView, width, height);
    }

    public static void downloadImage(Context context, String url, final String filePath, final
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

    public static abstract class AppVersion {
        /**
         * 获取最新版本
         *
         * @param context
         * @param tag
         * @param callback
         */
        public static void getAppVersion(Context context,
                                         String tag,
                                         ResponseCallback<ResponseAppVersion> callback) {
            GsonRequest<ResponseAppVersion> request = new GsonRequest<>(
                    Request.Method.GET,
                    Constant.API_VERSION_UPDATE,
                    ResponseAppVersion.class,
                    null,
                    null,
                    callback,
                    callback);
            HttpUtils.getInstance().request(tag, request);
        }
    }
}
