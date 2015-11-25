package com.ezb.jdb.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 邮件发送服务测试
 * author : liufeng
 * create time:2015/8/17 13:54
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
public class MailServiceTest {

    @Resource
    private IMailService mailServiceImpl;

    @Test
    public void sendMail(){
        log.info(mailServiceImpl.sendMail("564512326@qq.com","标题1","邮件发送测试"));
    }
}
