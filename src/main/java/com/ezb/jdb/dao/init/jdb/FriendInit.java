package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.FriendDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日上午10:39:16
 * @Description: 朋友初始化
 */
@Component
public class FriendInit {

    @Resource
    private FriendDao friendDao;
    @Resource
    private UserDao userDao;

    public void init() {
        for (int i = 1; i < 101; i++) {

            for (int j = 1; j < 6; j++) {

                Friend friend = new Friend();
                friend.setApplyDate(new Date());
                friend.setConfireDate(new Date());
                Random rd = new Random();

                double distance = rd.nextDouble() * 1000;
                friend.setDistance(distance);
                int stateId = rd.nextInt(2);

                boolean state;
                if (stateId == 1) {
                    state = true;
                } else {
                    state = false;
                }

                friend.setState(state);
                int UserId = i;

                int friendId = rd.nextInt(100) + 1;
                while (true) {
                    if (friendId != UserId)
                        break;
                    else
                        friendId = rd.nextInt(100) + 1;
                }

                User user = userDao.get(User.class, friendId);
                friend.setFriend(user);

                user = userDao.get(User.class, UserId);
                friend.setUser(user);
                friendDao.add(friend);
            }
        }
    }
}
