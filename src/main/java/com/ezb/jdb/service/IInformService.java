package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Inform;

import javax.servlet.http.HttpServletRequest;

/**
 * 举报
 * author : liufeng
 * create time:2015/8/13 14:28
 */
public interface IInformService {

    String addInform(String phone,Inform inform);

    PageResult<Inform> query(PageResult<Inform> pageResult,
                             String username,String realname,
                             String qbusername,String qbrealname,
                             String startTime,String endTime,
                             String reason, String type,String state);

    String del(String ids);

    String offline(HttpServletRequest request,Integer id);
}
