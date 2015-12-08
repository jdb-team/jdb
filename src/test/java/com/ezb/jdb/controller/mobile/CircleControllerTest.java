package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.CirCmt;
import com.ezb.jdb.model.Circle;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * 圈子
 * author : liufeng
 * create time:2015/8/15 11:34
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class CircleControllerTest {

    @Resource
    private CircleController circleController;

    @Test
    public void queryCircles() {
        PageResult<Circle> pageResult = new PageResult<Circle>();
        pageResult.setPageSize(10);
        pageResult.setCurPage(1);
        log.info(circleController.queryCircles(pageResult, "1111111", null));
        log.info(circleController.queryCircles(pageResult, "1111111", "title12"));
    }

    @Test
    public void queryMyCircles() {
        PageResult<Circle> pageResult = new PageResult<Circle>();
        pageResult.setPageSize(10);
        pageResult.setCurPage(1);
        log.info(circleController.queryMyCircles(pageResult, "1111113", null));
    }

    @Test
    public void queryCircmt() {
        PageResult<CirCmt> pageResult = new PageResult<CirCmt>();
        pageResult.setPageSize(10);
        pageResult.setCurPage(1);
        log.info(circleController.queryCircmt(pageResult, "85"));
    }

    @Test
    @Rollback(false)
    public void createCircmt() {
        Circle circle = new Circle();
        circle.setId(0);

        CirCmt cirCmt = new CirCmt();
        cirCmt.setContent("父评论内容");
        cirCmt.setCircle(circle);
        log.info(circleController.createCircmt("11111112", cirCmt));

        CirCmt cirCmt1 = new CirCmt();
        cirCmt1.setContent("子评论内容");
        cirCmt1.setCircle(circle);
        cirCmt1.setParentCirCmt(cirCmt);
        log.info(circleController.createCircmt("1111116", cirCmt1));
    }

    @Test
    @Rollback(false)
    public void join() {
        log.info(circleController.join("1111116", 12));
        log.info(circleController.join("1111117", 12));
        log.info(circleController.join("1111118", 12));
    }

    @Test
    @Rollback(false)
    public void exit(){
        log.info(circleController.exit("10000000013" , 59));
    }

    @Test
    public void view() {
        log.info(circleController.viewCircle("10000000008", 12));
    }

    @Test
    @Rollback(false)
    public void batchjoin(){
        log.info(circleController.batchJoin("10000000001,10000000002,10000000003,",18));
    }

    @Test
    @Rollback(false)
    public void nickName(){
        log.info(circleController.nickname("10000000002",18,"我的昵称"));
    }

    @Test
    @Rollback(false)
    public void viewnickName(){
        log.info(circleController.viewnickname("10000000002",18));
    }


    @Test
    @Rollback(false)
    public void create() {
        Circle circle = new Circle();
        circle.setTitle("1111");
        circle.setIntroduce("oooo");
        circle.setCreateTime(new Date());
        String phone = "13327689964";
        String uids = "1,1";
        log.info(circleController.create(phone,circle,uids));

    }
}
