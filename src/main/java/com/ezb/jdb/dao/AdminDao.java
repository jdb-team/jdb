package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Admin;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author : liufeng
 * create time:2015/8/21 14:09
 */
@Repository
public class AdminDao extends BaseDao<Admin> {

    public boolean login(String username, String pass) {
        String hql = " from Admin o where o.username=''{0}'' and o.password=''{1}''";
        return queryCount(MessageFormat.format(hql, username, pass)) == 1;
    }

    /**
     * 用户名校验
     *
     * @param admin
     * @return
     */
    public boolean checkUserName(Admin admin) {
        String hql = "from Admin o where o.username=''{0}'' and o.id!=''{1}''";
        return queryCount(MessageFormat.format(hql, admin.getUsername(), admin.getId())) >= 1;
    }

    public void deleteById(Integer id) {
        String sql = "delete from admin where id=''{0}''";
        executeSql(MessageFormat.format(sql,id));
    }

    public PageResult<Admin> query(PageResult<Admin> pageResult, String username, String realName,
                                   String startTime, String endTime) {
        List<String> paramList = new ArrayList<String>();
        String hql = "from Admin o where 1=1 and o.level=1";
        int i = 0;

        //账号
        if (!StringUtils.isEmpty(username)) {
            hql += " and o.username like ''%{" + i++ + "}%''";
            paramList.add(username);
        }

        //姓名
        if (!StringUtils.isEmpty(realName)) {
            hql += " and o.realName like ''%{" + i++ + "}%''";
            paramList.add(realName);
        }

        //注册日期(开始)
        if (!StringUtils.isEmpty(startTime)) {
            hql += " and o.createTime>=''{" + i++ + "}''";
            paramList.add(startTime);
        }

        //注册日期(结束)
        if (!StringUtils.isEmpty(endTime)) {
            hql += " and o.createTime<=''{" + i++ + "}''";
            paramList.add(endTime);
        }

        hql += "order by o.createTime desc";

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }

    public Admin queryByNameAndPass(String username, String pass) {
        String hql = "from Admin o where o.username=''{0}'' and o.id!=''{1}''";
        return queryUnique(MessageFormat.format(hql, username, pass));
    }
}
