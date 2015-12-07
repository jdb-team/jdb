package com.ezb.jdb.model;

import com.ezb.jdb.common.PageResult;
import lombok.Data;

/**
 * author : liufeng
 * create time:2015/12/7 14:15
 */
@Data
public class SearchPageResult {
    private PageResult<Circle> circlePageResult;    //圈子
    private PageResult<Activity> activityPageResult;//活动
    private PageResult<Topic> topicPageResult;      //话题
    private PageResult<Friend> friendPageResult;    //好友
}
