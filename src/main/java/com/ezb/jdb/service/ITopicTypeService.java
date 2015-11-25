package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.TopicType;

/**
 * 话题类型
 * author : liufeng
 * create time:2015/11/13 11:47
 */
public interface ITopicTypeService {

    String add(TopicType topicType);

    String delete(String id);

    String update(TopicType topicType);

    String query(PageResult<TopicType> pageResult, String typeName);

    String queryById(Integer id);
}
