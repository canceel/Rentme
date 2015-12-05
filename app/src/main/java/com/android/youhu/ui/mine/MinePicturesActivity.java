package com.android.youhu.ui.mine;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.youhu.R;
import com.android.youhu.adapter.MinePicturesAdapter;
import com.android.youhu.adapter.PicturesAdapter;
import com.android.youhu.bean.ActionBean;
import com.android.youhu.bean.ImageBean;
import com.android.youhu.common.util.LoadDialogUtil;
import com.android.youhu.common.util.SharedPre;
import com.android.youhu.common.util.SharedPreUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.ui.base.BaseActivity;
import com.android.youhu.widget.MinePicturesMenuPopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MinePicturesActivity extends BaseActivity implements MinePicturesMenuPopupWindow
        .OnMenuItemSelected, MinePicturesAdapter.OnClick {
    private static final String TAG = MinePicturesActivity.class.getSimpleName();
    private GridView mGirdView;
    private RelativeLayout topTitleRl;
    private RelativeLayout bottomeRl;

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ActionBean> mMenuActionBeans = new ArrayList<ActionBean>();


    private MinePicturesMenuPopupWindow picturesMenuPopupWindow;

    private MinePicturesAdapter adapter;
    private PicturesAdapter picturesAdapter;
    private RelativeLayout pictrueBgRl;
    private GridView bigPictruesGv;
    private ImageView editIv;
    private Button cancelButton;
    private Button selectAllButton;
    private HorizontalScrollView pictureHsv;

    private List<ImageBean> pictureUrls = new ArrayList<>();
    private List<String> pictureUrlStrs = new ArrayList<>();

    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListMenuPopupWindw() {
        ActionBean abd = new ActionBean();
        abd.action = "delete";
        abd.iconStr = "-";
        abd.title = "删除";
        ActionBean aba = new ActionBean();
        aba.action = "add";
        aba.iconStr = "+";
        aba.title = "新增";
        mMenuActionBeans.add(aba);
        mMenuActionBeans.add(abd);
        picturesMenuPopupWindow = new MinePicturesMenuPopupWindow(mContext, 120, 40 *
                mMenuActionBeans.size(),
                mMenuActionBeans, LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.adapter_pictures_list_dir, null));
        // 设置选择文件夹的回调
        picturesMenuPopupWindow.setMenuItemSelected(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_pictures);
        initListMenuPopupWindw();
        initView();
        getDatas(true);

    }


    public void getDatas(boolean isShowDialog) {
        final Dialog dialog = LoadDialogUtil.createLoadingDialog(mContext, R.string.loading);
        if (isShowDialog) {
            dialog.show();
        }
        String albumStr = SharedPreUtils.getString(mContext, SharedPre.User.ALBUM);
        if (!TextUtils.isEmpty(albumStr)) {
            String[] album = albumStr.split(";");
            for (int i = 0; i < album.length; i++) {
                ImageBean ib = new ImageBean();
                ib.url = album[i];
                pictureUrls.add(ib);
                pictureUrlStrs.add(album[i]);
            }
        }
        setDataToView(dialog);
    }

    public void setDataToView(Dialog dialog) {
        if (pictureUrls.size() > 0) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pictureUrls.size() *
                    (Utils
                            .getScreenWidth(mContext)), ViewGroup.LayoutParams.WRAP_CONTENT);
            bigPictruesGv.setLayoutParams(params);
            bigPictruesGv.setColumnWidth(Utils.getScreenWidth(mContext) / 3 - 10);
            bigPictruesGv.setVerticalSpacing(5);
            bigPictruesGv.setNumColumns(pictureUrls.size());
            if (picturesAdapter == null) {
                picturesAdapter = new PicturesAdapter(mContext, pictureUrlStrs, PicturesAdapter
                        .TYPE_OTHER);
                bigPictruesGv.setAdapter(picturesAdapter);
            } else {
                picturesAdapter.setData(pictureUrlStrs);
            }

            if (adapter == null) {
                adapter = new MinePicturesAdapter(mContext, pictureUrls, 0, this);
                adapter.pictrueBgRl = pictrueBgRl;
                adapter.pictureHsv = pictureHsv;
                adapter.width = Utils.getScreenWidth(mContext) - 2;
                mGirdView.setAdapter(adapter);
            } else {
                adapter.setData(pictureUrls);
            }
        }
        dialog.dismiss();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mGirdView = (GridView) findViewById(R.id.pictures);
        topTitleRl = (RelativeLayout) findViewById(R.id.title_top);
        bottomeRl = (RelativeLayout) findViewById(R.id.bottom);
        pictrueBgRl = (RelativeLayout) findViewById(R.id.picturebg);
        bigPictruesGv = (GridView) findViewById(R.id.bigPictures);
        editIv = (ImageView) findViewById(R.id.edit);
        cancelButton = (Button) findViewById(R.id.cancel);
        selectAllButton = (Button) findViewById(R.id.selectAll);
        pictureHsv = (HorizontalScrollView) findViewById(R.id.hsv);

        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        editIv.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        pictureHsv.setOnClickListener(this);
        selectAllButton.setOnClickListener(this);
        pictrueBgRl.setOnClickListener(this);
        bigPictruesGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                close(null);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.edit:
                showMenu(view);
                break;
            case R.id.cancel:
                cancel(view);
                break;
            case R.id.selectAll:
                selectAll(view);
                break;
            case R.id.delete:
                delete(view);
                break;
            case R.id.picturebg:
                close(view);
                break;
            default:
                super.onClick(view);
                break;
        }
    }

    public void showMenu(View view) {
        picturesMenuPopupWindow
                .setAnimationStyle(R.style.AnimPopupMenu);
        int[] location = new int[2];
        topTitleRl.getLocationOnScreen(location);

        picturesMenuPopupWindow.showAtLocation(topTitleRl, Gravity.NO_GRAVITY, location[0] +
                topTitleRl.getWidth(), location[1] + topTitleRl.getHeight());
    }

    public void delete(View view) {

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void cancel(View view) {
        if (adapter != null) {
            adapter.status = 0;
            adapter.notifyDataSetChanged();
            adapter.mSelectedImage.clear();
        }
        bottomeRl.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out));
        bottomeRl.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        editIv.setVisibility(View.VISIBLE);
    }

    public void close(View view) {
        if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.VISIBLE) {
            pictrueBgRl.setVisibility(View.GONE);
            pictrueBgRl.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim
                    .alpha_out));
        }
    }

    public void selectAll(View view) {
        if (adapter.mSelectedImage.size() == pictureUrls.size()) {
            adapter.mSelectedImage.clear();
            selectAllButton.setText("全选");
        } else {
            selectAllButton.setText("反选");
            adapter.mSelectedImage.clear();
            for (ImageBean ib : pictureUrls) {
                adapter.mSelectedImage.add(ib.url);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selected(ActionBean bean) {
        picturesMenuPopupWindow.dismiss();
        if (bean.action.equals("delete")) {
            if (adapter != null) {
                adapter.status = 1;
                adapter.notifyDataSetChanged();
            }
            cancelButton.setVisibility(View.VISIBLE);
            editIv.setVisibility(View.GONE);
            bottomeRl.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in));
            bottomeRl.setVisibility(View.VISIBLE);
        } else if (bean.action.equals("add")) {
            Intent it = new Intent(mContext, ChangePicturesActivity.class);
            startActivityForResult(it, 100);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (data != null && data.getBooleanExtra("isChanged", false)) {
                    getDatas(true);
                }
        }
    }

    @Override
    public void onMyClick() {
        if (adapter != null) {
            if (adapter.mSelectedImage.size() == pictureUrls.size()) {
                selectAllButton.setText("反选");
            } else {
                selectAllButton.setText("全选");
            }
        }
    }

    @Override
    public void processExit() {
        if (pictrueBgRl != null && pictrueBgRl.getVisibility() == View.VISIBLE) {
            pictrueBgRl.setVisibility(View.GONE);
            pictrueBgRl.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim
                    .alpha_out));
        } else {
            super.processExit();
        }
    }
}

