package com.allipper.rentme.net.response;

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
    }
}
