package com.allipper.rentme.ui.dynamic;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.allipper.rentme.R;
import com.allipper.rentme.common.util.DialogUtils;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.ui.login.CurrentCityActivity;
import com.allipper.rentme.widget.MyDatePickerUtils;
import com.allipper.rentme.widget.MyTimePickerUtils;

import java.util.Calendar;

public class MakeOrderActivity extends BaseActivity {
    private static final String TAG = MakeOrderActivity.class.getSimpleName();
    private static final int DATE_DIALOG = 0;
    private static final int TIME_DIALOG = 1;
    private static final int DURATION_DIALOG = 2;

    private ImageView backImageView;
    private TextView titleTextView;
    private ScrollView scrollViewScrollView;
    private ImageView imageViewImageView;
    private FrameLayout first_flFrameLayout;
    private TextView nameTextView;
    private TextView constellationTextView;
    private TextView locationTextView;
    private TextView fee_tvTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private TextView durationTextView;
    private EditText telphoneEditText;
    private TextView cityTextView;
    private EditText addressEditText;
    private TextView costTextView;
    private TextView totalTextView;
    private TextView total_feeTextView;
    private Button datingButton;

    private String[] datas;
    private int selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        datas = getResources().getStringArray(R.array
                .select_durations);
        findViews();
        getData(false);
    }

    private void getData(boolean isShowDialog) {

    }

    private void findViews() {
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        scrollViewScrollView = (ScrollView) findViewById(R.id.scrollView);
        imageViewImageView = (ImageView) findViewById(R.id.imageView);
        first_flFrameLayout = (FrameLayout) findViewById(R.id.first_fl);
        nameTextView = (TextView) findViewById(R.id.name);
        constellationTextView = (TextView) findViewById(R.id.constellation);
        locationTextView = (TextView) findViewById(R.id.location);
        fee_tvTextView = (TextView) findViewById(R.id.fee_tv);
        dateTextView = (TextView) findViewById(R.id.date);
        timeTextView = (TextView) findViewById(R.id.time);
        durationTextView = (TextView) findViewById(R.id.duration);
        telphoneEditText = (EditText) findViewById(R.id.telphone);
        cityTextView = (TextView) findViewById(R.id.city);
        addressEditText = (EditText) findViewById(R.id.address);
        costTextView = (TextView) findViewById(R.id.cost);
        totalTextView = (TextView) findViewById(R.id.total);
        total_feeTextView = (TextView) findViewById(R.id.total_fee);
        datingButton = (Button) findViewById(R.id.dating);

        backImageView.setOnClickListener(this);
        dateTextView.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
        cityTextView.setOnClickListener(this);
        durationTextView.setOnClickListener(this);
        datingButton.setOnClickListener(this);
    }

    /**
     * 创建日期及时间选择对话框
     */
    private void showAlertDialog(int id) {
        Dialog dialog = null;
        Calendar c = Calendar.getInstance();
        switch (id) {
            case DATE_DIALOG:
                dialog = new DatePickerDialog(
                        this, R.style
                        .CommonDialog,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker dp, int year, int month, int
                                    dayOfMonth) {
                                dateTextView.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                            }
                        },
                        c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                MyDatePickerUtils.changeDivider(((DatePickerDialog) dialog).getDatePicker());
                dialog.setTitle("请选择日期");
                break;
            case TIME_DIALOG:
                c = Calendar.getInstance();
                dialog = new TimePickerDialog(
                        this, R.style
                        .CommonDialog,
                        new TimePickerDialog.OnTimeSetListener() {
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                timeTextView.setText(hourOfDay + ":" + minute);
                            }
                        },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        false
                );
                MyTimePickerUtils.changeDivider((TimePickerDialog) dialog);
                dialog.setTitle("请选择时间");
                break;
            case DURATION_DIALOG:
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style
                        .CommonDialog);
                builder.setSingleChoiceItems(R.array.select_durations, selectedIndex, new
                        DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedIndex = which;
                            }
                        });
                builder.setTitle("请选择时长");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        durationTextView.setText(datas[selectedIndex] + "小时");
                    }
                });
                dialog = builder.create();
                break;
        }
        dialog.show();
        DialogUtils.dialogTitleLineColor(dialog);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CurrentCityActivity.CURRENT_ACTIVITY_RESULT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                cityTextView.setText(data.getStringExtra("city"));
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.date:
                showAlertDialog(DATE_DIALOG);
                break;
            case R.id.time:
                showAlertDialog(TIME_DIALOG);
                break;
            case R.id.city:
                startActivityForResult(new Intent(this, CurrentCityActivity.class),
                        CurrentCityActivity.CURRENT_ACTIVITY_RESULT);
                break;
            case R.id.duration:
                showAlertDialog(DURATION_DIALOG);
                break;
            case R.id.dating:
                Intent it = new Intent(mContext, OrderDetailActivity.class);
                startActivity(it);
                break;
            default:
                super.onClick(view);
                break;
        }
    }
}

