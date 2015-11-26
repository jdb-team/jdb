package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.ShildJoinUserCircleDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.JoinUserCircle;
import com.ezb.jdb.model.User;
import com.ezb.jdb.dao.JoinUserCircleDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by Administrator on 2015/11/26.
 */
@Component
public class JoinUserCircleInit {

    @Resource
    private JoinUserCircleDao joinUserCircleDao;

    @Resource
    private CircleDao circleDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ShildJoinUserCircleDao shildJoinUserCircleDao;

    public void init() {
        int count = 1;
        for(int i = 0; i < 100; i++){
            JoinUserCircle joinUserCircle = new JoinUserCircle();
            Random rd = new Random();

            int circleId = rd.nextInt(100) + 1;
            int userId = rd.nextInt(100) + 1;
            JoinUserCircle joinUserCircle1 = shildJoinUserCircleDao.queryByUserAndCircle(userId,circleId);
            if(joinUserCircle1 != null){
                i--;
                break;
            }

            String name = "account";
            name = name + count;
            count++;
            joinUserCircle.setNickName(name);

            User receiver = userDao.get(User.class, userId);
            joinUserCircle.setUser(receiver);

            Circle circle = circleDao.get(Circle.class,circleId);
            joinUserCircle.setCircle(circle);


            joinUserCircleDao.add(joinUserCircle);
        }
    }

}
