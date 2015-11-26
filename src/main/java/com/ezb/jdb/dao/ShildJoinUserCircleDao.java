package com.ezb.jdb.dao;

import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.JoinUserCircle;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * Created by Administrator on 2015/11/26.
 */
@Repository
public class ShildJoinUserCircleDao extends BaseDao<JoinUserCircle> {
    public JoinUserCircle queryByUserAndCircle(int userId, int circleId){
        String hql = "from JoinUserCircle o where o.user.id=''{0}'' and o.circle.id=''{1}''";
        return queryUnique(MessageFormat.format(hql,userId,circleId));
    }

}
