package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.dao.AccessKeyDao;
import com.ezb.jdb.service.IAccessKeyService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author : liufeng
 * create time:2015/9/21 14:44
 */
@Service
public class AccessKeyServiceImpl implements IAccessKeyService {

    @Resource
    private AccessKeyDao accessKeyDao;

    public boolean exist(String accessKey, String type) {
        return null != accessKeyDao.query(accessKey,type);
    }

    public int expireMinute(String type, int minute) {
        DateTime dateTime = DateTime.now().minusMinutes(minute);
        return accessKeyDao.expire(type,dateTime.toString(JdbConstants.DATE_TIME_FMT));
    }

    public int expireHour(String type, int hour) {
        DateTime dateTime = DateTime.now().minusHours(hour);
        return accessKeyDao.expire(type,dateTime.toString(JdbConstants.DATE_TIME_FMT));
    }

    public int expireDay(String type, int day) {
        DateTime dateTime = DateTime.now().minusDays(day);
        return accessKeyDao.expire(type,dateTime.toString(JdbConstants.DATE_TIME_FMT));
    }

    public void add(String accessKey, String type) {
        accessKeyDao.add(accessKey,type);
    }
}
