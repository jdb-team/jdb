package com.ezb.jdb.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 操作响应状态
 * author : liufeng
 * create time:2015/8/12 15:05
 */
public class ResponseState {

    public static String FIAL = getResState("-1", "操作失败");
    public static String SUCCESS = getResState("0", "操作成功");
    public static String INVALID_PHONE = getResState("100", "手机号码不存在");
    public static String PHONE_EMPTY = getResState("1100", "手机号码为空");
    public static String PASS_EMPTY = getResState("1101", "密码为空");
    public static String LOGIN_PASS_ERR = ResponseState.getResState("101", "密码错误!");
    public static String LIKE_OPT_ERR = ResponseState.getResState("102", "点赞操作失败!");
    public static String REGED_PHONE = ResponseState.getResState("103", "手机号已注册!");
    public static String FAIL = ResponseState.getResState("104", "操作失败!");
    public static String PAS_RESET_ERR = ResponseState.getResState("105", "密码重置失败!");
    public static String LOCATION_FMT_ERR = ResponseState.getResState("106", "经纬度格式不正确,正确格式为xxx.xx,xxx.xx!");
    public static String EMPTY_STR = ResponseState.getResState("107", "字符串为空");
    public static String PIC_ERR = "pic_err";
    public static String PIC_SAVE_ERR_JSON = ResponseState.getResState("108", "图片保存失败");
    public static String INVALID_ID = ResponseState.getResState("109", "ID不存在");
    public static String CONFIRE_FRIEND_ERR = ResponseState.getResState("110", "好友关系确认失败");
    public static String FRIEND_ADDED = ResponseState.getResState("111","phone1,phone2已添加好友");
    public static String FRIEND_APPLY_FRIST = ResponseState.getResState("112","phone1,phone2还未申请为好友");
    public static String INFORM_NULL = ResponseState.getResState("113","举报信息填写不完整");
    public static String INVITATE_ERR =  ResponseState.getResState("114","邀请码错误");
    public static String VERIFYCODE_ERR =  ResponseState.getResState("115","验证码错误");
    public static String MAIL_ERR = ResponseState.getResState("116","邮件发送失败");
    public static String SMS_CONN_ERR = ResponseState.getResState("117","SMS 连接失败");
    public static String SMS_IO_ERR = ResponseState.getResState("118","SMS IO异常");
    public static String USERNAME_EXISIT = ResponseState.getResState("120", "用户名已存在!");
    public static String TYPE_ERR = ResponseState.getResState("121", "类型错误!");
    public static String SESSION_ERR = ResponseState.getResState("122", "用户未登录或session失效!");
    public static String USER_DISABLE = ResponseState.getResState("124", "用户已被停用");
    public static String LIKE_OPT_REPEAT = ResponseState.getResState("125", "重复点赞!");
    public static String INVITATE_OPT_REPEAT = ResponseState.getResState("126", "请一分钟后再生成新的邀请码!");
    public static String[] UNEXIST_REFID = new String[]{"127","{0}号焦点图绑定的id不存在,请重新填写!"};
    public static String TOPICTYPE_MISS = ResponseState.getResState("128", "请传入话题分类id!");
    public static String SHARE_OPT_REPEAT = ResponseState.getResState("125", "重复分享!");
    public static String PARAMS_LACK = ResponseState.getResState("129", "参数不完整!");
    public static String AREADY_JOIN_CIRCLE = ResponseState.getResState("130", "用户已加入圈子!");
    public static String AUTH_CIRCLE = ResponseState.getResState("131", "你没有权限解散此圈子!");


    /**
     * 获取返回状态
     *
     * @param code
     * @param errorInfo
     * @return
     */
    public static String getResState(String code, String errorInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("error", errorInfo);
        return JSONObject.toJSONString(jsonObject);
    }
}
