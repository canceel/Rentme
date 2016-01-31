package com.android.youhu.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.bean.RentMeResponse;

import java.util.List;


public class MineRentAdapter extends CommonAdapter<RentMeResponse.DataEntity.ItemsEntity> {

    public MineRentAdapter(Context context, List<RentMeResponse.DataEntity.ItemsEntity> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_mine_rent;
    }


    public void convert(final ViewHolder holder, RentMeResponse.DataEntity.ItemsEntity review) {
        //---------- ��ʼ������--------------
        TextView dateTextView = holder.getView(R.id.date);
        TextView statusTextView = holder.getView(R.id.status);
        TextView nameTextView = holder.getView(R.id.name);
        TextView phoneTextView = holder.getView(R.id.phone);
        ImageView authImageView = holder.getView(R.id.auth);
        TextView timeTextView = holder.getView(R.id.time);
        TextView costTextView = holder.getView(R.id.cost);
        TextView locationTextView = holder.getView(R.id.location);
        TextView offercontentTextView = holder.getView(R.id.offercontent);
        holder.setImageByUrl(review.avatarUrl, R.mipmap.icon_defaultheader, R.id.head_cv);
        if (review.isAuth == 1) {
            authImageView.setVisibility(View.VISIBLE);
        } else {
            authImageView.setVisibility(View.GONE);
        }
        holder.setText(R.id.date, review.createTime);
        holder.setText(R.id.status, review.state);
        holder.setText(R.id.name, review.nickName);
        holder.setText(R.id.phone, review.mobile);
        holder.setText(R.id.time, review.meetTime);
        holder.setText(R.id.location, review.meetAddress);
        holder.setText(R.id.cost, review.totalPrice + "元");
        holder.setText(R.id.offercontent, review.rentRange);
    }
}