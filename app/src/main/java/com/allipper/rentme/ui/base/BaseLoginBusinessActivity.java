package com.allipper.rentme.ui.base;

import android.app.Dialog;
import android.os.Bundle;

import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.response.LoginResult;

/**
 * allipper 登录逻辑基类
 */
public class BaseLoginBusinessActivity extends BaseActivity {

    private static final String TAG = BaseLoginBusinessActivity.class.getSimpleName();

    public static final String IS_LOGIN = "is_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 登录成功后处理逻辑
     *
     * @param listener
     * @param dialog
     */
    protected void loginSuccess(final LoginResult.DataEntity dataEntity, LoginSuccessListener
            listener, Dialog dialog) {
        if(dataEntity != null){
            Utils.saveLoginResult(mContext,dataEntity);
        }
        if(listener != null){
            listener.onSuccessed(dialog);
        }
    }

    //登录成功后发送广播
    protected void sendBrodCastToIndex(Boolean isLogin) {
//        Intent it = new Intent(IndexActivity.UPDATE_USER_INFO);
//        it.putExtra(IS_LOGIN, isLogin);
//        sendBroadcast(it);
    }

    public interface LoginSuccessListener {
        void onSuccessed(Dialog dialog);
    }
}
