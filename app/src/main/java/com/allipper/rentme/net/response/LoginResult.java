package com.allipper.rentme.net.response;

import java.util.List;

/**
 * Created by allipper on 2015-11-04.
 */
public class LoginResult extends ResponseBase {

    /**
     * request : /api/account/login.json
     * data : {"token":"6a9c2fad-ef40-46fc-8f9a-ce2e995b3717","userInfo":{"userId":3,
     * "mobile":"15280050320","realName":"","userDetail":"","nickName":"","constellation":0,
     * "gender":0,"album":[],"job":0,"ageRange":0,"heightRange":0,"weightRange":0,"rentRange":"",
     * "schedule":""}}
     */

    public String request;
    /**
     * token : 6a9c2fad-ef40-46fc-8f9a-ce2e995b3717
     * userInfo : {"userId":3,"mobile":"15280050320","realName":"","userDetail":"","nickName":"",
     * "constellation":0,"gender":0,"album":[],"job":0,"ageRange":0,"heightRange":0,
     * "weightRange":0,"rentRange":"","schedule":""}
     */

    public DataEntity data;

    public static class DataEntity {
        public String token;
        /**
         * userId : 3
         * mobile : 15280050320
         * realName :
         * userDetail :
         * nickName :
         * constellation : 0
         * gender : 0
         * album : []
         * job : 0
         * ageRange : 0
         * heightRange : 0
         * weightRange : 0
         * rentRange :
         * schedule :
         */

        public UserInfoEntity userInfo;

        public static class UserInfoEntity {
            public int userId;
            public String mobile;
            public String realName;
            public String userDetail;
            public String nickName;
            public int constellation;
            public int gender;
            public int job;
            public int ageRange;
            public int heightRange;
            public int weightRange;
            public String rentRange;
            public String schedule;
            public List<?> album;
        }
    }
}
