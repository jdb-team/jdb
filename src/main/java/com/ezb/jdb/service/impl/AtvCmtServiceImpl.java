package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.AccessKeyDao;
import com.ezb.jdb.dao.ActivityDao;
import com.ezb.jdb.dao.AtvCmtDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Activity;
import com.ezb.jdb.model.AtvCmt;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.IAtvCmtService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 活动
 * author : liufeng
 * create time:2015/8/10 13:57
 */
@Service
public class AtvCmtServiceImpl implements IAtvCmtService {

    @Resource
    private AtvCmtDao atvCmtDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ActivityDao activityDao;

    @Resource
    private AccessKeyDao accessKeyDao;

    public List<AtvCmt> qAtvCmtByActivityId(Integer id) {
        return atvCmtDao.qAtvCmtByActivityId(id);
    }

    public String addAtvCmt(String phone, AtvCmt atvCmt) {

        User user = userDao.queryByPhone(phone);
        if (null != user) {
            Activity activity = activityDao.get(Activity.class, atvCmt.getActivity().getId());
            if (null != activity) {
                atvCmt.setActivity(activity);

                if (null != atvCmt.getParentAtvCmt()) {
                    if (null != atvCmt.getParentAtvCmt().getId()) {
                        AtvCmt parentAtvCmt = atvCmtDao.get(AtvCmt.class, atvCmt.getParentAtvCmt().getId());
                        atvCmt.setParentAtvCmt(parentAtvCmt);
                    }
                }

                atvCmt.setLikeCount(0);
                atvCmt.setCommentUser(user);
                atvCmt.setCreateTime(new Date());
                atvCmtDao.add(atvCmt);
                return ResponseState.SUCCESS;
            } else {
                return ResponseState.INVALID_ID;
            }
        } else {
            return ResponseState.INVALID_PHONE;
        }
    }

    public String likeAtvCmt(String phone, Integer id) {
        User user = userDao.queryByPhone(phone);
        if (null != user) {
            String accessKey = DateTime.now().toString(JdbConstants.HOUR_FMT) + phone + "likeAtvCmt" + id;
            if (!accessKeyDao.exist(accessKey, JdbConstants.ACCKEY_ATVCMT)) {
                accessKeyDao.add(accessKey, JdbConstants.ACCKEY_ATVCMT);
                if (1 == atvCmtDao.likeAtvCmt(id)) {
                    return ResponseState.SUCCESS;
                } else {
                    return ResponseState.LIKE_OPT_ERR;
                }
            } else {
                return ResponseState.LIKE_OPT_REPEAT;
            }
        } else {
            return ResponseState.INVALID_PHONE;
        }
    }
}
