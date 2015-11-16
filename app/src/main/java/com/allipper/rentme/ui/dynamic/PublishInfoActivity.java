package com.allipper.rentme.ui.dynamic;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.PicturesAdapter;
import com.allipper.rentme.common.util.CropUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.response.PulishInfoResponse;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.ui.base.ParameterConstant;
import com.allipper.rentme.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

public class PublishInfoActivity extends BaseActivity {
    private static final String TAG = PublishInfoActivity.class.getSimpleName();

    private ImageView backImageView;
    private ImageView icon1ImageView;
    private ImageView icon2ImageView;
    private ImageView icon3ImageView;
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
    private View persen_picture_dividerView;
    private ImageView deleteIv;
    private CircleImageView headCv;
    private HorizontalScrollView horizontalScrollView;

    private int selectedIcon = 0;
    private PulishInfoResponse.DataEntity.ItemsEntity data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_info);
        findViews();
        getData();
        setDataToView();
    }

    private void getData() {
        data = getIntent().getExtras().getParcelable(ParameterConstant.PARAM_ITEM_DATA);

    }

    private void setDataToView() {
        nameTextView.setText(data.nickName);
        constellationTextView.setText("双鱼座");
        feeTextView.setText("￥" + data.perHourPrice);
        fee_tvTextView.setText("￥" + data.perHourPrice);
        careerTextView.setText(data.job);
        ageTextView.setText(data.ageRange);
        tallTextView.setText(data.heightRange);
        weightTextView.setText(data.weightRange);
        offercontentTextView.setText(data.rentRange);
        scheduleTextView.setText(data.Schedule);
        hobbyTextView.setText("睡觉、花花");
        HttpLoad.getImage(data.avatarUrl, headCv);
        String gender = "男";
        if ("男".equals(gender)) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.sex_girl);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            nameTextView.setCompoundDrawables(null, null, drawable, null);
        } else if ("女".equals(gender)) {
            Drawable drawable = mContext.getResources().getDrawable(R.mipmap.sex_man);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            nameTextView.setCompoundDrawables(null, null, drawable, null);
        } else {
            nameTextView.setCompoundDrawables(null, null, null, null);
        }

        if (data.album != null && data.album.size() > 0) {
            persen_picture_dividerView.setVisibility(View.VISIBLE);
            persenPictureLl.setVisibility(View.VISIBLE);
            horizontalScrollView.setVisibility(View.VISIBLE);
            List<String> pictureUrls = new ArrayList<String>(data.album.size());
            for (int i = 0; i < data.album.size(); i++) {
                pictureUrls.add(data.album.get(i).PictureUrl);
                if (i == 0) {
                    icon1ImageView.setVisibility(View.VISIBLE);
                    HttpLoad.getImage(data.album.get(i).PictureUrl, icon1ImageView);
                } else if (i == 1) {
                    icon2ImageView.setVisibility(View.VISIBLE);
                    HttpLoad.getImage(data.album.get(i).PictureUrl, icon2ImageView);
                } else if (1 == 2) {
                    icon3ImageView.setVisibility(View.VISIBLE);
                    HttpLoad.getImage(data.album.get(i).PictureUrl, icon3ImageView);
                }

            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(data.album.size()
                    * (Utils
                    .getScreenWidth(mContext)), Utils.getScreenWidth(mContext) * 4 / 3);
            pictrues.setLayoutParams(params);
            pictrues.setColumnWidth(Utils.getScreenWidth(mContext) / 3 - 2);
            pictrues.setNumColumns(data.album.size());
            pictrues.setAdapter(new PicturesAdapter(mContext, pictureUrls,
                    PicturesAdapter.TYPE_OTHER));
        } else {
            persen_picture_dividerView.setVisibility(View.GONE);
            persenPictureLl.setVisibility(View.GONE);
            horizontalScrollView.setVisibility(View.GONE);
        }
    }

    private void findViews() {
        backImageView = (ImageView) findViewById(R.id.back);
        icon1ImageView = (ImageView) findViewById(R.id.icon1);
        icon2ImageView = (ImageView) findViewById(R.id.icon2);
        icon3ImageView = (ImageView) findViewById(R.id.icon3);
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
        persen_picture_dividerView = findViewById(R.id.persen_picture_divider);
        pictrues = (GridView) findViewById(R.id.bigPictures);
        deleteIv = (ImageView) findViewById(R.id.delete);
        headCv = (CircleImageView) findViewById(R.id.head_cv);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        persenPictureLl.setOnClickListener(this);
        pictrueBgRl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                int[] loc = new int[2];
                int[] loc1 = new int[2];
                if (deleteIv != null) {
                    deleteIv.getLocationOnScreen(loc);
                }
                if (horizontalScrollView != null) {
                    horizontalScrollView.getLocationOnScreen(loc);
                }
                if ((x > loc[0] && x < loc[0] + deleteIv.getWidth() && y > loc[1] && y < loc[1] +
                        deleteIv.getHeight()) || (x > loc1[0] && x < loc1[0] +
                        horizontalScrollView.getWidth() && y > loc1[1] && y < loc1[1] +
                        horizontalScrollView.getHeight())) {
                    return false;
                }
                return true;
            }
        });
        deleteIv.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        datingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.dating:
                Intent it = new Intent(mContext, MakeOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ParameterConstant.PARAM_ITEM_DATA, data);
                it.putExtras(bundle);
                startActivity(it);
                back(null);
                break;
            case R.id.persen_picture:
                if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.GONE) {
                    pictrueBgRl.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.delete:
                if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.VISIBLE) {
                    pictrueBgRl.setVisibility(View.GONE);
                }
            default:
                super.onClick(view);
                break;
        }
    }

    public void processExit() {
        if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.VISIBLE) {
            pictrueBgRl.setVisibility(View.GONE);
        } else {
            super.processExit();
        }
    }
}

