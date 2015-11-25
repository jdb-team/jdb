package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.CirCmtDao;
import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.CirCmt;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.ICirCmtService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 圈子评论
 * author : liufeng
 * create time:2015/8/14 16:48
 */
@Service
public class CirCmtServiceImpl implements ICirCmtService {

    @Resource
    private CirCmtDao cirCmtDao;

    @Resource
    private UserDao userDao;

    @Resource
    private CircleDao circleDao;

    public PageResult<CirCmt> queryCircmts(PageResult<CirCmt> pageResult, String circleid) {
        return cirCmtDao.queryCircmts(pageResult, circleid);
    }

    public String createCircmt(String phone, CirCmt cirCmt) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }

        Circle circle = circleDao.get(Circle.class,cirCmt.getCircle().getId());
        if(null == circle){
            return ResponseState.INVALID_ID;
        }
        cirCmt.setCircle(circle);

        if(null != cirCmt.getParentCirCmt()){
            if(null != cirCmt.getParentCirCmt().getId()){
                CirCmt parentCirCmt = cirCmtDao.get(CirCmt.class,cirCmt.getParentCirCmt().getId());
                cirCmt.setParentCirCmt(parentCirCmt);
            }
        }

        cirCmt.setCommentUser(user);
        cirCmt.setCreateTime(new Date());
        cirCmt.setLikeCount(0);
        cirCmtDao.add(cirCmt);
        return ResponseState.SUCCESS;
    }
}
