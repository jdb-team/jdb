package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.CirCmt;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * 圈子
 * author : liufeng
 * create time:2015/8/14 16:51
 */
@Repository
public class CirCmtDao extends BaseDao<CirCmt> {
    public int queryCount(Integer circleId) {
        String hql = "from CirCmt o where o.circle.id=''{0}'' order by o.createTime desc";
        return super.queryCount(MessageFormat.format(hql, circleId));
    }

    public PageResult<CirCmt> queryCircmts(PageResult<CirCmt> pageResult, String circleid) {
        String hql = "from CirCmt o where o.circle.id=''{0}'' order by o.createTime desc";
        return query(MessageFormat.format(hql,circleid),pageResult);
    }
}
