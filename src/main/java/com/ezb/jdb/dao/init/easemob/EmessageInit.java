package com.ezb.jdb.dao.init.easemob;

import com.ezb.jdb.dao.MessageDao;
import com.ezb.jdb.easemob.api.Messages;
import com.ezb.jdb.model.Message;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 消息发送初始化
 * author : liufeng
 * create time:2015/11/25 13:40
 */
@Slf4j
@Component
public class EmessageInit {

    @Resource
    private MessageDao messageDao;

    private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    public void init() {

        List<Message> list = messageDao.queryAll();

        for (int i = 0; i < list.size(); i++) {
            Message message = list.get(i);

            String from = message.getSender().getId() + "";
            String targetTypeus = "users";
            ObjectNode ext = factory.objectNode();
            ArrayNode targetusers = factory.arrayNode();
            targetusers.add(message.getReceiver().getId() + "");
            ObjectNode txtmsg = factory.objectNode();
            txtmsg.put("msg", message.getContent());
            txtmsg.put("type", "txt");
            ObjectNode sendTxtMessageusernode = Messages.sendMessages(targetTypeus, targetusers, txtmsg, from, ext);

            if (null != sendTxtMessageusernode) {
                try {
                    Thread.sleep(2000);
                    log.info("cur:" + i + "/" + list.size());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 环信 消息关系的清除方法
     */
    public  void  remove(){

    }
}
