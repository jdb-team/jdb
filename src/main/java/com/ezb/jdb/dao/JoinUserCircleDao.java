package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.JoinUserCircle;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;

/**
 * 圈子昵称
 * author : liufeng
 * create time:2015/11/19 13:59
 */
@Repository
public class JoinUserCircleDao extends BaseDao<JoinUserCircle> {

    /**
     * 根据用户id，圈子id查找昵称
     *
     * @param uid 用户id
     * @param cid 圈子id
     * @return
     */
    public JoinUserCircle getByUCId(Integer uid, Integer cid) {
        String hql = "from JoinUserCircle o where o.user.id=''{0}'' and o.circle.id=''{1}''";
        return queryUnique(MessageFormat.format(hql, uid, cid));
    }

    /**
     * 查询圈子成员
     * @param pageResult
     * @param circleid
     * @return
     */
    public PageResult<JoinUserCircle> queryMembers(PageResult<JoinUserCircle> pageResult, String circleid) {
        String hql = "from JoinUserCircle o where o.circle.id=''{0}''";
        return query(MessageFormat.format(hql,circleid),pageResult);
    }

    /**
     * 根据用户id，圈子id 确定圈子未读的消息条数 将未读的消息加一
     *
     * @param uid 用户id
     * @param cid 圈子id
     */
    public int updateMsgCount(Integer uid, Integer cid) {
        String sql =" UPDATE join_user_circle  j SET j.msg_count = if(isnull(j.msg_count),0,j.msg_count) + 1  WHERE j.user_id = "
                +uid+" and j.circle_id = "
                + cid;
        return  executeSql(sql);
    }

    /**
     * 根据用户id，圈子id  消息查看后，将消息的条数置为0
     *
     * @param uid 用户id
     * @param cid 圈子id
     */
    public int updateToZero(Integer uid, Integer cid){
        String sql =" UPDATE join_user_circle  j SET j.msg_count = 0  WHERE j.user_id = "
                +uid+" and j.circle_id = "
                + cid;
        return  executeSql(sql);
    }

    public int deleteById(Integer uid , Integer cid){
        String sql = "DELETE from join_user_circle  where user_id = ''{0}'' and circle_id =  ''{1}''";
        return executeSql(MessageFormat.format(sql,uid,cid));
    }
}
