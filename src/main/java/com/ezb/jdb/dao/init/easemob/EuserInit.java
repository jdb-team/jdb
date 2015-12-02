package com.ezb.jdb.dao.init.easemob;

import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.easemob.api.ImUsers;
import com.ezb.jdb.model.User;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * author : liufeng
 * create time:2015/11/23 17:27
 */
@Slf4j
@Component
public class EuserInit {

    @Resource
    private UserDao userDao;

    public void init() {
        List<User> list = userDao.queryAll();
        for (int i = 0; i < list.size(); i++) {

            User user = list.get(i);
            ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
            objectNode.put("username", user.getId() + "");
            objectNode.put("password", user.getPassword());
            ImUsers.createNewIMUserSingle(objectNode);
            try {
                Thread.sleep(2000);
                log.info("cur:" + i + "/" + list.size());
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 环信 用户关系的清除方法
     */
    public  void  remove(){

    }
}
