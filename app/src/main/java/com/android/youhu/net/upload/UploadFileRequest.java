package com.android.youhu.net.upload;

import com.android.youhu.common.util.FileUtils;
import com.android.youhu.net.request.AndroidMultiPartEntity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.content.FileBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 上传文件
 * Created by allipper on 2015/9/7.
 */
public class UploadFileRequest<T> extends Request<T> {
    private final String TAG = UploadFileRequest.class.getCanonicalName();
    private final String mTag;
    private AndroidMultiPartEntity entity;
    private final Response.ErrorListener mErrorListener;
    private final Response.Listener mListener;
    private AndroidMultiPartEntity.ProgressListener progressListener;
    private final File mImageFile;
    private final Class<T> clz;
    private static final String FILE_PART_NAME = "file";
    private final static int TIME_OUT = 2 * 60 * 1000;

    public UploadFileRequest(int method, String tag, String url, File imageFile, Class<T> clz,
                             Response.Listener listener,
                             Response.ErrorListener errorListener,
                             AndroidMultiPartEntity.ProgressListener progressListener) {
        super(method, url, errorListener);
//        FakeX509TrustManager.allowAllSSL();
        this.mTag = tag;
        this.mErrorListener = errorListener;
        this.mListener = listener;
        this.mImageFile = imageFile;
        this.progressListener = progressListener;
        this.clz = clz;
        buildMutipartEntity();
        setSocketTimeout(TIME_OUT);
    }

    public void setSocketTimeout(int timeOut) {
        setRetryPolicy(new DefaultRetryPolicy(timeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    private void buildMutipartEntity() {
        if (mImageFile == null || !mImageFile.exists()) {
            return;
        }
        FileBody fileBody = new FileBody(mImageFile, "image/" + FileUtils.getFileType(mImageFile));
        entity = new AndroidMultiPartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, null,
                progressListener);
        entity.addPart(FILE_PART_NAME, fileBody);
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            Gson gson = new Gson();
            String json = new String(response.data, "UTF-8");
            return Response.success(gson.fromJson(json, clz), HttpHeaderParser
                    .parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    @Override
    public void deliverError(VolleyError error) {
        mErrorListener.onErrorResponse(error);
    }

    @Override
    public String getBodyContentType() {
        String contentTypeHeader = entity.getContentType().getValue();
        return contentTypeHeader;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream bos, building the multipart" +
                    " request.");
        }

        return bos.toByteArray();
    }


}
