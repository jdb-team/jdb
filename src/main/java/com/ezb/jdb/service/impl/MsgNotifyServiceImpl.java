package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.dao.MsgNotifyDao;
import com.ezb.jdb.model.CircleMessage;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.MsgNotify;
import com.ezb.jdb.service.IMsgNotifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 消息通知服务
 * author : liufeng
 * create time:2015/9/17 17:41
 */
@Service
public class MsgNotifyServiceImpl implements IMsgNotifyService {

    @Resource
    private MsgNotifyDao msgNotifyDao;

    public void put(Message message) {

        //sender notify
        MsgNotify senderNotify = msgNotifyDao.queryUser(message.getSender().getId(),message.getReceiver().getId());
        if(null == senderNotify){
            senderNotify = new MsgNotify();
        }
        senderNotify.setCtcUser(message.getSender());
        senderNotify.setCurUser(message.getReceiver());
        senderNotify.setMsg(message.getContent());
        senderNotify.setType(0);
        senderNotify.setCreateTime(message.getCreateTime());
        msgNotifyDao.saveOrUpdate(senderNotify);

        //receiver notify(curuserid,ctc user id, ctc circle)
        MsgNotify receiveNotify = msgNotifyDao.queryUser2(message.getReceiver().getId(),message.getSender().getId());
        if(null == receiveNotify){
            receiveNotify = new MsgNotify();
        }
        receiveNotify.setCtcUser(message.getReceiver());
        receiveNotify.setCurUser(message.getSender());
        receiveNotify.setMsg(message.getContent());
        receiveNotify.setType(0);
        receiveNotify.setCreateTime(message.getCreateTime());
        msgNotifyDao.saveOrUpdate(receiveNotify);
    }

    public void putCircle(CircleMessage circleMessage) {

        MsgNotify senderNotify = msgNotifyDao.queryCircle(circleMessage.getSender().getId(),circleMessage.getCircle().getId());
        if(null == senderNotify){
            senderNotify = new MsgNotify();
        }
        senderNotify.setCtcUser(circleMessage.getSender());
        senderNotify.setCtcCircle(circleMessage.getCircle());
        senderNotify.setMsg(circleMessage.getContent());
        senderNotify.setType(1);
        senderNotify.setCreateTime(circleMessage.getCreateTime());
        msgNotifyDao.saveOrUpdate(senderNotify);
    }

    public String query(PageResult<MsgNotify> pageResult, String phone) {
        return ResponseData.getResData(msgNotifyDao.query(pageResult,phone).getResultList());
    }
}
