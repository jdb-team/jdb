package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.FriendApply;
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
 * create time:2015/8/13 15:18
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class FriendControllerTest {

    @Resource
    private FriendController friendController;

    @Test
    @Rollback(false)
    public void queryFriend(){
        PageResult<User> pageResult = new PageResult<User>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryFriend(pageResult, "13327689964", null));
    }

    @Test
    @Rollback(false)
    public void confireFriend(){
        log.info(friendController.confireFriend(17));
    }

    @Test
    @Rollback(false)
    public void confirFriend2Phone(){
        log.info(friendController.confireFriend2Phone("10000000001", "10000000002",1));
    }

    @Test
    @Rollback(false)
    public void queryUnFriend(){

        PageResult<User> pageResult = new PageResult<User>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryUnFriend(pageResult, "10000000006", null, null));


        Alumnus alumnus = new Alumnus();
        alumnus.setRealName("realname1");
        alumnus.setSex(1);
        alumnus.setSchool("school1");
        alumnus.setDepartment("department1");
        alumnus.setGrade("grade1");
        log.info(friendController.queryUnFriend(pageResult, "10000000006", alumnus, null));

        log.info(friendController.queryUnFriend(pageResult, "10000000006", null, JdbConstants.ORDERBY_USERNAME));
        log.info(friendController.queryUnFriend(pageResult, "10000000006", null, JdbConstants.ORDERBY_LOCATION));
    }

    @Test
    @Rollback(false)
    public void queryAllFriend(){

        PageResult<User> pageResult = new PageResult<User>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryUnFriend(pageResult, "10000000006", null, null));


        Alumnus alumnus = new Alumnus();
        alumnus.setRealName("realname1");
        alumnus.setSex(1);
        alumnus.setSchool("school1");
        alumnus.setDepartment("department1");
        alumnus.setGrade("grade1");
        log.info(friendController.queryAllFriend(pageResult, "10000000006", alumnus, null));

        log.info(friendController.queryAllFriend(pageResult, "10000000006", null, JdbConstants.ORDERBY_USERNAME));
        log.info(friendController.queryAllFriend(pageResult, "10000000006", null, JdbConstants.ORDERBY_LOCATION));
    }

    @Test
    public void queryNearUsers(){
        PageResult<User> pageResult = new PageResult<User>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryNearUsers(pageResult, "10000000001"));
    }

    @Test
    @Rollback(false)
    public void addFriendApply(){
        String phone1 = "10000000001";
        String phone2 = "10000000002";
        String message = "HelloWorld!";
        log.info(friendController.addFriendApply(phone1 , phone2 , message));

        String phone3 = "10000000010";
        String phone4 = "10000000011";
        String message1 = "HelloWorld!";
        log.info(friendController.addFriendApply(phone3 , phone4 , message1));
    }

    @Test
    public void queryFriendApply(){
        PageResult<FriendApply> pageResult = new PageResult<FriendApply>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(friendController.queryFriendApply(pageResult,"10000000002"));
    }


    @Test
    @Rollback(false)
    public void confireFriend2Phone(){
        String phone1 = "10000000001";
        String phone2 = "10000000002";
        log.info(friendController.confireFriend2Phone(phone1 , phone2,1));

        String phone3 = "13327689964";
        String phone4 = "10000000010";
        log.info(friendController.confireFriend2Phone(phone3 , phone4,1));
    }

    @Test
    @Rollback(false)
    public void release() {
        String phone3 = "10000000010";
        String phone4 = "10000000011";
        log.info(friendController.release(phone3, phone4));
    }
}
