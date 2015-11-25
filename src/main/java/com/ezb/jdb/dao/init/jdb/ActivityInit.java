package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.ActivityDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日上午9:09:03
 * @Description: 活动初始化
 */
@Slf4j
@Component
public class ActivityInit {

    @Resource
    private ActivityDao activityDao;
    @Resource
    private UserDao userDao;

    public void init() {
        for (int i = 1; i < 101; i++) {

            Activity activity = new Activity();
            activity.setActivityDesc("activitydesc" + String.valueOf(i));
            activity.setAddress("address" + String.valueOf(i));
            activity.setCity("city" + String.valueOf(i));

            activity.setContactEmail("contactemail" + String.valueOf(i) + "@qq.com");
            activity.setContactMan("contactman" + String.valueOf(i));
            activity.setContactPhone("contactphone" + String.valueOf(i));
            activity.setCreateTime(new Date());
            activity.setEndTime(new Date());
            activity.setJoinFee("joinfee" + String.valueOf(i));

            Random rd = new Random();
            int picPathId = rd.nextInt(3);

            String[] picPath = {"uploadfiles/20150811/20150811111719207/pic1.jpg",
                    "uploadfiles/20150811/20150811111719207/pic2.jpg",
                    "uploadfiles/20150811/20150811111719207/pic3.jpg"};

            activity.setPicPath(picPath[picPathId]);
            activity.setPv(rd.nextInt(100) + 1);
            activity.setStartTime(new Date());
            activity.setState(1);
            activity.setTitle("title" + String.valueOf(i));

            int userId = rd.nextInt(100) + 1;
            User user = userDao.get(User.class, userId);
            activity.setCreateUser(user);
            activity.setCloseTime(new Date());
            activity.setOtherInfo("otherinfo" + String.valueOf(i));
            activity.setPersonLimit(rd.nextInt(100) + 1);
            activity.setTopic("topic" + String.valueOf(i));

            activityDao.add(activity);
        }
    }
}
