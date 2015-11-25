package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.JoinUserCircle;

/**
 * author : liufeng
 * create time:2015/11/19 17:40
 */
public interface IJoinUserCircleService {
    /**
     * 查询圈子成员列表
     * @param pageResult
     * @param circleid
     * @return
     */
    PageResult<JoinUserCircle> queryMembers(PageResult<JoinUserCircle> pageResult, String circleid);
}
