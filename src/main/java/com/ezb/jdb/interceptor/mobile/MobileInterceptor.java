package com.ezb.jdb.interceptor.mobile;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.service.IAccessKeyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

/**
 * author : liufeng
 * create time: 2015/8/3 9:56.
 */
@Component
public class MobileInterceptor implements HandlerInterceptor {

    @Resource
    private IAccessKeyService accessKeyServiceImpl;

    @Transactional
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        boolean isLogin = false;
        String token = httpServletRequest.getHeader(JdbConstants.TOKEN_HEADER);
        if(StringUtils.isEmpty(token)){
            token = httpServletRequest.getParameter(JdbConstants.TOKEN_HEADER);
        }

        if(accessKeyServiceImpl.exist(token, JdbConstants.ACCKEY_TOKEN)){
            isLogin = true;
        }

        if(!isLogin){
            httpServletResponse.setContentType("text/json;UTF-8");
            httpServletResponse.getWriter().println(ResponseState.SESSION_ERR);
        }
        return isLogin;
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
