package com.ezb.jdb.service;

/**
 * 短信相关服务
 * author : liufeng
 * create time: 2015/8/6 9:48.
 */

public interface ISmsService {

    /**
     * 给指定手机号发送消息
     * @param phone
     * @param content
     */
    String sendMes(String phone,String content);
}
