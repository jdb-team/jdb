package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Admin;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统管理员
 * author : liufeng
 * create time:2015/8/21 14:06
 */
public interface IAdminService {

    boolean login(String username,String pass);

    String saveOrUpdate(Admin admin);

    String delete(String ids);

    String query(PageResult<Admin> pageResult, String username, String realName, String startTime, String endTime);

    Admin queryByNameAndPass(String username,String pass);

    String queryCurAdmin(HttpServletRequest request);

    String updatePass(HttpServletRequest request,String password);

    String queryById(Integer id);
}
