package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.SpeColumnDao;
import com.ezb.jdb.model.SpeColumn;
import com.ezb.jdb.service.ISpeColumnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 专栏
 * author : liufeng
 * create time:2015/11/13 13:42
 */
@Service
public class SpeColumnServiceImpl implements ISpeColumnService {

    @Resource
    private SpeColumnDao speColumnDao;

    @Override
    public String add(SpeColumn speColumn) {
        speColumnDao.add(speColumn);
        return ResponseState.SUCCESS;
    }

    @Override
    public String delete(String id) {
        speColumnDao.deleteById(id);
        return ResponseState.SUCCESS;
    }

    @Override
    public String update(SpeColumn speColumn) {
        speColumnDao.update(speColumn);
        return ResponseState.SUCCESS;
    }

    @Override
    public String query(PageResult<SpeColumn> pageResult, String typeName) {
        pageResult = speColumnDao.query(pageResult, typeName);
        return ResponseData.getResData(pageResult);
    }

    @Override
    public String queryById(Integer id) {
        SpeColumn speColumn = speColumnDao.get(SpeColumn.class, id);
        if (null == speColumn) {
            return ResponseState.INVALID_ID;
        }
        return ResponseData.getResData(speColumn);
    }
}
