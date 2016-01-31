package com.android.youhu.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.youhu.R;
import com.android.youhu.common.util.Utils;

import java.util.List;


public class PicturesAdapter extends CommonAdapter<String> {

    public final static int TYPE_THREE = 0;
    public final static int TYPE_OTHER = 1;

    private int type = 0;

    public PicturesAdapter(Context context, List<String> datas, int type) {
        super(context, datas);
        this.layoutId = R.layout.adapter_pictures;
        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, String s) {
        ImageView pictureImageView = holder.getView(R.id.picture);
        holder.setImageByUrl(s, R.id.picture);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pictureImageView
                .getLayoutParams();
        switch (type) {
            case TYPE_THREE:
                lp.width = (Utils.getScreenWidth(context) - Utils.dip2px(context, 76)) / 3 - 6;
                lp.height = (Utils.getScreenWidth(context) - Utils.dip2px(context, 76)) / 3 - 6;
                pictureImageView.setLayoutParams(lp);
                break;
            case TYPE_OTHER:
                lp.width = Utils.getScreenWidth(context) - 6;
                lp.height = Utils.getScreenHeight(context) * 2 / 3;
                pictureImageView.setLayoutParams(lp);
                break;
        }


    }
}

