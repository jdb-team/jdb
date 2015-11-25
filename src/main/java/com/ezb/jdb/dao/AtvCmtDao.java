package com.ezb.jdb.dao;

import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.AtvCmt;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * 活动评论
 * author : liufeng
 * create time:2015/8/10 14:01
 */
@Repository
public class AtvCmtDao extends BaseDao<AtvCmt> {

    public List<AtvCmt> qAtvCmtByActivityId(Integer id) {
        String hql = "from AtvCmt o where o.activity.id=''{0}'' order by o.createTime desc";
        return query(MessageFormat.format(hql, id));
    }

    public int qAtvCountCmtByPId(Integer id) {
        String cql = "from AtvCmt o where o.parentAtvCmt.id=''{0}''";
        return queryCount(MessageFormat.format(cql, id));
    }

    public int likeAtvCmt(Integer id) {
        String hql = "update AtvCmt o set o.likeCount=o.likeCount+1 where o.id=''{0}''";
        return executeHql(MessageFormat.format(hql, id));
    }
}
