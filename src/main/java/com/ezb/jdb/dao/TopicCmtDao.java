package com.ezb.jdb.dao;

import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.TopicCmt;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * author : liufeng
 * create time: 2015/8/7 14:50.
 */
@Repository
public class TopicCmtDao extends BaseDao<TopicCmt> {

    public int likeTopicCmt(Integer id) {
        String hql = "update TopicCmt o set o.likeCount=o.likeCount+1 where o.id=''{0}''";
        return executeHql(MessageFormat.format(hql,id));
    }

    public List<TopicCmt> qTopicCmtByTopicId(Integer topicId) {
        String hql = "from TopicCmt o where o.topic.id=''{0}'' order by o.createTime desc";
        return query(MessageFormat.format(hql, topicId));
    }

    public int qChildCmtCount(Integer id) {
        String cql = "from TopicCmt o where o.parentTopicCmt.id=''{0}''";
        return queryCount(MessageFormat.format(cql,id));
    }
}
