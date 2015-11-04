package com.allipper.rentme.ui.mine;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.allipper.rentme.R;
import com.allipper.rentme.adapter.MineChangePicturesAdapter;
import com.allipper.rentme.adapter.MinePicturesAdapter;
import com.allipper.rentme.adapter.PicturesAdapter;
import com.allipper.rentme.bean.ActionBean;
import com.allipper.rentme.bean.ImageBean;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.ui.base.BaseActivity;
import com.allipper.rentme.widget.MinePicturesMenuPopupWindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MinePicturesActivity extends BaseActivity implements MinePicturesMenuPopupWindow
        .OnMenuItemSelected, MinePicturesAdapter.OnClick {
    private static final String TAG = MinePicturesActivity.class.getSimpleName();
    private GridView mGirdView;
    private RelativeLayout topTitleRl;
    private RelativeLayout bottomeRl;
    private MineChangePicturesAdapter mAdapter;
    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ActionBean> mMenuActionBeans = new ArrayList<ActionBean>();


    private MinePicturesMenuPopupWindow picturesMenuPopupWindow;

    private MinePicturesAdapter adapter;
    private RelativeLayout pictrueBgRl;
    private GridView bigPictruesGv;
    private ImageView closeIv;
    private ImageView editIv;
    private Button cancelButton;
    private Button selectAllButton;
    private HorizontalScrollView pictureHsv;

    private List<ImageBean> pictureUrls = new ArrayList<>();


    /**
     * 初始化展示文件夹的popupWindw
     */
    private void initListMenuPopupWindw() {
        ActionBean abd = new ActionBean();
        abd.action = "delete";
        abd.icon = R.mipmap.icon_add;
        abd.title = "删除";
        ActionBean aba = new ActionBean();
        aba.action = "add";
        aba.icon = R.mipmap.icon_add;
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

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    @Override
    public void getTestDatas(boolean isShowDialog) {
        for (int i = 0; i < 5; i++) {
            ImageBean ib = new ImageBean();
            ib.url = "uri" + i;
            pictureUrls.add(ib);
        }

        List<String> pictureUrlStrs = new ArrayList<>(15);
        pictureUrlStrs.add("1");
        pictureUrlStrs.add("2");
        pictureUrlStrs.add("2");
        pictureUrlStrs.add("2");
        pictureUrlStrs.add("2");
        int ii = pictureUrls.size();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ii * (Utils
                .getScreenWidth(mContext)), ViewGroup.LayoutParams.WRAP_CONTENT);
        bigPictruesGv.setLayoutParams(params);
        bigPictruesGv.setColumnWidth(Utils.getScreenWidth(mContext) / 3 - 10);
        bigPictruesGv.setVerticalSpacing(5);
        bigPictruesGv.setNumColumns(ii);
        bigPictruesGv.setAdapter(new PicturesAdapter(mContext, pictureUrlStrs, PicturesAdapter
                .TYPE_OTHER));

        if (adapter == null) {
            adapter = new MinePicturesAdapter(mContext, pictureUrls, 0, this);
            adapter.pictrueBgRl = pictrueBgRl;
            adapter.pictureHsv = pictureHsv;
            adapter.width = Utils.getScreenWidth(mContext);
            mGirdView.setAdapter(adapter);
        } else {
            adapter.setData(pictureUrls);
        }
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
        closeIv = (ImageView) findViewById(R.id.close);
        editIv = (ImageView) findViewById(R.id.edit);
        cancelButton = (Button) findViewById(R.id.cancel);
        selectAllButton = (Button) findViewById(R.id.selectAll);
        pictureHsv = (HorizontalScrollView) findViewById(R.id.hsv);

        findViewById(R.id.back).setOnClickListener(this);
        editIv.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        selectAllButton.setOnClickListener(this);
        closeIv.setOnClickListener(this);
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
            case R.id.close:
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
        } else {
            super.processExit();
        }
    }
}

