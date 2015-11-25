package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.CirCmt;

/**
 * 圈子评论
 * author : liufeng
 * create time:2015/8/14 16:47
 */
public interface ICirCmtService {

    PageResult<CirCmt> queryCircmts(PageResult<CirCmt> pageResult, String circleid);

    String createCircmt(String phone, CirCmt cirCmt);
}
