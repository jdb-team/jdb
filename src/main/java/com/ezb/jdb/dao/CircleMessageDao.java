package com.ezb.jdb.dao;

import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.CircleMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author : liufeng
 * create time:2015/11/26 10:12
 */
@Repository
public class CircleMessageDao extends BaseDao<CircleMessage> {

    public List<CircleMessage> queryAll(){
        String hql = "from CircleMessage o";
        return query(hql);
    }
}
