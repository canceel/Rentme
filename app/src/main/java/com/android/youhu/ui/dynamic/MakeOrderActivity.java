package com.android.youhu.ui.dynamic;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.toolbox.NetworkImageView;
import com.android.youhu.R;
import com.android.youhu.common.util.DialogUtils;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.SharedPre;
import com.android.youhu.common.util.SharedPreUtils;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.HttpLoad;
import com.android.youhu.net.ResponseCallback;
import com.android.youhu.net.response.PulishInfoResponse;
import com.android.youhu.net.response.ResponseBase;
import com.android.youhu.ui.base.BaseActivity;
import com.android.youhu.ui.base.ParameterConstant;
import com.android.youhu.ui.mine.MineRentActivity;
import com.android.youhu.widget.CircleImageView;
import com.android.youhu.widget.MyDatePickerUtils;
import com.android.youhu.widget.MyTimePickerUtils;

import java.util.Calendar;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.message.TextMessage;

public class MakeOrderActivity extends BaseActivity {
    private static final String TAG = MakeOrderActivity.class.getSimpleName();
    private static final int DATE_DIALOG = 0;
    private static final int TIME_DIALOG = 1;
    private static final int DURATION_DIALOG = 2;

    private ScrollView scrollViewScrollView;
    private NetworkImageView imageViewImageView;
    private FrameLayout first_flFrameLayout;
    private TextView nameTextView;
    private TextView constellationTextView;
    private TextView locationTextView;
    private TextView fee_tvTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private TextView durationTextView;
    private TextView telphoneTextView;
    private EditText addressEditText;
    private TextView costTextView;
    private TextView totalTextView;
    private TextView total_feeTextView;
    private Button datingButton;
    private NetworkImageView headCv;

    private String[] datas;
    private int selectedIndex = 0;

    private String providerUserId;
    private String targetUserId = "1";
    private String perHourPrice = "100";
    private String meetDate;
    private String meetTime;
    private String meetAddress;
    private String totalPrice;
    private PulishInfoResponse.DataEntity.ItemsEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        datas = getResources().getStringArray(R.array
                .select_durations);
        findViews();
        getData();
        setDataToView();
    }

    private void getData() {
        data = getIntent().getExtras().getParcelable(ParameterConstant.PARAM_ITEM_DATA);
        providerUserId = "" + SharedPreUtils.getInt(mContext, SharedPre.User.USERID, 0);
        targetUserId = data.userId + "";
        perHourPrice = data.perHourPrice + "";
    }

    private void setDataToView() {
        nameTextView.setText(data.nickName);
        //TODO 星座
        constellationTextView.setText("双鱼座");
        fee_tvTextView.setText("￥" + data.perHourPrice);
        telphoneTextView.setText(SharedPreUtils.getString(mContext, SharedPre.User.MOBILE));
        HttpLoad.getImage(data.backgroudImage, R.mipmap.publish_bg, imageViewImageView);
        HttpLoad.getImage(data.avatarUrl, R.mipmap.icon_defaultheader, headCv);

    }

    private void findViews() {
        backImageView = (TextView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        scrollViewScrollView = (ScrollView) findViewById(R.id.scrollView);
        imageViewImageView = (NetworkImageView) findViewById(R.id.imageView);
        first_flFrameLayout = (FrameLayout) findViewById(R.id.first_fl);
        nameTextView = (TextView) findViewById(R.id.name);
        constellationTextView = (TextView) findViewById(R.id.constellation);
        locationTextView = (TextView) findViewById(R.id.location);
        fee_tvTextView = (TextView) findViewById(R.id.fee_tv);
        dateTextView = (TextView) findViewById(R.id.date);
        headCv = (CircleImageView) findViewById(R.id.head_cv);
        timeTextView = (TextView) findViewById(R.id.time);
        durationTextView = (TextView) findViewById(R.id.duration);
        telphoneTextView = (TextView) findViewById(R.id.telphone);
        addressEditText = (EditText) findViewById(R.id.address);
        costTextView = (TextView) findViewById(R.id.cost);
        totalTextView = (TextView) findViewById(R.id.total);
        total_feeTextView = (TextView) findViewById(R.id.total_fee);
        datingButton = (Button) findViewById(R.id.dating);
        titleTextView.setText("生成订单");
        backImageView.setOnClickListener(this);
        dateTextView.setOnClickListener(this);
        timeTextView.setOnClickListener(this);
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
                                meetDate = year + "-" + (month + 1) + "-" + dayOfMonth;
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
                                meetTime = hourOfDay + ":" + minute;
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
                        totalPrice = Integer.valueOf(datas[selectedIndex]) * Float.valueOf
                                (perHourPrice) + "";
                        total_feeTextView.setText("￥" + totalPrice);
                    }
                });
                dialog = builder.create();
                break;
        }
        dialog.show();
        DialogUtils.dialogTitleLineColor(dialog);
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
            case R.id.duration:
                showAlertDialog(DURATION_DIALOG);
                break;
            case R.id.dating:
                dating(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    private void dating(View view) {
        meetAddress = addressEditText.getText().toString();
        if (TextUtils.isEmpty(meetDate)) {
            ToastUtils.show(mContext, "请选择日期");
        } else if (TextUtils.isEmpty(meetTime)) {
            ToastUtils.show(mContext, "请选择时间");
        } else if (TextUtils.isEmpty(meetAddress)) {
            ToastUtils.show(mContext, "请设置见面地址");
        } else if (TextUtils.isEmpty(totalPrice)) {
            ToastUtils.show(mContext, "请设置预约时长");
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
            dialog.show();
            HttpLoad.Order.createOrder(TAG, Utils.getToken(mContext), providerUserId,
                    targetUserId, perHourPrice, meetDate + " " + meetTime, meetAddress,
                    totalPrice, new ResponseCallback<ResponseBase>(mContext) {

                        @Override
                        public void onRequestSuccess(ResponseBase result) {
                            dialog.dismiss();
                            /**
                             * 发送消息。
                             *
                             * @param type        会话类型。
                             * @param targetId    目标 Id。根据不同的 conversationType，可能是用户 Id、讨论组 Id、群组
                             *                    Id 或聊天室 Id。
                             * @param content     消息内容。
                             * @param pushContent push 时提示内容，为空时提示文本内容。
                             * @param callback    发送消息的回调。
                             * @return
                             */
                            RongIM.getInstance().getRongIMClient().sendMessage(Conversation
                                    .ConversationType.PRIVATE, data.userId + "", TextMessage.obtain
                                    ("我是" + SharedPreUtils.getString(mContext, SharedPre.User
                                            .NICKNAME) + ",已经预约了您，希望您同意！"), "", "", null, null);
                            startActivity(new Intent(mContext, MineRentActivity.class));
                            back(null);
                        }

                        @Override
                        public void onReuquestFailed(String error) {
                            dialog.dismiss();
                            ToastUtils.show(mContext, error);
                        }
                    });
        }

    }
}

