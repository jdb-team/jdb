package com.ezb.jdb.dao;

import com.ezb.jdb.common.NavType;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Focus;
import org.springframework.stereotype.Repository;

/**
 * author : liufeng
 * create time:2015/8/20 10:18
 */
@Repository
public class FocusDao extends BaseDao<Focus> {

    /**
     * 获取首页顶部焦点图(话题、活动)
     *
     * @param pageResult
     * @return
     */
    public PageResult<Focus> getTopFocus(PageResult<Focus> pageResult) {
        String hql = "from Focus o where o.refId is not null and o.refId <>'' and (o.type='" + NavType.TOPIC + "' " +
                "or o.type='" + NavType.ACTIVITY + "' or o.type='" + NavType.OTHER + "') order by o.position";
        return query(hql, pageResult);
    }

    /**
     * 获取圈子推荐
     *
     * @param pageResult
     * @return
     */
    public PageResult<Focus> getCircleFocus(PageResult<Focus> pageResult) {
        String hql = "from Focus o where o.refId is not null and o.refId <>'' and o.type='" + NavType.CIRCLE + "' order by o.position";
        return query(hql, pageResult);
    }

    /**
     * 获取首页顶部焦点图(话题、活动)
     *
     * @param pageResult
     * @return
     */
    public PageResult<Focus> getMTopFocus(PageResult<Focus> pageResult) {
        String hql = "from Focus o where (o.type='" + NavType.SPECOL + "' " +
                "or o.type='" + NavType.ACTIVITY + "' or o.type='" + NavType.OTHER + "') order by o.position";
        return query(hql, pageResult);
    }

    /**
     * 获取圈子推荐
     *
     * @param pageResult
     * @return
     */
    public PageResult<Focus> getMCircleFocus(PageResult<Focus> pageResult) {
        String hql = "from Focus o where o.type='" + NavType.CIRCLE + "' order by o.position";
        return query(hql, pageResult);
    }

}
