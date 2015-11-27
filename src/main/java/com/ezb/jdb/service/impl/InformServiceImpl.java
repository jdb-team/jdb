package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.NavType;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.*;
import com.ezb.jdb.model.*;
import com.ezb.jdb.service.IInformService;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * author : liufeng
 * create time:2015/8/13 14:29
 */
@Service
public class InformServiceImpl implements IInformService {

    @Resource
    private InformDao informDao;

    @Resource
    private UserDao userDao;

    @Resource
    private TopicDao topicDao;

    @Resource
    private CircleDao circleDao;

    @Resource
    private ActivityDao activityDao;

    @Resource
    private AdminDao adminDao;

    @Resource
    private OfflineNoticeDao offlineNoticeDao;

    public String addInform(String phone, Inform inform) {
        User user = userDao.queryByPhone(phone);

        if (null == user) {

            return ResponseState.INVALID_PHONE;

        } else {
            if (null == inform || null == inform.getAssoId()) {
                return ResponseState.INFORM_NULL;
            }

            if (StringUtils.equals(inform.getType(), NavType.CIRCLE.toString())) {
                inform.setViewurl(JdbConstants.VIEWURL_CIRCLE + "?id=" + inform.getAssoId());
            }

            if (StringUtils.equals(inform.getType(), NavType.TOPIC.toString())) {
                inform.setViewurl(JdbConstants.VIEWURL_TOPIC + "?id=" + inform.getAssoId());
                Topic topic = topicDao.get(Topic.class, inform.getAssoId());
                if (topic == null) {
                    return ResponseState.INFORM_NULL;
                }
                inform.setInformUser(topic.getCreateUser());
            }

            if (StringUtils.equals(inform.getType(), NavType.ACTIVITY.toString())) {
                inform.setViewurl(JdbConstants.VIEWURL_ACTIVITY + "?id=" + inform.getAssoId());
                Activity activity = activityDao.get(Activity.class, inform.getAssoId());
                if (activity == null) {
                    return ResponseState.INFORM_NULL;
                }
                inform.setInformUser(activity.getCreateUser());
            }

            if (StringUtils.equals(inform.getType(), NavType.USER.toString())) {
                User informUser = userDao.get(User.class, inform.getAssoId());
                if (null == informUser) {
                    return ResponseState.INFORM_NULL;
                }
                inform.setInformUser(informUser);
                inform.setAssoName(informUser.getAlumnus().getRealName());
                inform.setViewurl(JdbConstants.VIEWURL_USER + "?username=" + informUser.getUsername());
            }

            inform.setCreateUser(user);
            inform.setCreateTime(new Date());
            inform.setState(0);
            informDao.add(inform);
            return ResponseState.SUCCESS;
        }
    }

    public PageResult<Inform> query(PageResult<Inform> pageResult,
                                    String username, String realname,
                                    String qbusername, String qbrealname,
                                    String startTime, String endTime,
                                    String reason, String type, String state) {

        return informDao.query(pageResult, username, realname, qbusername, qbrealname,
                startTime, endTime, reason, type, state);
    }

    public String del(String ids) {
        String[] idArrs = StringUtils.splitByWholeSeparator(ids, ",");
        for (String id : idArrs) {
            if (informDao.del(Integer.parseInt(id)) != 1) {
                return ResponseState.FAIL;
            }
        }
        return ResponseState.SUCCESS;
    }

    public String offline(HttpServletRequest request, Integer id) {
        Inform inform = informDao.get(Inform.class, id);
        if (null == inform) {
            return ResponseState.INVALID_ID;
        }

        Object obj = request.getSession().getAttribute(JdbConstants.ADMINID);
        if (obj == null) {
            return ResponseState.SESSION_ERR;
        }

        Integer adminId = (Integer) obj;
        OfflineNotice offlineNotice = new OfflineNotice();
        offlineNotice.setAdmin(adminDao.get(Admin.class, adminId));
        offlineNotice.setCreateTime(new Date());
        offlineNotice.setC3(inform.getReason());

        if (StringUtils.equals(inform.getType(), NavType.TOPIC.toString())) {
            Topic topic = topicDao.get(Topic.class, inform.getAssoId());
            topicDao.update(topic);
            offlineNotice.setC1(NavType.TOPIC.toString());
            offlineNotice.setC2(new DateTime(topic.getCreateTime()).toString(JdbConstants.DATE_TIME_FMT));

        } else if (StringUtils.equals(inform.getType(), NavType.ACTIVITY.toString())) {
            Activity activity = activityDao.get(Activity.class, inform.getAssoId());
            activity.setState(0);
            activityDao.update(activity);
            offlineNotice.setC1(NavType.ACTIVITY.toString());
            offlineNotice.setC2(activity.getTitle());

        } else if (StringUtils.equals(inform.getType(), NavType.CIRCLE.toString())) {
            Circle circle = circleDao.get(Circle.class, inform.getAssoId());
            circle.setState(0);
            circleDao.update(circle);
            offlineNotice.setC1(NavType.CIRCLE.toString());
            offlineNotice.setC2(circle.getTitle());

        } else if (StringUtils.equals(inform.getType(), NavType.USER.toString())) {
            User user = userDao.get(User.class, inform.getAssoId());
            user.setState(0);
            userDao.update(user);
            offlineNotice.setC1(NavType.USER.toString());
            offlineNotice.setC2(user.getAlumnus().getRealName());
        } else {
            return ResponseState.TYPE_ERR;
        }

        inform.setState(1);
        informDao.update(inform);
        offlineNoticeDao.add(offlineNotice);
        return ResponseState.SUCCESS;
    }
}
