#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import MySQLdb, initconn
import time


def initAdmin():
    """初始化管理员信息表"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        # 添加超级管理员
        value = [
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            0,
            "123456",
            "111111" + str(0),
            "超级管理员",
            "superadmin"
        ]

        cur.execute('insert into admin(c_time,level,password,phone,realname,username) values(%s,%s,%s,%s,%s,%s)', value)

        for i in range(1,101):
            value = [
                time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
                str(1),
                "pass" + str(i),
                "111111" + str(i),
                "realname" + str(i),
                "username" + str(i)
            ]
            cur.execute('insert into admin(c_time,level,password,phone,realname,username) values(%s,%s,%s,%s,%s,%s)', value)
        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])

if __name__ == '__main__':
    initAdmin()