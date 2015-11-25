package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.Message;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 活动
 * author : liufeng
 * create time:2015/8/10 10:05
 */
@Repository
public class ActivityDao extends BaseDao<Activity> {

    public PageResult<Activity> queryActivity(PageResult<Activity> pageResult, String keyWords) {

        String hql = "from Activity o ";
        if (null != keyWords) {
            if (!StringUtils.isEmpty(keyWords.trim())) {
                hql += "where o.title like ''%{0}%'' ";
            }
        }
        hql += " order by o.createTime desc";

        return query(MessageFormat.format(hql, keyWords), pageResult);
    }

    public int updatePv(Integer id) {
        String hql = "update Activity o set o.pv=o.pv+1 where id=''{0}''";
        return executeHql(MessageFormat.format(hql, id));
    }

    public PageResult<Activity> queryMyActivity(PageResult<Activity> pageResult,
                                                String phone, String queryWords) {
        String hql = "from Activity o where o.createUser.username=''{0}''";
        if (null != queryWords) {
            if (!StringUtils.isEmpty(queryWords.trim())) {
                hql += " and o.title like ''%{1}%'' ";
            }
        }
        hql += " order by o.createTime desc";

        return query(MessageFormat.format(hql, phone, queryWords), pageResult);
    }

    public PageResult<Activity> queryMyJoinActivity(PageResult<Activity> pageResult,
                                                    String phone, String queryWords) {
        String appendQl = "from Activity o join o.joinUsers u where u.username=''{0}''";
        if (null != queryWords) {
            if (!StringUtils.isEmpty(queryWords.trim())) {
                appendQl += " and o.title like ''%{1}%'' ";
            }
        }
        appendQl += " order by o.createTime desc";
        appendQl = MessageFormat.format(appendQl, phone, queryWords);

        String hql = "select o " + appendQl;
        String cql = "select count(*) " + appendQl;
        return query(hql, cql, pageResult);
    }

    public PageResult<Activity> query(PageResult<Activity> pageResult, Integer id, String title,
                                      String username, String realName,
                                      String state, String startTime, String endTime) {
        List<Object> paramList = new ArrayList<Object>();
        String hql = "from Activity o where 1=1";
        int i = 0;

        if (null != id) {
            hql += "and o.id like ''%{" + i + "}%''";
            paramList.add(id);
            i++;
        }

        if (!StringUtils.isEmpty(username)) {
            hql += "and o.createUser.username like ''%{" + i + "}%''";
            paramList.add(username);
            i++;
        }

        if (!StringUtils.isEmpty(realName)) {
            hql += "and o.createUser.alumnus.realName like ''%{" + i + "}%''";
            paramList.add(realName);
            i++;
        }

        if (!StringUtils.isEmpty(title)) {
            hql += "and o.title like ''%{" + i + "}%''";
            paramList.add(title);
            i++;
        }

        if (!StringUtils.isEmpty(state)) {
            hql += "and o.state = ''{" + i + "}''";
            paramList.add(state);
            i++;
        }

        if (!StringUtils.isEmpty(startTime)) {
            hql += "and o.createTime >= ''{" + i + "}''";
            paramList.add(startTime);
            i++;
        }

        if (!StringUtils.isEmpty(endTime)) {
            hql += "and o.createTime <= ''{" + i + "}''";
            paramList.add(endTime);
            i++;
        }

        hql += "order by o.createTime desc";

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }
}
