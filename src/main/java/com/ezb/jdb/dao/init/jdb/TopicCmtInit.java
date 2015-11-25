package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.TopicCmtDao;
import com.ezb.jdb.dao.TopicDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Topic;
import com.ezb.jdb.model.TopicCmt;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月23日下午4:07:54
 * @Description: 标题评论
 */
@Component
public class TopicCmtInit {

    @Resource
    private TopicCmtDao topicCmtDao;

    @Resource
    private UserDao userDao;

    @Resource
    private TopicDao topicDao;

    public void init() {
        //前十条为父评论
        for (int i = 1; i < 11; i++) {
            TopicCmt topicCmt = new TopicCmt();
            topicCmt.setContent("content" + String.valueOf(i));
            topicCmt.setCreateTime(new Date());
            Random rd = new Random();

            int likeCount = rd.nextInt(100);
            likeCount += 1;
            topicCmt.setLikeCount(likeCount);

            int commentUserId = rd.nextInt(100);
            commentUserId += 1;
            User commentUser = userDao.get(User.class, commentUserId);
            topicCmt.setCommentUser(commentUser);

            int topicId = rd.nextInt(100);
            topicId += 1;
            Topic topic = topicDao.get(Topic.class, topicId);
            topicCmt.setTopic(topic);
            topicCmtDao.add(topicCmt);
        }
        //插入后90条子评论
        for (int i = 11; i < 101; i++) {

            TopicCmt topicCmt = new TopicCmt();
            topicCmt.setContent("content" + String.valueOf(i));
            topicCmt.setCreateTime(new Date());
            Random rd = new Random();

            int likeCount = rd.nextInt(100);
            likeCount += 1;
            topicCmt.setLikeCount(likeCount);

            int commentUserId = rd.nextInt(100);
            commentUserId += 1;
            User commentUser = userDao.get(User.class, commentUserId);
            topicCmt.setCommentUser(commentUser);

            int topicId = rd.nextInt(100);
            topicId += 1;
            Topic topic = topicDao.get(Topic.class, topicId);
            topicCmt.setTopic(topic);
            topicCmtDao.add(topicCmt);

            int parentId = rd.nextInt(10);
            parentId += 1;
            TopicCmt parentTopicCmt = topicCmtDao.get(TopicCmt.class, parentId);
            topicCmt.setParentTopicCmt(parentTopicCmt);
            topicCmtDao.add(topicCmt);
        }
    }
}
