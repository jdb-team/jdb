package com.ezb.jdb.view;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.User;
import com.ezb.jdb.tool.JdbGisUtil;

import java.util.List;

/**
 * 好友视图包装
 * author : liufeng
 * create time:2015/8/12 11:19
 */
public class FriendView {

    public static String convert2Json(User user, List<Friend> list) {

        JSONArray jsonArray = new JSONArray();
        for (Friend friend : list) {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("id", friend.getId());
            jsonObject.put("user_id", friend.getUser().getId());
            jsonObject.put("phone", friend.getUser().getUsername());
            jsonObject.put("friend_id", friend.getFriend().getId());
            jsonObject.put("headpic", friend.getFriend().getAlumnus().getHeadPicPath());
            jsonObject.put("name", friend.getFriend().getAlumnus().getRealName());
            jsonObject.put("enrealname", friend.getFriend().getAlumnus().getEnRealName());
            jsonObject.put("sex", friend.getFriend().getAlumnus().getSex());
            jsonObject.put("school", friend.getFriend().getAlumnus().getSchool());
            jsonObject.put("department", friend.getFriend().getAlumnus().getDepartment());
            jsonObject.put("grade", friend.getFriend().getAlumnus().getGrade());
            jsonObject.put("company", friend.getFriend().getAlumnus().getCompany());
            jsonObject.put("title", friend.getFriend().getAlumnus().getTitle());

            jsonObject.put(
                    "distance",
                    String.format(
                            "%.1f",
                            JdbGisUtil.getDistance(
                                    user.getAlumnus().getLat(),
                                    user.getAlumnus().getLng(),
                                    friend.getFriend().getAlumnus().getLat(),
                                    friend.getFriend().getAlumnus().getLng())
                    )
            );

            jsonArray.add(jsonObject);
        }
        return ResponseData.getResData(jsonArray);
    }

    public static String convertUList2Json(User curUser, List<User> list) {

        JSONArray jsonArray = getUserJsonArray(curUser, list);
        return ResponseData.getResData(jsonArray);
    }

    /**
     * 获取用户的jsonArray
     * @param curUser
     * @param list
     * @return
     */
    public static JSONArray getUserJsonArray(User curUser, List<User> list) {
        JSONArray jsonArray = new JSONArray();
        for (User user : list) {

            JSONObject jsonObject = new JSONObject();

            jsonObject.put("user_id", user.getId());
            jsonObject.put("phone", user.getUsername());
            jsonObject.put("headpic", user.getAlumnus().getHeadPicPath());
            jsonObject.put("name", user.getAlumnus().getRealName());
            jsonObject.put("enrealname", user.getAlumnus().getEnRealName());
            jsonObject.put("sex", user.getAlumnus().getSex());
            jsonObject.put("school", user.getAlumnus().getSchool());
            jsonObject.put("department", user.getAlumnus().getDepartment());
            jsonObject.put("grade", user.getAlumnus().getGrade());
            jsonObject.put("company", user.getAlumnus().getCompany());
            jsonObject.put("title", user.getAlumnus().getTitle());

            jsonObject.put(
                    "distance",
                    String.format(
                            "%.1f",
                            JdbGisUtil.getDistance(
                                    curUser.getAlumnus().getLat(),
                                    curUser.getAlumnus().getLng(),
                                    user.getAlumnus().getLat(),
                                    user.getAlumnus().getLng())
                    )
            );
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
