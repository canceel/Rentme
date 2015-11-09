package com.allipper.rentme.ui.mine;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.application.ApplicationInit;
import com.allipper.rentme.common.util.CropUtils;
import com.allipper.rentme.common.util.DialogUtils;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.net.response.GetPublishInfoResponse;
import com.allipper.rentme.net.response.ItemsEntity;
import com.allipper.rentme.net.response.ResponseBase;
import com.allipper.rentme.net.response.SysEnumsResponse;
import com.allipper.rentme.net.response.UserInfo;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.widget.CircleImageView;
import com.allipper.rentme.widget.NoRequsetGridView;

import java.util.List;

public class MinePublishInfoActivity extends BaseActivity {
    private static final String TAG = MinePublishInfoActivity.class.getSimpleName();

    private static final int PHOTO_REQUEST_CAREMA = 0;
    private static final int PHOTO_REQUEST_GALLERY = 1;
    private static final int PHOTO_REQUEST_CUT = 2;

    public static final int CUSTOMER_INFO_MODIFY = 3;


    public static final int MODIDFY_STATUS = 0;
    public static final int NORMAL_STATUS = 1;
    public static final int NODATA_STATUS = 2;
    public static final int CREATE_STATUS = 3;

    //用于保存剪裁后图片的URI
    private Uri imageUri = CropUtils.buildSavedUri();
    //用于保存拍照图片的URI
    private Uri imagePhotoUri = CropUtils.buildPhotoUri();

    private ImageView backImageView;
    private TextView titleTextView;
    private ScrollView scrollViewScrollView;
    private ImageView imageViewImageView;
    private FrameLayout first_flFrameLayout;
    private TextView nameTextView;
    private TextView constellationTextView;
    private TextView locationTextView;
    private LinearLayout persen_pictureLinearLayout;
    private CircleImageView headCircleImageView;
    private TextView careerTextView;
    private TextView ageTextView;
    private TextView tallTextView;
    private TextView weightTextView;
    private TextView offercontentTextView;
    private TextView scheduleTextView;
    private TextView hobbyTextView;
    private TextView feeTextView;
    private TextView statusTextView;
    private TextView toPictureTextView;
    private RelativeLayout picturebgRelativeLayout;
    private ImageView deleteImageView;
    private Button editButton;
    private HorizontalScrollView hsvHorizontalScrollView;
    private NoRequsetGridView bigPicturesNoRequsetGridView;
    private LinearLayout bottomLinearLayout;

    private boolean[] offerContentIndex;
    private String[] offerContents;
    private boolean[] scheduleIndex;
    private String[] schedules;

    private String rentRange;
    private String scheduleRange;
    private String perHourPrice;
    private UserInfo userInfo;
    private int currentStatus = NORMAL_STATUS;
    private GetPublishInfoResponse.DataEntity data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_publish_info);
        initData();
        findViews();
        getDatas(true);
    }

    private void initData() {
        if (userInfo == null) {
            userInfo = Utils.getUserInfo(mContext);
        }
        offerContents = ApplicationInit.getFormatStringArray(SysEnumsResponse.RENT);
        offerContentIndex = new boolean[offerContents.length];
        schedules = ApplicationInit.getFormatStringArray(SysEnumsResponse.SCHEDULE);
        scheduleIndex = new boolean[schedules.length];
    }

    public void getDatas(boolean isShowDialog) {
        if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string
                    .loading);
            if (isShowDialog) {
                dialog.show();
            }
            HttpLoad.UserModule.getPublishInfo(TAG, Utils.getToken(mContext), new
                    ResponseCallback<GetPublishInfoResponse>(mContext) {
                        @Override
                        public void onRequestSuccess(GetPublishInfoResponse result) {
                            dialog.dismiss();
                            data = result.data;
                            if (data == null) {
                                scrollViewScrollView.setVisibility(View.GONE);
                                editButton.setText("新增");
                                currentStatus = NODATA_STATUS;
                            } else {
                                scrollViewScrollView.setVisibility(View.VISIBLE);
                                StringBuffer sb = new StringBuffer();
                                StringBuffer sb1 = new StringBuffer();

                                for (ItemsEntity item : data.rentRange.items) {
                                    sb.append("、").append(item.name);
                                    sb1.append(",").append(item.value);
                                }
                                rentRange = sb1.deleteCharAt(0).toString();
                                offercontentTextView.setText(sb.deleteCharAt(0));
                                sb.delete(0, sb.length() - 1);
                                sb1.delete(0, sb1.length() - 1);
                                for (ItemsEntity item : data.schedule.items) {
                                    sb.append("、").append(item.name);
                                }
                                scheduleRange = sb1.deleteCharAt(0).toString();
                                scheduleTextView.setText(sb.deleteCharAt(0));

                                perHourPrice = data.perHourPrice;
                                feeTextView.setText(data.perHourPrice + "元/时");
                            }
                        }

                        @Override
                        public void onReuquestFailed(String error) {
                            ToastUtils.show(mContext, error);
                            dialog.dismiss();
                        }
                    });
        }
    }


    private void findViews() {
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        toPictureTextView = (TextView) findViewById(R.id.toPicture);
        scrollViewScrollView = (ScrollView) findViewById(R.id.scrollView);
        imageViewImageView = (ImageView) findViewById(R.id.imageView);
        first_flFrameLayout = (FrameLayout) findViewById(R.id.first_fl);
        nameTextView = (TextView) findViewById(R.id.name);
        constellationTextView = (TextView) findViewById(R.id.constellation);
        locationTextView = (TextView) findViewById(R.id.location);
        persen_pictureLinearLayout = (LinearLayout) findViewById(R.id.persen_picture);
        careerTextView = (TextView) findViewById(R.id.career);
        ageTextView = (TextView) findViewById(R.id.age);
        tallTextView = (TextView) findViewById(R.id.tall);
        weightTextView = (TextView) findViewById(R.id.weight);
        offercontentTextView = (TextView) findViewById(R.id.offercontent);
        scheduleTextView = (TextView) findViewById(R.id.schedule);
        hobbyTextView = (TextView) findViewById(R.id.hobby);
        feeTextView = (TextView) findViewById(R.id.fee);
        statusTextView = (TextView) findViewById(R.id.status);
        picturebgRelativeLayout = (RelativeLayout) findViewById(R.id.picturebg);
        deleteImageView = (ImageView) findViewById(R.id.delete);
        hsvHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        bigPicturesNoRequsetGridView = (NoRequsetGridView) findViewById(R.id.bigPictures);
        editButton = (Button) findViewById(R.id.edit);
        headCircleImageView = (CircleImageView) findViewById(R.id.head);
        bottomLinearLayout = (LinearLayout) findViewById(R.id.bottom);


        backImageView.setOnClickListener(this);
        imageViewImageView.setOnClickListener(this);
        feeTextView.setOnClickListener(this);
        offercontentTextView.setOnClickListener(this);
        scheduleTextView.setOnClickListener(this);
        toPictureTextView.setOnClickListener(this);
        findViewById(R.id.dating).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        editButton.setOnClickListener(this);

        if (userInfo != null) {
            nameTextView.setText(userInfo.nickName);
            constellationTextView.setText(userInfo.userDetail);
            careerTextView.setText(userInfo.job);
            ageTextView.setText(userInfo.ageRange);
            tallTextView.setText(userInfo.heightRange);
            weightTextView.setText(userInfo.weightRange);
            hobbyTextView.setText(userInfo.interests);
            CropUtils.setHeadFromDisk(mContext, headCircleImageView);
        }

        scrollViewScrollView.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.edit:
                changeStatus(view);
                break;
            case R.id.dating:
                confirm(view);
                break;
            case R.id.cancel:
                cancel(view);
                break;
            case R.id.imageView:
                changeBackgroud(view);
                break;
            case R.id.fee:
                changeFee(view);
                break;
            case R.id.toPicture:
                changePicture(view);
                break;
            case R.id.offercontent:
                changeOfferContent(view);
                break;
            case R.id.schedule:
                changeSchedule(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    private void cancel(View view) {
        if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string
                    .unpublishing);
            dialog.show();
            HttpLoad.UserModule.cancelInfo(TAG, Utils
                    .getToken(mContext), new ResponseCallback<ResponseBase>(mContext) {


                @Override
                public void onRequestSuccess(ResponseBase result) {
                    dialog.dismiss();
                    ToastUtils.show(mContext, "取消发布成功");
                    scrollViewScrollView.setVisibility(View.GONE);
                    scrollViewScrollView.setVisibility(View.GONE);
                    editButton.setText("新增");
                    currentStatus = NODATA_STATUS;
                }

                @Override
                public void onReuquestFailed(String error) {
                    dialog.dismiss();
                    ToastUtils.show(mContext, error);
                }
            });
        }
    }

    private void changeStatus(View view) {
        if (NODATA_STATUS == currentStatus) {
            editButton.setText("取消");
            bottomLinearLayout.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim
                    .slide_in));
            bottomLinearLayout.setVisibility(View.VISIBLE);
            currentStatus = CREATE_STATUS;
            scrollViewScrollView.setVisibility(View.VISIBLE);
        } else if (CREATE_STATUS == currentStatus) {
            editButton.setText("新增");
            bottomLinearLayout.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim
                    .slide_in));
            bottomLinearLayout.setVisibility(View.VISIBLE);
            scrollViewScrollView.setVisibility(View.VISIBLE);
            currentStatus = NORMAL_STATUS;
        } else if (NORMAL_STATUS == currentStatus) {
            editButton.setText("取消");
            bottomLinearLayout.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim
                    .slide_in));
            bottomLinearLayout.setVisibility(View.VISIBLE);
            currentStatus = MODIDFY_STATUS;
            scrollViewScrollView.setVisibility(View.VISIBLE);
        } else {
            editButton.setText("编辑");
            bottomLinearLayout.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim
                    .slide_out));
            bottomLinearLayout.setVisibility(View.GONE);
            currentStatus = NORMAL_STATUS;
            scrollViewScrollView.setVisibility(View.VISIBLE);
        }
    }


    public void changeBackgroud(View view) {
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.popupwindow_usericon, null);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.AnimationPopupwindow);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        Button btnCancel = (Button) contentView.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button btnTackPhoto = (Button) contentView.findViewById(R.id.take_photo);
        btnTackPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相机
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //将拍照结果保存至imageUri中，不保留在相册中
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imagePhotoUri);
                startActivityForResult(intentCamera, PHOTO_REQUEST_CAREMA);
                popupWindow.dismiss();
            }
        });

        Button btnPickPhoto = (Button) contentView.findViewById(R.id.pick_photo);
        btnPickPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统相册
                Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PHOTO_REQUEST_GALLERY);
                popupWindow.dismiss();
            }
        });

    }

    public void changePicture(View view) {
        Intent it = new Intent(mContext, MinePicturesActivity.class);
        startActivity(it);
    }


    public void changeOfferContent(View view) {
        for (int i = 0; i < offerContentIndex.length; i++) {
            offerContentIndex[i] = false;
        }
        String str = offercontentTextView.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            String[] strs = str.split("、");
            for (String temp : strs) {
                int i = 0;
                for (ItemsEntity temp1 : ApplicationInit.getInterestEntities().items) {
                    if (temp.equals(temp1.name)) {
                        offerContentIndex[i] = true;
                        break;
                    }
                    i++;
                }
            }
        }
        showAlertDialog(offerContentIndex, offerContents, "请选择出租范围", offercontentTextView,
                ApplicationInit.getRentRangeEntities().items, SysEnumsResponse.RENT);
    }

    public void changeSchedule(View view) {
        for (int i = 0; i < scheduleIndex.length; i++) {
            scheduleIndex[i] = false;
        }
        String str = scheduleTextView.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            String[] strs = str.split("、");
            for (String temp : strs) {
                int i = 0;
                for (ItemsEntity temp1 : ApplicationInit.getInterestEntities().items) {
                    if (temp.equals(temp1.name)) {
                        scheduleIndex[i] = true;
                        break;
                    }
                    i++;
                }
            }
        }
        showAlertDialog(scheduleIndex, schedules, "请选择档期范围", scheduleTextView,
                ApplicationInit.getScheduleEntities().items, SysEnumsResponse.SCHEDULE);
    }


    /**
     * 创建选择对话框
     */
    /**
     * 创建选择对话框
     */
    private void showAlertDialog(final boolean[] indexs,
                                 final String[] datas,
                                 String title,
                                 final TextView view,
                                 final List<ItemsEntity> itemsEntities,
                                 final String type) {
        Dialog dialog = null;
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style
                .CommonDialog);
        builder.setMultiChoiceItems(datas, indexs, new DialogInterface.OnMultiChoiceClickListener
                () {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                indexs[which] = isChecked;
            }
        });
        builder.setTitle(title);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                StringBuffer paramSb = new StringBuffer();
                int i = 0;
                for (boolean index : indexs) {
                    if (index) {
                        sb.append(itemsEntities.get(i++).name).append("、");
                        paramSb.append(itemsEntities.get(i++).value).append(",");
                    } else {
                        i++;
                    }
                }
                if (sb.length() > 0) {
                    view.setText(sb.deleteCharAt(sb.length() - 1));
                    paramSb.deleteCharAt(paramSb.length() - 1);
                    setData(type, paramSb.toString());
                }
            }
        });
        dialog = builder.create();
        dialog.show();
        DialogUtils.dialogTitleLineColor(dialog);
    }

    private void setData(String type, String s) {
        if (type.equals(SysEnumsResponse.RENT)) {
            rentRange = s;
        } else if (type.equals(SysEnumsResponse.SCHEDULE)) {
            scheduleRange = s;
        }
    }


    private void startIntentActivity(int type, String value) {
        Intent it = new Intent(mContext, ModifyInfoActivity.class);
        it.putExtra(ModifyInfoActivity.MODIFY_TYPE, type);
        it.putExtra(ModifyInfoActivity.MODIFY_VALUE, value);
        startActivityForResult(it, CUSTOMER_INFO_MODIFY);
    }


    public void changeFee(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_FEE, feeTextView.getText().toString());
    }

    public void confirm(View view) {
        if (TextUtils.isEmpty(rentRange)) {
            ToastUtils.show(mContext, "请设置出租范围");
        } else if (TextUtils.isEmpty(scheduleRange)) {
            ToastUtils.show(mContext, "请设置档期");
        } else if (TextUtils.isEmpty(perHourPrice)) {
            ToastUtils.show(mContext, "请设置时薪");
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.publishing);
            dialog.show();
            HttpLoad.UserModule.publishInfo(TAG, rentRange, scheduleRange, perHourPrice, Utils
                    .getToken(mContext), new ResponseCallback<ResponseBase>(mContext) {


                @Override
                public void onRequestSuccess(ResponseBase result) {
                    dialog.dismiss();
                    ToastUtils.show(mContext, "发布成功");
                }

                @Override
                public void onReuquestFailed(String error) {
                    dialog.dismiss();
                    ToastUtils.show(mContext, error);
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    //从相册选取成功后，需要从Uri中拿出图片的绝对路径，再调用剪切
                    Uri newUri = Uri.parse("file:///" + CropUtils.getPath(this, data.getData()));
                    if (newUri != null) {
                        CropUtils.cropImageUri(this, newUri, imageUri, imageViewImageView
                                        .getWidth(),
                                imageViewImageView.getHeight(), PHOTO_REQUEST_CUT);
                    } else {
                        ToastUtils.show(this, "没有得到相册图片");
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    ToastUtils.show(this, "从相册选取取消");
                } else {
                    ToastUtils.show(this, "从相册选取失败");
                }
                break;
            case PHOTO_REQUEST_CAREMA:
                if (resultCode == RESULT_OK) {
                    //从相册选取成功后，需要从Uri中拿出图片的绝对路径，再调用剪切
                    if (imagePhotoUri != null) {
//                        CropUtils.cropImageUri(this, imagePhotoUri, imageUri,
//                                head_cvCircleImageView.getWidth
//                                        (), head_cvCircleImageView.getHeight(),
// PHOTO_REQUEST_CUT);
                    } else {
                        ToastUtils.show(this, "没有得到拍照图片");
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    ToastUtils.show(this, "取消拍照");
                } else {
                    ToastUtils.show(this, "拍照失败");
                }
                break;
            case PHOTO_REQUEST_CUT:
                if (resultCode == RESULT_OK) {
                    uploadImage();
                } else if (resultCode == RESULT_CANCELED) {
                    ToastUtils.show(this, "取消剪切图片");
                } else {
                    ToastUtils.show(this, "剪切失败");
                }
                break;
            case CUSTOMER_INFO_MODIFY:
                if (resultCode == RESULT_OK) {
                    updateInfo(data.getIntExtra(ModifyInfoActivity.MODIFY_TYPE, -1), data
                            .getStringExtra(ModifyInfoActivity.MODIFY_VALUE));
                }
                break;
            default:
                break;
        }
    }

    private void updateInfo(int type, String value) {
        switch (type) {
            case ModifyInfoActivity.TYPE_FEE:
                feeTextView.setText(value+"元/时");
                perHourPrice = value;
                break;
        }
    }

    private void uploadImage() {
        if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.uploading);
            dialog.show();
//            HttpUpload.uploadUserHeadImg(mContext, TAG, new File(CropUtils.getPath(mContext,
//                    imageUri)), "", new ResponseCallback<UploadResult>(mContext) {
//
//                @Override
//                public void onRequestSuccess(UploadResult result) {
//                    dialog.dismiss();
////                    SharedPreUtils.putString(mContext, SharedPre.User.AVATARURL, result
////                            .avatarurl);
////                    CropUtils.setHeadFromDisk(mContext, head_cvCircleImageView);
//                    ToastUtils.show(mContext, "操作成功");
//                }
//
//                @Override
//                public void onReuquestFailed(String error) {
//                    dialog.dismiss();
//                    ToastUtils.show(mContext, error);
//                }
//            }, new AndroidMultiPartEntity.ProgressListener() {
//                @Override
//                public void transferred(long num) {
//
//                }
//            });

        }
    }


}

