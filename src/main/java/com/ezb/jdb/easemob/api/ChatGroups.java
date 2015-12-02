package com.ezb.jdb.easemob.api;

import com.ezb.jdb.easemob.comm.Constants;
import com.ezb.jdb.easemob.comm.HTTPMethod;
import com.ezb.jdb.easemob.comm.Roles;
import com.ezb.jdb.easemob.utils.JerseyUtils;
import com.ezb.jdb.easemob.vo.ClientSecretCredential;
import com.ezb.jdb.easemob.vo.Credential;
import com.ezb.jdb.easemob.vo.EndPoints;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.client.SystemDefaultCredentialsProvider;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 群组管理
 * <p/>
 * Doc URL: https://docs.easemob.com/doku.php?id=start:100serverintegration:60groupmgmt
 * <p/>
 * author : liufeng
 * create time:2015/9/5 10:16
 */
public class ChatGroups {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatGroups.class);
    private static final JsonNodeFactory factory = new JsonNodeFactory(false);
    private static final String APPKEY = Constants.APPKEY;


    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    /**
     * 获取APP中所有的群组ID
     *
     * @return
     */
    public static ObjectNode getAllChatgroupids() {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {
            JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]);
            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 获取一个或者多个群组的详情
     *
     * @return
     */
    public static ObjectNode getGroupDetailsByChatgroupid(String[] chatgroupIDs) {
        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupIDs.toString());
            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 创建群组
     */
    public static ObjectNode creatChatGroups(ObjectNode dataObjectNode) {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }

        // check properties that must be provided
        if (!dataObjectNode.has("groupname")) {
            LOGGER.error("Property that named groupname must be provided .");

            objectNode.put("message", "Property that named groupname must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("desc")) {
            LOGGER.error("Property that named desc must be provided .");

            objectNode.put("message", "Property that named desc must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("public")) {
            LOGGER.error("Property that named public must be provided .");

            objectNode.put("message", "Property that named public must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("approval")) {
            LOGGER.error("Property that named approval must be provided .");

            objectNode.put("message", "Property that named approval must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("owner")) {
            LOGGER.error("Property that named owner must be provided .");

            objectNode.put("message", "Property that named owner must be provided .");

            return objectNode;
        }
        if (!dataObjectNode.has("members") || !dataObjectNode.path("members").isArray()) {
            LOGGER.error("Property that named members must be provided .");

            objectNode.put("message", "Property that named members must be provided .");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]);

            objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_POST, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 删除群组
     */
    public static ObjectNode deleteChatGroups(String chatgroupid) {
        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 获取群组中的所有成员
     */
    public static ObjectNode getAllMemberssByGroupId(String chatgroupid) {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid).path("users");

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 在群组中添加一个人
     */
    public static ObjectNode addUserToGroup(String chatgroupid, String userName) {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {

            JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid).path("users")
                    .path(userName);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 在群组中减少一个人
     */
    public static ObjectNode deleteUserFromGroup(String chatgroupid, String userName) {
        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }

        try {
            JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
                    .resolveTemplate("app_name", APPKEY.split("#")[1]).path(chatgroupid).path("users")
                    .path(userName);

            objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 获取一个用户参与的所有群组
     *
     * @param username
     * @return
     */
    private static ObjectNode getJoinedChatgroupsForIMUser(String username) {
        ObjectNode objectNode = factory.objectNode();
        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }
        if (StringUtils.isBlank(username.trim())) {
            LOGGER.error("Property that named username must be provided .");
            objectNode.put("message", "Property that named username must be provided .");
            return objectNode;
        }

        try {
            objectNode = JerseyUtils.sendRequest(EndPoints.USERS_TARGET.path(username).path("joined_chatgroups"), null,
                    credential, HTTPMethod.METHOD_GET, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 群组批量添加成员
     *
     * @param toAddBacthChatgroupid
     * @param usernames
     * @return
     */
    private static ObjectNode addUsersToGroupBatch(String toAddBacthChatgroupid, ObjectNode usernames) {
        ObjectNode objectNode = factory.objectNode();
        // check appKey format
        if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + APPKEY);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }
        if (StringUtils.isBlank(toAddBacthChatgroupid.trim())) {
            LOGGER.error("Property that named toAddBacthChatgroupid must be provided .");
            objectNode.put("message", "Property that named toAddBacthChatgroupid must be provided .");
            return objectNode;
        }
        // check properties that must be provided
        if (null != usernames && !usernames.has("usernames")) {
            LOGGER.error("Property that named usernames must be provided .");
            objectNode.put("message", "Property that named usernames must be provided .");
            return objectNode;
        }

        try {
            objectNode = JerseyUtils.sendRequest(EndPoints.CHATGROUPS_TARGET.path(toAddBacthChatgroupid).path("users"), usernames,
                    credential, HTTPMethod.METHOD_POST, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    public static  void  main(String[] args){
        System.out.println(System.getProperty("java.class.path"));
//        System.out.println(deleteChatGroups("133235631391244844"));

    }

}
