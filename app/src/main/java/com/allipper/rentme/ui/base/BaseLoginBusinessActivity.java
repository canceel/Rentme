package com.allipper.rentme.ui.base;

import android.app.Dialog;
import android.os.Bundle;

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


//    /**
//     * 登录成功后处理逻辑
//     *
//     * @param account_guid 用户Guid
//     * @param account      用户名
//     * @param accessToken
//     * @param userType
//     * @param customer
//     * @param dialog
//     * @param listener
//     */
//    protected void loginSuccess(final String account_guid, final String account, final String accessToken, final String userType, final Customer customer, final Dialog dialog, final LoginSuccessListener listener) {
//        HttpLoad.ShoppingCartModule.getShoppingCart(mContext, TAG, account_guid, accessToken, new
//                ResponseCallback<ResponseShoppingCart>(mContext) {
//                    @Override
//                    public void onRequestSuccess(ResponseShoppingCart result) {
//                        String guest_guid = SharedPreUtils.getString(mContext, SharedPre.Cart
//                                .GUEST_GUID);
//                        if (!TextUtils.isEmpty(guest_guid)) {
//                            HttpLoad.ShoppingCartModule.mergShoppingCart(mContext, TAG, account_guid,
//                                    accessToken, guest_guid, result.cart.guid, new ResponseCallback<ResponseShoppingCart>(mContext) {
//                                        @Override
//                                        public void onRequestSuccess(ResponseShoppingCart result) {
//                                            saveInformation(account, accessToken, userType, customer, result);
//                                            // 合并后移除guest_guid
//                                            SharedPreUtils.removeSharedKey(mContext, SharedPre.Cart.GUEST_GUID);
//                                            sendBrodCastToIndex(true);
//                                            if (listener != null) {
//                                                listener.onSuccessed(dialog);
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onReuquestFailed(String error) {
//                                            dialog.dismiss();
//                                            ToastUtils.show(mContext, error);
//                                        }
//                                    });
//                        } else {
//                            saveInformation(account, accessToken, userType, customer, result);
//                            dialog.dismiss();
//                        }
//                    }
//
//                    @Override
//                    public void onReuquestFailed(String error) {
//                        dialog.dismiss();
//                        ToastUtils.show(mContext, error);
//                    }
//                });
//    }

//    /**
//     * 保存信息到sp中
//     *
//     * @param account
//     * @param accessToken
//     * @param userType
//     * @param customer
//     * @param result
//     */
//    private void saveInformation(final String account, final String accessToken, final String userType, final Customer customer, ResponseShoppingCart result) {
//        Utils.saveCart(mContext, result.cart.code, result.cart.guid);
//        Utils.saveAppInfo(BaseLoginBusinessActivity.this, account, accessToken, userType);
//        Utils.saveUserInfo(BaseLoginBusinessActivity.this, customer);
//    }


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
