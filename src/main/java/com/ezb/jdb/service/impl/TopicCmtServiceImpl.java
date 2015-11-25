package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.AccessKeyDao;
import com.ezb.jdb.dao.TopicCmtDao;
import com.ezb.jdb.dao.TopicDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Topic;
import com.ezb.jdb.model.TopicCmt;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.ITopicCmtService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 话题评论
 * author : liufeng
 * create time: 2015/8/7 14:48.
 */
@Service
public class TopicCmtServiceImpl implements ITopicCmtService {

    @Resource
    private UserDao userDao;

    @Resource
    private TopicCmtDao topicCmtDao;

    @Resource
    private TopicDao topicDao;

    @Resource
    private AccessKeyDao accessKeyDao;

    public String addTopicCmt(String phone, TopicCmt topicCmt) {
        User user = userDao.queryByPhone(phone);
        if (null != user) {

            Topic topic = topicDao.get(Topic.class, topicCmt.getTopic().getId());
            if (null == topic) {
                return ResponseState.INVALID_ID;
            }
            topicCmt.setTopic(topic);

            if (null != topicCmt.getParentTopicCmt()
                    && null != topicCmt.getParentTopicCmt().getId()) {

                TopicCmt parentTopicCmt = topicCmtDao.get(
                        TopicCmt.class, topicCmt.getParentTopicCmt().getId());
                topicCmt.setParentTopicCmt(parentTopicCmt);
            }

            topicCmt.setLikeCount(0);
            topicCmt.setCommentUser(user);
            topicCmt.setCreateTime(new Date());
            topicCmtDao.add(topicCmt);
            return ResponseState.SUCCESS;
        } else {
            return ResponseState.INVALID_PHONE;
        }
    }

    public String likeTopicCmt(String phone, Integer id) {
        User user = userDao.queryByPhone(phone);
        if (null != user) {
            String accessKey = DateTime.now().toString(JdbConstants.HOUR_FMT) + phone + "likeTopicCmt" + id;
            if (!accessKeyDao.exist(accessKey, JdbConstants.ACCKEY_TOPICCMT)) {
                accessKeyDao.add(accessKey, JdbConstants.ACCKEY_TOPICCMT);
                if (1 == topicCmtDao.likeTopicCmt(id)) {
                    return ResponseState.SUCCESS;
                } else {
                    return ResponseState.LIKE_OPT_ERR;
                }
            } else {
                return ResponseState.LIKE_OPT_REPEAT;
            }

        } else {
            return ResponseState.INVALID_PHONE;
        }
    }

    public List<TopicCmt> qTopicCmtByTopicId(Integer topicId) {
        return topicCmtDao.qTopicCmtByTopicId(topicId);
    }
}
