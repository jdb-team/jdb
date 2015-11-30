package com.ezb.jdb.view;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.User;

import java.util.List;

/**
 * 圈子视图包装
 * author : liufeng
 * create time:2015/9/11 11:20
 */
public class CircleView {

    public static String convert2Json(List<Circle> list, User user) {
        JSONArray jsonArray = new JSONArray();
        for (Circle circle : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("circle", circle);
            if (null == circle.getMembers() || null != circle.getMembers() && !circle.getMembers().contains(user)) {
                jsonObject.put("join", "0");
            } else {
                jsonObject.put("join", "1");
            }
            jsonArray.add(jsonObject);
        }
        return ResponseData.getResData(jsonArray);
    }
}
