package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.CircleMessage;
import com.ezb.jdb.service.ICircleMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * author : Administrator
 * create time:2015/11/28
 */
@Controller
public class CircleMessageController {

    @Resource
    private ICircleMessageService circleMessageServiceImpl;

    /**
     * 查询圈子消息
     *
     * @param pageResult
     * @param cid        圈子id
     * @return
     */
    @RequestMapping(value = "mobile/circlemessage/query")
    public
    @ResponseBody
    String query(PageResult<CircleMessage> pageResult, Integer cid) {
        return circleMessageServiceImpl.query(pageResult, cid);
    }

    /**
     * 发送群组消息
     *
     * @param circleMessage
     * @return
     */
    @RequestMapping(value = "mobile/message/sendcirclemessage")
    public
    @ResponseBody
    String sendCircleMessage(CircleMessage circleMessage) {
        return circleMessageServiceImpl.sendCircleMessage(circleMessage);
    }

    /**
     * 发送群组消息
     *
     * @param circleMessage
     * @return 影响的条数，如果返回 0  说明该次请求不成功
     */
    @RequestMapping(value = "mobile/message/setupzero")
    public
    @ResponseBody
    String setupZero(CircleMessage circleMessage) {

        int count = circleMessageServiceImpl.setupZero(circleMessage);
        if (count == 0) {
            return ResponseState.FIAL;
        } else {
            return ResponseState.SUCCESS;
        }
    }

}
