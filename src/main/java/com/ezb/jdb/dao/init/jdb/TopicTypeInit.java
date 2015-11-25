package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.TopicTypeDao;
import com.ezb.jdb.model.TopicType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: liuyan
 * @date: 2015年11月23日下午3:37:57
 * @Description: 标题类型
 */
@Component
public class TopicTypeInit {

    @Resource
    private TopicTypeDao topicTypeDao;

    public void init() {

        TopicType topicType1 = new TopicType();
        topicType1.setTypeDesc("商机信息");
        topicType1.setTypeName("商机");
        topicTypeDao.add(topicType1);

        TopicType topicType2 = new TopicType();
        topicType2.setTypeDesc("招聘信息");
        topicType2.setTypeName("招聘");
        topicTypeDao.add(topicType2);

        TopicType topicType3 = new TopicType();
        topicType3.setTypeDesc("校企信息");
        topicType3.setTypeName("校企");
        topicTypeDao.add(topicType3);

        TopicType topicType4 = new TopicType();
        topicType4.setTypeDesc("杂谈信息");
        topicType4.setTypeName("杂谈");
        topicTypeDao.add(topicType4);

        TopicType topicType5 = new TopicType();
        topicType5.setTypeDesc("资讯信息");
        topicType5.setTypeName("咨讯");
        topicTypeDao.add(topicType5);
    }
}
