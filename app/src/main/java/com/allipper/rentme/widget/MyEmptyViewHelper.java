package com.allipper.rentme.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allipper.rentme.R;


/**
 * Created by king on 2015/9/3.
 */
public class MyEmptyViewHelper {

    public static final int TYPE_NETWORK_ERROR = -1;
    public static final int TYPE_ORDER_EMPTY = 0;
    public static final int TYPE_COLLECTION_EMPTY = 1;
    public static final int TYPE_EVALUATION_EMPTY = 2;
    public static final int TYPE_CART_EMPTY = 3;
    public static final int TYPE_INDEX_EMPTY = 4;
    public static final int TYPE_COUPONS_EMPTY = 5;
    public static final int TYPE_DEFAULT_EMPTY = 6;

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
            emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                    .MATCH_PARENT, LinearLayout
                    .LayoutParams.MATCH_PARENT));
            ImageView icon = (ImageView) emptyView.findViewById(R.id.icon);
            TextView text = (TextView) emptyView.findViewById(R.id.text);
            Button retry = (Button) emptyView.findViewById(R.id.retry);
            switch (type) {
                case TYPE_ORDER_EMPTY:
                    break;
            }
        }

        view.setVisibility(View.GONE);
        if (emptyView.getParent() != null) {
            ((ViewGroup) emptyView.getParent()).removeView(emptyView);
        }
        ((ViewGroup) view.getParent()).addView(emptyView);
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


}
