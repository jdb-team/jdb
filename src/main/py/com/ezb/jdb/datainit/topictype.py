#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import MySQLdb, initconn


def inittopictype():
    """话题分类"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        value = [1, "商机信息", "商机"]
        cur.execute('insert into topictype(id,typedesc,typename) values(%s,%s,%s)', value)

        value = [2, "招聘信息", "招聘"]
        cur.execute('insert into topictype(id,typedesc,typename) values(%s,%s,%s)', value)

        value = [3, "校企信息", "校企"]
        cur.execute('insert into topictype(id,typedesc,typename) values(%s,%s,%s)', value)

        value = [4, "杂谈信息", "杂谈"]
        cur.execute('insert into topictype(id,typedesc,typename) values(%s,%s,%s)', value)

        value = [5, "资讯信息", "资讯"]
        cur.execute('insert into topictype(id,typedesc,typename) values(%s,%s,%s)', value)

        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])


if __name__ == '__main__':
    inittopictype()
