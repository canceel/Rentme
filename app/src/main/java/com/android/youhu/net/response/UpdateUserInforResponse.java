package com.android.youhu.net.response;

/**
 * Created by allipper on 2015-11-05.
 */
public class UpdateUserInforResponse extends ResponseBase {

    /**
     * request : /api/account/update.json
     * data : {"userId":3,"mobile":"15280050320","avatarUrl":"","userDetail":null,
     * "nickName":"allipper","constellation":0,"gender":0,"album":[],"job":0,"ageRange":0,
     * "heightRange":0,"weightRange":0,"interests":[]}
     */

    /**
     * userId : 3
     * mobile : 15280050320
     * avatarUrl :
     * userDetail : null
     * nickName : allipper
     * constellation : 0
     * gender : 0
     * album : []
     * job : 0
     * ageRange : 0
     * heightRange : 0
     * weightRange : 0
     * interests : []
     */

    public UserInfoEntity data;

}
