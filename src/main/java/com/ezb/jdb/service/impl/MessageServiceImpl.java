package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.MessageDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.IMessageService;
import com.ezb.jdb.service.IMsgNotifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * author : liufeng
 * create time:2015/8/18 10:45
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Resource
    private MessageDao messageDao;

    @Resource
    private UserDao userDao;

    @Resource
    private IMsgNotifyService msgNotifyServiceImpl;

    public String sendMessage(Message message) {

        if (message.getReceiver() == null || message.getSender() == null) {
            return ResponseState.INVALID_PHONE;
        }

        User sender = userDao.queryByPhone(message.getSender().getUsername());
        User receiver = userDao.queryByPhone(message.getReceiver().getUsername());

        if (sender == null || receiver == null) {
            return ResponseState.INVALID_PHONE;
        }

        message.setSender(sender);
        message.setReceiver(receiver);
        message.setState(0);
        message.setCreateTime(new Date());

        messageDao.add(message);
        msgNotifyServiceImpl.put(message);

        return ResponseState.SUCCESS;
    }

    public String unReadCount(String phone) {
        Integer count = messageDao.unReadCount(phone);
        return ResponseData.getResData(count);
    }

    public String unReadMessage(PageResult<Message> pageResult, String phone) {
        List<Message> list = messageDao.unReadMessage(pageResult, phone).getResultList();
        chageState(list);
        return ResponseData.getResData(list);
    }

    public String query2UserMessage(PageResult<Message> pageResult, String senderPhone, String receiverPhone) {
        List<Message> list = messageDao.query2UserMessage(pageResult, senderPhone, receiverPhone).getResultList();
        chageState(list);
        Collections.reverse(list);
        return ResponseData.getResData(list);
    }

    public String delMessage(Integer id) {
        Message message = messageDao.get(Message.class, id);
        if (null == message) {
            return ResponseState.INVALID_ID;
        }
        messageDao.delete(message);
        return ResponseState.SUCCESS;
    }

    /**
     * 设置消息为已查收
     *
     * @param list
     */
    private void chageState(List<Message> list) {
        for (Message message : list) {
            message.setState(1);
            messageDao.update(message);
        }
    }
}
