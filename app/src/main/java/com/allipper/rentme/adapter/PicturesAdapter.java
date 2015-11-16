package com.allipper.rentme.adapter;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.Utils;

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
        switch (type) {
            case TYPE_THREE:
                holder.getConvertView().setLayoutParams(new AbsListView.LayoutParams(Utils
                        .getScreenWidth(context) / 3 - 2, Utils.getScreenWidth(context) / 3 - 2));
                break;
            case TYPE_OTHER:
                holder.getConvertView().setLayoutParams(new AbsListView.LayoutParams(Utils
                        .getScreenWidth
                                (context), Utils.getScreenHeight(context) * 2 / 3));
                break;
        }


    }
}

