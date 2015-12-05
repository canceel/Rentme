package com.android.youhu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.youhu.R;
import com.android.youhu.bean.ImageBean;
import com.android.youhu.common.util.Utils;

import java.util.LinkedList;
import java.util.List;


public class MinePicturesAdapter extends CommonAdapter<ImageBean> {

    /**
     * 用户选择的图片，存储为图片的完整路径
     */
    public List<String> mSelectedImage = new LinkedList<String>();

    public int status = 0;

    private OnClick onClickListener;
    public RelativeLayout pictrueBgRl;
    public HorizontalScrollView pictureHsv;
    public int width = 0;


    public MinePicturesAdapter(Context context, List<ImageBean> datas, int status, OnClick
            onClickListener) {
        super(context, datas);
        this.layoutId = R.layout.adapter_pictures_grid_item;
        this.status = status;
        this.onClickListener = onClickListener;
    }

    @Override
    public void convert(final ViewHolder holder, final ImageBean s) {
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
        holder.setImageByUrl(s.url, s.defaultResId, R.id.id_item_image);
        //设置no_selected
        holder.setImageResource(R.id.id_item_select,
                R.mipmap.picture_unselected);

        final ImageView mImageView = holder.getView(R.id.id_item_image);
        final ImageView mSelect = holder.getView(R.id.id_item_select);
        if (status == 0) {
            mImageView.setColorFilter(null);
            mSelect.setVisibility(View.GONE);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.GONE) {
                        pictrueBgRl.setVisibility(View.VISIBLE);
                        pictrueBgRl.startAnimation(AnimationUtils.loadAnimation(context, R.anim
                                .alpha_in));
                    }
                    if (pictureHsv != null) {
                        pictureHsv.smoothScrollTo((width + 1) * holder.position, 0);
                    }
                }
            });

        } else {
            mSelect.setVisibility(View.VISIBLE);
            mImageView.setColorFilter(null);
            //设置ImageView的点击事件
            mImageView.setOnClickListener(new View.OnClickListener() {
                //选择，则将图片变暗，反之则反之
                @Override
                public void onClick(View v) {

                    // 已经选择过该图片
                    if (mSelectedImage.contains(s.url)) {
                        mSelectedImage.remove(s.url);
                        mSelect.setImageResource(R.mipmap.picture_unselected);
                        mImageView.setColorFilter(null);
                    } else
                    // 未选择该图片
                    {
                        mSelectedImage.add(s.url);
                        mSelect.setImageResource(R.mipmap.pictures_selected);
                        mImageView.setColorFilter(Color.parseColor("#77000000"));
                    }
                    if (onClickListener != null) {
                        onClickListener.onMyClick();
                    }
                }
            });

            /**
             * 已经选择过的图片，显示出选择过的效果
             */
            if (mSelectedImage.contains(s.url)) {
                mSelect.setImageResource(R.mipmap.pictures_selected);
                mImageView.setColorFilter(Color.parseColor("#77000000"));
            }
        }

    }

    public interface OnClick {
        void onMyClick();
    }
}

