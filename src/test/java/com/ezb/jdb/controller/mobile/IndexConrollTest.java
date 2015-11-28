package com.ezb.jdb.controller.mobile;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * 首页
 * author : liufeng
 * create time:2015/8/20 11:08
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class IndexConrollTest {

    @Resource
    private IndexController indexController;

    @Test
    public void indexData(){
        log.info(indexController.indexData("1111111",6,4,5));
    }

}
