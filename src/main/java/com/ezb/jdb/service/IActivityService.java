package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Activity;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动
 * author : liufeng
 * create time:2015/8/10 10:02
 */
public interface IActivityService {

    String queryActivity(PageResult<Activity> pageResult,String phone,String queryWords);

    Activity queryById(Integer id);

    String addActivity(HttpServletRequest request,Activity activity, String phone);

    String signup(String phone, Integer id);

    /**
     * 查询我创建的活动
     * @param pageResult
     * @param phone 当前用户的手机号
     * @param queryWords
     * @return
     */
    String queryMyActivity(PageResult<Activity> pageResult, String phone, String queryWords);

    /**
     * 查询我报名的活动
     * @param pageResult
     * @param phone 当前用户的手机号
     * @param queryWords
     * @return
     */
    String queryMyJoinActivity(PageResult<Activity> pageResult, String phone, String queryWords);

    /**
     * 导出名单至email
     * @param id
     * @return
     */
    String sendJoinUsers2Email(Integer id);

    PageResult<Activity> query(
            PageResult<Activity> pageResult,Integer id,String title,
            String username,String realName,
            String state,String startTime,String endTime);

    String state(Integer id);

    String viewActivity( String phone,Integer id);

    /**
     * 取消活动报名
     * @param phone
     * @param id
     * @return
     */
    String unsignup(String phone, Integer id);
}
