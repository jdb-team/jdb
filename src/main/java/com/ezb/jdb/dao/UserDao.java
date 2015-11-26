package com.ezb.jdb.dao;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.dao.base.BaseDao;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * author : liufeng
 * create time: 2015/8/1 16:21.
 */
@Repository
public class UserDao extends BaseDao<User> {

    public List<User> queryAll(){
        String hql = "from User user";
        return query(hql);
    }

    public User login(User user) {
        String hql = "from User user where user.username=''{0}'' and user.password=''{1}''";
        return queryUnique(MessageFormat.format(hql, user.getUsername(), user.getPassword()));
    }

    public User queryByPhone(String phone) {
        String hql = "from User user where user.username=''{0}''";
        return queryUnique(MessageFormat.format(hql, phone));
    }

    public int qcByPhone(String phone) {
        String hql = "from User user where user.username=''{0}''";
        return queryCount(MessageFormat.format(hql, phone));
    }

    public PageResult<User> queryUnFriendUser(PageResult<User> pageResult,
                                              String phone, Alumnus alumnus, String orderby) {

        List<Object> paramList = new ArrayList<Object>();

        User user = queryByPhone(phone);

        Double lat = user.getAlumnus().getLat();
        Double lng = user.getAlumnus().getLng();

        String hql = "from User o where o.state=1 and o.username not in " +
                " (select o1.user.username from Friend o1 where o1.user.username=''{0}'')" +
                " and o.username not in (select o2.friend.username from Friend o2 where o2.user.username=''{0}'')";
        paramList.add(phone);

        hql = buildHql(1,alumnus, orderby, paramList, lat, lng, hql);

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }

    private String buildHql(int i,Alumnus alumnus, String orderby, List<Object> paramList, Double lat, Double lng, String hql) {

        if (null != alumnus) {
            //姓名
            if (!StringUtils.isEmpty(alumnus.getRealName())) {
                hql += " and o.alumnus.realName like ''%{" + i + "}%''";
                paramList.add(alumnus.getRealName());
                i++;
            }

            //性别
            if (null != alumnus.getSex()) {
                hql += " and o.alumnus.sex=''{" + i + "}''";
                paramList.add(alumnus.getSex());
                i++;
            }

            //学校
            if (!StringUtils.isEmpty(alumnus.getSchool())) {
                hql += " and o.alumnus.school like ''%{" + i + "}%''";
                paramList.add(alumnus.getSchool());
                i++;
            }

            //院系
            if (!StringUtils.isEmpty(alumnus.getDepartment())) {
                hql += " and o.alumnus.department like ''%{" + i + "}%''";
                paramList.add(alumnus.getDepartment());
                i++;
            }

            //年级
            if (!StringUtils.isEmpty(alumnus.getGrade())) {
                hql += " and o.alumnus.grade=''{" + i + "}''";
                paramList.add(alumnus.getGrade());
                i++;
            }
        }

        //按距离排序
        if (StringUtils.equals(JdbConstants.ORDERBY_LOCATION, orderby)) {
            int latIndex = i;
            int lngIndex = ++i;
            hql += " order by round(6378.138*2*asin(sqrt(pow(sin( (o.alumnus.lat*pi()/180-{" + latIndex + "}*pi()/180)/2),2)" +
                    "+cos(o.alumnus.lat*pi()/180)*cos({" + latIndex + "}*pi()/180)* pow(sin( (o.alumnus.lng*pi()/180-" +
                    "{" + lngIndex + "}*pi()/180)/2),2)))*1000)/1000";
            paramList.add(lat);
            paramList.add(lng);
        }

        //按姓名排序
        if (StringUtils.equals(JdbConstants.ORDERBY_USERNAME, orderby)) {
            hql += " order by o.alumnus.realName";
        }
        return hql;
    }

    public PageResult<User> queryNearUsers(PageResult<User> pageResult, String phone) {

        User user = queryByPhone(phone);
        Double lat = user.getAlumnus().getLat();
        Double lng = user.getAlumnus().getLng();

        String hql = "from User o where o.state=1 and o.username != ''{0}''";
        hql += " order by round(6378.138*2*asin(sqrt(pow(sin( (o.alumnus.lat*pi()/180-{1}*pi()/180)/2),2)" +
                "+cos(o.alumnus.lat*pi()/180)*cos({1}*pi()/180)* pow(sin( (o.alumnus.lng*pi()/180-" +
                "{2}*pi()/180)/2),2)))*1000)/1000";

        return query(MessageFormat.format(hql, phone, lat, lng), pageResult);
    }

    public PageResult<User> queryAllUser(PageResult<User> pageResult, User user, Alumnus alumnus, String orderby) {
        List<Object> paramList = new ArrayList<Object>();

        Double lat = user.getAlumnus().getLat();
        Double lng = user.getAlumnus().getLng();

        String hql = "from User o where o.state=1";
        hql = buildHql(0,alumnus, orderby, paramList, lat, lng, hql);

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }

    public PageResult<User> query(PageResult<User> pageResult, String username, String state,
                                  Alumnus alumnus, String startTime, String endTime) {
        List<Object> paramList = new ArrayList<Object>();
        String hql = "from User o where 1=1";
        int i = 0;

        if (!StringUtils.isEmpty(username)) {
            hql += " and o.username like ''%{" + i + "}%''";
            paramList.add(username);
            i++;
        }

        if (!StringUtils.isEmpty(state)) {
            hql += " and o.state = ''{" + i + "}''";
            paramList.add(state);
            i++;
        }

        if (!StringUtils.isEmpty(startTime)) {
            hql += " and o.createTime >= ''%{" + i + "}%''";
            paramList.add(startTime);
            i++;
        }

        if (!StringUtils.isEmpty(endTime)) {
            hql += " and o.createTime <= ''%{" + i + "}%''";
            paramList.add(endTime);
            i++;
        }

        if (null != alumnus) {
            //姓名
            if (!StringUtils.isEmpty(alumnus.getRealName())) {
                hql += " and o.alumnus.realName like ''%{" + i + "}%''";
                paramList.add(alumnus.getRealName());
                i++;
            }

            //性别
            if (null != alumnus.getSex()) {
                hql += " and o.alumnus.sex=''{" + i + "}''";
                paramList.add(alumnus.getSex());
                i++;
            }

            //学校
            if (!StringUtils.isEmpty(alumnus.getSchool())) {
                hql += " and o.alumnus.school like ''%{" + i + "}%''";
                paramList.add(alumnus.getSchool());
                i++;
            }

            //院系
            if (!StringUtils.isEmpty(alumnus.getDepartment())) {
                hql += " and o.alumnus.department like ''%{" + i + "}%''";
                paramList.add(alumnus.getDepartment());
                i++;
            }

            //年级
            if (!StringUtils.isEmpty(alumnus.getGrade())) {
                hql += " and o.alumnus.grade=''{" + i + "}''";
                paramList.add(alumnus.getGrade());
                i++;
            }

            //邮箱
            if (!StringUtils.isEmpty(alumnus.getEmail())) {
                hql += " and o.alumnus.email like ''%{" + i + "}%''";
                paramList.add(alumnus.getEmail());
                i++;
            }
        }

        hql +=  "order by o.createTime desc";

        return query(MessageFormat.format(hql, paramList.toArray()), pageResult);
    }
}
