package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.NavType;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.*;
import com.ezb.jdb.service.IActivityService;
import com.ezb.jdb.service.ICircleService;
import com.ezb.jdb.service.IFocusService;
import com.ezb.jdb.service.ITopicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 焦点图设置相关
 * author : liufeng
 * create time:2015/8/20 10:12
 */
@Controller
@Slf4j
public class MFocusController {

    @Resource
    private IFocusService focusServiceImpl;

    @Resource
    private ITopicService topicServiceImpl;

    @Resource
    private IActivityService activityServiceImpl;

    @Resource
    private ICircleService circleServiceImpl;

    /**
     * 话题、活动
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/focus/topicactivity")
    public
    @ResponseBody
    String topicActivity(PageResult<Focus> pageResult) {
        pageResult = focusServiceImpl.getMTopFocus(pageResult);
        return ResponseData.getResData(pageResult);
    }

    /**
     * 圈子
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/focus/circle")
    public
    @ResponseBody
    String circle(PageResult<Focus> pageResult) {
        pageResult = focusServiceImpl.getMCircleFocus(pageResult);
        return ResponseData.getResData(pageResult);
    }

    /**
     * 焦点数据绑定
     *
     * @param id   对应id
     * @param type 类型
     * @return
     */
    @RequestMapping(value = "pc/admin/focus/databind")
    public
    @ResponseBody
    String dataBind(Integer id, String type) {

        if (StringUtils.equals(NavType.ACTIVITY.toString(), type)) {
            Activity activity = activityServiceImpl.queryById(id);
            return ResponseData.getResData(activity);
        }

        if (StringUtils.equals(NavType.SPECOL.toString(), type)) {
            Topic topic = topicServiceImpl.queryTopicById(id);
            return ResponseData.getResData(topic);
        }

        if (StringUtils.equals(NavType.CIRCLE.toString(), type)) {
            Circle circle = circleServiceImpl.queryById(id);
            return ResponseData.getResData(circle);
        }
        return ResponseState.TYPE_ERR;
    }

    /**
     * 焦点数据保存
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/focus/datasave")
    public
    @ResponseBody
    String dataSave(FocusData focusData) {
        return focusServiceImpl.saveFocusDatas(focusData.getFocuses());
    }

    /**
     * 保存单个焦点图
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/focus/saveone")
    public
    @ResponseBody
    String saveOne(HttpServletRequest request, Focus focus) {
        return focusServiceImpl.saveOne(request, focus);
    }

    /**
     * 查询焦点
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/focus/view")
    public
    @ResponseBody
    String view(Integer id) {
        return focusServiceImpl.view(id);
    }

}
