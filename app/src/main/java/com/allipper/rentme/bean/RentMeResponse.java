package com.allipper.rentme.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.allipper.rentme.net.response.ResponseBase;

import java.util.List;

/**
 * Created by allipper on 2015-11-15.
 */
public class RentMeResponse extends ResponseBase {

    /**
     * pager : {"currentPage":1,"pageSize":10,"numberOfPages":1,"totalNumberOfResults":3}
     * items : [{"createTime":"2015-11-16","orderId":"02f8df57-95b5-4294-b156-e69a6e89ee84",
     * "state":"等待中","userId":3,"nickName":"你好","gender":2,"mobile":"15280050320",
     * "meetTime":"2015-11-16","meetAddress":"江苏","rentRange":"陪吃","totalPrice":1200},
     * {"createTime":"2015-11-15","orderId":"6fff9640-723b-4eed-87f7-0c0375b889d6","state":"等待中",
     * "userId":2,"nickName":"","gender":0,"mobile":"15980236670","meetTime":"2015-11-12",
     * "meetAddress":"福州仓山区","rentRange":"陪吃","totalPrice":200}]
     */

    public DataEntity data;

    public static class DataEntity {
        /**
         * currentPage : 1
         * pageSize : 10
         * numberOfPages : 1
         * totalNumberOfResults : 3
         */

        public Pagination pager;
        /**
         * createTime : 2015-11-16
         * orderId : 02f8df57-95b5-4294-b156-e69a6e89ee84
         * state : 等待中
         * userId : 3
         * nickName : 你好
         * gender : 2
         * mobile : 15280050320
         * meetTime : 2015-11-16
         * meetAddress : 江苏
         * rentRange : 陪吃
         * totalPrice : 1200
         */

        public List<ItemsEntity> items;




        public static class ItemsEntity implements Parcelable {
            public String createTime;
            public String orderId;
            public String state;
            public int userId;
            public String nickName;
            public String gender;
            public String mobile;
            public String meetTime;
            public String meetAddress;
            public String rentRange;
            public int totalPrice;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.createTime);
                dest.writeString(this.orderId);
                dest.writeString(this.state);
                dest.writeInt(this.userId);
                dest.writeString(this.nickName);
                dest.writeString(this.gender);
                dest.writeString(this.mobile);
                dest.writeString(this.meetTime);
                dest.writeString(this.meetAddress);
                dest.writeString(this.rentRange);
                dest.writeInt(this.totalPrice);
            }

            public ItemsEntity() {
            }

            protected ItemsEntity(Parcel in) {
                this.createTime = in.readString();
                this.orderId = in.readString();
                this.state = in.readString();
                this.userId = in.readInt();
                this.nickName = in.readString();
                this.gender = in.readString();
                this.mobile = in.readString();
                this.meetTime = in.readString();
                this.meetAddress = in.readString();
                this.rentRange = in.readString();
                this.totalPrice = in.readInt();
            }

            public static final Parcelable.Creator<ItemsEntity> CREATOR = new Parcelable.Creator<ItemsEntity>() {
                public ItemsEntity createFromParcel(Parcel source) {
                    return new ItemsEntity(source);
                }

                public ItemsEntity[] newArray(int size) {
                    return new ItemsEntity[size];
                }
            };
        }
    }
}
