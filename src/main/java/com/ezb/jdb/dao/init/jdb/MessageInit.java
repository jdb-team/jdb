package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.MessageDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日下午1:47:04
 * @Description: 消息初始化
 */
@Component
public class MessageInit {

    @Resource
    private MessageDao messageDao;

    @Resource
    private UserDao userDao;

    public void init() {
        for (int i = 1; i < 101; i++) {

            Message message = new Message();
            message.setContent("content" + String.valueOf(i));
            message.setCreateTime(new Date());
            Random rd = new Random();
            message.setState(rd.nextInt(2));

            int receiverId = rd.nextInt(100) + 1;
            User receiver = userDao.get(User.class, receiverId);
            message.setReceiver(receiver);

            int senderId = rd.nextInt(100) + 1;
            User sender = userDao.get(User.class, senderId);
            message.setSender(sender);
            messageDao.add(message);
        }
    }
}
