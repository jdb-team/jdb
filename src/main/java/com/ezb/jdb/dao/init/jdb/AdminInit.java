package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.AdminDao;
import com.ezb.jdb.model.Admin;
import com.ezb.jdb.tool.JdbMd5Util;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: liuyan
 * @date: 2015年11月23日下午2:29:41
 * @Description: 初始化管理员
 */
@Component
public class AdminInit {
    @Resource
    private AdminDao adminDao;

    public void init() {

        Admin superadmin = new Admin();
        superadmin.setCreateTime(new Date());
        superadmin.setLevel(0);
        superadmin.setPassword(JdbMd5Util.md5("123456"));
        superadmin.setPhone("111111" + String.valueOf(0));
        superadmin.setRealName("超级管理员");
        superadmin.setUsername("superadmin");

        adminDao.add(superadmin);

        for (int i = 1; i < 101; i++) {
            Admin admin = new Admin();
            admin.setCreateTime(new Date());
            admin.setLevel(1);
            admin.setPassword(JdbMd5Util.md5("pass" + String.valueOf(i)));
            admin.setPhone("111111" + String.valueOf(i));
            admin.setRealName("realname" + String.valueOf(i));
            admin.setUsername("username" + String.valueOf(i));
            adminDao.add(admin);
        }
    }
}
