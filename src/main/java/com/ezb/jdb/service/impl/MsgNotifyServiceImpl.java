package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.dao.MsgNotifyDao;
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
        MsgNotify senderNotify = msgNotifyDao.query(message.getSender().getId(),message.getReceiver().getId());
        if(null == senderNotify){
            senderNotify = new MsgNotify();
        }
        senderNotify.setCurUser(message.getSender());
        senderNotify.setCtcUser(message.getReceiver());
        senderNotify.setMsg(message.getContent());
        senderNotify.setCreateTime(message.getCreateTime());
        msgNotifyDao.saveOrUpdate(senderNotify);

        //receiver notify
        MsgNotify receiveNotify = msgNotifyDao.query(message.getReceiver().getId(),message.getSender().getId());
        if(null == receiveNotify){
            receiveNotify = new MsgNotify();
        }
        receiveNotify.setCurUser(message.getReceiver());
        receiveNotify.setCtcUser(message.getSender());
        receiveNotify.setMsg(message.getContent());
        receiveNotify.setCreateTime(message.getCreateTime());
        msgNotifyDao.saveOrUpdate(receiveNotify);
    }

    public String query(PageResult<MsgNotify> pageResult, String phone) {
        return ResponseData.getResData(msgNotifyDao.query(pageResult,phone).getResultList());
    }
}
