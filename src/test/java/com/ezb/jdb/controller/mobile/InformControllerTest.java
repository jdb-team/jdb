package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.model.Inform;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * 举报
 * author : liufeng
 * create time:2015/8/14 10:24
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
@Transactional
public class InformControllerTest {

    @Resource
    private InformController informController;

    @Resource
    private IUserService userServiceImpl;

    @Test
    @Rollback(false)
    public void createInform(){
        log.info(informController.createInform("11111196",null));

        User user = userServiceImpl.queryUserByPhone("11111112");
        Inform inform = new Inform();
        inform.setAssoId(user.getId());
        inform.setAssoName(user.getAlumnus().getRealName());
        inform.setOtherInfo("otherinfo");
        inform.setReason("政治");
        inform.setType("话题");

        log.info(informController.createInform(user.getUsername(),inform));
    }
}
