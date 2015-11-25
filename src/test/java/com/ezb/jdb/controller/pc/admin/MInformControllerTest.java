package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Inform;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * author : liufeng
 * create time:2015/8/22 13:22
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
public class MInformControllerTest {

    @Resource
    private MInformController mInformController;

    @Test
    public void query() {
        PageResult<Inform> pageResult = new PageResult<Inform>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(mInformController.query(pageResult, "","","", "", "", "", "", "", ""));
        log.info(mInformController.query(pageResult, "","","11", "", "", "", "", "", ""));
        log.info(mInformController.query(pageResult, "","","", "realname2", "", "", "", "", ""));
        log.info(mInformController.query(pageResult, "","","", "", "2015-08-22", "", "", "", ""));
        log.info(mInformController.query(pageResult, "","","", "", "", "2015-08-23", "", "", ""));
        log.info(mInformController.query(pageResult, "","","", "", "", "", "reason2", "", ""));
        log.info(mInformController.query(pageResult, "","","", "", "", "", "", "", "0"));
    }

    @Test
    public void del() {
        log.info(mInformController.del("50,51,53"));
    }

    @Test
    public void handle() {
    }

}
