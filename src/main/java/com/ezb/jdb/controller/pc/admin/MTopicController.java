package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.Topic;
import com.ezb.jdb.service.ITopicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 话题
 * author : liufeng
 * create time:2015/8/25 18:12
 */
@Controller
public class MTopicController {
    @Resource
    private ITopicService topicServiceImpl;

    /**
     * 话题详情页
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/topic/viewTopic")
    public
    @ResponseBody
    String viewTopic(Integer id) {
        Topic Topic = topicServiceImpl.queryTopicById(id);//新闻实体
        return ResponseData.getResData(Topic);
    }

    /**
     * 话题查询
     *
     * @param id        编号
     * @param title     标题
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param username  创建者账号
     * @param realName  创建者姓名
     * @return
     */
    @RequestMapping(value = "pc/admin/topic/query")
    public
    @ResponseBody
    String query(PageResult<Topic> pageResult,
                 Integer id,
                 String title,
                 String startTime,
                 String endTime,
                 String username,
                 String realName,
                 String type,
                 String state) {

        pageResult = topicServiceImpl.query(pageResult,id,title,startTime,endTime,username,realName,type,state);
        return ResponseData.getResData(pageResult);
    }

    /**
     * 下线或恢复话题
     * @param id
     * @return
     */
    @RequestMapping(value = "pc/admin/topic/state")
    public
    @ResponseBody
    String state(Integer id){
        return topicServiceImpl.state(id);
    }
}
