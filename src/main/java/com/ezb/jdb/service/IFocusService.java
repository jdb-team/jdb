package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Focus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 焦点
 * author : liufeng
 * create time:2015/8/20 10:16
 */
public interface IFocusService {

    /**
     * 获取焦点图
     * @param pageResult
     * @return
     */
    PageResult<Focus> getTopFocus(PageResult<Focus> pageResult);

    /**
     * 获取朋友圈子推荐
     * @param pageResult
     * @return
     */
    PageResult<Focus> getCircleFocus(PageResult<Focus> pageResult);

    /**
     * 获取焦点图
     * @param pageResult
     * @return
     */
    PageResult<Focus> getMTopFocus(PageResult<Focus> pageResult);

    /**
     * 获取朋友圈子推荐
     * @param pageResult
     * @return
     */
    PageResult<Focus> getMCircleFocus(PageResult<Focus> pageResult);

    String saveFocusDatas(List<Focus> focusList);

    String saveOne(HttpServletRequest request, Focus focus);

    String view(Integer id);
}
