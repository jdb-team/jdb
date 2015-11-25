package com.ezb.jdb.interceptor.mobile;

import com.ezb.jdb.common.ResponseState;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author : liufeng
 * create time: 2015/8/3 9:56.
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
//        boolean isLogin = (null != httpServletRequest.getSession().getAttribute("isLogin"));
//        if (!isLogin) {
//            httpServletResponse.setContentType("text/json;UTF-8");
//            httpServletResponse.getWriter().println(ResponseState.UNLOGIN);
//        }
//        return isLogin;
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {

    }
}
