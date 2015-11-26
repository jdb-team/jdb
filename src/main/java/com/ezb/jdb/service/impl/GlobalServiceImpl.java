package com.ezb.jdb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.dao.ActivityDao;
import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.FriendDao;
import com.ezb.jdb.dao.TopicDao;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.Topic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * app全局服务类
 * author : liufeng
 * create time:2015/11/21 9:44
 */
@Service
public class GlobalServiceImpl implements IGlobalService {

    @Resource
    private CircleDao circleDao;

    @Resource
    private ActivityDao activityDao;

    @Resource
    private TopicDao topicDao;

    @Resource
    private FriendDao friendDao;


    public String search(
            String phone,
            PageResult<Circle> circlePageResult,
            PageResult<Activity> activityPageResult,
            PageResult<Topic> topicPageResult,
            PageResult<Friend> friendPageResult, String keyword) {

        circlePageResult = circleDao.queryCircles(circlePageResult, keyword);
        activityPageResult = activityDao.queryActivity(activityPageResult, keyword,"");
        topicPageResult = topicDao.queryTopic(topicPageResult, keyword);
        friendPageResult = friendDao.queryFriend(friendPageResult, phone, keyword, 1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("circlePageResult", circlePageResult);
        jsonObject.put("activityPageResult", activityPageResult);
        jsonObject.put("topicPageResult", topicPageResult);
        jsonObject.put("friendPageResult",friendPageResult);

        return ResponseData.getResData(jsonObject);
    }
}
