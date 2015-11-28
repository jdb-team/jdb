package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.CircleMessageDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.CircleMessage;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.ICircleMessageService;
import org.apache.http.client.CircularRedirectException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/28.
 */
@Service
public class CircleMessageServiceImpl implements ICircleMessageService{
    @Resource
    private  UserDao userDao;

    @Resource
    private CircleDao circleDao;

    @Resource
    private CircleMessageDao circleMessageDao;

    public String sendCircleMessage(CircleMessage circleMessage){
        if (circleMessage.getSender() == null || circleMessage.getCircle() == null){
            return ResponseState.FIAL;
        }

        User sender = userDao.queryByPhone(circleMessage.getSender().getUsername());
        //查找圈子
        Circle receiver = circleDao.get(Circle.class,circleMessage.getCircle().getId());
        if (sender == null || receiver == null) {
            return ResponseState.FIAL;
        }
        circleMessage.setSender(sender);
        circleMessage.setCircle(receiver);
        circleMessage.setCreateTime(new Date());

        circleMessageDao.add(circleMessage);

        return ResponseState.SUCCESS;
    }
}