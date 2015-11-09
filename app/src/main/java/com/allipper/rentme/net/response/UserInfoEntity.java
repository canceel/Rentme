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
        info.userDetailValue = userDetail;
        info.nickName = TextUtils.isEmpty(nickName) ? "设置昵称" : nickName;
        info.nickNameValue = nickName;
        info.constellation = constellation.items == null && constellation.items.size() == 0 ?
                "设置星座" : constellation.items.get(0).name;
        info.constellationValue = constellation.items == null && constellation.items.size() == 0 ?
                0 : constellation.items.get(0).value;
        info.gender = gender.items == null && gender.items.size() == 0 ? "设置性别" : gender.items
                .get(0).name;
        info.genderValue = gender.items == null && gender.items.size() == 0 ? 0 : gender.items
                .get(0).value;
        info.job = job.items == null && job.items.size() == 0 ? "设置职业" : job.items.get(0).name;
        info.jobValue = job.items == null && job.items.size() == 0 ? 0 : job.items.get(0).value;
        info.ageRange = ageRange.items == null && ageRange.items.size() == 0 ? "设置年龄" : ageRange
                .items.get(0).name;
        info.ageRangeValue = ageRange.items == null && ageRange.items.size() == 0 ? 0 : ageRange
                .items.get(0).value;
        info.heightRange = heightRange.items == null && heightRange.items.size() == 0 ? "设置身高" :
                heightRange.items.get(0).name;
        info.heightRangeValue = heightRange.items == null && heightRange.items.size() == 0 ? 0 :
                heightRange.items.get(0).value;
        info.weightRange = weightRange.items == null && weightRange.items.size() == 0 ? "设置体重" :
                weightRange.items.get(0).name;
        info.weightRangeValue = weightRange.items == null && weightRange.items.size() == 0 ? 0 :
                weightRange.items.get(0).value;
        info.album = album;
        if (interests.items == null || interests.items.size() == 0) {
            info.interests = "设置兴趣";
            info.interestsValue = "";
        } else {
            StringBuffer sb = new StringBuffer();
            StringBuffer sb1 = new StringBuffer();
            for (ItemsEntity itemsEntity : interests.items) {
                sb.append("、").append(itemsEntity.name);
                sb1.append(",").append(itemsEntity.value);
            }
            info.interests = sb.deleteCharAt(0).toString();
            info.interestsValue = sb1.deleteCharAt(0).toString();
        }

        return info;
    }
}
