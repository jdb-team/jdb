package com.ezb.jdb.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共服务
 * author : liufeng
 * create time:2015/9/14 10:20
 */
public interface IPicService {

    /**
     * 上传 缩放 剪切 图片
     *
     * @param request
     * @return
     */
    String uploadResizeCut(HttpServletRequest request, double width, double heigth);

    /**
     * 上传图片 返回路径
     * @param request
     * @return
     */
    String uploadImage(HttpServletRequest request);
}
