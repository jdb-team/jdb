package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.CirCmtDao;
import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.CirCmt;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日下午1:27:50
 * @Description: 圈内评论
 */
@Component
public class CirCmtInit {

    @Resource
    private CirCmtDao cirCmtDao;

    @Resource
    private UserDao userDao;

    @Resource
    private CircleDao circleDao;

    public void init() {
        //前十条为父评论
        for (int i = 1; i < 11; i++) {

            CirCmt cirCmt = new CirCmt();
            cirCmt.setContent("content" + String.valueOf(i));
            cirCmt.setCreateTime(new Date());
            Random rd = new Random();

            int likeCount = rd.nextInt(100);
            likeCount += 1;
            cirCmt.setLikeCount(likeCount);

            int commentUserId = rd.nextInt(100);
            commentUserId += 1;
            User commentUser = userDao.get(User.class, commentUserId);
            cirCmt.setCommentUser(commentUser);

            int circleId = rd.nextInt(100) + 1;
            Circle circle = circleDao.get(Circle.class, circleId);
            cirCmt.setCircle(circle);
            cirCmtDao.add(cirCmt);
        }

        //插入后90条子评论
        for (int i = 11; i < 101; i++) {

            CirCmt cirCmt = new CirCmt();
            cirCmt.setContent("content" + String.valueOf(i));
            cirCmt.setCreateTime(new Date());
            Random rd = new Random();

            int likeCount = rd.nextInt(100);
            likeCount += 1;
            cirCmt.setLikeCount(likeCount);
            int commentUserId = rd.nextInt(100);
            commentUserId += 1;
            User commentUser = userDao.get(User.class, commentUserId);
            cirCmt.setCommentUser(commentUser);

            int circleId = rd.nextInt(100) + 1;
            Circle circle = circleDao.get(Circle.class, circleId);
            cirCmt.setCircle(circle);

            int parentId = rd.nextInt(10);
            parentId += 1;
            CirCmt parentCirCmt = cirCmtDao.get(CirCmt.class, parentId);
            cirCmt.setParentCirCmt(parentCirCmt);

            cirCmtDao.add(cirCmt);
        }
    }
}
