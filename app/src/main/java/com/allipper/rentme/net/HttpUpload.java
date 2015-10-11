package com.allipper.rentme.net;

import android.content.Context;

import com.allipper.rentme.common.util.Constant;
import com.allipper.rentme.net.request.GsonRequest;
import com.allipper.rentme.net.response.UploadResult;

import java.io.File;

/**
 * Created by allipper on 2015/9/7.
 */
public class HttpUpload {
    //根据TAG取消网络请求
    public static void loadCancel(String tag) {
        HttpUtils.cancelAll(tag);
    }

    /**
     * 上传用户头像
     * @param context
     * @param tag
     * @param file
     * @param accessToken
     * @param callback
     * @param listener
     */
    public static void uploadUserHeadImg(Context context, String tag, File file, String accessToken,
                                         ResponseCallback<UploadResult> callback,
                                         AndroidMultiPartEntity.ProgressListener listener) {
        final String url = String.format(Constant.API_USER_UPLOAD_HEADIMG, accessToken);
        UploadFileRequest request = new UploadFileRequest(GsonRequest.Method.POST, tag, url,
                file, UploadResult.class, callback, callback, listener);
        HttpUtils.getInstance().request(tag, request);
    }
}
