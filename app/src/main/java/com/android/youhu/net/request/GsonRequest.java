package com.android.youhu.net.request;

import com.android.youhu.common.Constant;
import com.android.youhu.common.util.Logger;
import com.android.youhu.net.FakeX509TrustManager;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by allipper on 2015/9/1.
 */
public class GsonRequest<T> extends Request<T> {

    private static final String TAG = GsonRequest.class.getSimpleName();
    private static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Map<String, String> body;
    private final Response.Listener<T> listener;
    private static final int SOCKET_TIMEOUT = 20 * 1000;
    private boolean notJsonResponse = false;

    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                       Map<String, String> body, Response.Listener<T> listener, Response
                               .ErrorListener errorListener) {
        super(method, url, errorListener);
        FakeX509TrustManager.allowAllSSL();
        this.clazz = clazz;
        this.headers = headers;
        this.body = body;
        this.listener = listener;
        setSocketTimeout(SOCKET_TIMEOUT);
        if (Constant.IS_DEBUG_MODE) {
            Logger.d(TAG + " URL", url);
        }
    }

    /**
     * 设置请求延迟
     *
     * @param timeOut
     */
    public void setSocketTimeout(int timeOut) {
        setRetryPolicy(new DefaultRetryPolicy(timeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (Constant.IS_DEBUG_MODE) {
            Logger.d(TAG + " HEADERS", headers != null ? headers.toString() : "NULL");
        }
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (Constant.IS_DEBUG_MODE) {
            Logger.d(TAG + " BODY", body != null ? body.toString() : "NULL");
        }
        return body != null ? body : super.getParams();
    }

    @Override
    public String getBodyContentType() {
        return CONTENT_TYPE;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, "UTF-8");
            if (Constant.IS_DEBUG_MODE) {
                Logger.d(TAG + " RESPONSE", json);
            }
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser
                    .parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    public void setNotJsonResponseFlag(boolean notJsonResponse) {
        this.notJsonResponse = notJsonResponse;
    }

}
