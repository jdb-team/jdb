package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Topic;
import com.ezb.jdb.model.TopicCmt;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * author : liufeng
 * create time: 2015/8/7 11:30.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/spring-*.xml"})
@Transactional
public class TopicContollerTest extends TestCase {

    @Resource
    private TopicController topicController;

    @Test
    public void queryTopic() {
        PageResult<Topic> pageResult = new PageResult<Topic>();
        pageResult.setPageSize(10);
        pageResult.setCurPage(1);
        log.info(topicController.query(pageResult, "13327689964", null, null, "2", false, false));
    }

    @Test
    public void viewTopic() {
        log.info(topicController.view("10000000001", 1000));
    }

    @Test
    @Rollback(false)
    public void createTopicCmt() {
        Topic Topic = new Topic();
        Topic.setId(0);

        TopicCmt TopicCmt = new TopicCmt();
        TopicCmt.setContent("评论2");
        TopicCmt.setTopic(Topic);

        log.info(topicController.createTopicCmt("1111113", TopicCmt));

        TopicCmt TopicCmt1 = new TopicCmt();
        TopicCmt1.setContent("评论2的子评论");
        TopicCmt1.setTopic(Topic);
        TopicCmt1.setParentTopicCmt(TopicCmt);

        log.info(topicController.createTopicCmt("1111116", TopicCmt1));
    }

    @Test
    @Rollback(false)
    public void likeTopicCmt() {
        log.info(topicController.likeTopicCmt("10000000001", 1));
    }

    @Test
    @Rollback(false)
    public void shield(){
        log.info(topicController.shield("10000000001","10000000069"));
    }
}
