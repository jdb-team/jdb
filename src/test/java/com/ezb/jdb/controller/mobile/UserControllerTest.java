package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.User;
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
 * create time: 2015/8/6 10:42.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class UserControllerTest {

    @Resource
    private UserController userController;

    @Test
    @Rollback(value = false)
    public void verifycode(){
        log.info(userController.verifycode("13521849772"));
    }

    @Test
    @Rollback(value = false)
    public void register(){
        User user = new User();
        user.setUsername("111111");
        user.setPassword("111111");
        log.info(userController.verifycode("888"));
    }

    @Test
    @Rollback(value = false)
    public void perfInfo1(){
        Alumnus alumnus = new Alumnus();
        alumnus.setRealName("刘锋");
        alumnus.setEnRealName("liufeng");
        alumnus.setSex(1);
        alumnus.setEmail("3113@qq.com");
        alumnus.setWeixin("weixin2332");
    }

    @Test
    @Rollback(value = false)
    public void perfInfo2(){
        Alumnus alumnus = new Alumnus();
        alumnus.setSchool("清华大学");
        alumnus.setDepartment("教育技术学");
        alumnus.setGrade("二年级");
        alumnus.setCompany("百度");
        alumnus.setTitle("架构师");
        alumnus.setLat(233.0);
        alumnus.setLng(221.0);
    }

    @Test
    @Rollback(value = false)
    public void resetPwd(){
        log.info(userController.resetPwd("11111110", "333333", "464833"));
    }

    @Test
    @Rollback(value = false)
    public void resetPhone(){
        log.info(userController.resetPhone("111111", "888", "408905"));
    }

    @Test
    @Rollback(false)
    public void makeInvitateCode(){
        log.info(userController.invitateCode("11111112"));
    }
}
