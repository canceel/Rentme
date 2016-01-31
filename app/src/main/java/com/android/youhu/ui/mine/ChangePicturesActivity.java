package com.android.youhu.ui.mine;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.adapter.MineChangePicturesAdapter;
import com.android.youhu.bean.ImageFloder;
import com.android.youhu.common.util.CropUtils;
import com.android.youhu.common.util.ImageFactory;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.SharedPre;
import com.android.youhu.common.util.SharedPreUtils;
import com.android.youhu.common.util.ToastUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.HttpUpload;
import com.android.youhu.net.ResponseCallback;
import com.android.youhu.net.request.AndroidMultiPartEntity;
import com.android.youhu.net.response.UploadPictureResult;
import com.android.youhu.ui.base.BaseActivity;
import com.android.youhu.widget.ListImageDirPopupWindow;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ChangePicturesActivity extends BaseActivity implements ListImageDirPopupWindow
        .OnImageDirSelected {
    private static final String TAG = ChangePicturesActivity.class.getSimpleName();

    private ProgressDialog mProgressDialog;

    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<String> mImgs;

    private GridView mGirdView;
    private MineChangePicturesAdapter mAdapter;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

    private RelativeLayout mBottomLy;

    private TextView mChooseDir;
    private TextView mImageCount;
    int totalCount = 0;

    private int mScreenHeight;

    private ListImageDirPopupWindow mListImageDirPopupWindow;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mProgressDialog.dismiss();
            // 为View绑定数据
            data2View();
            // 初始化展示文件夹的popupWindw
            initListDirPopupWindw();
        }
    };


    /**
     * 为View绑定数据
     */
    private void data2View() {
        if (mImgDir == null) {
            ToastUtils.show(getApplicationContext(), "擦，一张图片没扫描到");
            return;
        }

        mImgs = Arrays.asList(mImgDir.list());
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MineChangePicturesAdapter(getApplicationContext(), mImgs,
                mImgDir.getAbsolutePath());
        mGirdView.setAdapter(mAdapter);
        mImageCount.setText(totalCount + "张");
    }

    ;

    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListDirPopupWindw() {
        mListImageDirPopupWindow = new ListImageDirPopupWindow(
                LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
                mImageFloders, LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.adapter_pictures_list_dir, null));

        mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        // 设置选择文件夹的回调
        mListImageDirPopupWindow.setOnImageDirSelected(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pictures);

        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        mScreenHeight = outMetrics.heightPixels;

        initView();
        getImages();
        initEvent();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.upload:
                uploadAlbum(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    private void uploadAlbum(View view) {
        String album = SharedPreUtils.getString(mContext, SharedPre.User.ALBUM);
        if (!TextUtils.isEmpty(album)) {
            String[] strs = album.split(";");
            if (strs.length + mAdapter.mSelectedImage.size() > 5) {
                ToastUtils.show(mContext, "目前相册只支持最多5张");
                return;
            }
        }
        if (mAdapter == null || mAdapter.mSelectedImage == null || mAdapter.mSelectedImage.size()
                == 0) {
            ToastUtils.show(mContext, "请选择上传图片");
        } else if (mAdapter.mSelectedImage.size() > 5) {
            ToastUtils.show(mContext, "最多只能上传5张照片");
        } else if (Utils.isNetworkConnected(mContext)) {
            final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext);
            dialog.show();
            uploadOne(0, mAdapter.mSelectedImage.size() - 1 == 0, dialog);
        }
    }


    boolean isCompressed = false;

    private void uploadOne(int i, final boolean isLast, final Dialog dialog) {
        final int next = i + 1;
        final File file = new File(mAdapter.mSelectedImage.get(i));
//        isCompressed = false;
//        if (file != null && file.exists() && file.length() > 200 * 1024) {
//            String temp = CropUtils.getOrCreateFileInExternalStorage() + File.separator + file
//                    .getName();
//            try {
//                ImageFactory.compressAndGenImage(mAdapter.mSelectedImage.get(i), temp, 200 *
//                        1024, false);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            file = new File(temp);
//            isCompressed = true;
//        }
//        final File finalFile = file;
        HttpUpload.uploadUserPicture(TAG, file, Utils
                .getToken(mContext), new ResponseCallback<UploadPictureResult>(mContext) {
            @Override
            public void onRequestSuccess(UploadPictureResult result) {
//                if (isCompressed) {
//                    finalFile.delete();
//                }
                StringBuffer sb = new StringBuffer();
                if (result.data.album != null && result.data.album.size() > 0) {
                    for (String str : result.data.album) {
                        sb.append(str).append(";");
                    }
                }
                SharedPreUtils.putString(mContext, SharedPre.User.ALBUM, sb.toString());
                if (isLast) {
                    dialog.dismiss();
                    ToastUtils.show(mContext, "上传成功");
                    mAdapter.mSelectedImage.clear();
                    mAdapter.notifyDataSetChanged();
                } else {
                    uploadOne(next, next == mAdapter.mSelectedImage.size() - 1, dialog);
                }
            }

            @Override
            public void onReuquestFailed(String error) {
//                if (isCompressed) {
//                    finalFile.delete();
//                }
                dialog.dismiss();
                ToastUtils.show(mContext, error);
            }
        }, new AndroidMultiPartEntity.ProgressListener() {

            @Override
            public void transferred(long num) {

            }
        });
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            ToastUtils.show(this, "暂无外部存储");
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(this, null, "正在加载...");

        new Thread(new Runnable() {
            @Override
            public void run() {

                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = ChangePicturesActivity.this
                        .getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                Log.e("TAG", mCursor.getCount() + "");
                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    Log.e("TAG", path);
                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;
                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;
                    String dirPath = parentFile.getAbsolutePath();
                    ImageFloder imageFloder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        // 初始化imageFloder
                        imageFloder = new ImageFloder();
                        imageFloder.setDir(dirPath);
                        imageFloder.setFirstImagePath(path);
                    }
                    String[] fileNames = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    });
                    int picSize = fileNames == null ? 0 : fileNames.length;
                    totalCount += picSize;

                    imageFloder.setCount(picSize);
                    mImageFloders.add(imageFloder);

                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFile;
                    }
                }
                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;

                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);

            }
        }).start();

    }

    /**
     * 初始化View
     */
    private void initView() {
        mGirdView = (GridView) findViewById(R.id.pictures);
        mChooseDir = (TextView) findViewById(R.id.id_choose_dir);
        mImageCount = (TextView) findViewById(R.id.id_total_count);
        mBottomLy = (RelativeLayout) findViewById(R.id.id_bottom_ly);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.upload).setOnClickListener(this);
    }

    private void initEvent() {
        /**
         * 为底部的布局设置点击事件，弹出popupWindow
         */
        mBottomLy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListImageDirPopupWindow
                        .setAnimationStyle(R.style.AnimPopupDir);
                mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);

                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = .3f;
                getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    public void selected(ImageFloder floder) {

        mImgDir = new File(floder.getDir());
        mImgs = Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        }));
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        mAdapter = new MineChangePicturesAdapter(getApplicationContext(), mImgs, mImgDir
                .getAbsolutePath());
        mGirdView.setAdapter(mAdapter);
        // mAdapter.notifyDataSetChanged();
        mImageCount.setText(floder.getCount() + "张");
        mChooseDir.setText(floder.getName());
        mListImageDirPopupWindow.dismiss();

    }
}

