package com.allipper.rentme.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.allipper.rentme.R;
import com.allipper.rentme.bean.FilterItem;
import com.allipper.rentme.bean.FilterSubItem;
import com.allipper.rentme.common.util.Logger;
import com.allipper.rentme.common.util.ToastUtils;
import com.allipper.rentme.common.util.Utils;
import com.allipper.rentme.database.DbManager;
import com.allipper.rentme.fragment.HomePagerFragment;
import com.allipper.rentme.fragment.MineFragment;
import com.allipper.rentme.ui.base.FragmentBaseActivity;
import com.allipper.rentme.ui.login.CurrentCityActivity;
import com.allipper.rentme.ui.login.LoginActivity;
import com.allipper.rentme.ui.mine.SysSettingActivity;
import com.allipper.rentme.widget.MyFilterPopupWindow;

import java.util.ArrayList;
import java.util.HashMap;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

public class IndexActivity extends FragmentBaseActivity implements View.OnClickListener {

    public static final String UPDATE_USER_INFO = "update_user_info";
    public final static int ACTIVITY_LOGIN = 100;

    String Token = "/vF9at/zPgLACf2atvUXXLSUXIuhYrpnL2rv6v3TqMTdk6sO8EkeHI7KLV0+vJQqQ90T/Ijz" +
            "+lKSO4/TnNq1Hw==";//test
    private static long BACK_PRESSED;
    private static final int TAB_HOME = 0;
    private static final int TAB_MESSAGE = 1;
    private static final int TAB_MINE = 2;
    private HomePagerFragment homeFragment;
    private ConversationListFragment msgFragment;
    private MineFragment mineFragment;

    private Button titleBtn;
    private ImageView filterIv;
    //底部导航栏控件
    private LinearLayout homeTabLl;
    private LinearLayout msgTabLl;
    private LinearLayout mineTabLl;
    private MyFilterPopupWindow filterPopup;

    private FragmentManager fragmentManager;
    private DbManager db;
    //记录Fragment的位置
    private int position = 0;
    private ArrayList<FilterItem> filterItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        fragmentManager = getSupportFragmentManager();
        db = new DbManager(this);
        registerAction();
        findViews();
        setIMkitConnection();
        setIMkitUserInfo();
        setTabSelection(position);
    }

    private void registerAction() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_USER_INFO);
        registerReceiver(mReceiver, filter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(UPDATE_USER_INFO.equals(intent.getAction())){
                if(mineFragment != null){
                    mineFragment.updateUserInfo();
                }
            }
        }
    };

    private void setIMkitUserInfo() {
        /**
         * 设置用户信息的提供者，供 RongIM 调用获取用户名称和头像信息。
         *
         * @param userInfoProvider 用户信息提供者。
         * @param isCacheUserInfo  设置是否由 IMKit 来缓存用户信息。<br>
         *                         如果 App 提供的 UserInfoProvider。
         *                         每次都需要通过网络请求用户数据，而不是将用户数据缓存到本地内存，会影响用户信息的加载速度；<br>
         *                         此时最好将本参数设置为 true，由 IMKit 将用户信息缓存到本地内存中。
         * @see UserInfoProvider
         */
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {

                return new UserInfo("123", "abc", null);//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
    }

    private void setIMkitConnection() {
        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    private void initIMkitMsg(FragmentTransaction transaction) {
        msgFragment = ConversationListFragment.getInstance();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName()
                        , "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(),
                        "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION
                        .getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(),
                        "false")//设置系统会话非聚合显示
                .build();

        msgFragment.setUri(uri);
        transaction.add(R.id.fragment_container, msgFragment);
    }


    private void findViews() {
        homeTabLl = (LinearLayout) findViewById(R.id.home_tab_ll);
        msgTabLl = (LinearLayout) findViewById(R.id.msg_tab_ll);
        mineTabLl = (LinearLayout) findViewById(R.id.mine_tab_ll);
        titleBtn = (Button) findViewById(R.id.gps_location_btn);
        filterIv = (ImageView) findViewById(R.id.filter_iv);


        homeTabLl.setOnClickListener(this);
        msgTabLl.setOnClickListener(this);
        mineTabLl.setOnClickListener(this);
        titleBtn.setOnClickListener(this);
        filterIv.setOnClickListener(this);

        FilterItem filterItem1 = new FilterItem();
        filterItem1.title = "性别";
        FilterSubItem filterSubItem1 = new FilterSubItem();
        filterSubItem1.name = "汉子";
        filterItem1.item.add(filterSubItem1);
        FilterSubItem filterSubItem2 = new FilterSubItem();
        filterSubItem2.name = "妹子";
        filterItem1.item.add(filterSubItem2);
        filterItems.add(filterItem1);

        FilterItem filterItem2 = new FilterItem();
        filterItem2.title = "身高";
        FilterSubItem filterSubItem01 = new FilterSubItem();
        filterSubItem01.name = "160~165cm";
        filterItem2.item.add(filterSubItem01);
        FilterSubItem filterSubItem11 = new FilterSubItem();
        filterSubItem11.name = "165~170cm";
        filterItem2.item.add(filterSubItem11);
        FilterSubItem filterSubItem21 = new FilterSubItem();
        filterSubItem21.name = "170~180cm";
        filterItem2.item.add(filterSubItem21);
        FilterSubItem filterSubItem31 = new FilterSubItem();
        filterSubItem31.name = "180cm以上";
        filterItem2.item.add(filterSubItem31);
        filterItems.add(filterItem2);

        FilterItem filterItem3 = new FilterItem();
        filterItem3.title = "体重";
        FilterSubItem filterSubItem02 = new FilterSubItem();
        filterSubItem02.name = "40~55kg";
        filterItem3.item.add(filterSubItem02);
        FilterSubItem filterSubItem13 = new FilterSubItem();
        filterSubItem13.name = "56~60kg";
        filterItem3.item.add(filterSubItem13);
        FilterSubItem filterSubItem24 = new FilterSubItem();
        filterSubItem24.name = "61~65kg";
        filterItem3.item.add(filterSubItem24);
        FilterSubItem filterSubItem35 = new FilterSubItem();
        filterSubItem35.name = "66~75kg";
        filterItem3.item.add(filterSubItem35);
        FilterSubItem filterSubItem36 = new FilterSubItem();
        filterSubItem36.name = "75kg以上";
        filterItem3.item.add(filterSubItem36);
        filterItems.add(filterItem3);
    }


    /**
     * 根据时候有savedInstanceState来判断要显示的Fragment
     *
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        position = savedInstanceState.getInt("position");
        setTabSelection(position);
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * 在activity被回收时记入当前fragment位置
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //记录当前的position
        outState.putInt("position", position);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.home_tab_ll:
                setTabSelection(TAB_HOME);
                break;
            case R.id.msg_tab_ll:
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startConversationList(this);
                break;
            case R.id.mine_tab_ll:
                if (Utils.isGuestUser(mContext)) { // 匿名用户不可进入个人中心
                    startActivityForResult(new Intent(this, LoginActivity.class), ACTIVITY_LOGIN);
                } else {
                    setTabSelection(TAB_MINE);
                }
                break;
            case R.id.gps_location_btn:
                startActivityForResult(new Intent(this, CurrentCityActivity.class),
                        CurrentCityActivity.CURRENT_ACTIVITY_RESULT);
                break;
            case R.id.filter_iv:
                showFilterPopUpWindow();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (BACK_PRESSED + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            ToastUtils.show(this, "再按一次退出应用");
            BACK_PRESSED = System.currentTimeMillis();
        }
    }

    //切换Fragment
    private void setTabSelection(int position) {
        //记录position
        this.position = position;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        //更改底部导航栏按钮状态
        changeButtonStatus(position);
        switch (position) {
            case TAB_HOME:
                if (homeFragment == null) {
                    homeFragment = new HomePagerFragment();
                    transaction.add(R.id.fragment_container, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case TAB_MESSAGE:

                if (msgFragment == null) {
                    initIMkitMsg(transaction);
                } else {
                    transaction.show(msgFragment);
                }
                break;
            case TAB_MINE:

                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.fragment_container, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    //根据position更改底部按钮状态
    private void changeButtonStatus(int position) {
        switch (position) {
            case TAB_HOME:
                homeTabLl.setSelected(true);
                msgTabLl.setSelected(false);
                mineTabLl.setSelected(false);
                filterIv.setVisibility(View.VISIBLE);
                titleBtn.setText("福州");
                Drawable leftDrawable = getResources().getDrawable(R.mipmap.gps_location);
                leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable
                        .getIntrinsicHeight());
                titleBtn.setCompoundDrawables(leftDrawable, null, null, null);
                titleBtn.setOnClickListener(this);
                break;
            case TAB_MESSAGE:
                homeTabLl.setSelected(false);
                msgTabLl.setSelected(true);
                mineTabLl.setSelected(false);
                filterIv.setVisibility(View.GONE);
                titleBtn.setText("消息");
                titleBtn.setCompoundDrawables(null, null, null, null);
                titleBtn.setOnClickListener(null);
                break;
            case TAB_MINE:
                homeTabLl.setSelected(false);
                msgTabLl.setSelected(false);
                mineTabLl.setSelected(true);
                filterIv.setVisibility(View.GONE);
                titleBtn.setText("我的");
                titleBtn.setCompoundDrawables(null, null, null, null);
                titleBtn.setOnClickListener(null);
                break;
        }
    }

    //隐藏所有Fragment
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (msgFragment != null) {
            transaction.hide(msgFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    private void showFilterPopUpWindow() {
        if (filterPopup == null) {
            filterPopup = new MyFilterPopupWindow(this, filterItems);
        }
        filterPopup.showAsDropDown(findViewById(R.id.title_layout));
        filterPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                filterProductList(filterPopup.selectedItems);
            }
        });
    }

    private void filterProductList(HashMap<String, String> selectedItems) {

    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri data = intent.getData();
        if (data != null && !TextUtils.isEmpty(data.getPath()) && data.getPath().equals
                ("/conversationlist")) {
            setTabSelection(TAB_MESSAGE);
        }
        if (intent.getBooleanExtra(SysSettingActivity.EXIT_CURRENT_USER, false)) {
            setTabSelection(TAB_HOME);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CurrentCityActivity.CURRENT_ACTIVITY_RESULT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                titleBtn.setText(data.getStringExtra("city"));
            }
        } else if (ACTIVITY_LOGIN == requestCode) {
            if (resultCode == RESULT_OK) {
                setTabSelection(TAB_MINE);
            }
        }
    }

}
