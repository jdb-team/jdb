package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.JoinUserCircleDao;
import com.ezb.jdb.model.JoinUserCircle;
import com.ezb.jdb.service.IJoinUserCircleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 圈子评论
 * author : liufeng
 * create time:2015/8/14 16:48
 */
@Service
public class JoinUserCircleImpl implements IJoinUserCircleService {

    @Resource
    private JoinUserCircleDao joinUserCircleDao;

    public PageResult<JoinUserCircle> queryMembers(PageResult<JoinUserCircle> pageResult, String circleid) {
        return joinUserCircleDao.queryMembers(pageResult,circleid);
    }
}
