package com.ezb.jdb.service;

import com.ezb.jdb.model.CircleMessage;

/**
 * Created by Administrator on 2015/11/28.
 */
public interface ICircleMessageService {

    /*
     *发送圈子消息
     */
    String sendCircleMessage(CircleMessage circleMessage);
}
