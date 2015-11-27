package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.Topic;
import com.ezb.jdb.service.IAtvCmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 全站搜索
 * author : liufeng
 * create time:2015/11/21 9:38
 */
@Controller
@Slf4j
public class SearchController {

    @Resource
    private IAtvCmtService.IGlobalService globalServiceImpl;

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
    @RequestMapping(value = "mobile/global/search")
    public
    @ResponseBody
    String search(
            String phone,
            PageResult<Circle> circlePageResult,
            PageResult<Activity> activityPageResult,
            PageResult<Topic> topicPageResult,
            PageResult<Friend> friendPageResult,
            String keyword) {

        return globalServiceImpl.search(
                phone,
                circlePageResult,
                activityPageResult,
                topicPageResult,
                friendPageResult,
                keyword
        );
    }
}
