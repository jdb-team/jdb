package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.AtvCmt;
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
 * 活动单元测试
 * author : liufeng
 * create time:2015/8/10 16:28
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class ActivityControllerTest extends TestCase {

    @Resource
    private ActivityController activityController;

    @Test
    public void queryActivity() {
        PageResult<Activity> pageResult = new PageResult<Activity>();
        pageResult.setPageSize(10);
        pageResult.setCurPage(1);
        log.info(activityController.queryActivity(pageResult, "1111111", null));
        log.info(activityController.queryActivity(pageResult, "1111111", "满员测试"));
    }

    @Test
    public void viewActivity() {
        log.info(activityController.viewActivity("1111111",108 ));
    }

    @Test
    @Rollback(false)
    public void createAtvCmt() {
        Activity activity = new Activity();
        activity.setId(30);

        AtvCmt atvCmt = new AtvCmt();
        atvCmt.setContent("评论2");
        atvCmt.setActivity(activity);

        log.info(activityController.createAtvCmt("1111111", atvCmt));

        AtvCmt atvCmt1 = new AtvCmt();
        atvCmt1.setContent("评论2的子评论");
        atvCmt1.setActivity(activity);
        atvCmt1.setParentAtvCmt(atvCmt);

        log.info(activityController.createAtvCmt("11111114", atvCmt1));
    }

    @Test
    @Rollback(false)
    public void likeAtvCmt() {
        log.info(activityController.likeAtvCmt("11111114", 26));
    }

    @Test
    @Rollback(false)
    public void signup() {
        log.info(activityController.signup("1111111", 109));
    }

    @Test
    public void queryMyActivity() {
        PageResult<Activity> pageResult = new PageResult<Activity>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);

        log.info(activityController.queryMyActivity(pageResult, "1111111", ""));
        log.info(activityController.queryMyActivity(pageResult, "11111150", "title53"));
    }

    @Test
    public void queryMyJoinAcitivy() {
        PageResult<Activity> pageResult = new PageResult<Activity>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);

        log.info(activityController.queryMyJoinActivity(pageResult, "11111117", ""));
        log.info(activityController.queryMyJoinActivity(pageResult, "11111117", "99"));
    }

    @Test
    public void queryJoinUsers() {
        log.info(activityController.queryJoinUsers(30));
    }

    @Test
    public void sendJoinUsers2Email() {
        log.info(activityController.sendJoinUsers2Email(30));
    }
}
