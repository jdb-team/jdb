package com.ezb.jdb.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * 查询操作返回数据
 * author : liufeng
 * create time:2015/8/12 15:05
 */
public class ResponseData {

    /**
     * 获取返回值
     *
     * @param code
     * @param errorInfo
     * @param object
     * @return
     */
    public static String getResData(String code, String errorInfo, Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("error", errorInfo);
        jsonObject.put("data", object);
        return JSONObject.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 查询成功获取返回值
     *
     * @param data
     * @return
     */
    public static String getResData(Object data) {
        return getResData("0", "", data);
    }
}
