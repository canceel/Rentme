package com.android.youhu.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.youhu.R;
import com.android.youhu.common.util.CropUtils;
import com.android.youhu.common.util.SharedPre;
import com.android.youhu.common.util.SharedPreUtils;
import com.android.youhu.common.util.Utils;
import com.android.youhu.net.response.UserInfo;
import com.android.youhu.ui.GuideActivity;
import com.android.youhu.ui.mine.AgreementActivity;
import com.android.youhu.ui.mine.GiveBackActivity;
import com.android.youhu.ui.mine.MineAuthActivity;
import com.android.youhu.ui.mine.MineInfoActivity;
import com.android.youhu.ui.mine.MinePocketActivity;
import com.android.youhu.ui.mine.MinePublishInfoActivity;
import com.android.youhu.ui.mine.MineRentActivity;
import com.android.youhu.ui.mine.RentMeActivity;
import com.android.youhu.ui.mine.SysSettingActivity;
import com.android.youhu.widget.CircleImageView;
import com.umeng.analytics.MobclickAgent;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.message.PushAgent;

/**
 * @author
 * @version 1.00 2015/9/21
 * @(#)Other.java
 */


public class MineFragment extends Fragment implements View.OnClickListener {

    private TextView nameTextView;
    private CircleImageView headCV;
    private TextView statusTextView;
    private TextView mine_authTextView;
    private TextView mine_publishTextView;
    private TextView mine_rentTextView;
    private TextView rent_meTextView;
    private TextView givebackTextView;
    private TextView helpTextView;
    private TextView agreementTextView;
    private TextView settingTextView;
    private RelativeLayout mineRelativeLayout;
    private TextView pocketTextView;

    private FeedbackAgent fb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        return view;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MineScreen"); //统计页面
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MineScreen");
    }


    /**
     * 初始化界面
     *
     * @param view
     */
    private void initView(View view) {
        nameTextView = (TextView) view.findViewById(R.id.name);
        mine_authTextView = (TextView) view.findViewById(R.id.mine_auth);
        statusTextView = (TextView) view.findViewById(R.id.status);
        mine_publishTextView = (TextView) view.findViewById(R.id.mine_publish);
        mine_rentTextView = (TextView) view.findViewById(R.id.mine_rent);
        rent_meTextView = (TextView) view.findViewById(R.id.rent_me);
        givebackTextView = (TextView) view.findViewById(R.id.giveback);
        helpTextView = (TextView) view.findViewById(R.id.help);
        agreementTextView = (TextView) view.findViewById(R.id.agreement);
        settingTextView = (TextView) view.findViewById(R.id.setting);
        pocketTextView = (TextView) view.findViewById(R.id.pocket);
        mineRelativeLayout = (RelativeLayout) view.findViewById(R.id.mine_rl);
        headCV = (CircleImageView) view.findViewById(R.id.head_cv);

        mine_publishTextView.setOnClickListener(this);
        mine_authTextView.setOnClickListener(this);
        mine_rentTextView.setOnClickListener(this);
        rent_meTextView.setOnClickListener(this);
        givebackTextView.setOnClickListener(this);
        helpTextView.setOnClickListener(this);
        agreementTextView.setOnClickListener(this);
        settingTextView.setOnClickListener(this);
        pocketTextView.setOnClickListener(this);
        mineRelativeLayout.setOnClickListener(this);
        updateUserInfo();
        setUpUmengFeedback();
    }

    public void updateUserInfo() {
        String string = SharedPreUtils.getString(getActivity(), SharedPre.User.NICKNAME);
        string = TextUtils.isEmpty(string) ? "昵称" : string;
        nameTextView.setText(string);
        String gender = SharedPreUtils.getString(getActivity(), SharedPre.User.GENDER);
        if ("男".equals(gender)) {
            Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.sex_girl);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            nameTextView.setCompoundDrawables(null, null, drawable, null);
        } else if ("女".equals(gender)) {
            Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.sex_man);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            nameTextView.setCompoundDrawables(null, null, drawable, null);
        } else {
            nameTextView.setCompoundDrawables(null, null, null, null);
        }
        statusTextView.setText(SharedPreUtils.getString(getActivity(), SharedPre.User.USERDETAIL));
        CropUtils.setHeadFromDisk(getActivity(), headCV);
    }

    private void setUpUmengFeedback() {
        fb = new FeedbackAgent(getActivity());
        // check if the app developer has replied to the feedback or not.
        fb.sync();
        fb.openAudioFeedback();
        fb.openFeedbackPush();
        PushAgent.getInstance(getActivity()).setDebugMode(true);
        PushAgent.getInstance(getActivity()).enable();

        //fb.setWelcomeInfo();
        //  fb.setWelcomeInfo("Welcome to use umeng feedback app");
//        FeedbackPush.getInstance(this).init(true);
//        PushAgent.getInstance(this).setPushIntentServiceClass(MyPushIntentService.class);


        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = fb.updateUserInfo();
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent it;
        switch (id) {
            case R.id.mine_rl:
                it = new Intent(getActivity(), MineInfoActivity.class);
                startActivity(it);
                break;
            case R.id.mine_auth:
                it = new Intent(getActivity(), MineAuthActivity.class);
                startActivity(it);
                break;
            case R.id.mine_publish:
                UserInfo userInfo = Utils.getUserInfo(getActivity());
                if (userInfo == null || Utils.isUserInfoNoneComplete(userInfo)) {
                    new AlertDialog.Builder(getActivity(), R.style.CommonDialog).setMessage
                            ("资料不完善，请您到个人资料中心设置？")
                            .setNegativeButton("确定", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(getActivity(), MineInfoActivity
                                            .class));
                                }
                            }).setPositiveButton("取消", null).show();
                } else {
                    it = new Intent(getActivity(), MinePublishInfoActivity.class);
                    startActivity(it);
                }
                break;
            case R.id.mine_rent:
                it = new Intent(getActivity(), MineRentActivity.class);
                startActivity(it);
                break;
            case R.id.rent_me:
                it = new Intent(getActivity(), RentMeActivity.class);
                startActivity(it);
                break;
            case R.id.pocket:
                it = new Intent(getActivity(), MinePocketActivity.class);
                startActivity(it);
                break;
            case R.id.giveback:
                it = new Intent();
                it.setClass(getActivity(), GiveBackActivity.class);
                String covertId = new FeedbackAgent(getActivity()).getDefaultConversation().getId();
                it.putExtra(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID, covertId);
                startActivity(it);
                break;
            case R.id.help:
                it = new Intent(getActivity(), GuideActivity.class);
                startActivity(it);
                break;
            case R.id.agreement:
                it = new Intent(getActivity(), AgreementActivity.class);
                startActivity(it);
                break;
            case R.id.setting:
                it = new Intent(getActivity(), SysSettingActivity.class);
                startActivity(it);
                break;
        }
        getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.hold);
    }
}