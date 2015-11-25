package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.Topic;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * 全局搜索单元测试
 * author : liufeng
 * create time:2015/8/10 16:28
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class SearchControllerTest extends TestCase {

    @Resource
    private SearchController searchController;

    @Test
    public void query() {
        PageResult<Circle> circlePageResult = new PageResult<Circle>();
        PageResult<Activity> activityPageResult = new PageResult<Activity>();
        PageResult<Topic> topicPageResult = new PageResult<Topic>();
        PageResult<Friend> friendPageResult = new PageResult<Friend>();

        log.info(searchController.search("1111111", circlePageResult, activityPageResult,topicPageResult,friendPageResult,"1"));
    }
}
