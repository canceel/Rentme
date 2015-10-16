package com.allipper.rentme.adapter;

import android.content.Context;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.bean.OrderInfo;

import java.util.List;


public class RentMeAdapter extends CommonAdapter<OrderInfo> {

    public RentMeAdapter(Context context, List<OrderInfo> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_rent_me;
    }


    public void convert(final ViewHolder holder, OrderInfo review) {
        //---------- ��ʼ������--------------
        TextView dateTextView = holder.getView(R.id.date);
        TextView statusTextView = holder.getView(R.id.status);
        TextView nameTextView = holder.getView(R.id.name);
        TextView phoneTextView = holder.getView(R.id.phone);
        TextView timeTextView = holder.getView(R.id.time);
        TextView costTextView = holder.getView(R.id.cost);
        TextView  locationTextView = holder.getView(R.id.location);
        TextView  offercontentTextView = holder.getView(R.id.offercontent);

    }
}