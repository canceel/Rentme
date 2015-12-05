package com.android.youhu.widget;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.youhu.R;
import com.android.youhu.adapter.CommonAdapter;
import com.android.youhu.adapter.ViewHolder;
import com.android.youhu.bean.ActionBean;

import java.util.List;

public class MinePicturesMenuPopupWindow extends BasePopupWindowForListView<ActionBean> {
    private ListView mListDir;

    public MinePicturesMenuPopupWindow(Context context, int width, int height,
                                       List<ActionBean> datas, View convertView) {
        super(convertView, dpToPx(context, width), dpToPx(context, height), true, datas);
    }

    @Override
    public void initViews() {
        mListDir = (ListView) findViewById(R.id.id_list_dir);
        mListDir.setAdapter(new CommonAdapter<ActionBean>(context, mDatas,
                R.layout.adapter_pictures_list_menu_item) {
            @Override
            public void convert(ViewHolder helper, ActionBean item) {
                helper.setText(R.id.title, item.title);
                helper.setText(R.id.icon, item.iconStr);
            }
        });
    }

    public interface OnMenuItemSelected {
        void selected(ActionBean floder);
    }

    private OnMenuItemSelected mImageDirSelected;

    public void setMenuItemSelected(OnMenuItemSelected mImageDirSelected) {
        this.mImageDirSelected = mImageDirSelected;
    }

    @Override
    public void initEvents() {
        mListDir.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (mImageDirSelected != null) {
                    mImageDirSelected.selected(mDatas.get(position));
                }
            }
        });
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void beforeInitWeNeedSomeParams(Object... params) {
        // TODO Auto-generated method stub
    }

}
