package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Message;
import com.ezb.jdb.model.MsgNotify;

/**
 * 最新消息通知服务
 * author : liufeng
 * create time:2015/9/17 17:37
 */
public interface IMsgNotifyService {

    /**
     * 将发送的消息添加到消息通知队列中
     * @param message
     */
    void put(Message message);

    /**
     * 最新消息查询
     * @param pageResult
     * @param phone
     * @return
     */
    String query(PageResult<MsgNotify> pageResult, String phone);
}
