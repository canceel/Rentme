package com.allipper.rentme.adapter;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.response.PulishInfoResponse;
import com.allipper.rentme.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;


public class HomePageAdapter extends CommonAdapter<PulishInfoResponse> {

    public HomePageAdapter(Context context, List<PulishInfoResponse> datas) {
        super(context, datas);
        this.context = context;
        this.datas = datas;
        this.layoutId = R.layout.adapter_home_page;
    }


    public void convert(final ViewHolder holder, PulishInfoResponse review) {
        //---------- 开始定义域--------------
        CircleImageView head_cvCircleImageVie = holder.getView(R.id.head_cv);
        ImageView status_check_ivImageView = holder.getView(R.id.status_check_iv);
        TextView name_tvTextView = holder.getView(R.id.name_tv);
        LinearLayout second_llLinearLayout = holder.getView(R.id.second_ll);
        TextView career_title_tvTextView = holder.getView(R.id.career_title_tv);
        TextView career_tvTextView = holder.getView(R.id.career_tv);
        TextView age_title_tvTextView = holder.getView(R.id.age_title_tv);
        TextView age_tvTextView = holder.getView(R.id.age_tv);
        LinearLayout third_llLinearLayout = holder.getView(R.id.third_ll);
        TextView hight_title_tvTextView = holder.getView(R.id.hight_title_tv);
        TextView hight_tvTextView = holder.getView(R.id.hight_tv);
        TextView weight_title_tvTextView = holder.getView(R.id.weight_title_tv);
        TextView weight_tvTextView = holder.getView(R.id.weight_tv);
        LinearLayout forth_llLinearLayout = holder.getView(R.id.forth_ll);
        TextView offercontent_title_tvTextView = holder.getView(R.id.offercontent_title_tv);
        TextView offercontent_tvTextView = holder.getView(R.id.offercontent_tv);
        LinearLayout fifth_llLinearLayout = holder.getView(R.id.fifth_ll);
        TextView schedule_title_tvTextView = holder.getView(R.id.schedule_title_tv);
        TextView schedule_tvTextView = holder.getView(R.id.schedule_tv);
        GridView pictures_GridView = holder.getView(R.id.pictures);
        ImageView distance_ivImageView = holder.getView(R.id.distance_iv);
        TextView distance_tvTextView = holder.getView(R.id.distance_tv);
        TextView fee_tvTextView = holder.getView(R.id.fee_tv);
        review.pictureUrls = new ArrayList<>(5);
        review.pictureUrls.add("1");
        review.pictureUrls.add("2");
        review.pictureUrls.add("3");
        review.pictureUrls.add("4");
        review.pictureUrls.add("5");
        int ii = review.pictureUrls.size();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ii * (Utils
                .getScreenWidth(context) / 3), Utils.getScreenWidth(context) / 3);
        pictures_GridView.setLayoutParams(params);
        pictures_GridView.setColumnWidth(Utils.getScreenWidth(context) / 3);
        pictures_GridView.setNumColumns(ii);
        pictures_GridView.setAdapter(new PicturesAdapter(context, review.pictureUrls, PicturesAdapter.TYPE_THREE));
    }
}

