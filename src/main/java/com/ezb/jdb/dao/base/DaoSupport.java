package com.ezb.jdb.dao.base;

import java.util.List;

/**
 * author : liufeng
 * create time: 2015/8/1 15:51.
 */
interface DaoSupport<T> {

    List<T> query(String hql);

    void add(T t);

    void delete(T t);

    void update(T t);
}
