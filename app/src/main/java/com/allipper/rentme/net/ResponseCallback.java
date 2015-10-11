package com.allipper.rentme.net;

import android.content.Context;

import com.allipper.rentme.common.Constant;
import com.allipper.rentme.common.util.Logger;
import com.allipper.rentme.net.response.ResponseBase;
import com.android.volley.Response;
import com.android.volley.VolleyError;



/**
 * Created by zoulinlin123 on 15/7/15.
 */
public abstract class ResponseCallback<E> implements Response.ErrorListener, Response.Listener<E> {
    private static final String TAG = ResponseCallback.class.getSimpleName();
    private Context context;

    public ResponseCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {
        onReuquestFailed(context.getText(ErrorHelper.getMessage(volleyError)).toString());
        if (Constant.IS_DEBUG_MODE) {
            Logger.d(TAG + " ERROR", context.getText(ErrorHelper.getMessage(volleyError))
                    .toString());
        }
    }

    @Override
    public void onResponse(E result) {
        if (result == null) {
            onReuquestFailed("没有数据返回");
            return;
        }
        if (result instanceof ResponseBase) {
            //处理用户相关返回体
            ResponseBase userResultBase = (ResponseBase) result;
            if ("20000".equals(userResultBase.status) || "10000".equals(userResultBase.status)) {
                onRequestSuccess(result);
            } else {
                onReuquestFailed(userResultBase.message);
                if (Constant.IS_DEBUG_MODE) {
                    Logger.d(TAG + " ERROR", userResultBase.message == null ? "NULL" :
                            userResultBase.message);
                }
            }
        } else {
            onRequestSuccess(result);
        }
    }

    public abstract void onRequestSuccess(E result);

    public abstract void onReuquestFailed(String error);
}
