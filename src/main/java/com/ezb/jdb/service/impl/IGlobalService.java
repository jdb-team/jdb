package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.Topic;

/**
 * 全站搜索
 * author : liufeng
 * create time:2015/11/21 9:42
 */
public interface IGlobalService {

    /**
     * 全站搜索列表
     *
     * @param phone              当前用户手机号
     * @param circlePageResult   圈子
     * @param activityPageResult 活动
     * @param topicPageResult    话题
     * @param friendPageResult   好友
     * @param keyword            查询关键字
     * @return
     */
    String search(
            String phone,
            PageResult<Circle> circlePageResult,
            PageResult<Activity> activityPageResult,
            PageResult<Topic> topicPageResult,
            PageResult<Friend> friendPageResult,
            String keyword
    );
}
