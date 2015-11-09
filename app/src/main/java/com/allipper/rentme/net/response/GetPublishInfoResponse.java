package com.allipper.rentme.net.response;

/**
 * Created by allipper on 2015-11-08.
 */
public class GetPublishInfoResponse extends ResponseBase {

    /**
     * rentRange : {"multi":true,"items":[{"displayName":"陪吃","name":"陪吃","value":1}]}
     * schedule : {"multi":true,"items":[{"displayName":"任意时段","name":"任意时段","value":1}]}
     * perHourPrice : 300
     */

    public DataEntity data;

    public static class DataEntity {
        /**
         * multi : true
         * items : [{"displayName":"陪吃","name":"陪吃","value":1}]
         */

        public EnumEntity rentRange;
        /**
         * multi : true
         * items : [{"displayName":"任意时段","name":"任意时段","value":1}]
         */

        public EnumEntity schedule;
        public String perHourPrice;
    }
}
