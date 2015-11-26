package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.CircleMessageDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.CircleMessage;
import com.ezb.jdb.model.JoinUserCircle;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * author : liufeng
 * create time:2015/11/26 10:11
 */
@Component
public class CircleMessageInit {

    @Resource
    private CircleMessageDao circleMessageDao;

    @Resource
    private CircleDao circleDao;

    public void init() {

        for (int i = 1; i <= 100; i++) {

            Circle circle = circleDao.get(Circle.class, i);

            if (null != circle.getMembers()) {
                for (JoinUserCircle joinUserCircle : circle.getMembers()) {

                    User user = joinUserCircle.getUser();
                    CircleMessage circleMessage = new CircleMessage();
                    circleMessage.setCircle(circle);
                    circleMessage.setSender(user);

                    circleMessage.setContent("content" + i);
                    circleMessage.setCreateTime(new Date());
                    circleMessageDao.add(circleMessage);
                }
            }
        }
    }
}
