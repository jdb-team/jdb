package com.ezb.jdb.dao.init.easemob;

import com.ezb.jdb.dao.CircleMessageDao;
import com.ezb.jdb.easemob.api.ChatGroups;
import com.ezb.jdb.easemob.api.Messages;
import com.ezb.jdb.model.CircleMessage;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
@Slf4j
@Component
public class EcircleMessageInit {

    @Resource
    private CircleMessageDao circleMessageDao;

    private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    public void init() {
        List<CircleMessage> list = circleMessageDao.queryAll();
        JsonNode chatgroupidsNode =  ChatGroups.getAllChatgroupids().path("data").get(0);
        for (int i = 0; i < list.size(); i++) {
            CircleMessage circleMessage = list.get(i);

            String from = circleMessage.getSender().getId() + "";
            String targetTypegr = "chatgroups";
            ArrayNode targetgroup = factory.arrayNode();
            targetgroup.add(chatgroupidsNode.get(i).path("groupid").asText());
            ObjectNode txtmsg = factory.objectNode();
            txtmsg.put("msg", circleMessage.getContent());
            txtmsg.put("type", "txt");
            ObjectNode ext = factory.objectNode();
            ObjectNode sendTxtMessagegroupnode = Messages.sendMessages(targetTypegr, targetgroup, txtmsg, from, ext);

            if (null != sendTxtMessagegroupnode) {
                try {
                    Thread.sleep(1000);
                    log.info("cur:" + i + "/" + list.size());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }
}
