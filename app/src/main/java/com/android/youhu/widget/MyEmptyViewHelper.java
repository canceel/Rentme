package com.android.youhu.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.ui.base.BaseActivity;


/**
 * Created by king on 2015/9/3.
 */
public class MyEmptyViewHelper {

    public static final int TYPE_NETWORK_ERROR = -1;
    public static final int TYPE_DEFAULT_EMPTY = 1;

    public static final int RETRY_TYPE_NETWORK_ERROR = 0;
    public static final int RETRY_TYPE_GOTO_INDEX = 1;


    private static int type;

    /**
     * 设置，并返回，空页面View
     *
     * @param view
     * @param type
     * @return
     */
    public static View setEmptyView(View view, View emptyView, int type) {
        final Context context = view.getContext();
        if (emptyView == null || MyEmptyViewHelper.type != type) {
            if (MyEmptyViewHelper.type != type) {
                if (emptyView != null && emptyView.getParent() != null) {
                    ((ViewGroup) emptyView.getParent()).removeView(emptyView);
                }
            }
            MyEmptyViewHelper.type = type;
            emptyView = View.inflate(context, R.layout.layout_empty, null);
            emptyView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageView icon = (ImageView) emptyView.findViewById(R.id.icon);
            TextView text = (TextView) emptyView.findViewById(R.id.text);
            Button retry = (Button) emptyView.findViewById(R.id.retry);
            if (emptyView.getParent() != null) {
                ((ViewGroup) emptyView.getParent()).removeView(emptyView);
            }
            LinearLayout.LayoutParams llLp;
            RelativeLayout.LayoutParams rlLp;
            switch (type) {
                case TYPE_NETWORK_ERROR:
                    ViewGroup.LayoutParams lp = view.getLayoutParams();
                    icon.setVisibility(View.GONE);
                    text.setText("获得网络数据失败");
                    retry.setVisibility(View.VISIBLE);
                    retry.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((BaseActivity) context).retry(RETRY_TYPE_NETWORK_ERROR);
                        }
                    });
                    ((ViewGroup) view.getParent()).addView(emptyView,lp);
                    break;
                case TYPE_DEFAULT_EMPTY:
                    icon.setImageResource(R.mipmap.picture);
                    text.setText("暂无数据");
                    retry.setVisibility(View.GONE);
                    ((ViewGroup) view.getParent()).addView(emptyView);
                    break;
            }
        }
        view.setVisibility(View.GONE);

        return emptyView;
    }

    /**
     * 移除空View
     *
     * @param view
     * @param emptyView
     */
    public static void removeEmptyView(View view, View emptyView) {
        if (emptyView != null) {
            ((ViewGroup) view.getParent()).removeView(emptyView);
        }
        view.setVisibility(View.VISIBLE);
    }

    private static void gotoIndexActivity(TextView retry, final Context context) {
        retry.setVisibility(View.VISIBLE);
        retry.setText("去逛逛吧～");
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) context).retry(RETRY_TYPE_GOTO_INDEX);
            }
        });
    }


}
