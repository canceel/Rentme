package com.allipper.rentme.net.response;

/**
 * Created by allipper on 2015-10-30.
 */
public class ResponseMessageBean extends ResponseBase{
    /**
     * request : /api/account/regcaptcha.json
     * code : 0
     * message : 请求成功
     * data : {"mobile":"15280050320","captcha":"332041"}
     */

    public String request;

    /**
     * mobile : 15280050320
     * captcha : 332041
     */

    public DataEntity data;


    public static class DataEntity {
        public String mobile;
        public String captcha;
    }
}
