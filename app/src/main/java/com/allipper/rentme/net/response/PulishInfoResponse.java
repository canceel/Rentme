package com.allipper.rentme.net.response;

import java.util.List;

/**
 * Created by allipper on 2015/9/11.
 */
public class PulishInfoResponse extends ResponseBase {
    public String code;        //序列号（单据号）

    public String imgUrl;        //头像
    public String nickName;    //昵称
    public String sex;        //性别
    public String age;        //年龄
    public String createTime; //	发布时间
    public String career;        //职业
    public String fee;        //时薪
    public List<String> pictureUrls; //相册图片
    public String totalOfpicture = "5";    //相册总数
    public String viewTimes;    //查看次数
    public String distance;    //距离
    public String checkStatus;    //认证状态
    public String schedule;    //档期
    public String workContent;      //出租范围

}
