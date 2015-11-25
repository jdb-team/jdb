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
}
