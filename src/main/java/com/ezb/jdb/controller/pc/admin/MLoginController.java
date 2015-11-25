package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.Admin;
import com.ezb.jdb.service.IAdminService;
import com.ezb.jdb.tool.JdbMd5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 登录控制
 * author : liufeng
 * create time:2015/8/24 11:03
 */
@Controller
public class MLoginController {

    @Resource
    private IAdminService adminServiceImpl;

    @RequestMapping(value = "pc/login/dologin")
    public
    @ResponseBody
    String doLogin(HttpServletRequest request, String username, String password) {

        Admin admin = adminServiceImpl.queryByNameAndPass(username, JdbMd5Util.md5(password));
        if (null != admin) {
            if (null != request) {
                request.getSession().setAttribute(JdbConstants.ADMINID, admin.getId());
                if(admin.getLevel() == 0){
                    request.getSession().setAttribute("isSuper",true);
                }
                request.getSession().setAttribute("realName",admin.getRealName());
                return ResponseState.SUCCESS;
            }
        }
        return ResponseState.LOGIN_PASS_ERR;
    }

    @RequestMapping(value = "pc/login/loginout")
    public void loginout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Enumeration em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        response.sendRedirect(request.getContextPath()
                + "/admin/login.jsp");
    }
}
