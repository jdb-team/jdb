package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Message;

/**
 * 消息服务
 * author : liufeng
 * create time:2015/8/18 10:44
 */
public interface IMessageService {

    String sendMessage(Message message);

    /**
     * 未查收消息条数
     * @param phone
     * @return
     */
    String unReadCount(String phone);

    /**
     * 查询未查收的消息
     * @param pageResult
     * @param phone
     * @return
     */
    String unReadMessage(PageResult<Message> pageResult, String phone);

    /**
     * 查询两个用户之间的消息通讯
     * @param pageResult
     * @param senderPhone
     * @param receiverPhone
     * @return
     */
    String query2UserMessage(PageResult<Message> pageResult, String senderPhone, String receiverPhone);

    /**
     * 删除消息
     * @param id
     * @return
     */
    String delMessage(Integer id);
}
