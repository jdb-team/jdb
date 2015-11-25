package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.TopicType;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * 话题分类
 * author : liufeng
 * create time:2015/11/13 11:42
 */
@Repository
public class TopicTypeDao extends BaseDao<TopicType> {
    public void deleteById(String id) {
        String hql = "delete from TopicType o where o.id=''{0}''";
        executeHql(MessageFormat.format(hql, id));
    }

    public PageResult<TopicType> query(PageResult<TopicType> pageResult, String typeName) {
        String hql = "from TopicType o where 1=1";
        if (!StringUtils.isEmpty(typeName)) {
            hql += "o.typeName like ''%{0}%''";
        }
        return query(MessageFormat.format(hql, typeName), pageResult);
    }
}
