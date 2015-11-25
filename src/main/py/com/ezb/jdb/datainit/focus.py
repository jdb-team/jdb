#!/usr/bin/python
# -*- coding: utf-8 -*-
# Author   : liufeng
# Create   : 2015/8/13 16:50
import MySQLdb, initconn, random


def initfocus():
    """焦点推荐"""
    try:
        conn = initconn.getConn()
        cur = conn.cursor()

        id = random.randint(1,100)
        value = [1, "","", 1, id, "", "专栏", "admin/topic/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        id = random.randint(1,100)
        value = [2, "","", 2, id, "", "专栏", "admin/topic/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        id = random.randint(1,100)
        value = [3, "","", 3, id, "", "活动", "admin/activity/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        id = random.randint(1,100)
        value = [4, "","", 4, id, "", "活动", "admin/activity/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        id = random.randint(1,100)
        value = [5, "","", 5, id, "", "专栏", "admin/topic/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        id = random.randint(1,100)
        value = [6, "","", 6, id, "", "专栏", "admin/topic/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        id = random.randint(1,100)
        value = [7, "","", 7, id, "", "圈子", "admin/circle/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        id = random.randint(1,100)
        value = [8, "","", 8, id, "", "圈子", "admin/circle/view?id=" + str(id)]
        cur.execute('insert into focus values(%s,%s,%s,%s,%s,%s,%s,%s)', value)

        conn.commit()
        cur.close()
        conn.close()

    except MySQLdb.Error, e:
        print "Mysql Error %d: %s" % (e.args[0], e.args[1])

if __name__ == '__main__':
    initfocus()