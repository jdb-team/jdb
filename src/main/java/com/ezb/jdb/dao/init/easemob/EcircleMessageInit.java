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
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * author : liufeng
 * create time:2015/11/26 13:52
 */
@Slf4j
@Component
public class EcircleMessageInit {

    @Resource
    private CircleMessageDao circleMessageDao;

    private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    public void init() {
        List<CircleMessage> list = circleMessageDao.queryAll();

        for (int i = 0; i < list.size(); i++) {
            CircleMessage circleMessage = list.get(i);

            if (!StringUtils.isEmpty(circleMessage.getCircle().getEid())) {

                String from = circleMessage.getSender().getId() + "";
                String targetTypegr = "chatgroups";
                ArrayNode targetgroup = factory.arrayNode();
                targetgroup.add(circleMessage.getCircle().getEid());
                ObjectNode txtmsg = factory.objectNode();

                txtmsg.put("msg", circleMessage.getContent());
                txtmsg.put("type", "txt");
                ObjectNode ext = factory.objectNode();

                Messages.sendMessages(targetTypegr, targetgroup, txtmsg, from, ext);

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
