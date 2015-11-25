package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.SpeColumn;
import com.ezb.jdb.model.SpeColumn;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * 专栏
 * author : liufeng
 * create time:2015/8/14 16:04
 */
@Repository
public class SpeColumnDao extends BaseDao<SpeColumn> {

    public void deleteById(String id) {
        String hql = "delete from SpeColumn o where o.id=''{0}''";
        executeHql(MessageFormat.format(hql, id));
    }

    public PageResult<SpeColumn> query(PageResult<SpeColumn> pageResult, String columnName) {
        String hql = "from SpeColumn o where o.columnName like ''%{0}%''";
        return query(MessageFormat.format(hql,columnName),pageResult);
    }
}
