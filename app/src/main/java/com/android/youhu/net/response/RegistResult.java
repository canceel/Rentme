package com.android.youhu.net.response;

/**
 * Created by allipper on 2015-11-04.
 */
public class RegistResult extends ResponseBase {

    /**
     * request : /api/account/register.json
     * data : {"mobile":"15280050320","token":"67a9cfe2-314c-496a-b20f-80efeb15b5fc",
     * "regeditIP":"222.76.27.49","regeditTime":"2015-11-04 16:58:17"}
     */

    /**
     * mobile : 15280050320
     * token : 67a9cfe2-314c-496a-b20f-80efeb15b5fc
     * regeditIP : 222.76.27.49
     * regeditTime : 2015-11-04 16:58:17
     */

    public DataEntity data;


    public static class DataEntity {
        public String mobile;
        public String token;
        public String regeditIP;
        public String regeditTime;
    }
}
