package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.FriendApply;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 添加好友请求
 * Created by liuyan on 2015/12/1.
 */
@Repository
public class FriendApplyDao extends BaseDao<FriendApply>{
    public PageResult<FriendApply> queryFriendApply(PageResult<FriendApply> pageResult,String phone ){
        String hql = "from FriendApply f where f.receiver.username = ''{0}''";
        return query(MessageFormat.format(hql, phone), pageResult);
    }
}
