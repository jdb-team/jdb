package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Friend;
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

    public String confireFriend(Integer id) {
        String hql = "update FriendApply  set f.createDate=now() where o.id=''{0}''";
        if(executeHql(MessageFormat.format(hql,id)) != 1){
            return ResponseState.CONFIRE_FRIEND_ERR;
        }else{

            return ResponseState.SUCCESS;
        }
    }

    public FriendApply queryBy2Phone(String phone1, String phone2){
        String hql = "from FriendApply f where (f.sender.username=''{0}'' and f.receiver.username=''{1}'' " +
                "or f.sender.username=''{1}'' and f.receiver.username=''{0}'' and f.state=1 )";
        return queryUnique(MessageFormat.format(hql,phone1,phone2));
    }

    public int release(FriendApply friend) {
        String sql = "delete from friendapply where sender=''{0}'' and receiver=''{1}'' or receiver=''{0}'' and sender =''{1}''";
        return executeSql(MessageFormat.format(sql,friend.getSender().getId(),friend.getReceiver().getId()));
    }
}
