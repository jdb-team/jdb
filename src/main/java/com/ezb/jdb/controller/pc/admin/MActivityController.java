package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.service.IActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 活动管理
 * author : liufeng
 * create time:2015/8/25 17:39
 */
@Controller
public class MActivityController {

    @Resource
    private IActivityService activityServiceImpl;

    /**
     * 活动详细页
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/activity/viewactivity")
    public
    @ResponseBody
    String viewActivity(Integer id) {
        Activity activity = activityServiceImpl.queryById(id);
        return ResponseData.getResData(activity);
    }

    /**
     * 查询
     *
     * @param pageResult
     * @param id
     * @param title
     * @param username
     * @param realName
     * @param state
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "pc/admin/activity/query")
    public
    @ResponseBody
    String query(PageResult<Activity> pageResult,
                 Integer id,
                 String title,
                 String username,
                 String realName,
                 String state,
                 String startTime,
                 String endTime) {
        pageResult = activityServiceImpl.query(
                pageResult, id,title,username,realName,state,startTime,endTime);
        return ResponseData.getResData(pageResult);
    }

    /**
     * 停用或恢复活动
     * @param id
     * @return
     */
    @RequestMapping(value = "pc/admin/activity/state")
    public
    @ResponseBody
    String state(Integer id){
        return activityServiceImpl.state(id);
    }
}
