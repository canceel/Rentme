package com.android.youhu.common.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;


public class LoadDialogUtil {


    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @return
     */
    public static Dialog createLoadingDialog(Context context, int resID) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);
        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
        AnimationDrawable animation = (AnimationDrawable) context.getResources().getDrawable(R
                .drawable.loading);
        // 使用ImageView显示动画
        spaceshipImage.setBackgroundDrawable(animation);
        animation.start();
        tipTextView.setText(resID);

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
        loadingDialog.setCanceledOnTouchOutside(false);

        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        return loadingDialog;
    }

}
