package com.allipper.rentme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.bean.FilterItem;
import com.allipper.rentme.bean.FilterSubItem;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by king on 2015/7/29.
 */
public class FilterItemViewAdapter extends BaseAdapter implements PickUpFilterItemViewAdapter
        .SelectorListerner {
    private HashMap<String, String> selectMap = new HashMap<>();
    private Context context;
    private ArrayList<FilterItem> datas;
    private LayoutInflater inflater;

    public FilterItemViewAdapter(Context context, ArrayList<FilterItem> datas) {
        this.context = context;
        this.datas = datas;
        inflater = LayoutInflater.from(context);
        iniSelectMap();
    }

    private void iniSelectMap() {
        for (int i = 0; i < datas.size(); i++) {
            FilterItem fi = datas.get(i);
            for (int j = 0; j < fi.item.size(); j++) {
                FilterSubItem fsi = fi.item.get(j);
                if (fsi.isSelected) {
                    selectMap.put(fi.title, fsi.name);
                }
            }
        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        FilterItem filterData = datas.get(position);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_popup_filter, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.itemGridView = (GridView) convertView.findViewById(R.id.gridView);
            holder.mOrdersListItemDetailDatas = new ArrayList<>();
            holder.mOrdersListItemDetailDatas.addAll(filterData.item);//第一次填充数据
            holder.adapter = new PickUpFilterItemViewAdapter(context, holder
                    .mOrdersListItemDetailDatas, this);//新建adapter
            holder.adapter.setPos(position);
            holder.itemGridView.setAdapter(holder.adapter);//将adapter设置到listview里
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.adapter.setPos(position);
            holder.title.setText(filterData.title);
            holder.mOrdersListItemDetailDatas.clear();
            holder.mOrdersListItemDetailDatas.addAll(filterData.item);
            //视图复用的时候不重新new一个数据对象，而是使用之前的数据对象
            holder.adapter.notifyDataSetChanged();//然后通知适配器更新界面
        }
        return convertView;
    }

    class ViewHolder {
        TextView title;
        GridView itemGridView;//里层的listview也需要复用
        PickUpFilterItemViewAdapter adapter;//需要复用的适配器
        ArrayList<FilterSubItem> mOrdersListItemDetailDatas;//里层的listview的数据
    }

    @Override
    public void updateSelectorMap(int position, String value) {
        selectMap.put(datas.get(position).title, value);
    }

    public HashMap<String, String> getSelectMap() {
        return selectMap;
    }
}
