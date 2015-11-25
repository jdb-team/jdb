package com.ezb.jdb.dao.init.easemob;

import com.ezb.jdb.dao.FriendDao;
import com.ezb.jdb.easemob.api.ImUsers;
import com.ezb.jdb.model.Friend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 好友体系集成
 * author : liufeng
 * create time:2015/11/24 9:10
 */
@Slf4j
@Component
public class EfriendInit {

    @Resource
    private FriendDao friendDao;

    public void init() {

        List<Friend> list = friendDao.queryAll();
        for (int i = 0; i < list.size(); i++) {
            Friend friend = list.get(i);
            ImUsers.addFriendSingle(friend.getUser().getId() + "", friend.getFriend().getId() + "");
            try {
                Thread.sleep(2000);
                log.info("cur:" + i + "/" + list.size());
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }
}
