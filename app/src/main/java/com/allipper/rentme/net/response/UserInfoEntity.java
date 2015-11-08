package com.allipper.rentme.net.response;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by allipper on 2015-11-05.
 */
public class UserInfoEntity {
    public int userId;
    public String mobile;
    public String avatarUrl;
    public String realName;
    public String userDetail;
    public String nickName;
    public EnumEntity constellation;
    public EnumEntity gender;
    public EnumEntity job;
    public EnumEntity ageRange;
    public EnumEntity heightRange;
    public EnumEntity weightRange;
    public List<String> album;
    public EnumEntity interests;

    public UserInfo entityToInfo() {
        UserInfo info = new UserInfo();
        info.userId = userId;
        info.mobile = mobile;
        info.avatarUrl = avatarUrl;
        info.realName = realName;
        info.userDetail = TextUtils.isEmpty(userDetail) ? "设置个性签名" : userDetail;
        info.nickName = TextUtils.isEmpty(nickName) ? "设置昵称" : nickName;
        info.constellation = constellation.items == null && constellation.items.size() == 0 ?
                "请设置星座" : constellation.items.get(0).name;
        info.gender = gender.items == null && gender.items.size() == 0 ? "请设置性别" : gender.items
                .get(0).name;
        info.job = job.items == null && job.items.size() == 0 ? "请设置职业" : job.items.get(0).name;
        info.ageRange = ageRange.items == null && ageRange.items.size() == 0 ? "请设置年龄" : ageRange
                .items.get(0).name;
        info.heightRange = heightRange.items == null && heightRange.items.size() == 0 ? "请设置身高" :
                heightRange.items.get(0).name;
        info.weightRange = weightRange.items == null && weightRange.items.size() == 0 ? "请设置体重" :
                weightRange.items.get(0).name;
        info.album = album;
        if (interests.items == null || interests.items.size() == 0) {
            info.interests = "请设置兴趣";
        } else {
            StringBuffer sb = new StringBuffer();
            for (ItemsEntity itemsEntity : interests.items) {
                sb.append("、").append(itemsEntity.name);
            }
            info.interests = sb.deleteCharAt(0).toString();
        }

        return info;
    }
}
