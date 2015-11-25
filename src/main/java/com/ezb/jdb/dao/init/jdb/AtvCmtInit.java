package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.ActivityDao;
import com.ezb.jdb.dao.AtvCmtDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.AtvCmt;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日上午10:05:01
 * @Description: 活动评论
 */
@Component
public class AtvCmtInit {

    @Resource
    private AtvCmtDao atvCmtDao;
    @Resource
    private UserDao userDao;
    @Resource
    private ActivityDao activityDao;

    public void init() {

        //前十条为父评论

        for (int i = 1; i < 11; i++) {

            AtvCmt atvCmt = new AtvCmt();
            atvCmt.setContent("content" + String.valueOf(i));
            atvCmt.setCreateTime(new Date());
            Random rd = new Random();

            int likeCount = rd.nextInt(100);
            likeCount += 1;
            atvCmt.setLikeCount(likeCount);

            int commentUserId = rd.nextInt(100);
            commentUserId += 1;
            User commentUser = userDao.get(User.class, commentUserId);
            atvCmt.setCommentUser(commentUser);

            int activityId = rd.nextInt(100) + 1;
            Activity activity = activityDao.get(Activity.class, activityId);
            atvCmt.setActivity(activity);
            atvCmtDao.add(atvCmt);
        }

        //插入后90条子评论
        for (int i = 11; i < 101; i++) {

            AtvCmt atvCmt = new AtvCmt();
            atvCmt.setContent("content" + String.valueOf(i));
            atvCmt.setCreateTime(new Date());
            Random rd = new Random();

            int likeCount = rd.nextInt(100);
            likeCount += 1;
            atvCmt.setLikeCount(likeCount);

            int commentUserId = rd.nextInt(100);
            commentUserId += 1;
            User commentUser = userDao.get(User.class, commentUserId);
            atvCmt.setCommentUser(commentUser);

            int activityId = rd.nextInt(100) + 1;
            Activity activity = activityDao.get(Activity.class, activityId);
            atvCmt.setActivity(activity);

            int parentId = rd.nextInt(10);
            parentId += 1;
            AtvCmt parentAtvCmt = atvCmtDao.get(AtvCmt.class, parentId);
            atvCmt.setParentAtvCmt(parentAtvCmt);
            atvCmtDao.add(atvCmt);
        }
    }
}
