package com.android.youhu.common.util;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by allipper on 2015/10/9.
 */
public class DialogUtils {

    public static final void dialogTitleLineColor(Dialog dialog) {
        Context context = dialog.getContext();
        int divierId = context.getResources()
                .getIdentifier("android:id/titleDivider", null, null);
        View divider = dialog.findViewById
                (divierId);
        divider.setBackgroundColor(0xFFDCDCDC);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) divider.getLayoutParams();
        lp.height = 2;
        divider.setLayoutParams(lp);
    }

}
