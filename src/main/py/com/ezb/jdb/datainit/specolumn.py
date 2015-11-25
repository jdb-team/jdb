#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import MySQLdb, initconn


def initspecolumn():
    """专栏"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        value = [1, "150周年校庆", "这是150周年校庆专栏"]
        cur.execute('insert into specolumn(id,columnname,columndesc) values(%s,%s,%s)', value)

        value = [2, "专栏2", "专栏2描述"]
        cur.execute('insert into specolumn(id,columnname,columndesc) values(%s,%s,%s)', value)

        value = [3, "专栏3", "专栏3描述"]
        cur.execute('insert into specolumn(id,columnname,columndesc) values(%s,%s,%s)', value)

        value = [4, "专栏4", "专栏4描述"]
        cur.execute('insert into specolumn(id,columnname,columndesc) values(%s,%s,%s)', value)

        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


if __name__ == '__main__':
    initspecolumn()
