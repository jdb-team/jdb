package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.model.CircleMessage;
import com.ezb.jdb.service.ICircleMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/28.
 */
@Controller
public class CircleMessageController {

    @Resource
    private ICircleMessageService circleMessageService;

    @RequestMapping(value = "mobile/message/sendcirclemessage")
    public
    @ResponseBody
    String sendCircleMessage(CircleMessage circleMessage) {
        return circleMessageService.sendCircleMessage(circleMessage);
    }

}
