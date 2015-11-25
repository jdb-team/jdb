package com.ezb.jdb.dao;

import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.AccessKey;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.Date;

/**
 * author : liufeng
 * create time:2015/9/21 13:51
 */
@Repository
public class AccessKeyDao extends BaseDao<AccessKey> {

    public AccessKey query(String accessKey, String type) {
        String hql = "from AccessKey o where o.accessKey=''{0}'' and o.type=''{1}''";
        return queryUnique(MessageFormat.format(hql, accessKey, type));
    }

    public int expire(String type, String dateTime) {
        String hql = "delete from AccessKey o where o.type=''{0}'' and o.createTime<=''{1}''";
        return executeHql(MessageFormat.format(hql, type, dateTime));
    }

    public void add(String accessKeyStr, String type) {
        AccessKey accessKey = new AccessKey();
        accessKey.setAccessKey(accessKeyStr);
        accessKey.setCreateTime(new Date());
        accessKey.setType(type);
        add(accessKey);
    }

    public boolean exist(String accessKey, String type) {
        return query(accessKey,type) != null;
    }
}
