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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allipper.rentme.R;
import com.allipper.rentme.application.ApplicationInit;
import com.allipper.rentme.common.util.CropUtils;
import com.allipper.rentme.common.util.DialogUtils;
import com.allipper.rentme.common.util.LoadDialogUtil;
import com.allipper.rentme.common.util.SharedPre;
import com.allipper.rentme.common.util.SharedPreUtils;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.net.request.AndroidMultiPartEntity;
import com.allipper.rentme.net.HttpLoad;
import com.allipper.rentme.net.HttpUpload;
import com.allipper.rentme.net.ResponseCallback;
import com.allipper.rentme.net.response.ItemsEntity;
import com.allipper.rentme.net.response.SysEnumsResponse;
import com.allipper.rentme.net.response.UpdateUserInforResponse;
import com.allipper.rentme.net.response.UploadResult;
import com.allipper.rentme.net.response.UserInfo;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.widget.CircleImageView;

import java.io.File;
import java.util.List;

public class MineInfoActivity extends BaseActivity {
    private static final String TAG = MineInfoActivity.class.getSimpleName();

    private static final int PHOTO_REQUEST_CAREMA = 0;
    private static final int PHOTO_REQUEST_GALLERY = 1;
    private static final int PHOTO_REQUEST_CUT = 2;

    public static final int CUSTOMER_INFO_MODIFY = 3;


    //用于保存剪裁后图片的URI
    private Uri imageUri = CropUtils.buildSavedUri();
    //用于保存拍照图片的URI
    private Uri imagePhotoUri = CropUtils.buildPhotoUri();

    private RelativeLayout title_topRelativeLayout;
    private ImageView backImageView;
    private TextView titleTextView;
    private RelativeLayout head_rlRelativeLayout;
    private CircleImageView head_cvCircleImageView;
    private ImageView iconImageView;
    private LinearLayout nameLinearLayout;
    private TextView name_valueTextView;
    private LinearLayout statusLinearLayout;
    private TextView status_valueTextView;
    private LinearLayout constellationLinearLayout;
    private TextView constellation_valueTextView;
    private LinearLayout sexLinearLayout;
    private TextView sex_valueTextView;
    private LinearLayout pictureLinearLayout;
    private LinearLayout careerLinearLayout;
    private TextView career_valueTextView;
    private LinearLayout ageLinearLayout;
    private TextView age_valueTextView;
    private LinearLayout heightLinearLayout;
    private TextView height_valueTextView;
    private LinearLayout weightLinearLayout;
    private TextView weight_valueTextView;
    private LinearLayout hobbyLinearLayout;
    private TextView hobby_valueTextView;

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
    private int jobIndex;
    private String[] jobs;
    private boolean[] hobbyIndex;
    private String[] hobbies;


    private int selectedIndex;

    private UserInfo userInfoEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_info);
        initData();
        findViews();
        getDatas(false);
    }

    private void initData() {
        if (userInfoEntity == null) {
            userInfoEntity = Utils.getUserInfo(mContext);
        }
        constellations = ApplicationInit.getFormatStringArray(SysEnumsResponse.CONSTELLATION);
        sexs = ApplicationInit.getFormatStringArray(SysEnumsResponse.GENDER);
        ages = ApplicationInit.getFormatStringArray(SysEnumsResponse.AGE_RANGE);
        heights = ApplicationInit.getFormatStringArray(SysEnumsResponse.HEIGHT_RANGE);
        weights = ApplicationInit.getFormatStringArray(SysEnumsResponse.WEIGH_RANGE);
        jobs = ApplicationInit.getFormatStringArray(SysEnumsResponse.JOB);
        hobbies = ApplicationInit.getFormatStringArray(SysEnumsResponse.INTERESTS);
        hobbyIndex = new boolean[hobbies.length];
    }

    public void getDatas(boolean isShowDialog) {
        name_valueTextView.setText(userInfoEntity.nickName);
        status_valueTextView.setText(userInfoEntity.userDetail);
        constellation_valueTextView.setText(userInfoEntity.constellation);
        sex_valueTextView.setText(userInfoEntity.gender);
        career_valueTextView.setText(userInfoEntity.job);
        age_valueTextView.setText(userInfoEntity.ageRange);
        height_valueTextView.setText(userInfoEntity.heightRange);
        weight_valueTextView.setText(userInfoEntity.weightRange);
        hobby_valueTextView.setText(userInfoEntity.interests);
    }

    private void findViews() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        titleTextView = (TextView) findViewById(R.id.title);
        head_rlRelativeLayout = (RelativeLayout) findViewById(R.id.head_rl);
        head_cvCircleImageView = (CircleImageView) findViewById(R.id.head_cv);
        iconImageView = (ImageView) findViewById(R.id.icon);
        nameLinearLayout = (LinearLayout) findViewById(R.id.name);
        name_valueTextView = (TextView) findViewById(R.id.name_value);
        statusLinearLayout = (LinearLayout) findViewById(R.id.status);
        status_valueTextView = (TextView) findViewById(R.id.status_value);
        constellationLinearLayout = (LinearLayout) findViewById(R.id.constellation);
        constellation_valueTextView = (TextView) findViewById(R.id.constellation_value);
        sexLinearLayout = (LinearLayout) findViewById(R.id.sex);
        sex_valueTextView = (TextView) findViewById(R.id.sex_value);
        pictureLinearLayout = (LinearLayout) findViewById(R.id.picture);
        careerLinearLayout = (LinearLayout) findViewById(R.id.career);
        career_valueTextView = (TextView) findViewById(R.id.career_value);
        ageLinearLayout = (LinearLayout) findViewById(R.id.age);
        age_valueTextView = (TextView) findViewById(R.id.age_value);
        heightLinearLayout = (LinearLayout) findViewById(R.id.height);
        height_valueTextView = (TextView) findViewById(R.id.height_value);
        weightLinearLayout = (LinearLayout) findViewById(R.id.weight);
        weight_valueTextView = (TextView) findViewById(R.id.weight_value);
        hobbyLinearLayout = (LinearLayout) findViewById(R.id.hobby);
        hobby_valueTextView = (TextView) findViewById(R.id.hobby_value);


        backImageView.setOnClickListener(this);
        head_cvCircleImageView.setOnClickListener(this);
        name_valueTextView.setOnClickListener(this);
        status_valueTextView.setOnClickListener(this);
        constellation_valueTextView.setOnClickListener(this);
        sex_valueTextView.setOnClickListener(this);
        findViewById(R.id.toPicture).setOnClickListener(this);
        career_valueTextView.setOnClickListener(this);
        age_valueTextView.setOnClickListener(this);
        height_valueTextView.setOnClickListener(this);
        weight_valueTextView.setOnClickListener(this);
        hobby_valueTextView.setOnClickListener(this);


        CropUtils.setHeadFromDisk(this, head_cvCircleImageView);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.head_cv:
                changeHead(view);
                break;
            case R.id.name_value:
                changeName(view);
                break;
            case R.id.status_value:
                changeStatus(view);
                break;
            case R.id.constellation_value:
                changeConstellatione(view);
                break;
            case R.id.sex_value:
                changeSex(view);
                break;
            case R.id.toPicture:
                changePicture(view);
                break;
            case R.id.career_value:
                changeCareer(view);
                break;
            case R.id.age_value:
                changeAge(view);
                break;
            case R.id.height_value:
                changeHeight(view);
                break;
            case R.id.weight_value:
                changeWeight(view);
                break;
            case R.id.hobby_value:
                changeHobbi(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    public void changeHead(View view) {
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


    public void changeName(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_NAME, name_valueTextView.getText().toString());
    }

    public void changeStatus(View view) {
        startIntentActivity(ModifyInfoActivity.TYPE_STATUS, status_valueTextView.getText()
                .toString());
    }

    public void changeConstellatione(View view) {
        selectedIndex = constellationIndex = processIndex(constellation_valueTextView.getText()
                .toString(), ApplicationInit.getConstellationEntities().items);
        showAlertDialog(constellationIndex, constellations, "请选择星座", constellation_valueTextView,
                ApplicationInit.getConstellationEntities().items, SysEnumsResponse.CONSTELLATION);
    }

    public void changeHobbi(View view) {
        for (int i = 0; i < hobbyIndex.length; i++) {
            hobbyIndex[i] = false;
        }
        String str = hobby_valueTextView.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            String[] strs = str.split("、");
            for (String temp : strs) {
                int i = 0;
                for (ItemsEntity temp1 : ApplicationInit.getInterestEntities().items) {
                    if (temp.equals(temp1.name)) {
                        hobbyIndex[i] = true;
                        break;
                    }
                    i++;
                }
            }
        }
        showAlertDialog(hobbyIndex, hobbies, "请选择兴趣爱好", hobby_valueTextView,
                ApplicationInit.getInterestEntities().items, SysEnumsResponse.INTERESTS);
    }

    public void changeSex(View view) {
        selectedIndex = sexIndex = processIndex(sex_valueTextView.getText().toString(),
                ApplicationInit
                        .getGenderEntities().items);
        showAlertDialog(sexIndex, sexs, "请选择性别", sex_valueTextView, ApplicationInit
                .getGenderEntities().items, SysEnumsResponse.GENDER);
    }

    public void changeCareer(View view) {
        selectedIndex = jobIndex = processIndex(career_valueTextView.getText().toString(),
                ApplicationInit
                        .getJobEntities().items);
        showAlertDialog(jobIndex, jobs, "请选择职业", career_valueTextView, ApplicationInit
                .getJobEntities().items, SysEnumsResponse.JOB);
    }

    public void changeAge(View view) {
        selectedIndex = ageIndex = processIndex(age_valueTextView.getText().toString(),
                ApplicationInit
                        .getAgeEntities().items);
        showAlertDialog(ageIndex, ages, "请选择年龄", age_valueTextView, ApplicationInit
                .getAgeEntities().items, SysEnumsResponse.AGE_RANGE);
    }

    public void changeHeight(View view) {
        selectedIndex = heightIndex = processIndex(height_valueTextView.getText().toString(),
                ApplicationInit
                        .getHeightEntities().items);
        showAlertDialog(heightIndex, heights, "请选择身高", height_valueTextView, ApplicationInit
                .getHeightEntities().items, SysEnumsResponse.HEIGHT_RANGE);
    }

    public void changeWeight(View view) {
        selectedIndex = weightIndex = processIndex(weight_valueTextView.getText().toString(),
                ApplicationInit
                        .getWeightEntities().items);
        showAlertDialog(weightIndex, weights, "请选择体重", weight_valueTextView, ApplicationInit
                .getWeightEntities().items, SysEnumsResponse.WEIGH_RANGE);
    }


    private int processIndex(String value, final List<ItemsEntity>
            itemsEntities) {
        int index = 0;
        if (!TextUtils.isEmpty(value)) {
            for (ItemsEntity data : itemsEntities) {
                if (data.name.equals(value)) {
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
    private void showAlertDialog(final int index,
                                 final String[] datas,
                                 String title,
                                 final TextView view,
                                 final List<ItemsEntity>
                                         itemsEntities,
                                 final String type) {
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
                view.setText(itemsEntities.get(selectedIndex).name);
                updateUserInfo(type, itemsEntities.get(selectedIndex).value);
            }
        });
        dialog = builder.create();
        dialog.show();
        DialogUtils.dialogTitleLineColor(dialog);
    }

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
                    updateUserInfo(type, paramSb.toString());
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    //从相册选取成功后，需要从Uri中拿出图片的绝对路径，再调用剪切
                    Uri newUri = Uri.parse("file:///" + CropUtils.getPath(this, data.getData()));
                    if (newUri != null) {
                        CropUtils.cropImageUri(this, newUri, imageUri, head_cvCircleImageView
                                        .getWidth(),
                                head_cvCircleImageView.getHeight(), PHOTO_REQUEST_CUT);
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
                        CropUtils.cropImageUri(this, imagePhotoUri, imageUri,
                                head_cvCircleImageView.getWidth
                                        (), head_cvCircleImageView.getHeight(), PHOTO_REQUEST_CUT);
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
            case ModifyInfoActivity.TYPE_NAME:
                name_valueTextView.setText(value);
                break;
            case ModifyInfoActivity.TYPE_STATUS:
                status_valueTextView.setText(value);
                break;
        }
    }

    private void updateUserInfo(String type, int value) {
        updateUserInfo(type, String.valueOf(value));
    }

    private void updateUserInfo(String type, String value) {
        if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.updating);
            dialog.show();
            HttpLoad.UserModule.updateUserInfor(TAG, type, value, Utils.getToken
                    (mContext), new
                    ResponseCallback<UpdateUserInforResponse>(mContext) {

                        @Override
                        public void onRequestSuccess(UpdateUserInforResponse result) {
                            Utils.saveUserInfor(mContext, result.data);
                            dialog.dismiss();
                        }

                        @Override
                        public void onReuquestFailed(String error) {
                            dialog.dismiss();
                            ToastUtils.show(mContext, error);
                        }
                    });

        }
    }

    private void uploadImage() {
        if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.uploading);
            dialog.show();
            HttpUpload.uploadUserHeadImg(TAG, new File(CropUtils.getPath(mContext,
                    imageUri)), Utils.getToken
                    (mContext), new ResponseCallback<UploadResult>(mContext) {

                @Override
                public void onRequestSuccess(UploadResult result) {
                    dialog.dismiss();
                    SharedPreUtils.putString(mContext, SharedPre.User.AVATARURL, result
                            .data.avatarUrl);
                    CropUtils.setHeadFromDisk(mContext, head_cvCircleImageView);
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

