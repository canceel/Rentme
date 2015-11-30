package com.android.youhu.net.response;

/**
 * Created by allipper on 2015-10-30.
 */
public class ResponseMessageBean extends ResponseBase {

    /**
     * request : /api/account/getregcaptcha.json
     * data : {"mobile":"15280050320","captcha":"710876","expiredTime":"2015-11-04 11:27:01",
     * "createTime":"2015-11-04 10:57:01"}
     */

    /**
     * mobile : 15280050320
     * captcha : 710876
     * expiredTime : 2015-11-04 11:27:01
     * createTime : 2015-11-04 10:57:01
     */

    public DataEntity data;


    public static class DataEntity {
        public String mobile;
        public String captcha;
        public String expiredTime;
        public String createTime;
    }
}
