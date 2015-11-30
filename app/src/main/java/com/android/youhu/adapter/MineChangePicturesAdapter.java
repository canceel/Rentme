package com.android.youhu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.android.youhu.R;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;

import java.util.LinkedList;
import java.util.List;


public class MineChangePicturesAdapter extends CommonAdapter<String> {

    /**
     * 用户选择的图片，存储为图片的完整路径
     */
    public List<String> mSelectedImage = new LinkedList<String>();

    /**
     * 文件夹路径
     */
    private String mDirPath;

    public MineChangePicturesAdapter(Context context, List<String> datas, String dirPath) {
        super(context, datas);
        this.layoutId = R.layout.adapter_pictures_grid_item;
        this.mDirPath = dirPath;
    }

    @Override
    public void convert(ViewHolder holder, final String s) {
        AbsListView.LayoutParams lp = (AbsListView.LayoutParams) holder.getConvertView()
                .getLayoutParams();
        if (lp == null) {
            holder.getConvertView().setLayoutParams(new AbsListView.LayoutParams(Utils
                    .getScreenWidth(context) / 3 - 2, Utils.getScreenWidth(context) / 3 - 2));
        } else {
            lp.width = Utils.getScreenWidth(context) / 3 - 2;
            lp.height = Utils.getScreenWidth(context) / 3 - 2;
            holder.getConvertView().setLayoutParams(lp);
        }

        //设置no_pic
        holder.setImageResource(R.id.id_item_image, R.mipmap.picture);
        //设置no_selected
        holder.setImageResource(R.id.id_item_select,
                R.mipmap.picture_unselected);
        //设置图片
        holder.setImageByLocalUrl(mDirPath + "/" + s, R.id.id_item_image);

        final ImageView mImageView = holder.getView(R.id.id_item_image);
        final ImageView mSelect = holder.getView(R.id.id_item_select);

        mImageView.setColorFilter(null);
        //设置ImageView的点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {
            //选择，则将图片变暗，反之则反之
            @Override
            public void onClick(View v) {

                // 已经选择过该图片
                if (mSelectedImage.contains(mDirPath + "/" + s)) {
                    mSelectedImage.remove(mDirPath + "/" + s);
                    mSelect.setImageResource(R.mipmap.picture_unselected);
                    mImageView.setColorFilter(null);
                } else
                // 未选择该图片
                {
                    if(mSelectedImage.size() >= 4){
                        ToastUtils.show(context, "最多只能上传四张相片");
                        return;
                    }
                    mSelectedImage.add(mDirPath + "/" + s);
                    mSelect.setImageResource(R.mipmap.pictures_selected);
                    mImageView.setColorFilter(Color.parseColor("#77000000"));
                }

            }
        });

        /**
         * 已经选择过的图片，显示出选择过的效果
         */
        if (mSelectedImage.contains(mDirPath + "/" + s)) {
            mSelect.setImageResource(R.mipmap.pictures_selected);
            mImageView.setColorFilter(Color.parseColor("#77000000"));
        }


    }
}

