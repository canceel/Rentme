package com.allipper.rentme.net;

import com.allipper.rentme.common.util.Constant;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.request.AndroidMultiPartEntity;
import com.allipper.rentme.net.request.GsonRequest;
import com.allipper.rentme.net.response.UploadResult;
import com.allipper.rentme.net.upload.UploadFileRequest;

import java.io.File;

/**
 * Created by allipper on 2015/9/7.
 */
public class HttpUpload {
    //根据TAG取消网络请求
    public static void loadCancel(String tag) {
        HttpUtils.cancelAll(tag);
    }

    public static String signUrl(String token, String timestamp) {
        return Utils.MD5(token + timestamp + "7f99261538576fec");
    }

    /**
     * 上传用户头像
     *
     * @param tag
     * @param file
     * @param token
     * @param callback
     * @param listener
     */
    public static void uploadUserHeadImg(String tag, File file, String token,
                                         ResponseCallback<UploadResult> callback,
                                         AndroidMultiPartEntity.ProgressListener listener) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String url = String.format(Constant.API_USER_UPLOAD_HEADIMG, token, timestamp, signUrl(token,
                timestamp));
        UploadFileRequest request = new UploadFileRequest(GsonRequest.Method.POST, tag, url,
                file, UploadResult.class, callback, callback, listener);
        HttpUtils.getInstance().request(tag, request);
    }
}
