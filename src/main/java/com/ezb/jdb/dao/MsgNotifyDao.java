package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.MsgNotify;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * author : liufeng
 * create time:2015/9/17 17:36
 */
@Repository
public class MsgNotifyDao extends BaseDao<MsgNotify> {
    public MsgNotify queryUser(Integer ctcUserId,Integer curId){
        String hql = "from MsgNotify o where o.ctcUser.id=''{0}'' and o.curUser.id=''{1}''";
        return queryUnique(MessageFormat.format(hql,ctcUserId,curId));
    }

    public MsgNotify queryUser2(Integer curId,Integer ctcUserId){
        String hql = "from MsgNotify o where o.ctcUser.id=''{0}'' and o.curUser.id=''{1}''";
        return queryUnique(MessageFormat.format(hql,curId,ctcUserId));
    }


    public MsgNotify queryCircle(Integer ctcId,Integer ctcCircleId){
        String hql = "from MsgNotify o where o.ctcUser.id=''{0}'' and o.ctcCircle.id=''{1}''";
        return queryUnique(MessageFormat.format(hql,ctcId,ctcCircleId));
    }

    public PageResult<MsgNotify> query(PageResult<MsgNotify> pageResult,String curPhone){
        String hql = "from MsgNotify o where o.curUser.username=''{0}'' order by o.createTime desc";
        return query(MessageFormat.format(hql,curPhone),pageResult);
    }
}
