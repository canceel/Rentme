package com.android.youhu.adapter;

import android.content.Context;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.bean.CardInfo;

import java.util.List;


public class MineCardAdapter extends CommonAdapter<CardInfo> {

    public MineCardAdapter(Context context, List<CardInfo> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_mine_card;
    }


    public void convert(final ViewHolder holder, CardInfo review) {
        //---------- ��ʼ������--------------
        TextView cardTextView = holder.getView(R.id.card);
    }
}