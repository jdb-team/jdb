package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.CircleMessage;

/**
 * Created by Administrator on 2015/11/28.
 */
public interface ICircleMessageService {

    /**
     * 发送圈子消息
     */
    String sendCircleMessage(CircleMessage circleMessage);

    /**
     * 读取消息后，将消息的条数置为0
     *
     * @param circleMessage
     * @return 影响的条数，如果返回 0  说明该次请求不成功
     */
    int setupZero(CircleMessage circleMessage);

    /**
     * 查询某个圈子的消息
     *
     * @param circleMessage
     * @param cid
     * @return
     */
    String query(PageResult<CircleMessage> circleMessage, Integer cid);
}
