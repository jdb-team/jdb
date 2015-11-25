package com.ezb.jdb.easemob.comm;

public interface Constants {

	/**
	 * API_HTTP_SCHEMA
	 */
	String API_HTTP_SCHEMA = "https";

	/**
	 * API_SERVER_HOST
	 */
	String API_SERVER_HOST = PropertiesUtils.getProperties().getProperty("API_SERVER_HOST");

	/**
	 * APPKEY
	 */
	String APPKEY = PropertiesUtils.getProperties().getProperty("APPKEY");

	/**
	 * APP_CLIENT_ID
	 */
	String APP_CLIENT_ID = PropertiesUtils.getProperties().getProperty("APP_CLIENT_ID");

	/**
	 * APP_CLIENT_SECRET
	 */
	String APP_CLIENT_SECRET = PropertiesUtils.getProperties().getProperty("APP_CLIENT_SECRET");

	/**
	 * 是否初始化数据
	 */
	Boolean INIT_DATA = Boolean.parseBoolean(PropertiesUtils.getProperties().getProperty("APP_INIT_DATA"));

	/**
	 * DEFAULT_PASSWORD
	 */
	String DEFAULT_PASSWORD = "123456";

}
