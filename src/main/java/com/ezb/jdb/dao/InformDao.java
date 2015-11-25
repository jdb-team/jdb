package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Inform;
import com.ezb.jdb.model.Message;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 举报
 * author : liufeng
 * create time:2015/8/13 14:27
 */
@Repository
public class InformDao extends BaseDao<Inform> {

    public PageResult<Inform> query(PageResult<Inform> pageResult,
                                    String username,String realname,
                                    String qbusername,String qbrealname,
                                    String startTime,String endTime,
                                    String reason, String type, String state) {
        int index = 0;
        List<String> paramList = new ArrayList<String>();

        String hql = "from Inform o where 1=1";

        if (!StringUtils.isEmpty(username)) {
            hql += " and o.createUser.username like ''%{" + index++ + "}%''";
            paramList.add(username);
        }

        if (!StringUtils.isEmpty(realname)) {
            hql += " and o.createUser.alumnus.realName like ''%{" + index++ + "}%''";
            paramList.add(realname);
        }

        if (!StringUtils.isEmpty(qbusername)) {
            hql += " and o.informUser.username like ''%{" + index++ + "}%''";
            paramList.add(qbusername);
        }

        if (!StringUtils.isEmpty(qbrealname)) {
            hql += " and o.informUser.alumnus.realName like ''%{" + index++ + "}%''";
            paramList.add(qbrealname);
        }

        if (!StringUtils.isEmpty(startTime)) {
            hql += " and o.createTime>=''{" + index++ + "}''";
            paramList.add(startTime);
        }

        if (!StringUtils.isEmpty(endTime)) {
            hql += " and o.createTime<=''{" + index++ + "}''";
            paramList.add(endTime);
        }

        if (!StringUtils.isEmpty(reason)) {
            hql += " and o.reason like ''%{" + index++ + "}%''";
            paramList.add(reason);
        }

        if (!StringUtils.isEmpty(type)) {
            hql += " and o.type like ''%{" + index++ + "}%''";
            paramList.add(type);
        }

        if (!StringUtils.isEmpty(state)) {
            hql += " and o.state=''{" + index++ + "}''";
            paramList.add(state);
        }

        hql += "order by o.createTime desc";

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }

    public int del(Integer id) {
        String hql = "delete from Inform o where o.id=''{0}''";
        return executeHql(MessageFormat.format(hql, id));
    }
}
