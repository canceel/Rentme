package com.allipper.rentme.ui.mine;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.DialogUtils;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.ui.base.BaseActivity;

public class MinePayActivity extends BaseActivity {
    private static final String TAG = MinePayActivity.class.getSimpleName();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private TextView typeTextView;
    private TextView sumMoneyTextView;
    private TextView cardTextView;
    private LinearLayout stillNeedLinearLayout;
    private LinearLayout pocketmoneyLinearLayout;
    private TextView pocketmoneyTextView;
    private TextView stillNeedTextView;
    private View line1View;
    private View line2View;


    private String[] datas = new String[]{
            "尾号2341(中国银行)",
            "尾号2321(招商银行)",
            "尾号1231(建设银行)",
    };
    private int index = 0;
    private int selectedIndex;
    private boolean isCharged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_pay);
        isCharged = getIntent().getBooleanExtra("isCharged", false);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        typeTextView = (TextView) findViewById(R.id.type);
        sumMoneyTextView = (TextView) findViewById(R.id.sumMoney);
        cardTextView = (TextView) findViewById(R.id.card);

        pocketmoneyLinearLayout = (LinearLayout) findViewById(R.id.pocketmoneyll);
        stillNeedLinearLayout = (LinearLayout) findViewById(R.id.stillNeedll);
        pocketmoneyTextView = (TextView) findViewById(R.id.pocketmoney);
        stillNeedTextView = (TextView) findViewById(R.id.stillNeed);
        line1View = findViewById(R.id.line1);
        line2View = findViewById(R.id.line2);

        if (isCharged) {
            pocketmoneyLinearLayout.setVisibility(View.GONE);
            stillNeedLinearLayout.setVisibility(View.GONE);
            line1View.setVisibility(View.GONE);
            line2View.setVisibility(View.GONE);
        } else {
            pocketmoneyLinearLayout.setVisibility(View.VISIBLE);
            stillNeedLinearLayout.setVisibility(View.VISIBLE);
            line1View.setVisibility(View.VISIBLE);
            line2View.setVisibility(View.VISIBLE);
        }

    }


    public void chooseCard(View view) {
        /**
         * 创建选择对话框
         */
        Dialog dialog = null;
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style
                .CommonDialog);
        builder.setSingleChoiceItems(datas, index, new
                DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedIndex = which;
                    }
                });
        builder.setTitle("请选择银行卡");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cardTextView.setText(datas[selectedIndex]);
            }
        });
        dialog = builder.create();
        dialog.show();
        DialogUtils.dialogTitleLineColor(dialog);
    }

    public void confirm(View view) {
        if (isCharged) {
            ToastUtils.show(mContext, "支付成功");
            Intent it = new Intent(mContext, MinePocketActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(it);
        } else {
            typeTextView.setText("预约支出");
            finish();
        }

    }


}

