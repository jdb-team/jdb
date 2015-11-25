package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.controller.mobile.MessageController;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 消息发送测试
 * author : liufeng
 * create time:2015/8/18 15:02
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:configs/spring/*.xml"})
public class MessageControllerTest {

    @Resource
    private MessageController messageController;

    @Test
    @Rollback(false)
    public void sendMessage(){
        User sender = new User();
        sender.setUsername("11111122");
        User receiver = new User();
        receiver.setUsername("11111112");
        Message message = new Message();
        message.setContent("消息内容测试");
        message.setSender(sender);
        message.setReceiver(receiver);

        log.info(messageController.sendMessage(message));
    }

    @Test
    public void unReadCount(){
        log.info(messageController.unReadCount("11111112"));
    }

    @Test
    public void unReadMessage(){
        PageResult<Message> pageResult = new PageResult<Message>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(2);
        log.info(messageController.unReadMessage(pageResult, "11111112"));
    }

    @Test
    public void query2UserMessage(){
        PageResult<Message> pageResult = new PageResult<Message>();
        pageResult.setCurPage(1);
        pageResult.setPageSize(10);
        log.info(messageController.query2UserMessage(pageResult, "11111122", "11111112"));
    }

    @Test
    public void delMessage(){
        log.info(messageController.delMessage(3));
    }
}
