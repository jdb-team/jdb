package com.ezb.jdb.dao.init.jdb;

/**
 * 话题数据初始化
 * author : liufeng
 * create time:2015/11/23 10:16
 */

import com.ezb.jdb.dao.TopicDao;
import com.ezb.jdb.dao.TopicTypeDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Topic;
import com.ezb.jdb.model.TopicType;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

@Component
public class TopicInit {

    @Resource
    private TopicDao topicDao;

    @Resource
    private TopicTypeDao topicTypeDao;

    @Resource
    private UserDao userDao;

    public void init() {
        for (int i = 1; i < 101; i++) {
            Topic topic = new Topic();
            topic.setContent("content" + String.valueOf(i));
            topic.setCreateTime(new Date());
            topic.setPv(1);
            topic.setState(1);
            Random rd = new Random();

            int topicId = rd.nextInt(5);
            topicId += 1;
            TopicType topicType = topicTypeDao.get(TopicType.class, topicId);
            topic.setTopicType(topicType);

            int userId = rd.nextInt(100);
            userId += 1;
            User user = userDao.get(User.class, userId);
            topic.setCreateUser(user);

            String[] paths = {"uploadfiles/20150811/20150811111719207/pic1.jpg",
                    "uploadfiles/20150811/20150811111719207/pic2.jpg",
                    "uploadfiles/20150811/20150811111719207/pic3.jpg"};

            int picPathId = rd.nextInt(3);
            topic.setPicPath(paths[picPathId]);
            topicDao.add(topic);
        }
    }
}