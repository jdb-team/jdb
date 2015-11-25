package com.ezb.jdb.dao.datainit;

import com.ezb.jdb.dao.init.OrderInit;
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
 * create time:2015/11/25 9:56
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class OrderInitTest {

    @Resource
    private OrderInit orderInit;

    @Test
    @Rollback(false)
    public void init(){
        orderInit.orderInit();
    }
}
