package com.ezb.jdb.dao;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.InvitateCode;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * author : liufeng
 * create time: 2015/8/6 11:06.
 */
@Repository
public class InvitateCodeDao extends BaseDao<InvitateCode> {

    public int qcByCode(String code) {
        String hql = "from InvitateCode o where o.code=''{0}''";
        return queryCount(MessageFormat.format(hql, code));
    }

    public int deleteByCode(String invitateCode) {
        String hql = "delete from InvitateCode o  where o.code=''{0}''";
        return executeHql(MessageFormat.format(hql, invitateCode));
    }

    public int clearExpire() {
        String hql = "delete from InvitateCode o where o.createTime < ''{0}''";
        String day = DateTime.now().minusDays(2).toString(JdbConstants.D_FMT);
        return executeHql(MessageFormat.format(hql, day));
    }

    public PageResult<InvitateCode> queryByUserId(PageResult<InvitateCode> pageResult, Integer userId) {
        String hql = "from InvitateCode o where o.createUser.id=''{0}''";
        return query(MessageFormat.format(hql, userId), pageResult);
    }
}
