package com.allipper.rentme.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.Utils;

import java.util.List;


/**
 * ImagePagerAdapter
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class HomePagerViewPagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<String> themeItems;
    private boolean isInfiniteLoop;

    public HomePagerViewPagerAdapter(Context context, List<String> themeItems) {
        this.context = context;
        this.themeItems = themeItems;
        isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : themeItems.size();
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % themeItems.size() : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = holder.imageView = new ImageView(context);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ThemeItem themeItem = themeItems.get(getPosition(position));
//                LinkToUtils.linkTo(context, themeItem);
//            }
//        });
        holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.imageView.setBackgroundResource(R.color.background);
//        HttpLoad.getImage(context, themeItems.get(getPosition(position)).imageURL,
//                holder.imageView);
        return view;
    }

    private static class ViewHolder {
        ImageView imageView;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public HomePagerViewPagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }
}
