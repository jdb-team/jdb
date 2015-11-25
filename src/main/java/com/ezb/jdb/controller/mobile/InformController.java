package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.model.Inform;
import com.ezb.jdb.service.IInformService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 举报信息
 * author : liufeng
 * create time:2015/8/13 14:37
 */
@Controller
public class InformController {

    @Resource
    private IInformService informServiceImpl;

    /**
     * 新增举报
     *
     * @return
     */
    @RequestMapping(value = "mobile/inform/createinform")
    public
    @ResponseBody
    String createInform(String phone,Inform inform) {
        return informServiceImpl.addInform(phone,inform);
    }

}
