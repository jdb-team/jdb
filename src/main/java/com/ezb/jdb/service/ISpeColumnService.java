package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.SpeColumn;

/**
 * 专栏
 * author : liufeng
 * create time:2015/11/13 11:47
 */
public interface ISpeColumnService {

    String add(SpeColumn speColumn);

    String delete(String id);

    String update(SpeColumn speColumn);

    String query(PageResult<SpeColumn> pageResult, String columnName);

    String queryById(Integer id);
}
