package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Admin;
import com.ezb.jdb.service.IAdminService;
import com.ezb.jdb.tool.JdbMd5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 管理员管理
 * author : liufeng
 * create time:2015/8/20 14:13
 */
@Controller
public class MAdminController {

    @Resource
    private IAdminService adminServiceImpl;

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/admin/updatepass")
    public
    @ResponseBody
    String updatePass(HttpServletRequest request, String password) {
        return adminServiceImpl.updatePass(request, JdbMd5Util.md5(password));
    }

    /**
     * 查询某个管理员账户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "pc/admin/admin/viewadmin")
    public
    @ResponseBody
    String viewAdmin(Integer id) {
        return adminServiceImpl.queryById(id);
    }

    /**
     * 查询当前用户
     *
     * @return
     */
    @RequestMapping(value = "pc/admin/admin/querycuradmin")
    public
    @ResponseBody
    String queryCurAdmin(HttpServletRequest request) {
        return adminServiceImpl.queryCurAdmin(request);
    }

    /**
     * 添加或更新系统管理员
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "pc/admin/admin/saveorupdate")
    public
    @ResponseBody
    String saveOrUpdate(Admin admin) {
        return adminServiceImpl.saveOrUpdate(admin);
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "pc/admin/admin/delete")
    public
    @ResponseBody
    String delete(String ids) {
        return adminServiceImpl.delete(ids);
    }

    /**
     * 查询
     *
     * @param pageResult 分页信息
     * @param username   账号
     * @param realName   姓名
     * @param startTime  注册日期 (开始)
     * @param endTime    注册日志 (结束)
     * @return
     */
    @RequestMapping(value = "pc/admin/admin/query")
    public
    @ResponseBody
    String query(PageResult<Admin> pageResult,
                 String username, String realName,
                 String startTime, String endTime) {
        return adminServiceImpl.query(pageResult, username, realName, startTime, endTime);
    }
}
