package com.allipper.rentme.net.response;

/**
 * Created by allipper on 2015/10/9.
 */
public class UploadResult extends ResponseBase {


    /**
     * request : /api/account/uploadavatar.json
     * data : {"avatarUrl":"http://7xnvj7.com1.z0.glb.clouddn
     * .com/images/avatars/20151105/1/0fdb5aa81ab74de18125a4e3c9c01efa.jpeg"}
     */


    /**
     * avatarUrl : http://7xnvj7.com1.z0.glb.clouddn
     * .com/images/avatars/20151105/1/0fdb5aa81ab74de18125a4e3c9c01efa.jpeg
     */

    public DataEntity data;


    public static class DataEntity {
        public String avatarUrl;
        public String backgroudImage;
    }
}
