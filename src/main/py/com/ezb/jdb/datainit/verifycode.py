#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import time

import MySQLdb

import initconn


def initVerifyCode():
    """初始化验证码"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        for i in range(1, 101):
            value = [
                str(i),
                time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()),
                "111111" + str(i),
                "123456"
            ]

            cur.execute('insert into verifycode(id,c_time,phone,verifycode) values(%s,%s,%s,%s)',
                        value)
        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


if __name__ == '__main__':
    initVerifyCode()
