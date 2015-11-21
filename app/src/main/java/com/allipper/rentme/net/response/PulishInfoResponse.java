package com.allipper.rentme.net.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.allipper.rentme.bean.Pagination;

import java.util.List;

/**
 * Created by allipper on 2015/9/11.
 */
public class PulishInfoResponse extends ResponseBase {


    /**
     * pager : {"currentPage":1,"pageSize":10,"numberOfPages":1,"totalNumberOfResults":2}
     * items : [{"userId":2,"avatarUrl":"","nickName":"","job":"学生","ageRange":"18岁以下",
     * "heightRange":"140~145cm","weightRange":"100kg以上","rentRange":"陪吃","Schedule":"下班后",
     * "album":[],"perHourPrice":300},{"userId":3,"avatarUrl":"http://7xnvj7.com1.z0.glb.clouddn
     * .com/images/avatars/20151108/3/ccfcc1d1daab46309546bfad0ac6c476.jpg","nickName":"你好",
     * "job":"计算机/互联网/通信","ageRange":"18~23","heightRange":"170~175cm","weightRange":"100kg以上",
     * "rentRange":"陪吃","Schedule":"下班后","album":[],"perHourPrice":300}]
     */

    public DataEntity data;


    public static class DataEntity {
        /**
         * currentPage : 1
         * pageSize : 10
         * numberOfPages : 1
         * totalNumberOfResults : 2
         */

        public Pagination pager;
        /**
         * userId : 2
         * avatarUrl :
         * nickName :
         * job : 学生
         * ageRange : 18岁以下
         * heightRange : 140~145cm
         * weightRange : 100kg以上
         * rentRange : 陪吃
         * Schedule : 下班后
         * album : []
         * perHourPrice : 300
         */

        public List<ItemsEntity> items;


        public static class ItemsEntity implements Parcelable {
            public int userId;
            public String avatarUrl;
            public String backgroudImage;
            public String nickName;
            public String job;
            public String gender;
            public String ageRange;
            public String heightRange;
            public String weightRange;
            public String rentRange;
            public String Schedule;
            public String interests;
            public int perHourPrice;
            public List<Album> album;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.userId);
                dest.writeString(this.avatarUrl);
                dest.writeString(this.backgroudImage);
                dest.writeString(this.nickName);
                dest.writeString(this.job);
                dest.writeString(this.gender);
                dest.writeString(this.ageRange);
                dest.writeString(this.heightRange);
                dest.writeString(this.weightRange);
                dest.writeString(this.rentRange);
                dest.writeString(this.Schedule);
                dest.writeString(this.interests);
                dest.writeInt(this.perHourPrice);
                dest.writeTypedList(album);
            }

            public ItemsEntity() {
            }

            protected ItemsEntity(Parcel in) {
                this.userId = in.readInt();
                this.avatarUrl = in.readString();
                this.backgroudImage = in.readString();
                this.nickName = in.readString();
                this.job = in.readString();
                this.gender = in.readString();
                this.ageRange = in.readString();
                this.heightRange = in.readString();
                this.weightRange = in.readString();
                this.rentRange = in.readString();
                this.Schedule = in.readString();
                this.interests = in.readString();
                this.perHourPrice = in.readInt();
                this.album = in.createTypedArrayList(Album.CREATOR);
            }

            public static final Parcelable.Creator<ItemsEntity> CREATOR = new Parcelable
                    .Creator<ItemsEntity>() {
                public ItemsEntity createFromParcel(Parcel source) {
                    return new ItemsEntity(source);
                }

                public ItemsEntity[] newArray(int size) {
                    return new ItemsEntity[size];
                }
            };
        }

        public static class Album implements Parcelable {

            /**
             * AlbumId : 1
             * UserId : 3
             * PictureUrl : http://7xnvj7.com1.z0.glb.clouddn
             * .com/images/avatars/20151115/3/33ef3d5b7f1d4bcd97cbf226a7c60dad.jpg
             * State : 1
             * CreateTime : /Date(1447594940000)/
             */

            public int AlbumId;
            public int UserId;
            public String PictureUrl;
            public int State;
            public String CreateTime;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.AlbumId);
                dest.writeInt(this.UserId);
                dest.writeString(this.PictureUrl);
                dest.writeInt(this.State);
                dest.writeString(this.CreateTime);
            }

            public Album() {
            }

            protected Album(Parcel in) {
                this.AlbumId = in.readInt();
                this.UserId = in.readInt();
                this.PictureUrl = in.readString();
                this.State = in.readInt();
                this.CreateTime = in.readString();
            }

            public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>
                    () {
                public Album createFromParcel(Parcel source) {
                    return new Album(source);
                }

                public Album[] newArray(int size) {
                    return new Album[size];
                }
            };
        }
    }
}
