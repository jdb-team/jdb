package com.ezb.jdb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.AccessKeyDao;
import com.ezb.jdb.dao.ActivityDao;
import com.ezb.jdb.dao.AtvCmtDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.AtvCmt;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.IPicService;
import com.ezb.jdb.service.IActivityService;
import com.ezb.jdb.service.IMailService;
import com.ezb.jdb.tool.JdbDateUtil;
import com.ezb.jdb.tool.JdbMd5Util;
import com.ezb.jdb.view.ActivityView;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * 活动
 * author : liufeng
 * create time:2015/8/10 10:04
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Resource
    private ActivityDao activityDao;

    @Resource
    private AtvCmtDao atvCmtDao;

    @Resource
    private UserDao userDao;

    @Resource
    private IMailService mailServiceImpl;

    @Resource
    private IPicService picServiceImpl;

    @Resource
    private AccessKeyDao accessKeyDao;

    public String queryActivity(PageResult<Activity> pageResult, String phone, String queryWords,String city,String labelId) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        pageResult = activityDao.queryActivity(pageResult, queryWords,city,labelId);
        return ActivityView.convert2Json(pageResult.getResultList(), user);
    }

    public String queryMyActivity(PageResult<Activity> pageResult,
                                  String phone, String queryWords) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        pageResult = activityDao.queryMyActivity(pageResult, phone, queryWords);
        return ActivityView.convert2Json(pageResult.getResultList(), user);
    }

    public String queryMyJoinActivity(PageResult<Activity> pageResult,
                                      String phone, String queryWords) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        pageResult = activityDao.queryMyJoinActivity(pageResult, phone, queryWords);
        return ActivityView.convert2Json(pageResult.getResultList(), user);
    }

    public String sendJoinUsers2Email(Integer id) {
        Activity activity = activityDao.get(Activity.class, id);
        if (activity == null) {
            return ResponseState.INVALID_ID;
        }
        String subject = "参加" + activity.getTitle() + "活动人员详细名单";
        String content = ActivityView.buildHtmlContent(activity.getJoinUsers());
        return mailServiceImpl.sendHtmlMail(activity.getContactEmail(), subject, content);
    }

    public PageResult<Activity> query(PageResult<Activity> pageResult, Integer id, String title,
                                      String username, String realName,
                                      String state, String startTime, String endTime) {
        return activityDao.query(pageResult, id, title, username, realName, state, startTime, endTime);
    }

    public String state(Integer id) {
        Activity activity = activityDao.get(Activity.class, id);
        if (null == activity) {
            return ResponseState.INVALID_ID;
        }
        if (activity.getState() == 1) {
            activity.setState(0);
        } else {
            activity.setState(1);
        }
        activityDao.update(activity);
        return ResponseState.SUCCESS;
    }

    public String viewActivity(String phone, Integer id) {
        Activity activity = activityDao.get(Activity.class, id);
        if (null == activity) {
            return ResponseState.INVALID_ID;
        }
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }

        String accessKey = JdbMd5Util.md5(DateTime.now().toString(JdbConstants.HOUR_FMT) + phone + "viewActivity" + id);

        if (!accessKeyDao.exist(accessKey, JdbConstants.ACCKEY_ACTIVITY)) {
            activity.setPv(activity.getPv() + 1);
            activityDao.update(activity);
            accessKeyDao.add(accessKey, JdbConstants.ACCKEY_ACTIVITY);
        }

        List<AtvCmt> comments = atvCmtDao.qAtvCmtByActivityId(id);
        for (AtvCmt atvCmt : comments) {
            if(null != atvCmt.getParentAtvCmt()){
                atvCmt.setChildCmtCount(atvCmtDao.qAtvCountCmtByPId(atvCmt.getId()));
            }
        }

        JSONObject jsonObject = new JSONObject();
        activity.setInterTime(JdbDateUtil.interTime(activity.getCreateTime()));
        jsonObject.put("activity", activity);
        jsonObject.put("comments", comments);
        jsonObject.put("state", ActivityView.getState(activity));

        if (activity.getJoinUsers().contains(user)) {
            jsonObject.put("join", "1");
        } else {
            jsonObject.put("join", "0");
        }
        return ResponseData.getResData(jsonObject);
    }

    public Activity queryById(Integer id) {
        return activityDao.get(Activity.class, id);
    }

    public String addActivity(HttpServletRequest request, Activity activity,
                              String phone) {
        User user = userDao.queryByPhone(phone);
        if (null != user) {
            String rpath = picServiceImpl.uploadResizeCut(request, JdbConstants.PIC_WIDTH, JdbConstants.PIC_HEIGHT);
            if (StringUtils.equals(rpath, ResponseState.PIC_ERR)) {
                return ResponseState.PIC_SAVE_ERR_JSON;
            }
            activity.setCreateUser(user);
            activity.setCreateTime(new Date());
            activity.setPicPath(rpath);
            activity.setPv(0);
            activity.setState(1);

            //活动创建者默认参加活动
            HashSet<User> set = new HashSet<User>();
            set.add(user);
            activity.setJoinUsers(set);

            //人数上限 100000
            if (null == activity.getPersonLimit()) {
                activity.setPersonLimit(100000);
            }
            activityDao.add(activity);
            return ResponseData.getResData(ActivityView.getJsonObject(user, activity));
        } else {
            return ResponseState.INVALID_PHONE;
        }
    }

    public String signup(String phone, Integer id) {
        User user = userDao.queryByPhone(phone);
        if (null != user) {
            Activity activity = activityDao.get(Activity.class, id);

            if (null != activity) {

                if (null == activity.getJoinUsers()) {
                    activity.setJoinUsers(new HashSet<User>());
                }

                activity.getJoinUsers().add(user);
                activityDao.update(activity);

                return ResponseState.SUCCESS;

            } else {
                return ResponseState.INVALID_ID;
            }
        } else {
            return ResponseState.INVALID_PHONE;
        }
    }

    public String unsignup(String phone, Integer id) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        Activity activity = activityDao.get(Activity.class, id);
        if (null == activity) {
            return ResponseState.INVALID_ID;
        }
        if (null != activity.getJoinUsers()) {
            activity.getJoinUsers().remove(user);
        }
        activityDao.update(activity);
        return ResponseState.SUCCESS;
    }
}
