package com.android.youhu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.bean.BeanCountry;

import java.util.List;


public class CurrentCityAdapter extends BaseAdapter {
    private Context context;
    private List<BeanCountry> datas;
    private List<String> alphabet;

    public CurrentCityAdapter(Context context, List<BeanCountry> datas, List<String> alphabet) {
        this.context = context;
        this.datas = datas;
        this.alphabet = alphabet;

    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public BeanCountry getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout
                    .adapter_current_city, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.sortKryLayout = (LinearLayout) convertView.findViewById(R.id.sort_key_layout);
            holder.sortKey = (TextView) convertView.findViewById(R.id.sort_key);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(getItem(position).acronymName);
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            holder.sortKey.setText(getItem(position).acronym.substring(0, 1));
            holder.sortKryLayout.setVisibility(View.VISIBLE);
        } else {
            holder.sortKryLayout.setVisibility(View.GONE);
        }
        return convertView;
    }

    public int getPositionForSection(int sectionIndex) {
        if (sectionIndex >= alphabet.size()) {
            return alphabet.size();
        }
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).acronym.substring(0, 1).equals(alphabet.get(sectionIndex))) {
                return i;
            }
        }
        return -1;
    }

    public int getSectionForPosition(int position) {
        String temp = getItem(position).acronym.substring(0, 1);
        return alphabet.indexOf(temp);
    }

    static class ViewHolder {
        TextView name;
        LinearLayout sortKryLayout;
        TextView sortKey;
    }
}

