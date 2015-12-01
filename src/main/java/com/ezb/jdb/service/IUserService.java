package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Admin;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author : liufeng
 * create time: 2015/8/4 15:42.
 */
public interface IUserService {

    String register(User user, String invitateCode, String verifyCode);

    String login(HttpServletRequest request,HttpServletResponse response,User user);

    String uploadHeadPic(HttpServletRequest request, String phone);

    String perfInfo(HttpServletRequest request, Alumnus alumnus);

    boolean isValid(String phone);

    User queryUserByPhone(String phone);

    void update(User user);

    PageResult<User> queryUnFriendUser(PageResult<User> pageResult, String phone, Alumnus alumnus, String orderby);

    String resetPwd(String phone, String password, String verifyCode);

    String resetPhone(String phone, String newPhone, String verifyCode);

    PageResult<User> queryNearUsers(PageResult<User> pageResult, String phone);

    PageResult<User> queryAllUser(PageResult<User> pageResult, User user, Alumnus alumnus, String orderby);

    PageResult<User> query(PageResult<User> pageResult, String username, String state, Alumnus alumnus, String startTime, String endTime);

    String state(Integer id);

    String view(String phone);
}
