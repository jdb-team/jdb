package com.ezb.jdb.service;

/**
 * author : liufeng
 * create time:2015/9/21 14:42
 */
public interface IAccessKeyService {
    boolean exist(String accessKey, String type);

    int expireMinute(String type, int minue);

    int expireHour(String type, int minue);

    int expireDay(String type, int day);

    void add(String accessKey,String type);
}
