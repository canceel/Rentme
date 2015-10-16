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
import com.allipper.rentme.common.util.CropUtils;
import com.allipper.rentme.common.util.DialogUtils;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.SharedPre;
import com.allipper.rentme.common.util.SharedPreUtils;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.AndroidMultiPartEntity;
import com.allipper.rentme.net.HttpUpload;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.net.response.UploadResult;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.widget.NoRequsetGridView;

import java.io.File;

public class MinePublishInfoActivity extends BaseActivity {
    private static final String TAG = MinePublishInfoActivity.class.getSimpleName();

    private static final int PHOTO_REQUEST_CAREMA = 0;
    private static final int PHOTO_REQUEST_GALLERY = 1;
    private static final int PHOTO_REQUEST_CUT = 2;

    public static final int CUSTOMER_INFO_MODIFY = 3;

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
    private TextView careerTextView;
    private TextView ageTextView;
    private TextView tallTextView;
    private TextView weightTextView;
    private TextView offercontentTextView;
    private TextView scheduleTextView;
    private TextView hobbyTextView;
    private TextView feeTextView;
    private TextView statusTextView;
    private RelativeLayout picturebgRelativeLayout;
    private ImageView deleteImageView;
    private HorizontalScrollView hsvHorizontalScrollView;
    private NoRequsetGridView bigPicturesNoRequsetGridView;

    private int constellationIndex;
    private String[] constellations;
    private int sexIndex;
    private String[] sexs;
    private int ageIndex;
    private String[] ages;
    private int heightIndex;
    private String[] heights;
    private int weightIndex;
    private String[] weights;
    private boolean[] offerContentIndex;
    private String[] offerContents;

    private int selectedIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_publish_info);
        constellations = getResources().getStringArray(R.array.select_constellation);
        sexs = getResources().getStringArray(R.array.select_sex);
        ages = getResources().getStringArray(R.array.select_age);
        heights = getResources().getStringArray(R.array.select_height);
        weights = getResources().getStringArray(R.array.select_weight);
        offerContents = getResources().getStringArray(R.array.select_offercontent);
        offerContentIndex = new boolean[offerContents.length];
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

    }

    //���õ���¼�
    public void back(View view) {
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


    public void changeCareer(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_CAREER, careerTextView.getText()
                .toString());
    }

    public void changeAge(View view) {
        selectedIndex = ageIndex = processIndex(ageTextView.getText().toString(), ages);
        showAlertDialog(ageIndex, ages, "请选择年龄", ageTextView);
    }

    public void changeHeight(View view) {
        selectedIndex = heightIndex = processIndex(tallTextView.getText().toString(),
                heights);
        showAlertDialog(heightIndex, heights, "请选择身高", tallTextView);
    }

    public void changeWeight(View view) {
        selectedIndex = weightIndex = processIndex(weightTextView.getText().toString(),
                weights);
        showAlertDialog(weightIndex, weights, "请选择体重", weightTextView);
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
                for (String temp1 : offerContents) {
                    if (temp.equals(temp1)) {
                        offerContentIndex[i] = true;
                        break;
                    }
                    i++;
                }
            }
        }
        showAlertDialog(offerContentIndex, offerContents, "请选择出租范围", offercontentTextView);
    }

    public void changeSchedule(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_SCHEDULE, scheduleTextView.getText()
                .toString());
    }

    private int processIndex(String value, String[] datas) {
        int index = 0;
        if (!TextUtils.isEmpty(value)) {
            for (String data : datas) {
                if (data.equals(value)) {
                    break;
                }
                index++;
            }
        }
        return index;
    }

    /**
     * 创建选择对话框
     */
    private void showAlertDialog(final int index, final String[] datas, String title, final
    TextView view) {
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
        builder.setTitle(title);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                view.setText(datas[selectedIndex]);
            }
        });
        dialog = builder.create();
        dialog.show();
        DialogUtils.dialogTitleLineColor(dialog);
    }

    /**
     * 创建选择对话框
     */
    private void showAlertDialog(final boolean[] indexs, final String[] datas, String title, final
    TextView view) {
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
                int i = 0;
                for (boolean index : indexs) {
                    if (index) {
                        sb.append(datas[i++]).append("、");
                    } else {
                        i++;
                    }
                }
                if (sb.length() > 0) {
                    view.setText(sb.deleteCharAt(sb.length() - 1));
                }
            }
        });
        dialog = builder.create();
        dialog.show();
        DialogUtils.dialogTitleLineColor(dialog);
    }


    private void startIntentActivity(int type, String value) {
        Intent it = new Intent(mContext, ModifyInfoActivity.class);
        it.putExtra(ModifyInfoActivity.MODIFY_TYPE, type);
        it.putExtra(ModifyInfoActivity.MODIFY_VALUE, value);
        startActivityForResult(it, CUSTOMER_INFO_MODIFY);
    }


    public void changeHobbi(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_HOBBI, hobbyTextView.getText().toString());
    }

    public void changeFee(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_FEE, feeTextView.getText().toString());
    }

    public void changeStatus(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_STATUS, statusTextView.getText().toString());
    }

    public void delete(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    //从相册选取成功后，需要从Uri中拿出图片的绝对路径，再调用剪切
                    Uri newUri = Uri.parse("file:///" + CropUtils.getPath(this, data.getData()));
//                    if (newUri != null) {
//                        CropUtils.cropImageUri(this, newUri, imageUri, head_cvCircleImageView
//                                        .getWidth(),
//                                head_cvCircleImageView.getHeight(), PHOTO_REQUEST_CUT);
//                    } else {
//                        ToastUtils.show(this, "没有得到相册图片");
//                    }
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
            case ModifyInfoActivity.TYPE_SCHEDULE:
                scheduleTextView.setText(value);
                break;
            case ModifyInfoActivity.TYPE_FEE:
                feeTextView.setText(value);
                break;
            case ModifyInfoActivity.TYPE_HOBBI:
                hobbyTextView.setText(value);
                break;
            case ModifyInfoActivity.TYPE_STATUS:
                statusTextView.setText(value);
                break;
        }
    }

    private void uploadImage() {
        if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.uploading);
            dialog.show();
            HttpUpload.uploadUserHeadImg(mContext, TAG, new File(CropUtils.getPath(mContext,
                    imageUri)), "", new ResponseCallback<UploadResult>(mContext) {

                @Override
                public void onRequestSuccess(UploadResult result) {
                    dialog.dismiss();
                    SharedPreUtils.putString(mContext, SharedPre.User.AVATAR_URL, result
                            .avatarurl);
//                    CropUtils.setHeadFromDisk(mContext, head_cvCircleImageView);
                    ToastUtils.show(mContext, "操作成功");
                }

                @Override
                public void onReuquestFailed(String error) {
                    dialog.dismiss();
                    ToastUtils.show(mContext, error);
                }
            }, new AndroidMultiPartEntity.ProgressListener() {
                @Override
                public void transferred(long num) {

                }
            });

        }
    }


}

