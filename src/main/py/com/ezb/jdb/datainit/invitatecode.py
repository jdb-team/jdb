#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import time

import MySQLdb

import initconn

import random


def initInvitatecode():
    """初始化邀请码"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        for i in range(1, 101):
            value = [
                str(i),
                "123456",
                time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
                random.randint(1,100)
            ]

            cur.execute('insert into invitatecode(id,code,c_time,create_userid) values(%s,%s,%s,%s)',
                        value)
        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


if __name__ == '__main__':
    initInvitatecode()
