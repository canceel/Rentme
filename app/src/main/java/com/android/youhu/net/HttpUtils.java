package com.android.youhu.net;

import com.android.youhu.application.ApplicationInit;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by allipper on 2015/9/1.
 */
public class HttpUtils {

    private final static String TAG = HttpUtils.class.getSimpleName();

    private static HttpUtils instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public static synchronized HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                instance = new HttpUtils();
            }
        }
        return instance;
    }

    private HttpUtils() {
        initRequstQueue();
        initImageLoader();
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private void initRequstQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ApplicationInit.baseContext);
        }
    }

    private void initImageLoader() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(requestQueue, new LruBitmapCache(ApplicationInit.baseContext));
        }
    }

    public <T> void request(Object tag, Request<T> request) {
        if (tag != null) {
            request.setTag(tag);
        } else {
            request.setTag(TAG);
        }
        requestQueue.add(request);
    }

    public void cancel(Object tag) {
        if (tag != null) {
            requestQueue.cancelAll(tag);
        } else {
            requestQueue.cancelAll(TAG);
        }
    }

    public static  void cancelAll(String tag) {
        if (tag != null) {
            getInstance().requestQueue.cancelAll(tag);
        } else {
            getInstance().requestQueue.cancelAll(TAG);
        }
    }

    /**
     * 清除网络请求的缓存
     */
    public void clearCache() {
        requestQueue.getCache().clear();
    }
}