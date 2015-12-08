package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Friend;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;

/**
 * 通讯录
 * author : liufeng
 * create time:2015/8/12 10:53
 */
@Repository
public class FriendDao extends BaseDao<Friend> {

    public List<Friend> queryAll(){
        String hql = "from Friend friend";
        return query(hql);
    }

    public PageResult<Friend> queryFriend(PageResult<Friend> pageResult, String phone,
                                          String queryWords) {

        String hql = "from Friend o where (o.user.username=''{0}'' or o.friend.username=''{0}'') " +
                " and o.user.state=1 ";

        if(!StringUtils.isEmpty(queryWords)){
            hql += " and (o.user.alumnus.realName like ''%{1}%'' or o.friend.alumnus.realName like ''%{1}%'')";
        }

        return query(MessageFormat.format(hql,phone,queryWords),pageResult);
    }
    public Friend queryBy2Phone(String phone1,String phone2){
        String hql = "from Friend o where (o.user.username=''{0}'' and o.friend.username=''{1}'' " +
                "or o.user.username=''{1}'' and o.friend.username=''{0}'' and o.user.state=1 )";
        return queryUnique(MessageFormat.format(hql,phone1,phone2));
    }

    public int release(Friend friend) {
        String sql = "delete from friend where friend_id=''{0}'' and user_id=''{1}'' or user_id=''{0}'' and friend_id=''{1}''";
        return executeSql(MessageFormat.format(sql,friend.getFriend().getId(),friend.getUser().getId()));
    }

    public int release(Integer friendIs, Integer uerId) {
        String sql = "delete from friend where friend_id=''{0}'' and user_id=''{1}'' or user_id=''{0}'' and friend_id=''{1}''";
        return executeSql(MessageFormat.format(sql,friendIs,uerId));
    }
}
