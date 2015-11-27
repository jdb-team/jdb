package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.AtvCmt;
import com.ezb.jdb.service.IActivityService;
import com.ezb.jdb.service.IAtvCmtService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 活动
 * author : liufeng
 * create time:2015/8/10 9:46
 */
@Controller
public class ActivityController {

    @Resource
    private IActivityService activityServiceImpl;

    @Resource
    private IAtvCmtService atvCmtServiceImpl;

    /**
     * 页面提交的日期格式str注入Date类型
     *
     * @param request
     * @param binder
     */
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    /**
     * 活动分页列表
     *
     * @param pageResult
     * @param phone 手机号
     * @param queryWords 查询关键字
     * @return
     */
    @RequestMapping(value = "mobile/activity/queryactivity")
    public
    @ResponseBody
    String queryActivity(PageResult<Activity> pageResult, String phone, String queryWords,String city) {
        return activityServiceImpl.queryActivity(pageResult, phone, queryWords,city);
    }

    /**
     * 我发起的活动列表
     *
     * @param pageResult
     * @param phone      当前用户的手机号
     * @param queryWords 查询关键字
     * @return
     */
    @RequestMapping(value = "mobile/activity/querymyactivity")
    public
    @ResponseBody
    String queryMyActivity(PageResult<Activity> pageResult, String phone, String queryWords) {
        return activityServiceImpl.queryMyActivity(pageResult, phone, queryWords);
    }

    /**
     * 我报名的活动列表
     *
     * @param pageResult
     * @param phone      当前用户的手机号
     * @param queryWords 查询关键字
     * @return
     */
    @RequestMapping(value = "mobile/activity/querymyjoinactivity")
    public
    @ResponseBody
    String queryMyJoinActivity(PageResult<Activity> pageResult, String phone, String queryWords) {
        return activityServiceImpl.queryMyJoinActivity(pageResult, phone, queryWords);
    }

    /**
     * 活动详细页
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/viewactivity")
    public
    @ResponseBody
    String viewActivity(String phone,Integer id) {
        return activityServiceImpl.viewActivity(phone,id);
    }

    /**
     * 创建活动
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/createactivity")
    public
    @ResponseBody
    String createActivity(HttpServletRequest request, Activity activity, String phone) {
        return activityServiceImpl.addActivity(request, activity, phone);
    }

    /**
     * 创建评论
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/createatvcmt")
    public
    @ResponseBody
    String createAtvCmt(String phone, AtvCmt atvCmt) {
        return atvCmtServiceImpl.addAtvCmt(phone, atvCmt);
    }

    /**
     * 评论点赞
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/likeatvcmt")
    public
    @ResponseBody
    String likeAtvCmt(String phone, Integer id) {
        return atvCmtServiceImpl.likeAtvCmt(phone, id);
    }

    /**
     * 活动报名
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/signup")
    public
    @ResponseBody
    String signup(String phone, Integer id) {
        return activityServiceImpl.signup(phone, id);
    }

    /**
     * 取消活动报名
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/unsignup")
    public
    @ResponseBody
    String unsignup(String phone, Integer id) {
        return activityServiceImpl.unsignup(phone, id);
    }

    /**
     * 参加活动的详细名单
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/queryjoinusers")
    public
    @ResponseBody
    String queryJoinUsers(Integer id) {
        Activity activity = activityServiceImpl.queryById(id);
        if (null == activity) {
            return ResponseState.INVALID_ID;
        }
        return ResponseData.getResData(activity.getJoinUsers());
    }

    /**
     * 导出名单至Email
     *
     * @return
     */
    @RequestMapping(value = "mobile/activity/sendjoinusers2email")
    public
    @ResponseBody
    String sendJoinUsers2Email(Integer id) {
        return activityServiceImpl.sendJoinUsers2Email(id);
    }
}
