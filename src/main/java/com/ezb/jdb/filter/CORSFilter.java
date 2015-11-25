package com.ezb.jdb.filter;

import com.ezb.jdb.common.JdbConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域 Filter
 * author : liufeng
 * create time:2015/9/5 10:16
 */
public class CORSFilter implements Filter {

    public CORSFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");

        ((HttpServletResponse) response).addHeader(
                "Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept," + JdbConstants.TOKEN_HEADER);

        ((HttpServletResponse) response).addHeader(
                "Access-Control-Expose-Headers",
                JdbConstants.TOKEN_HEADER);

        chain.doFilter(request, response);
    }
}
