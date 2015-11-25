package com.ezb.jdb.dao;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.VerifyCode;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * author : liufeng
 * create time: 2015/8/6 10:36.
 */
@Repository
public class VerifyCodeDao extends BaseDao<VerifyCode> {

    public int qcByPhoneAndCode(String phone,String code){
        String hql = "from VerifyCode o where o.phone={0} and o.verifyCode={1}";
        return queryCount(MessageFormat.format(hql,phone,code));
    }

    public int clearExpire() {
        String hql = "delete from VerifyCode o where o.createTime < ''{0}''";
        String day = DateTime.now().minusDays(2).toString(JdbConstants.D_FMT);
        return executeHql(MessageFormat.format(hql,day));
    }
}
