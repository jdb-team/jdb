package com.ezb.jdb.service;

import com.ezb.jdb.model.TopicCmt;

import java.util.List;

/**
 * 话题评论
 * author : liufeng
 * create time: 2015/8/7 14:47.
 */
public interface ITopicCmtService {

    String addTopicCmt(String phone,TopicCmt topicCmt);

    String likeTopicCmt(String phone, Integer id);

    List<TopicCmt> qTopicCmtByTopicId(Integer topicId);
}
