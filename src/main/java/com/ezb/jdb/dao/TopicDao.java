package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Topic;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 话题
 * author : liufeng
 * create time: 2015/8/7 10:37.
 */
@Repository
public class TopicDao extends BaseDao<Topic> {

    public PageResult<Topic> queryTopic(PageResult<Topic> pageResult,
                                        String phone, String speColumnId,
                                        String typeId, String lableId,
                                        Boolean isShield, Boolean isOneUser) {

        List<Object> paramList = new ArrayList<Object>();
        int pIndex = 0;

        String hql = "from Topic topic where topic.state=1 ";

        if (!StringUtils.isEmpty(phone)) {
            //查询指定phone用户的话题列表
            if (null != isOneUser && isOneUser) {
                hql += " and topic.createUser.username=''{" + pIndex++ + "}'' ";
                paramList.add(phone);
            }

            //过滤掉屏蔽的用户话题
            if (null != isShield && isShield) {
                hql += " and topic.createUser.id not in (select o.shieldUser.id from" +
                        " ShieldUser o where o.curUser.username=''{" + pIndex++ + "}'')";
                paramList.add(phone);
            }
        }

        //查询指定专栏下的话题
        if (!StringUtils.isEmpty(speColumnId)) {
            hql += " and topic.speColumn.id=''{" + pIndex++ + "}'' ";
            paramList.add(speColumnId);
        }

        //查询指定分类下的话题
        if (!StringUtils.isEmpty(typeId)) {
            hql += " and topic.topicType.id=''{" + pIndex++ + "}'' ";
            paramList.add(typeId);
        }

        //热点
        if ("1".equals(lableId)) {
            hql += " order by topic.lv desc,topic.createTime desc";

        } else {
            //好友的最新话题
            if ("3".equals(lableId)) {
                if (null != isOneUser && !isOneUser) {

                    String inHql = " topic.createUser.id in (" +
                            " select friend.xxx.id from Friend friend " +
                            " where friend.user.username=''{" + pIndex + "}''" +
                            " or friend.friend.username=''{" + pIndex + "}'')";

                    hql += " and ("
                            + inHql.replace("xxx", "user") +
                            " or"
                            + inHql.replace("xxx", "friend")
                            + ")";

                    paramList.add(phone);
                }
            }

            //最新
            hql += " order by topic.createTime desc";
        }

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }

    public PageResult<Topic> queryTopic(PageResult<Topic> pageResult, String keyword) {

        List<Object> paramList = new ArrayList<Object>();
        int pIndex = 0;

        String hql = "from Topic o where o.state=1";

        if (!StringUtils.isEmpty(keyword)) {
            hql += " and o.content like ''%{" + pIndex++ + "}%'' ";
            paramList.add(keyword);
        }

        hql += " order by o.createTime desc";
        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }

    public PageResult<Topic> query(PageResult<Topic> pageResult,
                                   Integer id, String title,
                                   String startTime, String endTime,
                                   String username, String realName,
                                   String type, String state) {

        List<Object> paramList = new ArrayList<Object>();
        int pIndex = 0;

        String hql = "from Topic o where 1=1";

        if (null != id) {
            hql += " and o.id like ''%{" + pIndex++ + "}%'' ";
            paramList.add(id);
        }

        if (!StringUtils.isEmpty(title)) {
            hql += " and o.title like ''%{" + pIndex++ + "}%'' ";
            paramList.add(title);
        }

        if (!StringUtils.isEmpty(startTime)) {
            hql += " and o.createTime >= ''{" + pIndex++ + "}'' ";
            paramList.add(startTime);
        }

        if (!StringUtils.isEmpty(endTime)) {
            hql += " and o.createTime <= ''{" + pIndex++ + "}'' ";
            paramList.add(endTime);
        }

        if (!StringUtils.isEmpty(username)) {
            hql += " and o.createUser.username like ''%{" + pIndex++ + "}%'' ";
            paramList.add(username);
        }

        if (!StringUtils.isEmpty(realName)) {
            hql += " and o.createUser.alumnus.realName like ''%{" + pIndex++ + "}%'' ";
            paramList.add(realName);
        }

        if (!StringUtils.isEmpty(type)) {
            hql += " and o.type = ''{" + pIndex++ + "}'' ";
            paramList.add(type);
        }

        if (!StringUtils.isEmpty(state)) {
            hql += " and o.state = ''{" + pIndex++ + "}'' ";
            paramList.add(state);
        }

        hql += " order by o.createTime desc";
        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }
}
