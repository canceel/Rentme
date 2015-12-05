package com.android.youhu.common.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.youhu.R;
import com.android.youhu.net.response.LoginResult;
import com.android.youhu.net.response.UserInfo;
import com.android.youhu.net.response.UserInfoEntity;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2015/7/8.
 */
public class ConfigBean {

    public String baseUrl;

}
