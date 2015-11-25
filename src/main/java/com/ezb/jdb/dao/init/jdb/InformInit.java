package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.InformDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Inform;
import com.ezb.jdb.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日下午2:52:21
 * @Description: 信息初始化
 */
@Component
public class InformInit {

    @Resource
    private InformDao informDao;

    @Resource
    private UserDao userDao;

    public void init() {

        String[] type = {"话题", "活动", "圈子"};
        String[] viewurl = {"admin/topic/view", "admin/activity/view", "admin/circle/view"};
        String[] reason = {"色情", "欺诈", "诋毁侮辱", "广告骚扰", "政治", "非交大校友", "其他"};

        for (int i = 1; i < 101; i++) {

            Inform inform = new Inform();
            Random rd = new Random();

            int refId = rd.nextInt(100) + 1;
            inform.setAssoId(refId);
            inform.setAssoName("title" + String.valueOf(i));
            inform.setCreateTime(new Date());
            inform.setOtherInfo("otherinfo" + String.valueOf(i));
            inform.setReason(reason[rd.nextInt(7)]);
            inform.setState(rd.nextInt(2));

            int index = rd.nextInt(3);
            inform.setType(type[index]);
            inform.setViewurl(viewurl[index] + "id=" + String.valueOf(refId));

            int createUserId = rd.nextInt(100) + 1;
            User createUser = userDao.get(User.class, createUserId);
            inform.setCreateUser(createUser);

            int informUserId = rd.nextInt(100) + 1;
            User informUser = userDao.get(User.class, informUserId);
            inform.setInformUser(informUser);

            informDao.add(inform);
        }
    }
}
