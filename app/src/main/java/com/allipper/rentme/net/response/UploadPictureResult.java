package com.allipper.rentme.net.response;

import java.util.List;

/**
 * Created by allipper on 2015/10/9.
 */
public class UploadPictureResult extends ResponseBase {


    public DataEntity data;

    public static class DataEntity {
        public List<String> album;
    }
}
