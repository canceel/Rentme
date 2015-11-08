package com.allipper.rentme.net.response;

import java.util.List;

/**
 * Created by allipper on 2015-11-04.
 */
public class SysEnumsResponse extends ResponseBase {

    public final static String AGE_RANGE = "ageRange";
    public final static String CONSTELLATION = "constellation";
    public final static String GENDER = "gender";
    public final static String JOB = "job";
    public final static String HEIGHT_RANGE = "heightRange";
    public final static String WEIGH_RANGE = "weighRange";
    public final static String INTERESTS = "interests";
    public final static String RENT = "rent";
    public final static String SCHEDULE = "schedule";
    /**
     * request : /api/common/getsysenums.json
     * data : {"constellation":{"multi":false,"items":[{"displayName":"水瓶座(1月20日～2月18日)",
     * "name":"水瓶座","value":1},{"displayName":"双鱼座(2月19日～3月20日)","name":"双鱼座","value":2},
     * {"displayName":"白羊座(3月21日～4月20日)","name":"白羊座","value":3},{"displayName":"金牛座(4月21日～5月20日)
     * ","name":"金牛座","value":4},{"displayName":"双子座(5月21日～6月21日)","name":"双子座","value":5},
     * {"displayName":"巨蟹座(6月22日～7月22日)","name":"巨蟹座","value":6},{"displayName":"狮子座(7月23日～8月22日)
     * ","name":"狮子座","value":7},{"displayName":"处女座(8月23日～9月22日)","name":"处女座","value":8},
     * {"displayName":"天秤座(9月23日～10月23日)","name":"天秤座","value":9},{"displayName":"天蝎座
     * (10月24日～11月22日)","name":"天蝎座","value":10},{"displayName":"射手座(11月23日～12月21日)",
     * "name":"射手座","value":11},{"displayName":"摩羯座(12月22日～1月19日)","name":"摩羯座","value":12}]},
     * "gender":{"multi":false,"items":[{"displayName":"未知","name":"未知","value":0},
     * {"displayName":"女","name":"女","value":1},{"displayName":"男","name":"男","value":2}]},
     * "job":{"multi":false,"items":[{"displayName":"计算机/互联网/通信","name":"计算机/互联网/通信","value":1},
     * {"displayName":"生产/工艺/制造","name":"生产/工艺/制造","value":2},{"displayName":"医疗/护理/制药",
     * "name":"医疗/护理/制药","value":3},{"displayName":"金融/银行/投资/保险","name":"金融/银行/投资/保险","value":4},
     * {"displayName":"商业/服务员/个体经营","name":"商业/服务员/个体经营","value":5},{"displayName":"文化/广告/传媒",
     * "name":"文化/广告/传媒","value":6},{"displayName":"娱乐/艺术/表演","name":"娱乐/艺术/表演","value":7},
     * {"displayName":"律师/法务","name":"律师/法务","value":8},{"displayName":"教育/培训","name":"教育/培训",
     * "value":9},{"displayName":"公务员/行政/事业单位","name":"公务员/行政/事业单位","value":10},
     * {"displayName":"模特","name":"模特","value":11},{"displayName":"空姐","name":"空姐","value":12},
     * {"displayName":"学生","name":"学生","value":13},{"displayName":"其他","name":"其他","value":14}]},
     * "ageRange":{"multi":false,"items":[{"displayName":"18岁以下","name":"18岁以下","value":1},
     * {"displayName":"18~23","name":"18~23","value":2},{"displayName":"24~29","name":"24~29",
     * "value":3},{"displayName":"30~35","name":"30~35","value":4},{"displayName":"36~41",
     * "name":"36~41","value":5},{"displayName":"42~47","name":"42~47","value":6},
     * {"displayName":"48~53","name":"48~53","value":7},{"displayName":"54~59","name":"54~59",
     * "value":8},{"displayName":"60岁以上","name":"60岁以上","value":9}]},
     * "heightRange":{"multi":false,"items":[{"displayName":"140cm以下","name":"140cm以下",
     * "value":1},{"displayName":"140~145cm","name":"140~145cm","value":2},
     * {"displayName":"145~150cm","name":"145~150cm","value":3},{"displayName":"150~155cm",
     * "name":"150~155cm","value":4},{"displayName":"155~160cm","name":"155~160cm","value":5},
     * {"displayName":"160~165cm","name":"160~165cm","value":6},{"displayName":"165~170cm",
     * "name":"165~170cm","value":7},{"displayName":"170~175cm","name":"170~175cm","value":8},
     * {"displayName":"175~180cm","name":"175~180cm","value":9},{"displayName":"180~185cm",
     * "name":"180~185cm","value":10},{"displayName":"185~190cm","name":"185~190cm","value":11},
     * {"displayName":"190cm以上","name":"190cm以上","value":12}]},"weightRange":{"multi":false,
     * "items":[{"displayName":"35kg以下","name":"35kg以下","value":1},{"displayName":"35~40kg",
     * "name":"35~40kg","value":2},{"displayName":"35~40kg","name":"35~40kg","value":2},
     * {"displayName":"45~50kg","name":"45~50kg","value":3},{"displayName":"50~55kg",
     * "name":"50~55kg","value":4},{"displayName":"55~60kg","name":"55~60kg","value":5},
     * {"displayName":"60~65kg","name":"60~65kg","value":6},{"displayName":"65~70kg",
     * "name":"65~70kg","value":7},{"displayName":"70~75kg","name":"70~75kg","value":8},
     * {"displayName":"75~80kg","name":"75~80kg","value":9},{"displayName":"80~85kg",
     * "name":"80~85kg","value":10},{"displayName":"85~90kg","name":"85~90kg","value":11},
     * {"displayName":"90~95kg","name":"90~95kg","value":12},{"displayName":"95~100kg",
     * "name":"95~100kg","value":13},{"displayName":"100kg以上","name":"100kg以上","value":14}]},
     * "interest":{"multi":true,"items":[{"displayName":"运动","name":"运动","value":1},
     * {"displayName":"美食","name":"美食","value":2},{"displayName":"阅读","name":"阅读","value":3},
     * {"displayName":"电影","name":"电影","value":4},{"displayName":"娱乐","name":"娱乐","value":5},
     * {"displayName":"旅游","name":"旅游","value":6}]},"rentRange":{"multi":true,
     * "items":[{"displayName":"陪吃","name":"陪吃","value":1},{"displayName":"陪喝","name":"陪喝",
     * "value":2},{"displayName":"陪玩","name":"陪玩","value":3},{"displayName":"陪睡","name":"陪睡",
     * "value":4}]},"schedule":{"multi":true,"items":[{"displayName":"任意时段","name":"任意时段",
     * "value":1},{"displayName":"下班后","name":"下班后","value":2},{"displayName":"周末","name":"周末",
     * "value":3}]}}
     */

    /**
     * constellation : {"multi":false,"items":[{"displayName":"水瓶座(1月20日～2月18日)","name":"水瓶座",
     * "value":1},{"displayName":"双鱼座(2月19日～3月20日)","name":"双鱼座","value":2},{"displayName":"白羊座
     * (3月21日～4月20日)","name":"白羊座","value":3},{"displayName":"金牛座(4月21日～5月20日)","name":"金牛座",
     * "value":4},{"displayName":"双子座(5月21日～6月21日)","name":"双子座","value":5},{"displayName":"巨蟹座
     * (6月22日～7月22日)","name":"巨蟹座","value":6},{"displayName":"狮子座(7月23日～8月22日)","name":"狮子座",
     * "value":7},{"displayName":"处女座(8月23日～9月22日)","name":"处女座","value":8},{"displayName":"天秤座
     * (9月23日～10月23日)","name":"天秤座","value":9},{"displayName":"天蝎座(10月24日～11月22日)","name":"天蝎座",
     * "value":10},{"displayName":"射手座(11月23日～12月21日)","name":"射手座","value":11},
     * {"displayName":"摩羯座(12月22日～1月19日)","name":"摩羯座","value":12}]}
     * gender : {"multi":false,"items":[{"displayName":"未知","name":"未知","value":0},
     * {"displayName":"女","name":"女","value":1},{"displayName":"男","name":"男","value":2}]}
     * job : {"multi":false,"items":[{"displayName":"计算机/互联网/通信","name":"计算机/互联网/通信","value":1},
     * {"displayName":"生产/工艺/制造","name":"生产/工艺/制造","value":2},{"displayName":"医疗/护理/制药",
     * "name":"医疗/护理/制药","value":3},{"displayName":"金融/银行/投资/保险","name":"金融/银行/投资/保险","value":4},
     * {"displayName":"商业/服务员/个体经营","name":"商业/服务员/个体经营","value":5},{"displayName":"文化/广告/传媒",
     * "name":"文化/广告/传媒","value":6},{"displayName":"娱乐/艺术/表演","name":"娱乐/艺术/表演","value":7},
     * {"displayName":"律师/法务","name":"律师/法务","value":8},{"displayName":"教育/培训","name":"教育/培训",
     * "value":9},{"displayName":"公务员/行政/事业单位","name":"公务员/行政/事业单位","value":10},
     * {"displayName":"模特","name":"模特","value":11},{"displayName":"空姐","name":"空姐","value":12},
     * {"displayName":"学生","name":"学生","value":13},{"displayName":"其他","name":"其他","value":14}]}
     * ageRange : {"multi":false,"items":[{"displayName":"18岁以下","name":"18岁以下","value":1},
     * {"displayName":"18~23","name":"18~23","value":2},{"displayName":"24~29","name":"24~29",
     * "value":3},{"displayName":"30~35","name":"30~35","value":4},{"displayName":"36~41",
     * "name":"36~41","value":5},{"displayName":"42~47","name":"42~47","value":6},
     * {"displayName":"48~53","name":"48~53","value":7},{"displayName":"54~59","name":"54~59",
     * "value":8},{"displayName":"60岁以上","name":"60岁以上","value":9}]}
     * heightRange : {"multi":false,"items":[{"displayName":"140cm以下","name":"140cm以下",
     * "value":1},{"displayName":"140~145cm","name":"140~145cm","value":2},
     * {"displayName":"145~150cm","name":"145~150cm","value":3},{"displayName":"150~155cm",
     * "name":"150~155cm","value":4},{"displayName":"155~160cm","name":"155~160cm","value":5},
     * {"displayName":"160~165cm","name":"160~165cm","value":6},{"displayName":"165~170cm",
     * "name":"165~170cm","value":7},{"displayName":"170~175cm","name":"170~175cm","value":8},
     * {"displayName":"175~180cm","name":"175~180cm","value":9},{"displayName":"180~185cm",
     * "name":"180~185cm","value":10},{"displayName":"185~190cm","name":"185~190cm","value":11},
     * {"displayName":"190cm以上","name":"190cm以上","value":12}]}
     * weightRange : {"multi":false,"items":[{"displayName":"35kg以下","name":"35kg以下","value":1},
     * {"displayName":"35~40kg","name":"35~40kg","value":2},{"displayName":"35~40kg",
     * "name":"35~40kg","value":2},{"displayName":"45~50kg","name":"45~50kg","value":3},
     * {"displayName":"50~55kg","name":"50~55kg","value":4},{"displayName":"55~60kg",
     * "name":"55~60kg","value":5},{"displayName":"60~65kg","name":"60~65kg","value":6},
     * {"displayName":"65~70kg","name":"65~70kg","value":7},{"displayName":"70~75kg",
     * "name":"70~75kg","value":8},{"displayName":"75~80kg","name":"75~80kg","value":9},
     * {"displayName":"80~85kg","name":"80~85kg","value":10},{"displayName":"85~90kg",
     * "name":"85~90kg","value":11},{"displayName":"90~95kg","name":"90~95kg","value":12},
     * {"displayName":"95~100kg","name":"95~100kg","value":13},{"displayName":"100kg以上",
     * "name":"100kg以上","value":14}]}
     * interest : {"multi":true,"items":[{"displayName":"运动","name":"运动","value":1},
     * {"displayName":"美食","name":"美食","value":2},{"displayName":"阅读","name":"阅读","value":3},
     * {"displayName":"电影","name":"电影","value":4},{"displayName":"娱乐","name":"娱乐","value":5},
     * {"displayName":"旅游","name":"旅游","value":6}]}
     * rentRange : {"multi":true,"items":[{"displayName":"陪吃","name":"陪吃","value":1},
     * {"displayName":"陪喝","name":"陪喝","value":2},{"displayName":"陪玩","name":"陪玩","value":3},
     * {"displayName":"陪睡","name":"陪睡","value":4}]}
     * schedule : {"multi":true,"items":[{"displayName":"任意时段","name":"任意时段","value":1},
     * {"displayName":"下班后","name":"下班后","value":2},{"displayName":"周末","name":"周末","value":3}]}
     */

    public DataEntity data;

    public static class DataEntity {
        /**
         * multi : false
         * items : [{"displayName":"水瓶座(1月20日～2月18日)","name":"水瓶座","value":1},{"displayName":"双鱼座
         * (2月19日～3月20日)","name":"双鱼座","value":2},{"displayName":"白羊座(3月21日～4月20日)","name":"白羊座",
         * "value":3},{"displayName":"金牛座(4月21日～5月20日)","name":"金牛座","value":4},
         * {"displayName":"双子座(5月21日～6月21日)","name":"双子座","value":5},{"displayName":"巨蟹座
         * (6月22日～7月22日)","name":"巨蟹座","value":6},{"displayName":"狮子座(7月23日～8月22日)","name":"狮子座",
         * "value":7},{"displayName":"处女座(8月23日～9月22日)","name":"处女座","value":8},
         * {"displayName":"天秤座(9月23日～10月23日)","name":"天秤座","value":9},{"displayName":"天蝎座
         * (10月24日～11月22日)","name":"天蝎座","value":10},{"displayName":"射手座(11月23日～12月21日)",
         * "name":"射手座","value":11},{"displayName":"摩羯座(12月22日～1月19日)","name":"摩羯座","value":12}]
         */

        public EnumEntity constellation;
        /**
         * multi : false
         * items : [{"displayName":"未知","name":"未知","value":0},{"displayName":"女","name":"女",
         * "value":1},{"displayName":"男","name":"男","value":2}]
         */

        public EnumEntity gender;
        /**
         * multi : false
         * items : [{"displayName":"计算机/互联网/通信","name":"计算机/互联网/通信","value":1},
         * {"displayName":"生产/工艺/制造","name":"生产/工艺/制造","value":2},{"displayName":"医疗/护理/制药",
         * "name":"医疗/护理/制药","value":3},{"displayName":"金融/银行/投资/保险","name":"金融/银行/投资/保险",
         * "value":4},{"displayName":"商业/服务员/个体经营","name":"商业/服务员/个体经营","value":5},
         * {"displayName":"文化/广告/传媒","name":"文化/广告/传媒","value":6},{"displayName":"娱乐/艺术/表演",
         * "name":"娱乐/艺术/表演","value":7},{"displayName":"律师/法务","name":"律师/法务","value":8},
         * {"displayName":"教育/培训","name":"教育/培训","value":9},{"displayName":"公务员/行政/事业单位",
         * "name":"公务员/行政/事业单位","value":10},{"displayName":"模特","name":"模特","value":11},
         * {"displayName":"空姐","name":"空姐","value":12},{"displayName":"学生","name":"学生",
         * "value":13},{"displayName":"其他","name":"其他","value":14}]
         */

        public EnumEntity job;
        /**
         * multi : false
         * items : [{"displayName":"18岁以下","name":"18岁以下","value":1},{"displayName":"18~23",
         * "name":"18~23","value":2},{"displayName":"24~29","name":"24~29","value":3},
         * {"displayName":"30~35","name":"30~35","value":4},{"displayName":"36~41",
         * "name":"36~41","value":5},{"displayName":"42~47","name":"42~47","value":6},
         * {"displayName":"48~53","name":"48~53","value":7},{"displayName":"54~59",
         * "name":"54~59","value":8},{"displayName":"60岁以上","name":"60岁以上","value":9}]
         */

        public EnumEntity ageRange;
        /**
         * multi : false
         * items : [{"displayName":"140cm以下","name":"140cm以下","value":1},
         * {"displayName":"140~145cm","name":"140~145cm","value":2},{"displayName":"145~150cm",
         * "name":"145~150cm","value":3},{"displayName":"150~155cm","name":"150~155cm",
         * "value":4},{"displayName":"155~160cm","name":"155~160cm","value":5},
         * {"displayName":"160~165cm","name":"160~165cm","value":6},{"displayName":"165~170cm",
         * "name":"165~170cm","value":7},{"displayName":"170~175cm","name":"170~175cm",
         * "value":8},{"displayName":"175~180cm","name":"175~180cm","value":9},
         * {"displayName":"180~185cm","name":"180~185cm","value":10},{"displayName":"185~190cm",
         * "name":"185~190cm","value":11},{"displayName":"190cm以上","name":"190cm以上","value":12}]
         */

        public EnumEntity heightRange;
        /**
         * multi : false
         * items : [{"displayName":"35kg以下","name":"35kg以下","value":1},{"displayName":"35~40kg",
         * "name":"35~40kg","value":2},{"displayName":"35~40kg","name":"35~40kg","value":2},
         * {"displayName":"45~50kg","name":"45~50kg","value":3},{"displayName":"50~55kg",
         * "name":"50~55kg","value":4},{"displayName":"55~60kg","name":"55~60kg","value":5},
         * {"displayName":"60~65kg","name":"60~65kg","value":6},{"displayName":"65~70kg",
         * "name":"65~70kg","value":7},{"displayName":"70~75kg","name":"70~75kg","value":8},
         * {"displayName":"75~80kg","name":"75~80kg","value":9},{"displayName":"80~85kg",
         * "name":"80~85kg","value":10},{"displayName":"85~90kg","name":"85~90kg","value":11},
         * {"displayName":"90~95kg","name":"90~95kg","value":12},{"displayName":"95~100kg",
         * "name":"95~100kg","value":13},{"displayName":"100kg以上","name":"100kg以上","value":14}]
         */

        public EnumEntity weightRange;
        /**
         * multi : true
         * items : [{"displayName":"运动","name":"运动","value":1},{"displayName":"美食","name":"美食",
         * "value":2},{"displayName":"阅读","name":"阅读","value":3},{"displayName":"电影","name":"电影",
         * "value":4},{"displayName":"娱乐","name":"娱乐","value":5},{"displayName":"旅游","name":"旅游",
         * "value":6}]
         */

        public EnumEntity interest;
        /**
         * multi : true
         * items : [{"displayName":"陪吃","name":"陪吃","value":1},{"displayName":"陪喝","name":"陪喝",
         * "value":2},{"displayName":"陪玩","name":"陪玩","value":3},{"displayName":"陪睡","name":"陪睡",
         * "value":4}]
         */

        public EnumEntity rentRange;
        /**
         * multi : true
         * items : [{"displayName":"任意时段","name":"任意时段","value":1},{"displayName":"下班后",
         * "name":"下班后","value":2},{"displayName":"周末","name":"周末","value":3}]
         */

        public EnumEntity schedule;


    }
}
