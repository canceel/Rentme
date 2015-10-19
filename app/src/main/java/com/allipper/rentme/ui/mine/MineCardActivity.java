package com.allipper.rentme.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.MineCardAdapter;
import com.allipper.rentme.bean.CardInfo;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.widget.AutoHeightListView;

import java.util.ArrayList;
import java.util.List;

public class MineCardActivity extends BaseActivity {
    private static final String TAG = MineCardActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private AutoHeightListView listAutoHeightListView;
    private TextView cardTextView;

    private MineCardAdapter adapter;
    private List<CardInfo> datas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_card);
        findViews();
        getDatas(false);
    }


    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        listAutoHeightListView = (AutoHeightListView) findViewById(R.id.list);
        cardTextView = (TextView) findViewById(R.id.card);

        datas = new ArrayList<>();
        datas.add(new CardInfo());
        datas.add(new CardInfo());
        datas.add(new CardInfo());
        adapter = new MineCardAdapter(mContext, datas);
        listAutoHeightListView.setAdapter(adapter);
        listAutoHeightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(mContext, CardDetailActivity.class);
                startActivity(it);
            }
        });
    }


    public void bindCard(View view) {
        Intent it = new Intent(mContext, BindCardActivity.class);
        startActivity(it);
    }


}

