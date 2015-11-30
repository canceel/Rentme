package com.android.youhu.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.bean.BalanceInfo;

import java.util.List;


public class PocketDetailAdapter extends CommonAdapter<BalanceInfo> {

    public PocketDetailAdapter(Context context, List<BalanceInfo> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_pocket_detail;
    }


    public void convert(final ViewHolder holder, BalanceInfo review) {
        //---------- ��ʼ������--------------
        TextView dateTextView = holder.getView(R.id.date);
        TextView typeTitleTextView = holder.getView(R.id.typeTitle);
        TextView orderIdTextView = holder.getView(R.id.orderId);
        TextView valueTitleTextView = holder.getView(R.id.valueTitle);
        TextView valueTextView = holder.getView(R.id.value);
        TextView orderIdTitleTextView = holder.getView(R.id.orderIdTitle);
        TextView typeTextView = holder.getView(R.id.type);
        TextView statusTextView = holder.getView(R.id.status);
        TextView balanceTitleTextView = holder.getView(R.id.balanceTitle);
        TextView balanceTextView = holder.getView(R.id.balance);
        TextView backupTextView = holder.getView(R.id.backup);

    }
}