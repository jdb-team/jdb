package com.ezb.jdb.model;

import lombok.Data;

/**
 * 短信发送返回对象
 * author : liufeng
 * create time:2015/9/5 11:51
 */
@Data
public class SmsRes {
    private Integer state;
    private String msgState;
}
