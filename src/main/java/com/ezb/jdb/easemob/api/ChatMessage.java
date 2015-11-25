package com.ezb.jdb.easemob.api;

import com.ezb.jdb.easemob.comm.Constants;
import com.ezb.jdb.easemob.comm.HTTPMethod;
import com.ezb.jdb.easemob.comm.Roles;
import com.ezb.jdb.easemob.utils.JerseyUtils;
import com.ezb.jdb.easemob.vo.ClientSecretCredential;
import com.ezb.jdb.easemob.vo.Credential;
import com.ezb.jdb.easemob.vo.EndPoints;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST API Demo : 聊天记录 Jersey2.9实现
 * 
 * Doc URL: https://docs.easemob.com/doku.php?id=start:100serverintegration:30chatlog
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class ChatMessage {

	private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessage.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	private static final String APPKEY = Constants.APPKEY;

    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    /**
	 * 获取聊天消息
	 * 
	 * @param queryStrNode
	 *
	 */
	public static ObjectNode getChatMessages(ObjectNode queryStrNode) {
		ObjectNode objectNode = factory.objectNode();
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		try {
			JerseyWebTarget webTarget = EndPoints.CHATMESSAGES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);
			if (null != queryStrNode && null != queryStrNode.get("ql") && !StringUtils.isEmpty(queryStrNode.get("ql").asText())) {
				webTarget = webTarget.queryParam("ql", queryStrNode.get("ql").asText());
			}
			if (null != queryStrNode && null != queryStrNode.get("limit") &&!StringUtils.isEmpty(queryStrNode.get("limit").asText())) {
				webTarget = webTarget.queryParam("limit", queryStrNode.get("limit").asText());
			}
			if (null != queryStrNode && null != queryStrNode.get("cursor") &&!StringUtils.isEmpty(queryStrNode.get("cursor").asText())) {
				webTarget = webTarget.queryParam("cursor", queryStrNode.get("cursor").asText());
			}
			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectNode;
	}

}
