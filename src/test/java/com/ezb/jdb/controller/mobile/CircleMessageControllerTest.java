package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.CircleMessage;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/28.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
public class CircleMessageControllerTest {

    @Resource
    private  CircleMessageController circleMessageController;

    @Test
    public  void  sendCircleMessageTest(){
        User sender = new User();
        sender.setUsername("10000000019");
        Circle receiver = new Circle();
        receiver.setId(53);
        CircleMessage message = new CircleMessage();
        message.setContent("消息内容测试228888822");
        message.setSender(sender);
        message.setCircle(receiver);

        circleMessageController.sendCircleMessage(message);
    }


    @Test
    public  void  setupZeroTest(){
        User sender = new User();
        sender.setId(20);
        Circle receiver = new Circle();
        receiver.setId(53);
        CircleMessage message = new CircleMessage();
        message.setContent("消息内容测试2222");
        message.setSender(sender);
        message.setCircle(receiver);

        circleMessageController.setupZero(message);
        System.out.println(circleMessageController.setupZero(message));
    }

}
