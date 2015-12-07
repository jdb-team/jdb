package com.ezb.jdb.dao;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.CircleMessage;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
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

    public PageResult<CircleMessage> query(PageResult<CircleMessage> pageResult, Integer cid) {
        String hql = "from CircleMessage o where o.circle.id=''{0}''";
        return query(MessageFormat.format(hql,cid),pageResult);
    }
}
