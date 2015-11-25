package com.ezb.jdb.filter;

import com.ezb.jdb.common.JdbConstants;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jsp filter
 * author : liufeng
 * create time:2015/8/24 8:50
 */
@Slf4j
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getContextPath();

        String indexPath = req.getScheme() + "://" + req.getServerName() +
                ":" + req.getServerPort() + path + "/admin/login.jsp";

        if (req.getRequestURI().endsWith("login.jsp")) {

            chain.doFilter(request, response);
            return;
        }

        Object loginuser = req.getSession().getAttribute(JdbConstants.ADMINID);
        if (loginuser == null) {
            res.sendRedirect(indexPath);
            return;
        }
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
