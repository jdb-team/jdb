package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Topic;

import javax.servlet.http.HttpServletRequest;

/**
 * 话题服务类
 * author : liufeng
 * create time: 2015/8/7 10:34.
 */
public interface ITopicService {

    String queryTopic(PageResult<Topic> pageResult, String phone, String speColumnId,String type,String labelId,Boolean isShield,Boolean isAll);

    Topic queryTopicById(Integer id);

    Topic queryTopicById(String phone, Integer id);

    String addTopic(HttpServletRequest request, Topic topic, String phone);

    PageResult<Topic> query(PageResult<Topic> pageResult, Integer id, String title, String startTime, String endTime, String username, String realName, String type, String state);

    String state(Integer id);

    String lv(String phone, Integer id);

    String sv(String phone, Integer id);

    String view(String phone, Integer id);

    String shield(String phone, String tphone);
}
