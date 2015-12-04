package com.ezb.jdb.dao.init.easemob;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.easemob.api.ChatGroups;
import com.ezb.jdb.model.Circle;
import com.fasterxml.jackson.databind.node.*;
import com.mysql.jdbc.jmx.LoadBalanceConnectionGroupManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 圈子
 * author : liufeng
 * create time:2015/11/24 15:20
 */
@Slf4j
@Component
public class EcircleInit {
    @Resource
    private CircleDao circleDao;

    public void init() {

        List<Circle> list = circleDao.queryAll();
        for (int i = 0; i < list.size(); i++) {

            Circle circle = list.get(i);

            ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
            objectNode.put("groupname", circle.getTitle());
            objectNode.put("desc", circle.getIntroduce());
            objectNode.put("public", true);
            objectNode.put("maxusers", 300);
            objectNode.put("approval", false);

            objectNode.put("owner", circle.getCreateUser().getId() + "");

            ArrayNode arrayNode = JsonNodeFactory.instance.arrayNode();
            arrayNode.add("1");
            arrayNode.add("2");
            objectNode.put("members", arrayNode);

            ObjectNode resultNode = ChatGroups.creatChatGroups(objectNode);
            if(null != resultNode){
                circle.setEid(resultNode.path("data").get("groupid").asText());
            }

            circleDao.update(circle);

            try {
                Thread.sleep(2000);
                log.info("cur:" + i + "/" + list.size());
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 环信 群组关系的清除方法
     */
    public void  remove(){
        ObjectNode objectNode = ChatGroups.getAllChatgroupids();
        ArrayNode arrayNode = (ArrayNode) objectNode.path("data");
        for (int i = 0; i < 2; i++){
            ObjectNode objectNode1 =(ObjectNode) arrayNode.get(i);
            TextNode groupId = (TextNode) objectNode1.get("groupid");
            String  temp = groupId.toString();
            String groupId1 = temp.substring(1,temp.length()-1);
            log.info("groupId:" + groupId.toString());
            if (null != groupId1 ){
                log.info(ChatGroups.deleteChatGroups(groupId1).toString());
            }

            try {
                Thread.sleep(1000);
                log.info("cur:" + i + "/" + arrayNode.size());
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
   }
}
