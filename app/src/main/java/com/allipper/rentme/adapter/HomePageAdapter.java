package com.allipper.rentme.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.SharedPre;
import com.allipper.rentme.common.util.SharedPreUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.response.PulishInfoResponse;
import com.allipper.rentme.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;


public class HomePageAdapter extends CommonAdapter<PulishInfoResponse.DataEntity.ItemsEntity> {

    public HomePageAdapter(Context context, List<PulishInfoResponse.DataEntity.ItemsEntity> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_home_page;
    }


    public void convert(final ViewHolder holder, PulishInfoResponse.DataEntity.ItemsEntity review) {
        //---------- 开始定义域--------------
        ImageView status_check_ivImageView = holder.getView(R.id.status_check_iv);
        TextView name_tvTextView = holder.getView(R.id.name_tv);
        TextView career_tvTextView = holder.getView(R.id.career_tv);
        TextView age_tvTextView = holder.getView(R.id.age_tv);
        TextView hight_tvTextView = holder.getView(R.id.hight_tv);
        TextView weight_tvTextView = holder.getView(R.id.weight_tv);
        TextView offercontent_tvTextView = holder.getView(R.id.offercontent_tv);
        TextView schedule_tvTextView = holder.getView(R.id.schedule_tv);
        GridView pictures_GridView = holder.getView(R.id.pictures);
        ImageView distance_ivImageView = holder.getView(R.id.distance_iv);
        TextView distance_tvTextView = holder.getView(R.id.distance_tv);
        TextView fee_tvTextView = holder.getView(R.id.fee_tv);
        HorizontalScrollView horizontalScrollView = holder.getView(R.id.hsv);

        holder.setImageByUrl(review.avatarUrl, R.id.head_cv);
        holder.setText(R.id.name_tv, review.nickName);
        holder.setText(R.id.career_tv, review.job);
        holder.setText(R.id.age_tv, review.ageRange);
        holder.setText(R.id.hight_tv, review.heightRange);
        holder.setText(R.id.weight_tv, review.weightRange);
        holder.setText(R.id.offercontent_tv, review.rentRange);
        holder.setText(R.id.schedule_tv, review.Schedule);
        holder.setText(R.id.fee_tv, "￥" + review.perHourPrice);
        String gender = review.gender;
        if ("男".equals(gender)) {
            Drawable drawable = context.getResources().getDrawable(R.mipmap.sex_girl);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            name_tvTextView.setCompoundDrawables(null, null, drawable, null);
        } else if ("女".equals(gender)) {
            Drawable drawable = context.getResources().getDrawable(R.mipmap.sex_man);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            name_tvTextView.setCompoundDrawables(null, null, drawable, null);
        } else {
            name_tvTextView.setCompoundDrawables(null, null, null, null);
        }

        if (review.album != null && review.album.size() > 0) {
            horizontalScrollView.setVisibility(View.VISIBLE);
            List<String> pictureUrls = new ArrayList<String>(review.album.size());
            for (int i = 0; i < review.album.size(); i++) {
                pictureUrls.add(review.album.get(i).PictureUrl);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(review.album.size()
                    * (Utils
                    .getScreenWidth(context) / 3), Utils.getScreenWidth(context) / 3);
            pictures_GridView.setLayoutParams(params);
            pictures_GridView.setColumnWidth(Utils.getScreenWidth(context) / 3);
            pictures_GridView.setNumColumns(review.album.size());
            pictures_GridView.setAdapter(new PicturesAdapter(context, pictureUrls,
                    PicturesAdapter.TYPE_THREE));
        } else {
            horizontalScrollView.setVisibility(View.GONE);
        }
    }
}

