package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户管理
 * author : liufeng
 * create time:2015/8/29 9:28
 */
@Controller
public class MUserController {

    @Resource
    private IUserService userServiceImpl;

    /**
     * 查询
     *
     * @param pageResult 分页信息
     * @param username   账号
     * @param state      状态
     * @param alumnus    详细信息
     * @param startTime  注册日期 (开始)
     * @param endTime    注册日志 (结束)
     * @return
     */
    @RequestMapping(value = "pc/admin/user/query")
    public
    @ResponseBody
    String query(PageResult<User> pageResult,
                 String username,
                 String state,
                 Alumnus alumnus,
                 String startTime,
                 String endTime) {
        return ResponseData.getResData(
                userServiceImpl.query(pageResult, username,
                state,alumnus, startTime, endTime));
    }

    @RequestMapping(value = "pc/admin/user/state")
    public
    @ResponseBody
    String state(Integer id){
        return userServiceImpl.state(id);
    }

    /**
     * 查看好友
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/user/view")
    public
    @ResponseBody
    String view(String username) {

        User user = userServiceImpl.queryUserByPhone(username);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        return ResponseData.getResData(user);
    }
}
