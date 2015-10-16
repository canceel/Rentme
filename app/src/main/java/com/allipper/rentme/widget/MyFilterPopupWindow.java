package com.allipper.rentme.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.FilterItemViewAdapter;
import com.allipper.rentme.bean.FilterItem;
import com.allipper.rentme.common.util.Logger;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by king on 2015/7/21.
 */
public class MyFilterPopupWindow extends PopupWindow implements View.OnClickListener {

    private View conentView;
    private Context context;
    private ArrayList<FilterItem> datas;
    public HashMap<String, String> selectedItems;

    public MyFilterPopupWindow(Context context, ArrayList<FilterItem> datas) {
        this.context = context;
        this.datas = datas;
        LayoutInflater inflater = LayoutInflater.from(context);
        conentView = inflater.inflate(R.layout.popupwindow_filter, null);

        conentView.findViewById(R.id.btn_submit).setOnClickListener(this);
        conentView.findViewById(R.id.btn_cancel).setOnClickListener(this);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        initListView();
    }

    private FilterItemViewAdapter adapter;

    private void initListView() {
        Logger.d("initListView", "initListView");
        AutoHeightListView filter_listView = (AutoHeightListView) conentView.findViewById(R.id
                .lv_filter);
        if (datas != null && datas.size() > 0) {
            adapter = new FilterItemViewAdapter(context, datas);
            filter_listView.setAdapter(adapter);
            conentView.findViewById(R.id
                    .btn_submit).setVisibility(View.VISIBLE);
        } else {
            conentView.findViewById(R.id
                    .btn_submit).setVisibility(View.GONE);
        }

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            selectedItems = adapter.getSelectMap();
            if (selectedItems == null) {//different with cancel
                selectedItems = new HashMap<>();
            }
        }
        if (v.getId() == R.id.btn_cancel) {
            selectedItems = null;
        }
        dismiss();
    }


}
