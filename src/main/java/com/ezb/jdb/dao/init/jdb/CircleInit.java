package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日上午11:28:38
 * @Description: 圈子初始化
 */
@Component
public class CircleInit {

    @Resource
    private CircleDao circleDao;

    @Resource
    private UserDao userDao;

    public void init() {
        for (int i = 1; i < 101; i++) {

            Circle circle = new Circle();
            circle.setCreateTime(new Date());
            Random rd = new Random();

            int pathId = rd.nextInt(3);
            String[] path = {"uploadfiles/20150811/20150811111719207/pic1.jpg",
                    "uploadfiles/20150811/20150811111719207/pic2.jpg",
                    "uploadfiles/20150811/20150811111719207/pic3.jpg"};

            circle.setIconPath(path[pathId]);
            circle.setPicPath(path[pathId]);
            circle.setIntroduce("introduce" + String.valueOf(i));
            circle.setState(1);
            circle.setTitle("title" + String.valueOf(i));

            int id = rd.nextInt(100) + 1;
            User user = userDao.get(User.class, id);
            circle.setCreateUser(user);
            circleDao.add(circle);
        }
    }
}
