package com.android.youhu.net.response;

/**
 * Created by allipper on 2016-01-17.
 */
public class ResponseUserInfo extends ResponseBase {

    /**
     * avatarUrl : http://7xnvj7.com1.z0.glb.clouddn
     * .com/images/20160105/911/3c76f57f2706496dba62f325e39e34a3.jpg
     * nickName : Air
     * isAuth : 0
     */

    public DataEntity data;

    public static class DataEntity {
        public String avatarUrl;
        public String nickName;
        public int isAuth;
    }
}
