package com.ezb.jdb.dao;

import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.ShieldUser;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * 屏蔽关系表
 * author : liufeng
 * create time:2015/11/21 16:40
 */
@Repository
public class ShieldDao extends BaseDao<ShieldUser> {

    public ShieldUser queryBy2Phone(String phone1,String phone2){
        String hql = "from ShieldUser o where o.curUser.username=''{0}'' and o.shieldUser.username=''{1}''";
        return queryUnique(MessageFormat.format(hql,phone1,phone2));
    }
}
