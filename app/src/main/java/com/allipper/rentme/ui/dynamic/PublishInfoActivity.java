package com.allipper.rentme.ui.dynamic;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.PicturesAdapter;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class PublishInfoActivity extends BaseActivity {
    private static final String TAG = PublishInfoActivity.class.getSimpleName();

    private ImageView backImageView;
    private TextView titleTextView;
    private ImageView imageViewImageView;
    private ScrollView scrollViewScrollView;
    private TextView nameTextView;
    private TextView constellationTextView;
    private TextView locationTextView;
    private TextView fee_tvTextView;
    private TextView careerTextView;
    private TextView ageTextView;
    private TextView tallTextView;
    private TextView weightTextView;
    private TextView offercontentTextView;
    private TextView scheduleTextView;
    private TextView hobbyTextView;
    private RatingBar commentRatingBar;
    private TextView statusTextView;
    private TextView feeTextView;
    private Button datingButton;
    private RelativeLayout pictrueBgRl;
    private GridView pictrues;
    private LinearLayout persenPictureLl;
    private ImageView deleteIv;

    private int selectedIcon = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_info);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        imageViewImageView = (ImageView) findViewById(R.id.imageView);
        scrollViewScrollView = (ScrollView) findViewById(R.id.scrollView);
        nameTextView = (TextView) findViewById(R.id.name);
        constellationTextView = (TextView) findViewById(R.id.constellation);
        locationTextView = (TextView) findViewById(R.id.location);
        fee_tvTextView = (TextView) findViewById(R.id.fee_tv);
        careerTextView = (TextView) findViewById(R.id.career);
        ageTextView = (TextView) findViewById(R.id.age);
        tallTextView = (TextView) findViewById(R.id.tall);
        weightTextView = (TextView) findViewById(R.id.weight);
        offercontentTextView = (TextView) findViewById(R.id.offercontent);
        scheduleTextView = (TextView) findViewById(R.id.schedule);
        hobbyTextView = (TextView) findViewById(R.id.hobby);
        commentRatingBar = (RatingBar) findViewById(R.id.comment);
        statusTextView = (TextView) findViewById(R.id.status);
        feeTextView = (TextView) findViewById(R.id.fee);
        datingButton = (Button) findViewById(R.id.dating);
        pictrueBgRl = (RelativeLayout) findViewById(R.id.picturebg);
        persenPictureLl = (LinearLayout) findViewById(R.id.persen_picture);
        pictrues = (GridView) findViewById(R.id.bigPictures);
        deleteIv = (ImageView) findViewById(R.id.delete);
        List<String> pictureUrls = new ArrayList<>(15);
        pictureUrls.add("1");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("1");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("1");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("2");
        pictureUrls.add("2");
        int ii = pictureUrls.size();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ii * (Utils
                .getScreenWidth(mContext)), ViewGroup.LayoutParams.WRAP_CONTENT);
        pictrues.setLayoutParams(params);
        pictrues.setColumnWidth(Utils.getScreenWidth(mContext) / 3 - 10);
        pictrues.setVerticalSpacing(5);
        pictrues.setNumColumns(ii);
        pictrues.setAdapter(new PicturesAdapter(mContext, pictureUrls, PicturesAdapter.TYPE_OTHER));
    }

    //���õ���¼�
    public void dating(View view) {
        Intent it = new Intent(mContext, MakeOrderActivity.class);
        startActivity(it);
    }

    public void showMinePictures(View view) {
        if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.GONE) {
            pictrueBgRl.setVisibility(View.VISIBLE);
        }
    }

    public void delete(View view) {
        if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.VISIBLE) {
            pictrueBgRl.setVisibility(View.GONE);
        }
    }

    public void processExit() {
        if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.VISIBLE) {
            pictrueBgRl.setVisibility(View.GONE);
        }else{
            super.processExit();
        }
    }
}

