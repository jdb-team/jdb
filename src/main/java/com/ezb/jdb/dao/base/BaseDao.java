package com.ezb.jdb.dao.base;

import com.ezb.jdb.common.PageResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * author : liufeng
 * create time: 2015/8/1 15:55.
 */
public class BaseDao<T> implements DaoSupport<T> {

    @Autowired
    private SessionFactory sessionFactory;


    public T queryUnique(String hql) {
        return (T) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
    }

    public List<T> query(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    public PageResult<T> query(String hql, PageResult<T> pageResult) {
        String cql = "select count(*) " + hql;
       return query(hql,cql,pageResult);
    }

    public PageResult<T> query(String hql, String cql,PageResult<T> pageResult) {

        pageResult.calcFirstResult();

        Session session = sessionFactory.getCurrentSession();

        //set ResultList
        List<T> list = session.createQuery(hql)
                .setFirstResult(pageResult.getFirstResult())
                .setMaxResults(pageResult.getPageSize()).list();
        pageResult.setResultList(list);

        int count = ((Number) session.createQuery(cql).uniqueResult()).intValue();
        pageResult.setTotalCount(count);

        return pageResult;
    }

    public int queryCount(String hql) {
        String cql = "select count(*) " + hql;
        return ((Number) sessionFactory.getCurrentSession().createQuery(cql).uniqueResult()).intValue();
    }

    public void add(T t) {
        sessionFactory.getCurrentSession().save(t);
    }

    public void delete(T t) {
        sessionFactory.getCurrentSession().delete(t);
    }

    public void update(T t) {
        sessionFactory.getCurrentSession().update(t);
    }

    public void saveOrUpdate(T t) {
        sessionFactory.getCurrentSession().saveOrUpdate(t);
    }

    public T get(Class<T> clazz, Integer id) {
        return (T) sessionFactory.getCurrentSession().get(clazz, id);
    }

    public int executeHql(String hql){
        return sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
    }

    public int executeSql(String sql){
        return sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }
}
