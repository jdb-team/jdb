#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import MySQLdb, initconn, time, jdbmd5


def inituser():
    """初始化用户表"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        for i in range(1, 101):
            value = [
                str(i),
                time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
                jdbmd5.md5("pass" + str(i)),
                str(1),
                "111111" + str(i)
            ]
            cur.execute('insert into user(id,c_time,password,state,username) values(%s,%s,%s,%s,%s)', value)

        value = [
            "102",
            time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
            jdbmd5.md5("123456"),
            str(1),
            "13691513507"
        ]
        cur.execute('insert into user(id,c_time,password,state,username) values(%s,%s,%s,%s,%s)', value)

        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])
