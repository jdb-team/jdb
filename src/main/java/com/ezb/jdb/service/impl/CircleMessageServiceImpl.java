package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.CircleMessageDao;
import com.ezb.jdb.dao.JoinUserCircleDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.CircleMessage;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.ICircleMessageService;
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

    @Resource
    private JoinUserCircleDao joinUserCircleDao;

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
        //sender和receiver对应关系的表的msg_count加一
        joinUserCircleDao.updateMsgCount(sender.getId(),receiver.getId());
        return ResponseState.SUCCESS;
    }

    public  int setupZero(CircleMessage circleMessage){
        if (circleMessage.getSender() == null || circleMessage.getCircle() == null){
            return 0;
        }
//        User sender = userDao.queryByPhone(circleMessage.getSender().getUsername());
//        Circle receiver = circleDao.get(Circle.class,circleMessage.getCircle().getId());
        if(circleMessage.getSender().getId() == null || circleMessage.getCircle().getId() == null){
            return 0;
        }
        return joinUserCircleDao.updateToZero(circleMessage.getSender().getId(),circleMessage.getCircle().getId());
    }
}