package com.android.youhu.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.android.youhu.R;
import com.android.youhu.bean.FilterSubItem;

import java.util.List;


/**
 * Created by king on 2015/7/29.
 */

public class PickUpFilterItemViewAdapter extends CommonAdapter<FilterSubItem> {
    private SelectorListerner listener;
    private int pos;

    public PickUpFilterItemViewAdapter(Context context, List<FilterSubItem> datas,
                                       SelectorListerner listener) {
        super(context, datas);
        this.layoutId = R.layout.gridview_item_popup_filter;
        this.listener = listener;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public void convert(final ViewHolder holder, final FilterSubItem s) {
        final Button tv_filter = holder.getView(R.id.tv_filter);
        String name = s.name;
        tv_filter.setText(name);

        if (s.isSelected) {
            tv_filter.setSelected(true);
            listener.updateSelectorMap(pos, s.value);
        } else {
            tv_filter.setSelected(false);
        }

        tv_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < datas.size(); i++) {
                    if (i == holder.position) {
                        datas.get(i).isSelected = !datas.get(i).isSelected;
                    } else {
                        datas.get(i).isSelected = false;
                    }
                }
                notifyDataSetChanged();
            }
        });

    }

    public interface SelectorListerner {
        void updateSelectorMap(int position, int value);
    }
}
