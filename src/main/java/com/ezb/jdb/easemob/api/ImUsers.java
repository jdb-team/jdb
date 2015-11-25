package com.ezb.jdb.easemob.api;

import java.util.ArrayList;
import java.util.List;

import com.ezb.jdb.easemob.comm.Constants;
import com.ezb.jdb.easemob.comm.HTTPMethod;
import com.ezb.jdb.easemob.comm.Roles;
import com.ezb.jdb.easemob.utils.JerseyUtils;
import com.ezb.jdb.easemob.vo.ClientSecretCredential;
import com.ezb.jdb.easemob.vo.Credential;
import com.ezb.jdb.easemob.vo.EndPoints;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 用户体系集成 Jersey2.9实现
 * 
 * Doc URL: https://docs.easemob.com/doku.php?id=start:100serverintegration:20users
 * 
 * @author Lynch 2014-09-09
 * 
 */
public class ImUsers {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImUsers.class);
	private static final String APPKEY = Constants.APPKEY;
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    /**
	 * 注册IM用户[单个]
	 * 
	 * 给指定AppKey创建一个新的用户
	 * 
	 * @param dataNode
	 * @return
	 */
	public static ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		objectNode.removeAll();

		// check properties that must be provided
		if (null != dataNode && !dataNode.has("username")) {
			LOGGER.error("Property that named username must be provided .");

			objectNode.put("message",
					"Property that named username must be provided .");

			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
			LOGGER.error("Property that named password must be provided .");

			objectNode.put("message",
					"Property that named password must be provided .");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name",
					APPKEY.split("#")[0]).resolveTemplate("app_name",
					APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 注册IM用户[批量]
	 * 
	 * 给指定AppKey创建一批用户
	 * 
	 * @param dataArrayNode
	 * @return
	 */
	public static ObjectNode createNewIMUserBatch(ArrayNode dataArrayNode) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check properties that must be provided
		if (dataArrayNode.isArray()) {
			for (JsonNode jsonNode : dataArrayNode) {
				if (null != jsonNode && !jsonNode.has("username")) {
					LOGGER.error("Property that named username must be provided .");

					objectNode.put("message", "Property that named username must be provided .");

					return objectNode;
				}
				if (null != jsonNode && !jsonNode.has("password")) {
					LOGGER.error("Property that named password must be provided .");

					objectNode.put("message", "Property that named password must be provided .");

					return objectNode;
				}
			}
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name",
					APPKEY.split("#")[0]).resolveTemplate("app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataArrayNode, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 注册IM用户[批量生成用户然后注册]
	 * 
	 * 给指定AppKey创建一批用户
	 * 
	 * @param usernamePrefix
	 *            生成用户名的前缀
	 * @param perNumber
	 *            批量注册时一次注册的数量
	 * @param totalNumber
	 *            生成用户注册的用户总数
	 * @return
	 */
	public static ObjectNode createNewIMUserBatchGen(String usernamePrefix,
			Long perNumber, Long totalNumber) {
		ObjectNode objectNode = factory.objectNode();

		if (totalNumber == 0 || perNumber == 0) {
			return objectNode;
		}

		System.out.println("你即将注册" + totalNumber + "个用户，如果大于" + perNumber
				+ "了,会分批注册,每次注册" + perNumber + "个");

		ArrayNode genericArrayNode = ImUsers.genericArrayNode(
				usernamePrefix, totalNumber);
		if (totalNumber <= perNumber) {
			objectNode = ImUsers.createNewIMUserBatch(genericArrayNode);
		} else {

			for (int i = 0; i < genericArrayNode.size(); i++) {
				ArrayNode tmpArrayNode = factory.arrayNode();
				tmpArrayNode.add(genericArrayNode.get(i));
				// 300 records on one migration
				if ((i + 1) % perNumber == 0) {
					objectNode = ImUsers
							.createNewIMUserBatch(genericArrayNode);
					tmpArrayNode.removeAll();
					continue;
				}

				// the rest records that less than the times of 300
				if (i > (genericArrayNode.size() / perNumber * perNumber - 1)) {
					objectNode = ImUsers
							.createNewIMUserBatch(genericArrayNode);
					tmpArrayNode.removeAll();
				}
			}
		}

		return objectNode;
	}

	/**
	 * 获取IM用户
	 * 
	 * @param userName
	 *            用户主键：username或者uuid
	 * @return
	 */
	public static ObjectNode getIMUsersByUserName(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("The primaryKey that will be useed to query must be provided .");

			objectNode
					.put("message",
							"The primaryKey that will be useed to query must be provided .");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(userName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 删除IM用户[单个]
	 * 
	 * 删除指定AppKey下IM单个用户
	 *
	 * 
	 * @param userName
	 * @return
	 */
	public static ObjectNode deleteIMUserByUserName(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(userName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 删除IM用户[批量]
	 * 
	 * 批量指定AppKey下删除IM用户
	 * 
	 * @param limit
	 * @param queryStr
	 * @return
	 */
	public static ObjectNode deleteIMUserByUsernameBatch(Long limit,
			String queryStr) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.queryParam("ql", queryStr)
					.queryParam("limit", String.valueOf(limit));

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential,
					HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 重置IM用户密码 提供管理员token
	 * 
	 * @param userName
	 * @param dataObjectNode
	 * @return
	 */
	public static ObjectNode modifyIMUserPasswordWithAdminToken(
			String userName, ObjectNode dataObjectNode) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Property that named userName must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Property that named userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
			LOGGER.error("Property that named newpassword must be provided .");

			objectNode.put("message",
					"Property that named newpassword must be provided .");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.path(userName).path("password");

			objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode,
					credential, HTTPMethod.METHOD_PUT, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 添加好友[单个]
	 * 
	 * @param ownerUserName
	 * @param friendUserName
	 * 
	 * @return
	 */
	public static ObjectNode addFriendSingle(String ownerUserName,
			String friendUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserName)) {
			LOGGER.error("The userName of friend must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"The userName of friend must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.resolveTemplate("ownerUserName", ownerUserName)
					.resolveTemplate("friendUserName",
							friendUserName);

			ObjectNode body = factory.objectNode();
			objectNode = JerseyUtils.sendRequest(webTarget, body, credential,
					HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 解除好友关系
	 * 
	 * @param ownerUserName
	 * @param friendUserName
	 * 
	 * @return
	 */
	public static ObjectNode deleteFriendSingle(String ownerUserName,
			String friendUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message",
							"Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserName)) {
			LOGGER.error("The userName of friend must be provided，the value is username or uuid of imuser.");

			objectNode
					.put("message",
							"The userName of friend must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.resolveTemplate("ownerUserName", ownerUserName)
					.resolveTemplate("friendUserName",
							friendUserName);

			ObjectNode body = factory.objectNode();
			objectNode = JerseyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 查看好友
	 * 
	 * @param ownerUserName
	 * 
	 * @return
	 */
	public static ObjectNode getFriends(String ownerUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message",
							"Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1])
					.resolveTemplate("ownerUserName", ownerUserName)
					.resolveTemplate("friendUserName", "");

			ObjectNode body = factory.objectNode();
			objectNode = JerseyUtils.sendRequest(webTarget, body, credential, HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * IM用户登录
	 * 
	 * @param ownerUserName
	 * @param password
     *
	 * @return
	 */
	public static ObjectNode imUserLogin(String ownerUserName, String password) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}
		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message",
							"Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}
		if (StringUtils.isEmpty(password)) {
			LOGGER.error("Your password must be provided，the value is username or uuid of imuser.");

			objectNode.put("message",
							"Your password must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			ObjectNode dataNode = factory.objectNode();
			dataNode.put("grant_type", "password");
			dataNode.put("username", ownerUserName);
			dataNode.put("password", password);

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("Content-Type", "application/json"));

			objectNode = JerseyUtils.sendRequest(EndPoints.TOKEN_APP_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]),
					dataNode, null, HTTPMethod.METHOD_POST, headers);

		} catch (Exception e) {
			throw new RuntimeException(	"Some errors ocuured while fetching a token by usename and passowrd .");
		}

		return objectNode;
	}

	/**
	 * 指定前缀和数量生成用户基本数据
	 * 
	 * @param usernamePrefix
	 * @param number
	 * @return
	 */
	private static ArrayNode genericArrayNode(String usernamePrefix, Long number) {
		ArrayNode arrayNode = factory.arrayNode();
		for (int i = 0; i < number; i++) {
			ObjectNode userNode = factory.objectNode();
			userNode.put("username", usernamePrefix + "_" + i);
			userNode.put("password", Constants.DEFAULT_PASSWORD);

			arrayNode.add(userNode);
		}

		return arrayNode;
	}

}
