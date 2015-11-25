package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.TopicTypeDao;
import com.ezb.jdb.model.Admin;
import com.ezb.jdb.model.TopicType;
import com.ezb.jdb.service.ITopicTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * author : liufeng
 * create time:2015/11/13 13:42
 */
@Service
public class TopicTypeServiceImpl implements ITopicTypeService {

    @Resource
    private TopicTypeDao topicTypeDao;

    public String add(TopicType topicType) {
        topicTypeDao.add(topicType);
        return ResponseState.SUCCESS;
    }

    public String delete(String id) {
        topicTypeDao.deleteById(id);
        return ResponseState.SUCCESS;
    }

    public String update(TopicType topicType) {
        topicTypeDao.update(topicType);
        return ResponseState.SUCCESS;
    }

    public String query(PageResult<TopicType> pageResult, String typeName) {
        pageResult = topicTypeDao.query(pageResult, typeName);
        return ResponseData.getResData(pageResult);
    }

    public String queryById(Integer id) {
        TopicType topicType = topicTypeDao.get(TopicType.class, id);
        if (null == topicType) {
            return ResponseState.INVALID_ID;
        }
        return ResponseData.getResData(topicType);
    }
}
