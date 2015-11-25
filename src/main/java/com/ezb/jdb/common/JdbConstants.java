package com.ezb.jdb.common;

import com.ezb.jdb.easemob.comm.PropertiesUtils;

/**
 * 全局变量
 * author : liufeng
 * create time: 2015/8/5 17:45.
 */
public interface JdbConstants {

    String DAY_FMT = "yyyyMMdd";
    String HOUR_FMT = "yyyyMMddHH";
    String MIMUTE_FMT = "yyyyMMddHHmm";
    String MILL_SEC_FMT = "yyyyMMddHHmmssSSS";
    String D_FMT = "yyyy-MM-dd";
    String DATE_TIME_FMT = "yyyy-MM-dd HH:mm:ss";
    String ORDERBY_LOCATION = "location";
    String ORDERBY_USERNAME = "username";
    String VERIFYCODE_KEY = "validateCode";
    String VIEWURL_CIRCLE = "admin/circle/view";
    String VIEWURL_ACTIVITY = "admin/activity/view";
    String VIEWURL_TOPIC = "admin/topic/view";
    String VIEWURL_USER = "admin/user/view";
    String ADMINID = "admin_id";
    String DEFAULT_NONEPIC_BIG = "uploadfiles/nonepic_big.png";
    String DEFAULT_NONEPIC_SMALL = "uploadfiles/nonepic_small.png";
    String IMG_FMT_JPG = "jpg";
    String TOKEN_HEADER = "x-access-token";
    String ACCKEY_TOKEN = "token";
    String ACCKEY_INVITATE = "invitate";
    String ACCKEY_ACTIVITY = "activity";
    String ACCKEY_ATVCMT = "atvcmt";
    String ACCKEY_TOPICCMT = "Topiccmt";
    String ACCKEY_TOPIC = "topic";
    double PIC_WIDTH = 500;
    double PIC_HEIGHT = 281.25;

    /**
     * 是否需要初始化数据
     */
    Boolean JDB_INIT_DATA = Boolean.parseBoolean(PropertiesUtils.getProperties().getProperty("jdb_init_data"));
}
